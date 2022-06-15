#!/opt/homebrew/bin/python3
# encoding=utf-8


import time


# 声明一个装饰器 --被调函数没有参数，有返回值
def display_time(func):
    def wrapper():
        t1 = time.time()
        result = func()
        t2 = time.time()
        print("Total time: {:.4} s".format(t2 - t1))
        return result

    return wrapper


# 判断是否为质数
def is_prime(num):
    if num < 2:
        return False
    elif num == 2:
        return True
    else:
        for i in range(2, num):
            if num % i == 0:
                return False
        return True


# 打印质数个数
@display_time
def count_prime_nums():
    count = 0
    for i in range(2, 10000):
        if is_prime(i):
            print(i)
            count += 1
    return count


if __name__ == "__main__":
    print(count_prime_nums())
