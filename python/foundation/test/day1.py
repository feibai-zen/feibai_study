#!/opt/homebrew/bin/python3
# coding=utf-8
import random

for i in random(0, 10):
  print(i)

guess = int(input("请输入一个数字"))
while guess != 8:
  if guess == 8:
    print("对了")
  else:
    if guess > 8:
      print("大了")
      break
    else:
      print("小了")
  guess = int(input("请输入一个数字"))
print("猜对了...")
