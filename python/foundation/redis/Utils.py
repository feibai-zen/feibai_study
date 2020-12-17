# !/usr/bin/env python
# -*- coding:utf-8 -*-

import redis

host = '192.168.1.175'
port = 6386
password = 'jredis123456'
db = 0


class Util:

  def __init__(self):
    pass

  def getRedisConnection(self):
    pool = redis.ConnectionPool(host, port, password)

    return redis.Redis(connection_pool=pool)

  def set(self, util, key, value):
    r = util.getRedisConnection()
    list = []

    for i in list:
      r.zadd(key, 100, i)
