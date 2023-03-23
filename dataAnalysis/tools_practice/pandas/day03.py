from datetime import datetime

import pandas as pd
import re
import numpy as np

data = {'createTime': ['2020-03-16 11:30:18', '2020-03-16 10:58:48', '2020-03-16 10:46:39', '2020-03-16 10:45:48',
                       '2020-03-16 10:20:41'],
        'education': ['本科', '本科', '不限', '本科', '本科'],
        'salary': ['20k~35k', '20k~40k', '20k~35k', '13k~20k', '10k~20k']
        }


def data_frame():
    # 1.字典结构可以直接转成DateFrame
    df = pd.DataFrame(data)
    print(df)
    print('***********************')
    return df


# 21.将DataFrame保存为EXCEL
def data_frame_read_local_excel():
    df = data_frame()
    # 将DataFrame保存为EXCEL
    df.to_excel('filename.xlsx')
    df = pd.read_excel('filename.xlsx')

    print(df)


# 22.查看前5行数据
def data_frame_head_n_rows():
    df = data_frame()
    print(df.head())  # 默认为5


# 23.将salary列数据转换为最大值与最小值的平均值
def data_frame_exp():
    df = data_frame()

    for i in range(len(df)):
        str1 = df.iloc[i, 2]
        k = re.findall(r"\d+\.?\d*", str1)
        salary = ((int(k[0]) + int(k[1])) / 2) * 1000
        df.iloc[i, 2] = int(salary)
    print(df)


# 24.将数据根据学历进行分组并计算平均薪资
def data_frame_group_by_avg():
    df = data_frame()
    print(df.groupby('education').aggregate())


# 25.将createTime列时间转换为月-日
# 时间转成字符串，字符串转成时间
def data_frame_time_convert():
    df = data_frame()
    for i in range(len(df)):
        df.iloc[i, 0] = datetime.strptime(df.iloc[i, 0], "%Y-%m-%d %H:%M:%S").strftime('%m-%d')

    print(df)


# 26.查看索引、数据类型和内存信息
def data_frame_information():
    df = data_frame()
    print(df.info())


# 27.查看数值型列的汇总统计
def data_frame_describe():
    df = data_frame()
    print(df.describe())


# 28.新增一列根据salary将数据分为三组
def data_frame_add_one_column():
    df = _process_the_salary_to_middle_int()

    bins = [0, 5000, 20000, 50000]
    group_names = ['低', '中', '高']
    df['categories'] = pd.cut(df['salary'], bins, labels=group_names)
    print(df)


# 29.按照salary列对数据降序排列
def data_frame_sort_by_salary():
    df = data_frame()
    df = df.sort_values('salary', ascending=False)
    print(df)


# 30. 取出第33行数据
def data_frame_located_row():
    df = data_frame()
    print(df.loc[2])


def _process_the_salary_to_middle_int():
    df = data_frame()

    for i in range(len(df)):
        str1 = df.iloc[i, 2]
        k = re.findall(r"\d+\.?\d*", str1)
        salary = ((int(k[0]) + int(k[1])) / 2) * 1000
        df.iloc[i, 2] = int(salary)

    return df


if __name__ == '__main__':
    data_frame_add_one_column()
