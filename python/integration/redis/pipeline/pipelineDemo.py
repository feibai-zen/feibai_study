#!/opt/homebrew/bin/python3
# encoding=utf-8

from redis import Redis

if __name__ == '__main__':
  client = Redis(decode_responses=True)
  pipe = client.pipeline(transaction=False)
  pipe.set("msg", "hello world")
  # Pipeline < ConnectionPool < Connection < host = localhost, port = 6379, db = 0 >> > 
  pipe.incrby("pv_counter::12345", 100)
  # Pipeline < ConnectionPool < Connection < host = localhost, port = 6379, db = 0 >> >
  pipe.sadd("fruits", "apple", "banana", "cherry")
  # Pipeline < ConnectionPool < Connection < host = localhost, port = 6379, db = 0 >> > 

  pipe.execute()  # [True, 100, 3]
