# Python 基础

*参考尚硅谷 mia木棉老师*



## 一、变量与简单类型

### 1. 变量

```python
# 创建
name = "孙笑川"
age = 33
print(name)

# 修改
name = "药水哥"
print(name)

# 变量的数据类型
print(type(name))
print(type(age))

```



### 2. 整数

```python
# 整数
num1 = 10
num2 = -10

print(type(num1))  # <class 'int'>

```



### 3. 浮点数

```python
# 浮点数
float_num = 1.2
print(type(float_num))  # <class 'float'>

# 浮点数四舍五入
import math

# 向上取整
print(math.ceil(float_num))  # 2

# 向下取整
print(math.floor(float_num))  # 1

```



### 4. 字符串

```python
# 字符串
str = "这是一个字符串"

str1 = '''
这是另一个字符串
多行的形式
'''

print(str)
print(str1)

# 字符串拼接（注：字符串不能与数字拼接）
print(str + str1)

# 字符串乘法
# 打印10个abc
print("abc" * 10)  # abcabcabcabcabcabcabcabcabcabc

```



### 5. 字符串索引

```python
# 字符串索引
str = "这是一个字符串，变量名是str"

# 切片 变量名[起始索引:结束索引 + 1:步长]
# 起始索引默认为0，可以省略
# 结束索引默认为-1，可以省略
# 步长默认为1，可以省略
# 切片的是一个左闭右开的区间

print(str[0:3])  # 这是一
print(str[::2])  # 这一字串变名sr

# 字符串反转
print(str[::-1])  # rts是名量变，串符字个一是这

```



### 6. 数据类型的转换

```python
# 数据类型的转换
# 字符串 ---> 整数
str = '2025'
str_2_int = int(str)
print(int(str_2_int), type(str_2_int))

# 浮点数 ---> 整数
float_num = 1.1
float_2_int = int(float_num)
print(float_2_int, type(float_2_int))

# 布尔值 ---> 整数
bool_value = True
print(int(bool_value))

# 字符串 ---> 浮点数
str1 = "1.23"
print(float(str1))

# 字符串 ---> 布尔值
str2 = "1.234"
print(bool(str2))
print(bool(""))

```

