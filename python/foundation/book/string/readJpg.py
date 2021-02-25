from redis import Redis
from cache import Cache

if __name__ == "__main__":
    client = Redis("192.168.1.175", "6386", 0, "jredis123456", decode_responses=True)  #
    # 使用二进制编码方式打开客户端
    cache = Cache(client)
    image = open("redis-logo.jpg", "rb")  # 以二进制只读方式打开图片文件
    data = image.read()  # 读取文件内容
    image.close()  # 关闭文件
    cache.set("redis-logo.jpg", data)  # 将内存缓存到键redis-logo.jpg
    cache.get("redis-logo.jpg")[:20]  # 读取二进制数据的前20个字节         b'\xff\xd8\xff\xe0\x00\x10JFIF\x00\x01\x01\x01\x00H\x00H\x00\x00'
