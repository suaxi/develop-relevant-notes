## pandas

### 一、概要

#### 1. 简介

pandas 是Python数据分析工具链中最核心的库，可用于数据读取、清洗、分析、统计、输出等，适用于处理结构化数据（如表格型数据）。



#### 2. 核心设计理念

- 标签化数据结构：提供带标签的轴
- 灵活处理缺失数据：内置NaN处理机制
- 智能数据对齐：自动按标签对齐数据
- IO工具：支持CSV、Excel、SQL等多种数据源
- 时间序列处理：原生支持日期时间处理和频率转换

![1.pandas](static/1.简介/1.pandas.png)

| 特性     | Series               | DataFrame                        |
| -------- | -------------------- | -------------------------------- |
| 维度     | 一维                 | 二维                             |
| 索引     | 单索引               | 行索引 + 列名                    |
| 数据存储 | 同质化数据类型       | 各列的数据类型可以不同           |
| 类比     | Excel 单列           | Excel Sheet                      |
| 创建方式 | pd.Series([1, 2, 3]) | pd.DataFrame({'col': [1, 2, 3]}) |



### 二、Series

#### 1. 创建

```python
# 安装 pandas
pip install pandas
```


```python
import pandas as pd

s = pd.Series([5, 1, 3, 2, 8, 10])
print(s)

0     5
1     1
2     3
3     2
4     8
5    10
dtype: int64
```

```python
# 自定义索引
s = pd.Series([5, 1, 3, 2, 8, 10], index=['A', 'B', 'C', 'D', 'E', 'F'])
print(s)

s = pd.Series([5, 1, 3, 2, 8, 10], index=[10, 20, 30, 40, 50, 60])
print(s)

A     5
B     1
C     3
D     2
E     8
F    10
dtype: int64
10     5
20     1
30     3
40     2
50     8
60    10
dtype: int64
```

```python
# 自定义name（name用于描述这一列是干什么的）
s = pd.Series([5, 1, 3, 2, 8, 10], index=[10, 20, 30, 40, 50, 60], name='成绩')
print(s)

10     5
20     1
30     3
40     2
50     8
60    10
Name: 成绩, dtype: int64
```

```python
# 通过字典创建
s = pd.Series({'A': 10, 'B': 20, 'C': 30})
print(s)

s1 = pd.Series(s, index=['A', 'C'])
print(s1)

A    10
B    20
C    30
dtype: int64
A    10
C    30
dtype: int64
```



#### 2. 常用属性

| 属性         | 说明                       |
| ------------ | -------------------------- |
| index        | 索引（从0开始）            |
| values       | 值                         |
| dtype/dtypes | 元素的类型                 |
| shape        | 形状                       |
| ndim         | 维度                       |
| size         | 元素个数                   |
| name         | 名称                       |
| loc[]        | 显示索引，按标签索引或切片 |
| iloc[]       | 隐式索引，按位置索引或切片 |
| at[]         | 通过标签访问指定元素       |
| iat[]        | 通过位置访问指定元素       |

```python
s = pd.Series([1, 2, 3, 4, 5], index=['A', 'B', 'C', 'D', 'E'])

# index
print(s.index)

Index(['A', 'B', 'C', 'D', 'E'], dtype='object')
```

```python
# values
print(s.values)

[1 2 3 4 5]
```

```python
# shape ndim size
print(s.shape, s.ndim, s.size)

(5,) 1 5
```

```python
# name
print(s.name)

s.name = "测试"
print(s.name)

None
测试
```

```python
# loc[] 显示索引
print(s.loc['A'])

# iloc[] 隐示索引
print(s.iloc[0])

# 同时也支持切片语法
print(s.loc['A':'C'])
print(s.iloc[0:2])

1
1
A    1
B    2
C    3
Name: 测试, dtype: int64
A    1
B    2
Name: 测试, dtype: int64
```

```python
# at 注：不支持切片语法
print(s.at['A'])

# iat 注：不支持切片语法
print(s.iat[0])

1
1
```



#### 3. 访问数据的方式

```python
# 直接访问
# print(s[0]) 不建议这样使用，底层可能无法区分是取标签为0的值，还是取索引为0的值
print(s['A']) # 通过已设置的标签访问

1
```

```python
# 通过布尔索引访问
print(s[s<3])

A    1
B    2
Name: 测试, dtype: int64
```

```python
# head() 默认取前5行的数据
print(s.head())

s['F'] = 100
s['G'] = 200
print(s.head())

# tail() 默认取最后5行的数据
print(s.tail())
print("=======================")

# 通过参数指定取几行的数据
print(s.head(2))
print("=======================")
print(s.tail(3))

A    1
B    2
C    3
D    4
E    5
Name: 测试, dtype: int64
A    1
B    2
C    3
D    4
E    5
Name: 测试, dtype: int64
C      3
D      4
E      5
F    100
G    200
Name: 测试, dtype: int64
=======================
A    1
B    2
Name: 测试, dtype: int64
=======================
E      5
F    100
G    200
Name: 测试, dtype: int64
```

