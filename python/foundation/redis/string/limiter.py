#!/usr/local/bin/python3
# encoding=utf-8

from redis import Redis


class Limiter:
  def __init__(self, client, key):
    self.client = client
    self.key = key

  def set_max_execute_times(self, max_execute_times):
    """ 
    设置操作的最大可执行次数 
    """
    self.client.set(self.key, max_execute_times)

  def still_valid_to_execute(self):
    """
    检查是否可以继续执行被限制的操作，是则返回True，否则返回False 
    """
    num = self.client.decr(self.key)
    return (num >= 0)

  def remaining_execute_times(self):
    """ 
    返回操作的剩余可执行次数
    """
    num = int(self.client.get(self.key))
    if num < 0:
      return 0
    else:
      return num


if __name__ == "__main__":
  client = Redis(decode_responses=True)
  limiter = Limiter(client, 'wrong_password_limiter')  # 密码错误限制器 
  limiter.set_max_execute_times(3)  # 最多只能输入3次错误密码
  limiter.still_valid_to_execute()  # 前3次操作能够顺利执行  True 
  # limiter.still_valid_to_execute() 
  # limiter.still_valid_to_execute() 
  # limiter.still_valid_to_execute()  # 从第4次开始，操作将被拒绝执行 
  # limiter.still_valid_to_execute()  # False
"""
# 以下伪代码则展示了如何使用这个限速器去限制密码的错误次数：
# 试错次数未超过限制        
while limiter.still_valid_to_execute():  # 获取访客输入的账号和密码             
  account, password = get_user_input_account_and_password()  # 验证账号和密码是否匹配             
  if password_match(account, password):
    ui_print("密码验证成功")
  else:
    ui_print("密码验证失败，请重新输入")   # 试错次数已超过限制         
else:
  # 锁定账号             
  lock_account(account)             
  ui_print("连续尝试登录失败，账号已被锁定，请明天再来尝试登录。"
"""
