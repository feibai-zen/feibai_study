-- 把[1, 2, 3]字符串转成lua数组
local function split(s)
    s = string.sub(s, 2, string.len(s)-1)
    local rt= {}
    string.gsub(s, '[^, ]+', function(w) table.insert(rt, w) end )
    return rt
end

local taskKey =KEYS[1]
local date = ARGV[1]
local value = tonumber(ARGV[2])
local goals = split(ARGV[3])

-- 增长数据
local newValue = redis.call("hincrby", taskKey, "value", value);

-- 判断任务是否完成
local allTaskInfoTable = redis.call("hgetall", taskKey);
local taskInfoMap = {}
for i = 1, #allTaskInfoTable, 2 do
    taskInfoMap[allTaskInfoTable[i]] = allTaskInfoTable[i + 1]
end

-- 判断任务是否未完成且当前值大于目标值
local finishList = {};
for i,goal in pairs(goals) do
    local goalStatus = taskInfoMap[goal];
    if not goalStatus or goalStatus == 0 then
        if newValue >= tonumber(goal) then
            redis.call("hset", taskKey, goal, 1);
            table.insert(finishList, goal);
        end
    end
end

return {newValue, finishList};