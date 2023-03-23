# 北向资金占比市值topN
# 北向资金占比流通topN
#
#
# 缺少tushare接口调用权限

import pandas as pd
import numpy as np
import tushare as ts
import datetime

tok = '7c0b65d9371241179d81d7dc695f62267cf620463456c3fd69abde6c'
ts.set_token(tok)
pro = ts.pro_api(tok)


def get_latest_trade_date():
    date = str(datetime.datetime.now()).split(" ")[0].replace("-", "")

    df = pro.query('trade_cal', start_date='20210101')
    trade_date_list = df[df["is_open"] == 1]["cal_date"].tolist()
    if date in trade_date_list:
        return date
    else:
        trade_date_list = [i for i in trade_date_list if i <= date]
        return trade_date_list[-1]


def get_foundation_indicator(code_list, tradeDate):
    # pe_ttm  circ_mv 流通市值（万元） df = pro.daily_basic(ts_code='', trade_date='20180726', fields='ts_code,trade_date,turnover_rate,volume_ratio,pe,pb')
    # eps 基本每股收益 df = pro.fina_indicator(ts_code='600000.SH')
    # area 地区  industry所属行业   pro.stock_basic
    data_industry = pro.stock_basic(exchange='', list_status='L', fields='ts_code,area,industry')
    data_eps = pro.query('fina_indicator_vip')[['ts_code', "eps"]]
    data_foundation = pro.daily_basic(ts_code='', trade_date=tradeDate, fields='ts_code,pe_ttm,pb,circ_mv,close')

    data_total = pd.DataFrame()
    data_total["ts_code"] = code_list
    data_total = pd.merge(
        pd.merge(pd.merge(data_total, data_industry, on="ts_code", how="left"), data_eps, on="ts_code", how="left"),
        data_foundation,
        on="ts_code", how="left")
    data_total.drop_duplicates(inplace=True)
    return data_total


def getStockHoldingList(trade_date):
    # SH沪股通SZ深股通
    HKToSHChannel = pro.hk_hold(trade_date=trade_date, exchange='SH')[
        ['trade_date', 'ts_code', 'name', 'ratio']]  # 北向资金在本支股票所占的持股比例
    HKToSZChannel = pro.hk_hold(trade_date=trade_date, exchange='SZ')[['trade_date', 'ts_code', 'name', 'ratio']]
    northboundHolding = HKToSHChannel.append(HKToSZChannel)
    northboundHolding = northboundHolding[northboundHolding["ratio"] > 0]
    northboundHolding.drop_duplicates(inplace=True)

    code_list = northboundHolding["ts_code"].tolist()
    data_综合 = get_foundation_indicator(code_list, trade_date)
    data_北向资金 = pd.merge(northboundHolding, data_综合, on="ts_code", how="left")
    data_北向资金.dropna(subset=["circ_mv"], inplace=True)
    data_北向资金.drop_duplicates(inplace=True)

    return data_北向资金


def get_top_n(data_北向资金, 持仓比例_top_n, 市值_top_n):
    日期 = str(datetime.datetime.now()).split(" ")[0].replace("-", "")
    data_北向资金["占比市值"] = data_北向资金["circ_mv"] * data_北向资金["ratio"]
    data_北向资金 = data_北向资金.sort_values(by="占比市值", ascending=False)
    占比市值_top_n = data_北向资金.iloc[:int(市值_top_n)]
    占比市值_top_n.to_csv(日期 + "占比市值_top_" + str(市值_top_n) + ".csv", encoding="utf_8_sig", index=None)

    data_北向资金 = data_北向资金.sort_values(by="ratio", ascending=False)
    占流通盘_top_n = data_北向资金.iloc[:int(持仓比例_top_n)]
    占流通盘_top_n.to_csv(日期 + "占流通盘_top_" + str(市值_top_n) + ".csv", encoding="utf_8_sig", index=None)

    return 占比市值_top_n, 占流通盘_top_n


if __name__ == '__main__':
    tradeDate = get_latest_trade_date()
    dataNorthbound = getStockHoldingList(tradeDate)
    占比市值_top_n, 占流通盘_top_n = get_top_n(dataNorthbound, 200, 200)
