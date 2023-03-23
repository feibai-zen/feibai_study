import numpy as np
import pandas as pd
from scipy.stats import triang
import math
import matplotlib.pyplot as plt
import DataAPI


class Bargain:
    """
    某一日的筹码分布
    """

    def __init__(self, min_price, max_price, rolling_min, rolling_max, pdf, step=0.01):
        self.min_price = min_price
        self.max_price = max_price
        self.min_price_rolling = rolling_min
        self.max_price_rolling = rolling_max
        self.step = step
        self.pdf = pdf
        self.xs = np.arange(self.min_price, self.max_price + self.step, self.step)

        @staticmethod
        def norm_dist_draw(signal, n_draw):
            """
            重复n_draw次,将原先小于mean-3sigma的改为mean-3sigma;
            大于mean+3sigma的改为mean+3sigma
            :param signal:
            :param n_draw:
            :return:
            """
            series = signal.copy()  # do not modify input data
            for i in range(n_draw):
                std = series.std()
                mean = series.mean()
                series[series < mean - 3 * std] = mean - 3 * std
                series[series > mean + 3 * std] = mean + 3 * std
            return series

        def get_cost(self, x):
            """
            表示当日x(0~100)%获利盘的价格是多少,即有x的持仓成本在该价格以下;
            其余(100-x)的持仓成本在该价格以上,是套牢盘
            :param x: (0~100)代表获利盘的比例
            :return:
            """
            tot_cnt = abs(sum(self.pdf))
            acc_cnt = 0.0
            for price, cnt in zip(self.xs, self.pdf):
                acc_cnt += cnt
                if acc_cnt / tot_cnt > x / 100.0:
                    return price
            return np.nan

        def get_winner(self, x):
            """
            获利盘比例:表示以x价格卖出时获利盘比例是多少,0.1表示10%获利盘
            :param x:
            :return: 0.5
            """
            tot_cnt = sum(self.pdf)
            acc_cnt = 0.0
            for price, cnt in zip(self.xs, self.pdf):
                acc_cnt += cnt
                if price >= x:
                    break
            return acc_cnt / tot_cnt

        def get_max_min_price(self):
            """
            计算最大/最小价格
            :return:
            """
            # TODO: 这个地方可以不做三次winsorize
            normed_pdf = Bargain.norm_dist_draw(self.pdf, 3)
            return tuple([max(normed_pdf), min(normed_pdf)])

        def get_max_min_mean_price(self):
            """
            计算最大/最小价格
            :return:
            """
            normed_pdf = Bargain.norm_dist_draw(self.pdf, 3)
            mean_price = self.get_cost(x=50)
            return tuple([max(normed_pdf), min(normed_pdf), mean_price])

        def get_relative_position(self, cur_price):
            """
            计算相对价格位置
            :return:
            """
            mean_price = self.get_cost(x=50)
            return (cur_price - mean_price) / mean_price

        def get_asr(self, cur_price, ratio):
            """
            计算当前价格上下ratio%的活动筹码比例
            :param cur_price: 当前价格
            :param ratio: 上下百分比
            :return:
            """
            upper_ratio = self.get_winner(x=cur_price * (1.0 + ratio))
            lower_ratio = self.get_winner(x=cur_price * (1.0 - ratio))
            return upper_ratio - lower_ratio

        def get_concentration(self, ratio):
            """
            计算当前价格上下ratio的平均价格;
            筹码集中度,在给定范围内的最高价/最低价
            :param ratio: 0~100
            :return: [lower_price, upper_price, concentrate]
            """
            lower_price = self.get_cost((100.0 - ratio) / 2.0)
            upper_price = self.get_cost(100.0 - (100.0 - ratio) / 2.0)
            concentrate = (upper_price - lower_price) / (lower_price + upper_price)
            return tuple([lower_price, upper_price, concentrate])

        def plot_bargains(self):
            fig = plt.figure()
            plt.bar(left=self.xs, height=self.pdf, width=0.01)
            plt.xlim(self.min_price_rolling, self.max_price_rolling)
            plt.show()


# step1) 根据日线计算每天的筹码分布
def comp_cyq(df, list_date, a=1.0, step=0.01, n=80):
    """
    计算筹码分布,
    分布可选:三角形分布/均匀分布;历时换手衰减系数;该日换手率;
    np.random.triangular & np.histogram(density=True) & scipy.stats.triang
    :param df:
    :param a: 历史衰减系数,默认为1
    :param step: min/max间的间隔,精度
    :param n: 筹码计算保留天数
    :return:
    """
    if len(df.index) == 0:
        return df
    epsilon = 0.0001
    rolling_min = df['lowestPrice'].rolling(center=False, window=n, min_periods=0).min()
    rolling_max = df['highestPrice'].rolling(center=False, window=n, min_periods=0).max()
    min_price = min(df['lowestPrice'])
    max_price = max(df['highestPrice'])
    size = len(np.arange(min_price, max_price + step, step))
    size = size + 1 if size == 0 else size
    pdf_vols = np.zeros((len(df.index), size + 1))
    bargains = []
    for idx, (index, rows) in enumerate(df.iterrows()):
        alpha = rows['turnoverRate'] * a
        alpha = rows['turnoverValue'] / rows[
            'negMarketValue'] if alpha == 0.0 and 'negMarketValue' in df.columns else alpha
        alpha = 0.0001 if alpha == 0.0 else alpha
        if df.index.tolist()[idx] == list_date:
            alpha = 1.0
            rows['turnoverVol'] = rows['negMarketValue'] / rows['actPreClosePrice']
        if rows['highestPrice'] - rows['lowestPrice'] > step:
            # c = (rows['turnoverValue'] / rows['turnoverVol'] - rows['lowestPrice']) / (rows['highestPrice']-rows['lowestPrice'])
            c = (rows['closePrice'] - rows['lowestPrice']) / (rows['highestPrice'] - rows['lowestPrice'])
            pdf = triang.pdf(x=np.arange(min_price, max_price + step, step), c=c, loc=rows['lowestPrice'],
                             scale=rows['highestPrice'] - rows['lowestPrice'])
            pdf_vol = pdf / sum(pdf) * rows['turnoverVol']
            pdf_vols[idx, :-1] = pdf_vol
            pdf_vols[idx, -1] = alpha
        else:
            idy = max(math.floor((rows['highestPrice'] - min_price - epsilon) / step), 0)
            pdf_vol = np.zeros(size)
            pdf_vol[idy] = rows['turnoverVol']
            pdf_vols[idx, :-1] = pdf_vol
            pdf_vols[idx, -1] = alpha
    for idx, (_, rmin), (__, rmax) in zip(range(len(df.index)), rolling_min.iteritems(), rolling_max.iteritems()):
        st_idx = max(0, idx - n)
        pdf_acc = pdf_vols[st_idx, :-1] * pdf_vols[st_idx, -1]
        for idy in range(st_idx + 1, idx + 1):
            alpha = pdf_vols[idy, -1]
            pdf_vol = pdf_vols[idy, :-1]
            pdf_acc = pdf_acc * (1 - alpha) + alpha * pdf_vol
        bargains.append(Bargain(min_price, max_price, rmin, rmax, pdf_acc, step))
    df['BARGAINS'] = pd.Series(bargains, index=df.index)
    return df


def comp_signals(df):
    """
    计算广发研报中的四个因子
    """
    # CYQK_C
    df['CYQK_C'] = [rows['BARGAINS'].get_winner(x=rows['closePrice']) for index, rows in df.iterrows()]
    # ASR
    df['ASR'] = [rows['BARGAINS'].get_asr(cur_price=rows['closePrice'], ratio=0.1) for index, rows in df.iterrows()]
    # CKDW
    bargain_prices = [rows['BARGAINS'].get_max_min_mean_price() for index, rows in df.iterrows()]
    ckdw = map(lambda x: (x[2] - x[1]) / (x[0] - x[1]) if not np.any(np.isnan(x)) else np.nan, bargain_prices)
    df['CKDW'] = pd.Series(ckdw, index=df.index)
    # PRP
    prp = [rows['BARGAINS'].get_relative_position(cur_price=rows['closePrice']) for index, rows in df.iterrows()]
    df['PRP'] = pd.Series(prp, index=df.index)
    return df


def assign_vol_adjf(df, df_vol_adj, col_name='turnoverVol'):
    """
    成交量前复权
    """
    if len(df_vol_adj.index) == 0 or len(df.index) == 0:
        return df
    for idx in range(len(df_vol_adj.index)):
        prev_date = df_vol_adj.iloc[idx]['exDivDate']
        prev_ratio = df_vol_adj.iloc[idx]['accumAdjFactor4']
        if idx == 0 and len(df[df.index.values < prev_date].index) > 0:
            df.loc[df.index.values < prev_date, col_name] = df.loc[df.index.values < prev_date, col_name] * prev_ratio
        if idx != len(df_vol_adj.index) - 1:
            cur_date = df_vol_adj.iloc[idx + 1]['exDivDate']
            acc_adjf_ratio = df_vol_adj.iloc[idx + 1]['accumAdjFactor4']
            if len(df[(df.index.values >= prev_date) & (df.index.values < cur_date)].index) > 0:
                df.loc[(df.index.values >= prev_date) & (df.index.values < cur_date), col_name] \
                    = df.loc[(df.index.values >= prev_date) & (df.index.values < cur_date), col_name] * acc_adjf_ratio
    return df


# 测试样例，我们测试计算某一天的筹码分布及因子值
df = DataAPI.MktEqudGet(tradeDate=u"", secID=u"", ticker=u"000001", beginDate=u"20150101", endDate=u"20171018",
                        isOpen="1", field=u"", pandas="1")
df = df.set_index('tradeDate')
df_vol_adj = DataAPI.MktVolAdjfGet(secID=u"", ticker=u"000001", beginDate=u"20150101", endDate=u"20171018",
                                   exchangeCD=u"XSHE,XSHG", exDivDate=u"", field=u"", pandas="1")
df_sec = DataAPI.SecIDGet(partyID=u"", assetClass=u"E", ticker=u"000001", cnSpell=u"", field=u"", pandas="1")
df = assign_vol_adjf(df, df_vol_adj, col_name='turnoverVol')
df = comp_cyq(df, list_date=df_sec.iloc[0]['listDate'], a=1.0, step=0.01, n=80)
df = comp_signals(df)
# print(df.tail(1))
df.iloc[-1]['BARGAINS'].plot_bargains()
