#!/opt/homebrew/bin/python3
# encoding=utf-8
from redis import Redis
import random

"""
1.寻找附近用户
2.随机返回一个附近用户
"""

USER_LOCATION_KEY = "user_locations"


class Location:
  def __init__(self, client):
    self.client = client
    self.key = USER_LOCATION_KEY

  def pin(self, user, longitude, latitude):
    """
   记录指定用户的坐标 
   """
    self.client.geoadd(self.key, longitude, latitude, user)

  def get(self, user):
    """
    获取指定用户的坐标 
    """
    # geopos()允许用户输入多个位置，然后以列表形式返回各个位置的坐标。 
    # 因为这里只传入了一个位置，所以只需要取出列表的第一个元素即可
    position_list = self.client.geopos(self.key, user)
    if len(position_list) != 0:
      return position_list[0]

  def calculate_distance(self, user_a, user_b):
    """
    以千米为单位，计算两个用户之间的直线距离
    """
    return self.client.geodist(self.key, user_a, user_b, unit="km")

  def find_nearby(self, user, radius=1):
    """
    以千米为单位，寻找并返回user指定半径范围内的所有其他用户
    """
    # 因为georadiusbymember()方法会把user本身也包含在结果中，
    # 但由于我们并不需要这个用户，所以使用remove()方法将其移除
    all_nearby_users = self.client.georadiusbymember(self.key, user, radius, unit="km")
    all_nearby_users.remove(user)
    return all_nearby_users

  def find_random_nearby(self, user, radius=1):
    """ 
    以千米为单位，随机地返回一个位于user指定半径范围内的其他用户 
    """
    # random.choice()方法用于从列表中随机地选择并返回一个项 
    return random.choice(self.find_nearby(user, radius))


if __name__ == "__main__":
  client = Redis(decode_responses=True)
  location = Location(client)
  location.pin("peter", 113.0419413, 23.6936457)  # 添加5个用户的位置 
  location.pin("jack", 113.0399136, 23.6951166)
  location.pin("tom", 113.0398344, 23.6945014)
  location.pin("mary", 113.0398344, 23.6945014)
  location.pin("david", 113.0398861, 23.6933749)
  location.find_nearby("peter")  # 寻找peter附近的所有用户  ['david', 'mary', 'tom', 'jack']
  location.find_random_nearby("peter")  # 随机地返回peter附近的一位用户  'mary' 
  location.find_random_nearby("peter")  # 'jack' 
  location.find_random_nearby("peter")  # 'david'
