-- 把[1, 2, 3]字符串转成lua数组
local function split(s)
    s = string.sub(s, 2, string.len(s)-1)
    local rt= {}
    string.gsub(s, '[^, ]+', function(w) table.insert(rt, w) end )
    return rt
end

-- incr时,先incr整数部分,然后把ts(小数)更新到小数部分
local function zincrbyWithTs(key, member, increment, ts)
    local current = redis.call("zincrby", key, increment, member);
    local integer,decimal = math.modf(current);
    local offset = integer+ts-current;
    redis.call("zincrby", key, offset, member);
end

-- 指定主题-指定主播-轮次key,string,存主播已完成几轮
local anchorRoundKey =KEYS[1]
-- 指定主题-指定主播-指定轮次-轮次信息key,hash,存主播当前轮次card的count以及轮次状态(0:未完成,1:已完成)
local anchorRoundInfoKey = KEYS[2]
-- 指定主题-所有主播-轮次榜key,zset,member:主播uid,score:已完成轮次
local roundRankKey = KEYS[3]
-- 指定主题-指定主播-粉丝贡献榜,zset,member:粉丝uid,score:送礼金额累加
local fansContributionRankKey = KEYS[4]
-- 指定主题-指定主播-指定轮次-指定卡片-粉丝收集榜,zset,member:粉丝uid,score:卡片count
local fansCardRankKey = KEYS[5]
-- 指定主题-指定主播-指定轮次-粉丝贡献集合,set,粉丝uid
local fansContributionUidKey = KEYS[6]
-- 订单号key,用来保证消息不会被重复消费
local orderExistKey = KEYS[7]

local fansUid = ARGV[1]
local anchorUid = ARGV[2]
-- 所有卡片id
local allCardIds = split(ARGV[3])
-- 本次抽中的卡片
local achievedCardIds = split(ARGV[4])
-- 送礼价值
local innerValue = tonumber(ARGV[5])
-- 当前时间戳
local timestamp = ARGV[6]

local exists = redis.call("exists", orderExistKey);
if exists == 1 then
    return {0, 0};
end

-- 查询主播已完成几轮
local finishedRound = redis.call("get", anchorRoundKey);
if not finishedRound then
    finishedRound = 0;
    redis.call("set", anchorRoundKey, finishedRound);
end
-- 当前第几轮(已完成轮次+1)
local currentRound = finishedRound + 1;

-- 记录贡献的粉丝uid
fansContributionUidKey = fansContributionUidKey..currentRound;
redis.call("sadd", fansContributionUidKey, fansUid);

-- 更新粉丝贡献榜及辅助key
zincrbyWithTs(fansContributionRankKey, fansUid, innerValue, timestamp);

-- 更新粉丝卡片榜及主播卡片数量
anchorRoundInfoKey = anchorRoundInfoKey..currentRound;
for i,cardId in pairs(achievedCardIds) do
    local cardRankKey = fansCardRankKey..currentRound.."_"..cardId;
    zincrbyWithTs(cardRankKey, fansUid, 1, timestamp);-- 粉丝卡片榜
    redis.call("hincrby", anchorRoundInfoKey, cardId, 1);-- 主播卡片数量
end

-- 拿到当前轮次信息(上面有hincrby,所以不会nil),判断是否集齐
local roundInfoTable = redis.call("hgetall", anchorRoundInfoKey);
local roundInfoMap = {}
for i = 1, #roundInfoTable, 2 do
    roundInfoMap[roundInfoTable[i]] = roundInfoTable[i + 1]
end

-- 遍历所有卡片id,判断集齐所有卡片
local isRoundFinish = true;
for i,cardId in pairs(allCardIds) do
    local cardCount = roundInfoMap[cardId]
    if not cardCount or tonumber(cardCount) == '0' then
        isRoundFinish = false;
        break;
    end
end

local status = 0;
-- 若集齐
if isRoundFinish then
    -- 修改当前轮次状态为已完成
    redis.call("hset", anchorRoundInfoKey, "status", "1");
    -- 增加轮次
    finishedRound = redis.call("incr", anchorRoundKey);
    -- 更新轮次榜
    redis.call("zadd", roundRankKey, finishedRound+timestamp, anchorUid);
    status = 1;
end

-- 记录订单号,保证幂等消息不会重复消费
redis.call("set", orderExistKey, "1", "ex", 86400);
return {status, finishedRound}
