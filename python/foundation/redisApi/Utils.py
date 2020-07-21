# !/usr/bin/env python
# -*- coding:utf-8 -*-

import redis

host = '192.168.3.131'
port = 19000
password = 'jredis123456'
db = 0


class Util:

  def __init__(self):
    pass

  def getRedisConnection(self):
    pool = redis.ConnectionPool(host, port, password)

    return redis.Redis(connection_pool=pool)
