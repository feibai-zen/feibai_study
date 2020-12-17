#!/usr/bin/env python3
# coding=utf-8

from foundation.redis.Utils import Util
class StringApi:

  def set(self, util, key, value):
    r = util.getRedisConnection()
    list = []
    for i in list:
      r.zadd()
    r.set(key, value)
    r.get(key)


if __name__ == '__main__':
  util = Util()
  test.set(util, "name", "互动娱乐事业部")
