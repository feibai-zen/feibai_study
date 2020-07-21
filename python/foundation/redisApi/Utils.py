# !/usr/bin/env python
# -*- coding:utf-8 -*-

import redis


class Util:
  host = '192.168.3.131'
  port = 19000
  password = 'jredis123456'
  db = 0

  def __init__(self, name):
    self.name = name

  def getRedisConnection(self):
    pool = redis.ConnectionPool(self.host, self.port, self.password, self.db)

    return redis.Redis(connection_pool=pool)
