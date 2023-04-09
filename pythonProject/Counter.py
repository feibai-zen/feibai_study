from redis import Redis


class Counter:
    def __init__(self, client, key):
        self.client = client
        self.key = key

    def increase(self, n=1):
        """
        将计数器的值加上n，然后返回计数器当前的值。
        如果用户没有显式地指定n，那么将计数器的值加上1
        """
        return self.client.incr(self.key, n)

    def decrease(self, n=1):
        """
        将计数器的值减去n，然后返回计数器当前的值。
        如果用户没有显式地指定n，那么将计数器的值减去1
        """
        return self.client.decr(self.key, n)

    def get(self):
        """
        返回计数器当前的值
        """
        # 尝试获取计数器当前的值
        value = self.client.get(self.key)
        # 如果计数器并不存在，那么返回0作为计数器的默认值
        if value is None:
            return 0
        else:
            # 因为redis-py的get()方法返回的是字符串值，所以这里需要使用int()函数将字
            # 符串格式的数字转换为真正的数字类型，比如将"10"转换为10
            return int(value)

    def reset(self):
        """
        清零计数器，并返回计数器在被清零之前的值
        """
        old_value = self.client.getset(self.key, 0)
        # 如果计数器之前并不存在，那么返回0作为它的旧值
        if old_value is None:
            return 0
        else:
            # 与redis-py的get()方法一样，getset()方法返回的也是字符串值，所以程序在
            # 将计数器的旧值返回给调用者之前，需要先将它转换成真正的数字
            return int(old_value)


if __name__ == '__main__':
    client = Redis(decode_responses=True)
    counter = Counter(client, "counter::page_view")
    # counter.increase()  # 将计数器的值加上1
    # counter.increase()  # 将计数器的值加上1
    # counter.increase(10)  # 将计数器的值加上10
    # counter.decrease()  # 将计数器的值减去1
    # counter.decrease(5)  # 将计数器的值减去5=
    # counter.reset()  # 重置计数器，并返回旧值
    # counter.get()  # 返回计数器当前的值
