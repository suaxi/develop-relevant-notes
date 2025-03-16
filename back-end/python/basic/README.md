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



## 二、运算符与表达式

### 1. 算术运算符

```python
# 算术运算符
a = 1
b = 2

# 加
print(a + b)

# 减
print(a - b)

# 乘
print(a * b)

# 除
print(a / b)

# 整除（取整）
print(a // b)

# 求模
print(a % b)

# 幂运算
print(a ** 2)

```



### 2. 赋值运算符

```python
# 赋值运算符
a = 1

# 自增
a += 1
print(a)

# 自减
a -= 1
print(a)

```



### 3. 比较运算符

```python
# 比较运算符
a = 1
b = 2

# 不等于 !=
print(a != b)

# 等于 ==
print(a == b)

# 字符串比较（比较的是每个字符的ascii码值）
print("abc" < "efg")

print("abc" == False)

```



### 4. 逻辑运算符

```python
# 逻辑运算符
# 与 and
print(1 and 2)
print(False and True)
print("abc" and "edg") # 短路运算，按字符顺序比较，当第一个字符 e > a 时，返回edg

# 或 or
print(1 or 2)
print(0 or 1)
print(False or True)

# 非 not
print(not 1)
print(not False)
print(not "")

# 三者之间的优先级关系 not > and > or
print(True or False and not False)

```



### 5. 位运算符

```python
# 位运算符
# 按位与 &
'''
101
111
---
101
'''
print(5 & 7)

# 按位或 |
'''
101
111
---
111
'''
print(5 | 7)

# 按位异或
'''
101
111
---
010
'''
print(5 ^ 7)

# 按位取反 ~
'''
101
---
010
'''
print(~5)

# 左移 右移
'''
101
---
10100
'''
print(5 << 2)

```



### 6. 成员运算符

```python
# 成员运算符
str = "这是一个字符串"
print("a" in str)

a = 1
b = 2
print(a is b)
print(a is not b)

```



## 三、条件判断

### 1. 单分支

```python
# 单分支
flag = 1

if flag == 1:
    print("标志位为1")

if True:
    print("符合判断条件")
```



### 2. 双分支

```python
# 双分支
flag = 1

if flag == 1:
    print("标志位为1")
else:
    print("标志位不为1")

if True:
    print("符合判断条件")
else:
    print("不符合判断条件")

```



### 3. 多分支

```python
# 多分支
flag = 1

if flag == 1:
    print("标志位为1")
elif flag == 2:
    print("标志位为2")
elif flag == 3:
    print("标志位为3")
else:
    print("未匹配到对应的标志位")

```



### 4. match

```python
# match
flag = 1

match flag:
    case 1:
        print("标志位为1")
    case 2:
        print("标志位为2")
    case 3:
        print("标志位为3")
    case _:
        print("未匹配到对应的标志位")

```



## 四、循环

### 1. while

```python
# while
index = 0
while index < 100:
    print(index)
    index += 1

```



### 2. for

```python
# for
for i in range(100):
    print(i)

# 求n的阶乘
result = 0
index = 1
while index <= 5:
    current_result = 1
    m = 1
    while m <= index:
        current_result = current_result * m
        m += 1
    result += current_result
    index += 1
print("5的阶乘为：%d" % result)

```



### 3. break

```python
# break
for i in range(10):
    print(i)
    if i == 3:
        print("结束循环")
        break

```



### 4. continue

```python
# continue
for i in range(10):
    if i == 5:
        continue
    print(i)

```



## 五、组合数据类型

### 1. 列表

```python
# 列表
list = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
print(list)

# 索引
print(list[1])

# 切片
print(list[::-1])

# 内置函数
print(min(list))
print(max(list))
print(len(list))
print('-------------------------')

# 遍历
for i in list:
    print(i)

for i, index in enumerate(list):
    print(i, index)

for i in range(len(list)):
    print(i, list[i])

# 常用方法
# 添加元素
list.append('a')
list.extend('extend')
print(list)
print('-------------------------')

# 插入元素
list.insert(1, 'b')
print(list)
print('-------------------------')

# 根据索引删除元素
list.pop(1)
print(list)
print('-------------------------')

# 删除指定元素
list.remove(1)
print(list)
print('-------------------------')

# 清空列表
list.clear()
print(list)

```



### 2. 元组

```python
# 元组
tuple1 = (1, 2, 3, 4, 5, 6)
print(type(tuple1))
print(tuple1)

temp_tuple = (1,)  # 元组中只有一个元素时，加一个逗号
print(type(temp_tuple))
print('-------------------------')

# 类型转换
# str ---> tuple
print(tuple('abc'))

# list ---> tuple
print(tuple([1, 2, 3]))
print('-------------------------')

# 索引
print(tuple1[1])

# 切片
print(tuple1[::-1])

# 常用方法
print(min(tuple1))
print(max(tuple1))
print(len(tuple1))
print('-------------------------')

# 遍历
for i in tuple1:
    print(i)

for index, item in enumerate(tuple1):
    print(index, item)

for i in range(len(tuple1)):
    print(tuple1[i])

```



### 3. 字符串

```python
# 字符串
str = 'sunxiaochuan,liubo'

print(min(str))
print(max(str))
print(len(str))
print('-------------------------')

# 遍历
for i in str:
    print(i)

for index, item in enumerate(str):
    print(index, item)

for i in range(len(str)):
    print(str[i])
print('-------------------------')

# 常用方法
print(str.islower())
print(str.isupper())
print(str.count('c'))
print(str.split(','))
print(str.find('l'))

```



### 4. 字典

```python
# 字典
d = {}
print(type(d))  # <class 'dict'>

# 新增值（键值对）
d['name'] = 'sunxiaochuan'
d['age'] = 33
print(d)

# 获取键值对
print(d['name'])

# 修改
d['age'] = 30
print(d)
print('-------------------------')

# 遍历
for i in d:
    print(i, d[i])

for k, v in d.items():
    print(f'{k} = {v}')

for k in d.keys():
    print(k)

for value in d.values():
    print(value)
print('-------------------------')

# 常用方法
# d.pop('name')
# print(d)

new_dictionary = d.copy()
print(new_dictionary)

name = d.get('name')
print(name)

# d.popitem()
# print(d)

d.update({'age': 50})
print(d)

```



### 5. 集合

```python
# 集合
s = set()

s = {1, 2, 3, 4, 5}
# s1 = set([1, 2, 3, 4, 5])  # list ---> set
# s2 = set((1, 2, 3, 4, 5))  # tuple ---> set
# s3 = set('12345')  # str ---> list
# s4 = set({'key1': 'value1', 'key2': 1})  # dict ---> list

# 常用方法
print(1 in s)
print(len(s))
print(min(s))
print(max(s))
# del s  # 删除集合

s.add('a')
print(s)
s.remove(1)
print(s)
s.update({'a', 'b', 'c'})
print(s)

print('-------------------------')

# 遍历
for i in s:
    print(i)

print('-------------------------')

s1 = {1, 2, 3, 'a', 88}
s2 = {'c', 'b', 3, 1, 'f'}
print(s1 & s2)  # 交集
print(s1 | s2)  # 并集
print('-------------------------')

# 去重
s3 = set([1, 1, 2, 3, 4, 5, 6, 6, 7])
print(s3)

```



## 六、异常处理

```python
# 异常处理
try:
    n = int(input('请输入被除数：'))
    i = 10 / n
    print(i)
except ZeroDivisionError as e:
    print('除数不能为0')
except:
    print('请输入正确的数字！')
else:
    print('未被except捕获')
finally:
    print('执行完毕')

print('----------------------')

# 抛出异常
try:
    n = input('请输入一个任意数字：')
    if not n.isdigit():
        raise Exception('输入的内容有误，请检查！')
    else:
        print('您输入的数字是：%d' % n)
except Exception as e:
    print(e)

```



## 七、函数

### 1. 函数

```python
# 函数
def say_hello(name):
    print('大家好，我叫', name)


say_hello('孙笑川')


# 可变参数
def test(*args):
    print(args)


test(1, 2, 3, 4)


def test_1(**kwargs):
    for key, value in kwargs.items():
        print(key, value)


test_1(**{'name': '孙笑川', 'age': 33, 'address': '成都'})

```



### 2. 变量的作用域

```python
# 变量的作用域

# 不可变数据类型
index = 1

# 可变数据类型
list = [1, 2, 3]


def test():
    global index  # 全局变量
    index = 20
    index1 = 30
    list[1] = 300


test()

# print('函数执行完后打印index1的值', index1)  # 报错 Unresolved reference 'index1'
print('函数执行完后打印index的值', index)  # 20
print('函数执行完后打印list的值', list)  # [1, 300, 3]

```



### 3. 匿名函数

```python
# 匿名函数
test = lambda x, y: x + y
print(test(1, 2))

arr = [1, 2, 3, 4]
# 映射
result = map(lambda x: x ** 2, arr)
print(list(result))

# 归约
from functools import reduce

print(reduce(lambda x, y: x * y, arr))

# 过滤
print(list(filter(lambda x: x % 2 != 0, arr)))

```

