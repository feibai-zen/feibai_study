#!/opt/homebrew/bin/python3
# encoding=utf-8

from redis import Redis


class Timeline:
  def __init__(self, client, key):
    self.client = client
    self.key = key

  def add(self, item, time):
    """将元素添加到时间线中"""
    self.client.zadd(self.key, {item: time})

  def remove(self, item):
    """从时间线中移除指定元素
    """
    self.client.zrem(self.key, item)

  def count(self):
    """返回时间线包含的元素数量"""
    return self.client.zcard(self.key)

  def pagging(self, number, count, with_time=False):
    """
    按照每页count个元素计算，取出时间线第number页上的所有元素，
    这些元素将根据时间戳逆序排列。如果可选参数with_time的值为True，那么元素对应的时间戳也会一并被返回。 
    注意：number参数的起始值是1而不是0 
    """
    start_index = (number - 1) * count
    end_index = number * count - 1
    return self.client.zrevrange(self.key, start_index, end_index, withscores=with_time)

  def fetch_by_time_range(self, min_time, max_time, number, count, with_time=False):
    """
    按照每页count个元素计算，获取指定时间段第number页上的所有元素， 这些元素将根据时间戳逆序排列。 
    如果可选参数with_time的值为True，那么元素对应的时间戳也会一并被返回。 
    注意：number参数的起始值是1而不是0 
    """
    start_index = (number - 1) * count
    return self.client.zrevrangebyscore(self.key, max_time, min_time, start_index, count, withscores=with_time)


if __name__ == "__main__":
  client = Redis(decode_responses=True)
  blogs = Timeline(client, "blog_timelie")
  blogs.add("Switching from macOS: The Basics", 1477965600)
  blogs.add("Recent Loki Updates", 1477929600)
  blogs.add("What's Web Team Up To? ", 1477645200)
  blogs.add("We've Joined the Snap Format TOB! ", 1475618400)
  blogs.add("Loki Release Follow Up", 1474549200)
  blogs.add("Our Gtk+ Stylesheet Has Moved", 1473642000)
  blogs.add("Loki0.4 Stable Release!", 1473404400)
  blogs.add("The Store is Back! ", 1472068800)
  blogs.add("New Open Source Page On Our Website", 1470664800)
  blogs.add("We're back from the Snappy Sprint! ", 1469664000)
  blogs.count()  # 10
  blogs.pagging(1, 5, with_time=True)
  """
  outPut:
    [('Switching  from  macOS:  The  Basics', 1477965600.0),('Recent  Loki  Updates',1477929600.0),("What's  Web  Team  Up  To? ", 1477645200.0),
   ("We've  Joined  the  Snap     Format TOB! ", 1475618400.0),('Loki Release Follow Up', 1474549200.0)]
  """
  blogs.pagging(2, 5, with_time=True)
  """
  output:
    [('Our Gtk+ Stylesheet Has Moved', 1473642000.0), ('Loki 0.4 Stable Release! ', 1473404400.0),('The Store is Back! ', 1472068800.0), 
   ('New Open Source Page On Our Website', 1470664800.0),("We're back from the Snappy Sprint! ", 1469664000.0)]
  """
  blogs.fetch_by_time_range(1472659200, 1475251199, 1, 5, with_time=True)
  """
  output:
     [('Loki Release Follow Up', 1474549200.0), ('Our Gtk+ Stylesheet Has Moved', 1473642000.0),('Loki 0.4 Stable Release! ', 1473404400.0)]
  """