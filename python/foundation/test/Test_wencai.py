#!/opt/homebrew/bin/python3
# coding=utf-8

import wencai as wc

report = wc.get_scrape_report("上市天数大于60天；筹码集中度90小于20%；非停牌；非st；")
print(report)