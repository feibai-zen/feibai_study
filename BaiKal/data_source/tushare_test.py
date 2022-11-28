#经常用的lib
import tushare as ts
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt


pro = ts.pro_api('7c0b65d9371241179d81d7dc695f62267cf620463456c3fd69abde6c')
#df = pro.query('trade_cal', exchange='', start_date='20180901', end_date='20181001',
               #fields='exchange,cal_date,is_open,pretrade_date', is_open='0')

# print(ts.__version__)
# ts.get_apis()


