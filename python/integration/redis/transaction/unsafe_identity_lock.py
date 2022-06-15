#!/usr/local/bin/python3
# encoding=utf-8

from redis import Redis

"""
分布式锁--不安全方式

这个锁实现在绝大部分情况下都能够正常运行，但它的release()方法包含了一个非常隐蔽的错误：在程序使用GET命令获取锁键的值以后，直到程序
调用DEL命令删除锁键的这段时间里面，锁键的值有可能已经发生了变化，因此程序执行的DEL命令有可能会导致当前持有者的锁被错误地释放。
"""


class IdentityLock:
  def __init__(self, client, key):
    self.client = client
    self.key = key

  def acquire(self, identity, timeout):
    """尝试获取一个带有身份标识符和最大使用时限的锁，
    成功时返回True，失败时返回False"""
    result = self.client.set(self.key, identity, ex=timeout, nx=True)
    return result is not None

  def release(self, input_identity):
    """根据给定的标识符，尝试释放锁。
    返回True表示释放成功；
    返回False则表示给定的标识符与锁持有者的标识符并不相同，释放请求被拒绝 """
    # 获取锁键存储的标识符 
    lock_identity = self.client.get(self.key)
    if lock_identity is None:
      # 如果锁键的标识符为空，那么说明锁已经被释放
      return True
    elif input_identity == lock_identity:
      # 如果给定的标识符与锁键的标识符相同，那么释放这个锁 
      self.client.delete(self.key)
      return True
    else:
      # 如果给定的标识符与锁键的标识符并不相同
      # 那么说明当前客户端不是锁的持有者
      # 拒绝本次释放请求
      return False
