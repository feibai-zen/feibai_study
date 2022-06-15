#!/opt/homebrew/bin/python3
# encoding=utf-8

from redis import Redis

""""
@author feibai
使用set进行抽奖

"""


class Lottery:
  def __init__(self, client, key):
    self.client = client
    self.key = key

  def add_player(self, user):
    """ 
    将用户添加到抽奖名单当中 
    """
    self.client.sadd(self.key, user)

  def get_all_players(self):
    """
     返回参加抽奖活动的所有用户 
    """
    return self.client.smembers(self.key)

  def player_count(self):
    """ 
    返回参加抽奖活动的用户人数
    """
    return self.client.scard(self.key)

  def draw(self, number):
    """
    抽取指定数量的获奖者 
    """
    return self.client.srandmember(self.key, number)


if __name__ == "__main__":
  client = Redis(decode_responses=True)
  lottery = Lottery(client, 'birthday party lottery')  # 这是一次生日派对抽奖活动 
  lottery.add_player('peter')  # 添加抽奖者 
  lottery.add_player('jack')
  lottery.add_player('tom')
  lottery.add_player('mary')
  lottery.add_player('dan')
  lottery.player_count()  # 查看抽奖者数量         5 
  lottery.draw(1)  # 抽取一名获奖者         ['dan']
