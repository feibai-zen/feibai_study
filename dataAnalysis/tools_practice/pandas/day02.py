import pandas as pd
import numpy as np

data = {"grammer": ["Python", "C#", "Java", "GO", "Lua", "SQL", "PHP", "Python"],
        "score": [1, 2, 4, 4, 5, 6, 7, 10]}


def data_frame():
    # 1.字典结构可以直接转成DateFrame
    df = pd.DataFrame(data)
    print(df)
    print('***********************')
    return df


def data_frame_to_excel():
    df = data_frame()
    # 11.将DataFrame保存为EXCEL
    df.to_excel('filename.xlsx')


# 12.查看数据行，列数
def data_frame_column_cnt():
    df = data_frame()
    shape = df.shape
    print(shape)


# 13.提取score列的值大于3小于7的行
def data_frame_query_matched():
    df = data_frame()
    df = df[(df['score'] > 3) & (df['score'] < 7)]
    print(df)


# 14.交换两列位置
def data_frame_switch_column():
    df = data_frame()
    '''
    方法1
    '''
    temp = df['score']
    # 删除列
    df.drop(labels=['score'], axis=1, inplace=True)
    # 插入列
    df.insert(0, 'score', temp)
    print(df)
    '''
    方法2
    cols = df.columns[[1,0]]
    df = df[cols]
    print(df)
    '''


# 15.提取score列最大值所在行
def data_frame_max_value_row():
    df = data_frame()
    max_value_row = df[df['score'] == df['score'].max()]
    print(max_value_row)


# 16.查看最后5行数据
def data_frame_tail_n_rows():
    df = data_frame()
    df = df.tail(5)
    print(df)


# 17.删除最后一行数据
def data_frame_delete_last_row():
    df = data_frame()
    df.drop([len(df) - 1], inplace=True)
    print(df)


# 18.追加一行数据['Perl',6.6]
def data_frame_add_tail():
    oneRow = {'grammer': 'Perl', 'score': 6.6}
    df = data_frame()
    df = df.append(oneRow, ignore_index=True)
    print(df)


# 19.对数据按照"score"列值的大小进行排序
def data_frame_sort_by_column():
    df = data_frame()
    df.sort_values('grammer', inplace=True)

    print(df)


# 20.统计grammer列每个字符串的长度
def data_frame_column_char_len():
    df = data_frame()
    char_len = df['grammer'].map(lambda x: len(x))
    print(char_len)


if __name__ == '__main__':
    data_frame_column_char_len()
