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



## 八、模块

*此处以 random、正则、socket 为例*

### 1. random

```python
# random
import random

# 随机小数
print(random.random())

# 1 - 10 之间的随机数
print(random.randint(1, 10))

```



### 2. 正则

```python
# 正则
import re

# \d 数字
result = re.match(r'\d+', '123abc')
print(result)  # 123

# \w 数字、字母、下划线
result = re.match(r'\w+', '1a2b3c!')
print(result)  # 1a2b3c

# \s 空白字符串；\S 非空字符串
result = re.match(r'^\s+$', '1 2 3')
print(result)  # None

result = re.match(r'^\S+$', '1 abc')
print(result)  # None

# . 任意字符
result = re.match(r'.+$', 'abc 1q2')
print(result)  # abc 1q2

# [] 区间
result = re.match(r'^a[1a]', 'a123456')
print(result)  # a1

# | 或
result = re.match(r'^a|1|c$', 'a12345c')
print(result)  # a

```



### 3. socket

#### 3.1 server

```python
# socket
import socket

sk = socket.socket()
sk.bind(('127.0.0.1', 8088))
sk.listen(5)

conn, addr = sk.accept()
print(conn)
print(addr)

while True:
    accept_data = conn.recv(1024)
    print('收到客户端发送的消息：', accept_data.decode('utf-8'))
    send_data = input('请输入要回复的消息：')
    conn.send(send_data.encode('utf-8'))
```



#### 3.2 client

```python
# socket
import socket

sk = socket.socket()
sk.connect(('127.0.0.1', 8088))

while True:
    sand_data = input('请输入要发送的消息：')
    sk.send(sand_data.encode('utf-8'))
    accept_data = sk.recv(1024)
    print(accept_data.decode('utf-8'))

```



## 九、文件及IO操作

### 1. 读取文件

```python
# 读取文件
import os

# 绝对路径
# absolute_path = os.getcwd()
# file_name = absolute_path + '/test.txt'
# f = open(file_name, mode='r', encoding='utf-8')

# 相对路径
f = open('test.txt', mode='r', encoding='utf-8')
# content = f.read()  # 读取全部
# content = f.readline()  # 读取一行
content = f.readlines()  # 按行读取
print(content)

# 关闭
f.close()

```



### 2. 写文件

```python
# 写文件
f = open('test.txt', mode='w', encoding='utf-8')
f.write('大家好\n')

write_content = ['孙笑川\n', '刘波\n']
f.writelines(write_content)
f.close()

f = open('test.txt', mode='r', encoding='utf-8')
read_content = f.read()
print(read_content)
f.close()

```



### 3. 追加写

```python
# 追加写
f = open('test.txt', mode='a', encoding='utf-8')
f.write('你好\n')

content = ['带带大师兄\n', '药水哥\n']
f.writelines(content)
f.close()

```



### 4. with

```python
# with open() as f 操作文件
with open('test.txt', mode='r', encoding='utf-8') as f:
    print(f.read())
print('使用with语法糖读取文件内容完成')

```



## 十、面向对象

### 1. 类

```python
# 类
class Person(object):  # object 基类
    pass


# 创建对象（类的实例化）
student = Person()
print(type(student))  # <class '__main__.Person'>
print(isinstance(student, object))
print(isinstance(student, Person))

```



### 2. 实例属性

```python
# 实例属性
class Person(object):
    def __init__(self, name, age, address):
        self.name = name
        self.age = age
        self.address = address


student = Person('孙笑川', 33, '成都')
student.job = '主播'
print(student.__dict__)  # 获取实例的所有属性

```



### 3. 类属性

```python
# 类属性
class Person(object):
    index = 0  # 类属性

    def __init__(self, name, age, address):
        self.name = name
        self.age = age
        self.address = address
        Person.index += 1
        if age < 0 or age > 100:
            raise Exception('年龄有误，请检查！')


try:
    student = Person('孙笑川', 33, '成都')
    print(student.__dict__)
    print('第%d个用户' % Person.index)

    student1 = Person('刘波', 30, '湖北')
    print(student1.__dict__)
    print('第%d个用户' % Person.index)
except Exception as e:
    print(e)

```



### 4. 实例方法

```python
# 实例方法
class Person(object):
    index = 0  # 类属性

    def __init__(self, name, age, address):
        self.name = name
        self.age = age
        self.address = address
        Person.index += 1
        # print('第%d个用户' % Person.index)

    # 实例方法
    def say_hello(self):
        print('大家好，我是%s' % self.name)


try:
    student = Person('孙笑川', 33, '成都')
    # print(student.__dict__)
    student.say_hello()

    student1 = Person('刘波', 30, '湖北')
    # print(student1.__dict__)
except Exception as e:
    print(e)

```



### 5. 类方法

```python
# 类方法
class Person(object):
    index = 0  # 类属性

    def __init__(self, name, age, address):
        self.name = name
        self.age = age
        self.address = address
        Person.index += 1
        if age < 0 or age > 100:
            raise Exception('年龄有误，请检查！')

    # 实例方法
    def say_hello(self):
        print('大家好，我是%s' % self.name)

    # 类方法
    @classmethod
    def get_user_no(cls):
        print('第%d个用户' % cls.index)


try:
    student = Person('孙笑川', 33, '成都')
    # print(student.__dict__)
    student.say_hello()

    student1 = Person('刘波', 30, '湖北')
    # print(student1.__dict__)
    Person.get_user_no()
except Exception as e:
    print(e)

```



### 6. 静态方法

```python
# 静态方法
class Person(object):
    index = 0  # 类属性

    def __init__(self, name, age, address):
        self.name = name
        self.age = age
        self.address = address
        Person.index += 1
        if age < 0 or age > 100:
            raise Exception('年龄有误，请检查！')

    # 实例方法
    def say_hello(self):
        print('大家好，我是%s' % self.name)

    # 类方法
    @classmethod
    def get_user_no(cls):
        print('第%d个用户' % cls.index)

    # 静态方法
    @staticmethod
    def is_valid(**kwargs):
        if kwargs['token'] == '123456':
            return True
        else:
            print('前置校验失败')
            return False


try:
    check_key = {'token': '123456'}
    if Person.is_valid(**check_key):
        student = Person('孙笑川', 33, '成都')
        # print(student.__dict__)
        student.say_hello()

        student1 = Person('刘波', 30, '湖北')
        # print(student1.__dict__)
        Person.get_user_no()
    else:
        print('创建用户失败')
except Exception as e:
    print(e)

```



### 7. 继承

```python
# 继承
class Person(object):
    index = 0  # 类属性

    def __init__(self, name, age, address):
        self.name = name
        self.age = age
        self.address = address
        Person.index += 1
        if age < 0 or age > 100:
            raise Exception('年龄有误，请检查！')

    # 实例方法
    def say_hello(self):
        print('大家好，我是%s' % self.name)

    # 类方法
    @classmethod
    def get_user_no(cls):
        print('第%d个用户' % cls.index)

    # 静态方法
    @staticmethod
    def is_valid(**kwargs):
        if kwargs['token'] == '123456':
            return True
        else:
            print('前置校验失败')
            return False


class People(Person):

    # 重写父类的 say_hello 方法
    def say_hello(self):
        print('大家好，我是%s，我来自%s' % (self.name, self.address))


try:
    check_key = {'token': '123456'}
    if Person.is_valid(**check_key):
        student = Person('孙笑川', 33, '成都')
        # print(student.__dict__)
        student.say_hello()

        student1 = People('刘波', 30, '湖北')
        # print(student1.__dict__)
        student1.say_hello()
    else:
        print('创建用户失败')
except Exception as e:
    print(e)

```



### 8. 多态

```python
# 多态
class Animal(object):
    def say(self):
        print('Animal Say...')


class Dog(Animal):
    def say(self):
        print('汪汪汪...')


class Cat(Animal):
    def say(self):
        print('喵喵喵...')


animal = Animal()
animal.say()

dog = Dog()
dog.say()

cat = Cat()
cat.say()

```



### 9. 封装

```python
# 封装
class Person(object):
    def __init__(self, name, age, address):
        # self._name = name  # 受保护的变量
        self.__name = name  # 私有变量
        self.__age = age
        self.__address = address
        if not isinstance(age, int) or age < 0 or age > 100:
            raise Exception('年龄有误，请检查！')

    """
    把函数当作变量使用
    @property
    def 变量名(self):  # 获取变量 get

    @变量名.setter
    def 变量名(self, 变量名):  # 设置变量 set
    """

    @property
    def name(self):
        return self.__name

    @name.setter
    def name(self, name):
        self.__name = name

    @property
    def age(self):
        return self.__age

    @age.setter
    def age(self, age):
        if not isinstance(age, int) or age < 0 or age > 100:
            raise Exception('年龄有误，请检查！')
        else:
            self.__age = age

    @property
    def address(self):
        return self.__address

    @address.setter
    def address(self, address):
        self.__address = address


try:
    student = Person('孙笑川', 33, '成都')
    print(student.name)

    # 修改属性
    student.name = '刘波'
    print(student.__dict__)
except Exception as e:
    print(e)

```

