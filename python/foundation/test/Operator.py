#!/opt/homebrew/bin/python3
# encoding=utf-8

import random

print(-2 ** 4)
print(2 ** -4)
print(10 // 3)
print(10 / 3)  # python中的除法是精确的除法

var1 = 1
var2 = 2
print(var1 if var1 < var2 else var2)  # 三目运算符

str1 = "liyuanlong"
print(type(str1))  # 获取变量类型
print(isinstance(str1, str))  # 类型判断

# 循环
for i in range(0, 10):
  print(i)

# 随机数
print(random.randint(1, 100))

# 数据类型
print(True == 1)
print(False == 0)

print(int('520'))
print(float('520'))
print(str(520))
