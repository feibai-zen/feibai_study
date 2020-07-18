# !/usr/bin/env python
# -*- coding:utf-8 -*-

import redis


def getRedisConnection():
  pool = redis.ConnectionPool(host='192.168.3.131', port=19000, password="jredis123456")
  return redis.Redis(connection_pool=pool)


if __name__ == '__main__':
  r = getRedisConnection()
  r.set('foo', 'Bar')
  print(r.get('foo'))
