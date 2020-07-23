#!/usr/bin/env python
# coding=utf-8

from foundation.redisApi.Utils import Util


class StringApi:

  def set(self, util, key, value):
    r = util.getRedisConnection()
    r.set(key, value)
    r.get(key)


if __name__ == '__main__':
  util = Util()
  test = StringApi()
  test.set(util, "name", "互动娱乐事业部")
