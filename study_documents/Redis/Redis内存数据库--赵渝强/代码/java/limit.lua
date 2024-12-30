--限制的Key
local key = KEYS[1]
--限流的大小
local limit = tonumber(ARGV[1])

--已经有的流量
local current = tonumber(redis.call('get', key) or "0")
if current + 1 > limit then
  --拒绝客户端的操作
    return 0
else
    redis.call("INCRBY", key,"1")
    redis.call("expire", key,"2")
end
return 1