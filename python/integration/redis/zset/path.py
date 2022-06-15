#!/usr/local/bin/python3
# encoding=utf-8

from redis import Redis
"""
1)每当用户从起点origin对终点destination进行一次访问，程序都会使用ZINCRBY命令对存储着起点origin访问记录的有序集合的destination成员执行一次分值加1操作。
2)之后，程序只需要对存储着origin访问记录的有序集合执行ZREVRANGE命令，就可以知道用户在访问了起点origin之后，最经常访问的目的地有哪些。
"""

def make_record_key(origin):
  return "forward_to_record::{0}".format(origin)


class Path:
  def __init__(self, client):
    self.client = client

  def forward_to(self, origin, destination):
    """记录一次从起点origin到目的地destination的访问"""
    key = make_record_key(origin)
    self.client.zincrby(key, 1, destination)

  def pagging_record(self, origin, number, count, with_time=False):
    """按照每页count个目的地计算，从起点origin的访问记录中取出位于第number页的访问记录，其中所有访问记录均按照访问次数从多到少进行排列。 
     如果可选的with_time参数的值为True，那么将具体的访问次数也一并返回 
    """
    key = make_record_key(origin)
    start_index = (number - 1) * count
    end_index = number * count - 1
    # score_cast_func=int用于将成员的分值从浮点数转换为整数
    return self.client.zrevrange(key, start_index, end_index, withscores=with_time, score_cast_func=int)


if __name__ == "__main__":
  client = Redis(decode_responses=True)
  see_also = Path(client)
  see_also.forward_to("book1", "book2")  # 从book1到book2的访问为3次 
  see_also.forward_to("book1", "book2")
  see_also.forward_to("book1", "book2")
  see_also.forward_to("book1", "book3")  # 从book1到book3的访问为2次 
  see_also.forward_to("book1", "book3")
  see_also.forward_to("book1", "book4")  # 从book1到book4和book5的访问各为1次 
  see_also.forward_to("book1", "book5")
  see_also.forward_to("book1", "book6")  # 从book1到book6的访问为2次 
  see_also.forward_to("book1", "book6")
  see_also.pagging_record("book1", 1, 5)  # 展示顾客在看了book1之后，最常看的其他书   ['book2', 'book6', 'book3', 'book5', 'book4'] 
  see_also.pagging_record("book1", 1, 5,
                          with_time=True)  # 将查看的次数也列出来   [('book2', 3), ('book6', 2), ('book3', 2), ('book5', 1), ('book4', 1)]
