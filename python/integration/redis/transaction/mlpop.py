#!/opt/homebrew/bin/python3
# encoding=utf-8

from redis import Redis


def mlpop(client, list_key, number):
  # 开启事务 
  transaction = client.pipeline()  # 将多个LPOP命令放入事务队列
  for i in range(number):
    transaction.lpop(list_key)
  # 执行事务 
  return transaction.execute()


if __name__ == '__main__':
  client = Redis(decode_responses=True)
  client.rpush("lst", "123", "456", "789")  # 向列表右端推入3个元素         3L 
  mlpop(client, "lst", 3)  # 从列表左端弹出3个元素         ['123', '456', '789']
