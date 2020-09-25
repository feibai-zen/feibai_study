local lock =KEYS[1]
local expireSec = tonumber(ARGV[1])

-- 没有拿到锁返回的是nil
return redis.call("set", lock, 1, "ex", expireSec, "nx");