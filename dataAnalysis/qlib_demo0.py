import baostock as bs
import pandas as pd
import time
import akshare as ak
import numpy as np

if __name__ == '__main__':
    ### 第一步_获取所有股票的代码

    # 深证A指
    all_sz = ak.stock_info_sz_name_code(indicator="A股列表")
    # 上证指数
    all_sh1 = ak.stock_info_sh_name_code(indicator="主板A股")
    all_sh2 = ak.stock_info_sh_name_code(indicator="主板B股")
    # 次新股
    all_new = ak.stock_zh_a_new()
