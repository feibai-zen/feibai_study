from redis import Redis


# 排行榜
class RankingList:
    def __init__(self, client, key):
        self.client = client
        self.key = key

    def set_score(self, item, score):
        """
        为排行榜中的指定元素设置分数，不存在的元素会被添加到排行榜中
        """
        self.client.zadd(self.key, {item: score})

    def get_score(self, item):
        """
        获取排行榜中指定元素的分数
        """
        return self.client.zscore(self.key, item)

    def remove(self, item):
        """
        从排行榜中移除指定的元素
        """
        self.client.zrem(self.key, item)

    def increase_score(self, item, increment):
        """
        将给定元素的分数增加increment分
        """
        self.client.zincrby(self.key, increment, item)

    def decrease_score(self, item, decrement):
        """
        将给定元素的分数减少decrement分
        """
        # 因为Redis没有直接提供能够减少元素分值的命令
        # 所以这里通过传入一个负数减量来达到减少分值的目的
        self.client.zincrby(self.key, 0 - decrement, item)

    def get_rank(self, item):
        """
        获取给定元素在排行榜中的排名
        """
        rank = self.client.zrevrank(self.key, item)
        # 因为Redis元素的排名是以0为开始的，
        # 而现实世界中的排名通常以1为开始，
        # 所以这里在返回排名之前会执行加1操作
        if rank is not None:
            return rank + 1

    def top(self, n, with_score=False):
        """
        获取排行榜中得分最高的n个元素，
        如果可选的with_score参数的值为True，那么将元素的分数（分值）也一并返回
        """
        return self.client.zrevrange(self.key, 0, n - 1, withscores=with_score)


if __name__ == '__main__':
    client = Redis(decode_responses=True)
    ranking = RankingList(client, "music download ranking")
    ranking.set_score("ninelie", 3500)
    ranking.set_score("StarRingChild", 2700)
    ranking.set_score("RE:I AM", 3300)
    ranking.set_score("Your voice", 2200)
    ranking.set_score("theDOGS", 1800)
    ranking.get_score("ninelie")
    ranking.get_rank("ninelie")
    ranking.top(5)
    ranking.top(5, True)  # 在获取榜单的同时显示歌曲的下载次数
