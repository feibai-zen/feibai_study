local preKey =KEYS[1]
local currentKey =KEYS[2]

local newSeasonId = tonumber(ARGV[1])
local newRound = tonumber(ARGV[2])

local oldSeasonRoundTable = redis.call("hgetall", currentKey);
-- 首次执行没有数据
if next(oldSeasonRoundTable) == nil then
    redis.call("hmset", currentKey, "seasonId", newSeasonId, "round", newRound);
    return {newSeasonId, newRound};
end

local oldSeasonRoundMap = {}
for i = 1, #oldSeasonRoundTable, 2 do
    oldSeasonRoundMap[oldSeasonRoundTable[i]] = oldSeasonRoundTable[i + 1]
end

local oldSeasonId = tonumber(oldSeasonRoundMap["seasonId"]);
local oldRound = tonumber(oldSeasonRoundMap["round"]);

if newSeasonId > oldSeasonId or (newSeasonId == oldSeasonId and newRound > oldRound) then
    redis.call("rename", currentKey, preKey);
    redis.call("hmset", currentKey, "seasonId", newSeasonId, "round", newRound);
    return {newSeasonId, newRound, oldSeasonId, oldRound};
end
return {oldSeasonId, oldRound};

