#!/usr/local/bin/python3
# encoding=utf-8

from redis import Redis


class DbIterator:
  def __init__(self, client, match=None, count=None):
    """
    创建一个新的迭代器。 
    可选的match参数用于指定迭代的匹配模式， 
    而可选的count参数则用于指定我们期待每次迭代能够返回的键数量 
    """
    self.client = client
    self.match = match
    self.count = count  # 当前迭代游标 
    self.current_cursor = 0  # 记录迭代是否已经完成的状态变量 
    self.iteration_is_over = False

  def next(self):
    """
    以列表形式返回当前被迭代到的数据库键， 
    返回None则表示本次迭代已经完成 
    """
    if self.iteration_is_over:
      return None  # 获取下次迭代的游标以及当前被迭代的数据库键
    next_cursor, keys = self.client.scan(self.current_cursor, self.match, self.count)
    # 如果下次迭代的游标为0，那么表示迭代已完成
    if next_cursor == 0:
      self.iteration_is_over = True  # 更新游标 
    self.current_cursor = next_cursor  # 返回当前被迭代的数据库键 
    return keys

if __name__ == "__main__":
  client = Redis(decode_responses=True)
  for i in range(50):  # 向数据库插入50个键 
    key = "key{0}".format(i)
    value = i
    client.set(key, value)

  iterator = DbIterator(client)
  iterator.next()  # 开始迭代 
  iterator.next()
  iterator.next()  # 迭代结束
