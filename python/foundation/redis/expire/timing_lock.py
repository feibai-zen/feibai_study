#!/usr/local/bin/python3
# encoding=utf-8
from redis import Redis

VALUE_OF_LOCK = "locking"


class TimingLock:
  def __init__(self, client, key):
    self.client = client
    self.key = key

  def acquire(self, timeOut):
    """尝试获取一个带有秒级最大使用时限的锁，成功时返回True，失败时返回False"""
    result = self.client.set(self.key, VALUE_OF_LOCK, ex=timeOut, nx=True)
    return result

  def release(self):
    """
    尝试释放锁，成功返回True，失败返回False
    """
    return self.client.delete(self.key) == 1


if __name__ == "__main__":
  client = Redis(decode_responses=True)
  lock = TimingLock(client, "test-lock")
  lock.acquire(5)  # 获取一个在5s之后自动释放的锁  True 
  lock.acquire(5)  # 在5s之内尝试再次获取锁，但是由于锁未被释放而失败  False
  lock.acquire(5)  # 在5s之后尝试再次获取锁  True  # 因为之前获取的锁已经自动被释放，所以这次将成功取得新的锁
