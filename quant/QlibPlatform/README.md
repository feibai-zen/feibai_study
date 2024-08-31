## 文档
### 官方文档
https://qlib.readthedocs.io/en/latest/index.html
### 中文文档
https://www.wuzao.com/document/qlib/introduction/quick.html#id3

## 依赖管理
pytorch               # 深度机器学习相关
lightGBM              # 传统机器学习相关
numpy                 # 矩阵运算

## 本地环境
Qlib 仅支持 Python3.7以上的版本且暂不支持 Python3.10. 另外Python 3.9 版本不支持模型性能绘制，因此选择创建Python3.8版本的虚拟环境。

### 配置conda 软件源
```shell
清华源：
conda config --add channels  https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/main
conda config --add channels  https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free
conda config --add channels  https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/conda-forge/
conda config --set show_channel_urls yes
```

### 安装必要的软件
```shell
pip install numpy
pip install --upgrade cython
使用pip安装或者conda安装pyqlib均失败，使用源码安装pyqlib
```

## 数据相关
### 数据下载
python qlib-main/scripts/get_data.py qlib_data --target_dir ./qlib_data/cn_data --region cn


### 数据转换
第三方数据转换成qlib bin文件格式
