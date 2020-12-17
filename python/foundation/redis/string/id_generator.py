#!/usr/local/bin/python3
# encoding=utf-8

from time import time  # time()函数用于获取当前UNIX时间戳  
from redis import Redis


class IdGenerator:
  def __init__(self, client, key):
    self.client = client
    self.key = key

  def produce(self):
    """ 
    生成并返回下一个ID。
    """
    return self.client.incr(self.key)

  def reserve(self, n):
    """ 
    保留前n个ID，使得之后执行的produce()方法产生的ID都大于n。为了避免produce() 
    方法产生重复ID，这个方法只能在produce()方法和reserve()方法都没有执行过的情况下使 
    用。这个方法在ID被成功保留时返回True，在produce()方法或reserve()方法已经执行 
    过而导致保留失败时返回False 
    """
    result = self.client.set(self.key, n, nx=True)
    return result is True


if __name__ == "__main__":
  client = Redis("192.168.1.175", "6386", 0, "jredis123456", decode_responses=True)
  id_generator = IdGenerator(client, "user::id")
  id_generator.reserve(1000000)  # 保留前100万个ID 
  print(id_generator.produce())  # 生成ID，这些ID的值都大于100万 
  print(id_generator.produce())
  print(id_generator.produce())
  print(id_generator.reserve(1000))  # 键已经有值，无法再次执行reserve()方法 
