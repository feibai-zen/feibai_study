#!/opt/homebrew/bin/python3
# encoding=utf-8
from redis import Redis


# from relationship import Relationship

def following_key(user):
  return user + "::following"


class CommonFollowing:
  def __init__(self, client):
    self.client = client

  def calculate(self, user, target):
    """ 
     计算并返回当前用户和目标用户共同关注的人 
    """
    user_following_set = following_key(user)
    target_following_set = following_key(target)
    return self.client.sinter(user_following_set, target_following_set)

  def calculate_and_store(self, user, target, store_key):
    """ 
     计算出当前用户和目标用户共同关注的人，并把结果存储到store_key指定的键中，最后返回共同 
     关注的人数 
    """
    user_following_set = following_key(user)
    target_following_set = following_key(target)
    return self.client.sinterstore(store_key, user_following_set, target_following_set)


if __name__ == "__main__":
  client = Redis(decode_responses=True)
  # peter = Relationship(client, "peter")
  # jack = Relationship(client, "tom")  # peter关注一些用户 
  # peter.follow("david")
  # peter.follow("mary")
  # jack.follow("tom")  # jack关注一些用户 
  # jack.follow("david")
  # jack.follow("lily")
  common_following = CommonFollowing(client)
  common_following.calculate("peter",
                             "jack")  # 计算peter和jack共同关注的用户         set(['tom', 'david'])                                 # 他们都关注了tom和david
