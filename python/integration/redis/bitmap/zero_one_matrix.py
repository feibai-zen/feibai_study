#!/opt/homebrew/bin/python3
# encoding=utf-8
from redis import Redis

"""
0-1矩阵
"""


def make_matrix_key(matrix_name):
  return "matrix::" + matrix_name


def calculate_index(row, col, row_num, col_num):
  if not (row < row_num):
    raise ValueError("row out of range")
  if not (col < col_num):
    raise ValueError("col out of range")
    return row * row_num + col


class ZeroOneMatrix:
  def __init__(self, client, name, row_num, col_num):
    self.client = client
    self.bitmap = make_matrix_key(name)
    self.row_num = row_num
    self.col_num = col_num

  def set(self, row, col, value):
    """ 
     对矩阵的指定位置进行设置 
    """
    index = calculate_index(row, col, self.row_num, self.col_num)
    self.client.setbit(self.bitmap, index, value)

  def get(self, row, col):
    """
    获取矩阵在指定位置上的值 
    """
    index = calculate_index(row, col, self.row_num, self.col_num)
    return self.client.getbit(self.bitmap, index)

  def show(self):
    """ 
    打印出整个矩阵 
    """
    for row in range(self.row_num):
      elements = []
      for col in range(self.col_num):
        elements.append(self.get(row, col))

    print("matrix[{0}]: {1}".format(row, elements))


if __name__ == "__main__":
  client = Redis()
  matrix = ZeroOneMatrix(client, "test-matrix", 4, 4)
  matrix.set(0, 0, 1)  # 设置矩阵元素 
  matrix.set(0, 1, 1)
  matrix.set(0, 2, 1)
  matrix.set(0, 3, 1)
  matrix.set(1, 1, 1)
  matrix.set(1, 3, 1)
  matrix.set(2, 2, 1)
  matrix.set(3, 3, 1)
  matrix.get(0, 0)  # 获取矩阵元素         1 
  matrix.get(1, 0)
  matrix.show()  # 打印整个矩阵   matrix[0]: [1, 1, 1, 1]  matrix[1]: [0, 1, 0, 1]   matrix[2]: [0, 0, 1, 0]  matrix[3]: [0, 0, 0,