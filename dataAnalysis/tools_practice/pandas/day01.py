import pandas as pd
import numpy as np

data = {"grammer": ["Python", "C#", "Java", "GO", "Lua", "SQL", "PHP", "Python"],
        "score": [1, 2, 4, 4, 5, 6, 7, 10]}


def data_frame():
    # 1.字典结构可以直接转成DateFrame
    df = pd.DataFrame(data)
    print(df)
    return df


def data_frame_matched_column():
    df = data_frame()
    # 2.提取含有字符串"Python"的行, 如果df['grammer'].str.contains('Python') 结果为NAN
    result = df[df['grammer'].str.contains('Python')]
    print(result)


def data_frame_all_columns():
    df = data_frame()
    # 3.输出df的所有列名
    print(df.columns)


def data_frame_rename_column():
    df = data_frame()
    print(df)
    # 4.修改列名为popularity
    print(df.rename(columns={'score': 'popularity'}, inplace=True))


def data_frame_value_count():
    df = data_frame()
    print(df)
    # 5.统计grammer列中每种编程语言出现的次数
    print(df['grammer'].value_counts())


def data_frame_fill_nan_by_avg():
    data = {"grammer": ["Python", "C#", "Java", "GO", "Lua", "SQL", "PHP", "Python"],
            "score": [1, 2, 4, 4, np.nan, 5, 7, 10]}
    df = pd.DataFrame(data)
    print(df)

    # 6.将空值用上下值的平均值填充
    df['score'] = df['score'].fillna(df['score'].interpolate())
    print(df)


def data_frame_rows_matched():
    df = data_frame()
    # 7.提取score列中值大于3的行
    print(df[df['score'] > 3])


def data_frame_drop_duplicates():
    df = data_frame()
    # 8.按照grammer列进行去重. grammer列相同的话就去重,不考虑score列
    print(df.drop_duplicates(['grammer']))


def data_frame_calculate_avg_column():
    # 9.计算score列平均值
    df = data_frame()
    print(df['score'].mean())


def data_frame_convert_column_to_list():
    df = data_frame()
    # 10.将grammer列转换为list
    li = df['grammer'].to_list()
    print(li)


def data_frame_append_row():
    df = data_frame()
    # 11.向DataFrame中添加一行数据
    row = {'grammer': 'Clojure', 'score': 0.5}
    df = df.append(row, ignore_index=True)
    print(df)


if __name__ == "__main__":
    data_frame_convert_column_to_list()
