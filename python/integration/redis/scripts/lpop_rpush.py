#!/opt/homebrew/bin/python3
# encoding=utf-8

from redis import Redis


def lpoprpush(client, source, target):
  script = """
  local source = KEYS[1]
  local target = KEYS[2]
  -- 从源列表左端弹出一个元素
  -- 当源列表为空时，LPOP返回的nil将被Lua转换为false
  local item = redis.call("LPOP", source) 
  -- 如果被弹出元素不为空，那么将它推入目标列表的右端             
  -- 并向调用者返回该元素
  if item ～= false then
    redis.call("RPUSH", target, item)
    return item             
  end 
  """
  return client.eval(script, 2, source, target)


if __name__ == '__main__':
  client = Redis(decode_responses=True)
  client.rpush("source", "a", "b", "c")  # 创建源列表和目标列表   3L 
  client.rpush("target", "d", "e", "f")  # 3L 
  lpoprpush(client, "source", "target")  # 弹出源列表的左端元素   'a'  # 并将其推入目标列表的右端 
  client.lrange("source", 0, -1)  # ['b', 'c']  >> >
  client.lrange("target", 0, -1)  # ['d', 'e', 'f', 'a']
