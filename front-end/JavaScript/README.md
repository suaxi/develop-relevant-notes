# JavaScript

*参照 B站李立超老师2022年 JavaScript 课程*

## 一、入门

### 1. 基本语法

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>基本语法</title>
  </head>
  <body>
    <script>
      /*
        1.多行注释
        */

      //2.单行注释

      //3.js严格区分大小写

      //4.在js中，多个空格和换行会被忽略

      //5.js中的每条语句都应该以分号结尾（如果没写，解释器会自动添加）
    </script>
  </body>
</html>
```



### 2. 字面量和变量

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>字面量和变量</title>
</head>
<body>
    <script>
        /*
        字面量：表示字面所代表的意思（在js中，所有的字面量都可以直接使用）
        
        变量：
            - 可以用来“存储”字面量
            - 存储的字面量可以修改
        */

        /* 
        变量的使用：
            - 声明变量：let 变量名 / var 变量名
            - 变量的赋值： a = 10
            - 使用时，声明和赋值同时进行 let a = 10
        */
       let a = 10
       console.log(a)
    </script>
</body>
</html>
```



### 3. 变量的内存

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body>
    <script>
      /*
        变量中并不存储任何值，而是存储值的内存地址
        */
      let a = "孙笑川";
    </script>
  </body>
</html>

```



### 4. 常量

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>常量</title>
</head>
<body>
    <script>
        /*
        在js中，用const声明常量
        常量只能赋值一次，重复赋值会报错
        除常规常量外，一些对象类型的数据也会被声明为常量
        */
       const PI = 3.1415926
       console.log(PI);
    </script>
</body>
</html>
```



### 5.标识符

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>标识符</title>
  </head>
  <body>
    <script>
        /*
        在js中，所有可以自主命名的内容，都可以称为是一个标识符
        例：变量名、函数名、类名......
        标识符命名规范：
            1.只能含有字母、数字、下划线、$，且不能以数字开头
            2.不能是js中的关键字、保留字
            3.一般使用小驼峰命名，类名使用大驼峰，常量使用全大写 + 下划线
        */

        let a = 10
        let a1 = 10
        let $a1_ = 10
        //let ##a = 10
    </script>
  </body>
</html>

```



## 二、数据类型

### 1. 数值

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>数值</title>
  </head>
  <body>
    <script>
        /*
        数值（Number）
            - 在js中，所有的整数和浮点数都是Number类型
            - js中的数值并不是无限大的，当超过一定范围后，会显示近似值
            - Infinity是一个特殊的数值，表示无穷大
            - 进行计算时，需注意精度的问题
        */

        let a = 10
        a = 100
        a = 99999 * 999999
        a= Infinity
        a = 1 - 'a' //NaN（Not a number）
        console.log(a)

        /*
        大整数（BigInt）
            - 用来表示比较大的整数
            - 大整数使用n结尾，它可以表示的数字范围是无限大
        */

        /*
        其他进制的数字
            - 二进制 0b
            - 八进制 0o
            - 十六进制 0x
        */
       let b
       b = 0b1010
       b = 0o10
       b = 0xff
       console.log(b) //打印时都是十进制
    </script>
  </body>
</html>

```



### 2. 类型检查

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>类型检查</title>
  </head>
  <body>
    <script>
        let a = 10
        let b = 10n
        console.log(a)
        console.log(b)

        //typeof检查的是变量的值的类型，而不是变量的类型，在js中，变量是没有类型的
        //typeof返回的结果是一个字符串
        console.log(typeof a)
        console.log(typeof b)
    </script>
  </body>
</html>

```



### 3. 字符串

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>字符串</title>
  </head>
  <body>
    <script>
        /*
        字符串
            - 在js中使用单引号或双引号表示字符串
            - 转义字符  
                \ 做斜杠
                \t 制表符
                \n 换行
            - 模板字符串
                使用 ` 表示，可以跨行，引号不能跨行
        */

        let a = '孙笑川'
        a = '这是一个"字符串"'
        a = "这是一个\"字符串\""
        console.log(a)

        let b = `我的名字是：
        孙笑川`
        console.log(b)

        let name = '孙笑川'
        let str = `我的名字是${name}`
        console.log(str)
    </script>
  </body>
</html>

```



### 4. 其他的数据类型

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>其他的数据类型</title>
  </head>
  <body>
    <script>
        /*
        布尔值（Boolean）
            - 用于逻辑判断
            - 只有true、false两个值
        */
       let flag = false
       console.log(flag)
       console.log(typeof flag)

       /*
       空值（Null）
            - 表示空对象
            - 空值只有一个，即null
            - 使用typeof检查空值时，会返回"object"，这其实是js发展历史中的一个bug，typeof无法检查
       */
      let a = null
      console.log(a)
      console.log(typeof a)

      /*
      未定义（Undefined）
            - 当声明一个变量没有赋值时，它的值就是undefined
            - 使用typeof检查时，返回的是"undefined"
      */
     //let b
     let b = undefined
     console.log(b)

     /*
     符号（Symbol）
        - 用来创建一个唯一的标识符
        - 使用typeof检查时，返回的是"Symbol"
     */
    let c = Symbol() //调用Symbol函数创建了一个符号
    console.log(c)

    //这七种数据类型的原始值不可变（即创建之后不可修改）
    </script>
  </body>
</html>

```



### 5. 类型转换 - 字符串

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>类型转换-字符串</title>
  </head>
  <body>
    <script>
      /*
        将一种数据类型转换为其他类型
        */

      let a = 10
      a = true
      a = 100n
      //a = null
      console.log(typeof a, a)

      /*
      1.调用toString()方法，注意：null和undefined没有该方法
      */
      a = a.toString()
      console.log(typeof a, a)
      
      /*
      2.调用String()函数
        对于拥有toString()方法的值调用String()函数时，实际上就是在调用toString()
        对于null、undefined等值，则是直接进行转换
      */
      let b = 10
      b = null
      b = undefined
      console.log(typeof b, b)
      
      b = String(b)
      console.log(typeof b, b)
    </script>
  </body>
</html>

```



### 6. 类型转换 - 数值

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>类型转换-数值</title>
  </head>
  <body>
    <script>
        /*
        1.使用Number()函数
            - 字符串：
                如果字符串是一个合法的数字，则会自动转换为对应的数值
                反之，则是 Nan
                如果字符串是空串或纯空格的字符串，转换后则是0
            - 布尔值：
                true转换为1，false转换为0
            - null 转换为0
            - undefined 转换为Nan
        
        2.专门将字符串转换为数值的两个方法
            - parseInt() 转换为整数
                解析时会自左向右逐一读取字符串，直到读取完字符串中所有有效的整数
                有些时候可以使用该方法对数值进行取整（不推荐）
            - parseFloat() 转换为浮点数
                解析时会自左向右逐一读取字符串，直到读取完字符串中所有有效的浮点数（小数）
        */
       let a = '123'
       a = 'abc' //Nan
       a = '11px' //Nan
       a = '' //0
       a = '   ' //0
       a = true //1
       a = false //0
       a = null //0
       a = undefined //Nan
       console.log(typeof a, a)

       a = Number(a)
       console.log(typeof a, a)
       
       
       let b = '123'
       b = '123px' //123
       b = 456 //当传的值不是字符串而是数值时，parseInt()会先将456转换为'456'，再进行整数转换
       console.log(typeof b, b)

       b = parseInt(b)
       console.log(typeof b, b)
       
       b = parseFloat(b)
       console.log(typeof b, b)
    </script>
  </body>
</html>

```



### 7. 类型转换 - 布尔值

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>类型转换-布尔值</title>
  </head>
  <body>
    <script>
        /*
        1.使用Boolean()函数将其他类型转换为布尔值
            - 数字：除了0和Nan转换后是false，其余都是true
            - 字符串：空串转换为false，其余是true
            - null、undefined转换为false
            - 对象会转换为true
        
        所有表示空的没有错误的值都会转换为false，即0,Nan,空串,null,undefined
        */
       let a = 1 //true
       a = -1 //true
       a = 0 //false
       a = NaN //false
       a = Infinity //true

       a = 'abc' //true
       a = 'true' //true
       a = 'false' //true
       a = '' //false
       a = ' ' //true
       a = null //false
       a = undefined //false
       console.log(typeof a, a)
       
       a = Boolean(a)
       console.log(typeof a, a)
    </script>
  </body>
</html>

```



## 三、运算符

### 1. 运算符

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>运算符</title>
  </head>
  <body>
    <script>
        /*
        运算符（操作符）：可以对一个或多个操作数进行运算
            - 算术运算符：
                 + 加
                 - 减
                 * 乘
                 / 除
                 ** 幂运算
                 % 取模（两个数相除取余数）
            注：算数运算时，除了字符串的加法，其他运算的操作数是非数值时，都会先进行类型转换再运算
        */

        let a = 1 + 1
        a = 10 -5
        a = 10 * 5
        a = 10 / 5
        a = 10 / 0 //Infinity
        a = 10 ** 4
        a = 16 ** .5 //开平方
        a = 10 % 2

        /*
        js是一门弱类型语言，当进行运算时会通过自动转换类型来完成运算
        */
        a = 10 - '5' // 10 -5
        a = 10 + true // 10 + 1
        a = 5 + null //5 + 0
        a = 6 - undefined // 6 - Nan = Nan

        /*
        当任意一个值和字符串做加法运算时，会先将其他值转换为字符串，再进行字符串的拼接操作
        */
        a = 'false' + true //'falsetrue'
        a = 1 + '2' //'12'
        console.log(a)
        

        //可以通过任意类型+空串的方法来将其转换为字符串，原理同String()函数
        let b = 1
        b = b + ''
        console.log(typeof b, b)
        
    </script>
  </body>
</html>

```



### 2. 赋值运算符

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>赋值运算符</title>
  </head>
  <body>
    <script>
        /*
        赋值运算符用来将一个值赋值给一个变量
        */
       let a = 10
       a += 10 //等价于 a = a + 10

       /*
       ??= 空赋值，即：只有变量的值为null或undefined时才会进行赋值操作
       */
       let b = null
       b ??= 100
       console.log(b) //100
    
       let c = 100
       c ??= 123
       console.log(c) //100
       
    </script>
  </body>
</html>

```



### 3. 一元±

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>一元±</title>
  </head>
  <body>
    <script>
        /*
        一元的±
            + 正号
            - 负号（对数值进行符号位取反）
        */
       let a = 1
       a = +a //1
       a = -a //-1
       console.log(a)
       
       //当我们对非数值类型进行正负运算时，会先将其转换为数值再进行运算
       let b = '123'
       //b = Number(b)
       b = +b
       console.log(typeof b, b)
       
    </script>
  </body>
</html>

```



### 4. 自增自减

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>自增和自减</title>
  </head>
  <body>
    <script>
        /*
        自增：
            - 前自增 ++a
            - 后自增 a++
        */
       let a = 1

       //先用后加
       //let b = a++
       //console.log('a++ = ', b) //1
       //console.log(a) //2

       //先加后用
       let b = ++a
       console.log('a++ = ', b) //2
       console.log(a) //2
       
       let c = 2
       let result = c++ + ++c + c //2 + 4 + 4
       console.log(result)
       
       /*
       自减：（同理自增）
           - 前自减
           - 后自减
       */
    </script>
  </body>
</html>

```



### 5. 逻辑运算符

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>逻辑运算符</title>
  </head>
  <body>
    <script>
        /*
        ! 逻辑非：
            - 可以对一个布尔值进行取反操作
            - 如果对一个非布尔值进行取反，js会先将其转换为布尔值再取反
            - 类型转换：
                转换为字符串：
                    显示：String()
                    隐示：+ ''
                转换为数值：
                    显示：Number()
                    隐示：+
                转换为布尔值：
                    显示：Boolean()
                    隐示：!!（取反再取反）
        */
       let a = true
       console.log(!a)
       
       let b = 123
       console.log(!!b)

       /*
       && 逻辑与：
            - 可以对两个值进行与运算
            - 当&&左右都为true时，返回true，否则返回false
            - 与运算是短路运算，即：第一个值为false时，则直接返回false，不会再看第二个值
            - 与运算是找false的运算，只要找到了第一个false，就直接返回false
       */
       let c = true && true
       console.log(c) //true

       let d = false && true
       console.log(d) //false
       
       //true && alert('逻辑与运算') //alert会执行
       //false && alert('逻辑与运算')

       /*
       对于非布尔值进行与运算，js会先将其转为布尔值，再进行与运算：
            - 最终返回结果是原值
            - 如果第一个值为false，则返回第一个值
            - 如果第一个值为true，则返回第二个值
       */
       console.log(1 && 2) //2
       console.log(1 && 0) //0
       console.log(0 && Nan) //0
       
       /*
       || 逻辑或：
            - 可以对两个值进行或运算
            - 当||左右只要有一个true时，则返回true，否则返回false
            - 或运算也是短路运算，如果第一个值为true，则不看第二个值
            - 或运算是找true的运算，只要找到了第一个true，就直接返回true
       */

       console.log(true || false) //true
       console.log(false || true) //true
       console.log(true || true) //true
       console.log(false || false) //false
       
       //false || alert('逻辑或运算') //alert会执行
       //true || alert('逻辑或运算') //第一个值为true，alert不会执行

       //此处与逻辑与运算同理
       console.log(1 || 2) // 1
       console.log('abc' || null) // 'abc'
       console.log(Nan || 1) // 1
       
    </script>
  </body>
</html>

```



### 6. 关系运算符

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>关系运算符</title>
  </head>
  <body>
    <script>
        /*
        用来检查两个值之间的关系是否成立，成立 true，不成立 false
        > 大于
        < 小于
        >= 大于等于
        <= 小于等于

        注：
            - 当对非数值进行关系运算时，会先将其转换（隐式转换）为数值，再进行比较
            - 当两端都是字符串时，js不会进行数值转换，而是逐位比较二者的Unicode编码
              两个字符串长度不相同时，只需比较第一位（即比较一位就能出结果），利用该特性，可以对字符串进行按字母顺序排序
        */
       console.log('2 > 1', 2 > 1)
       console.log('2 > 2', 2 > 2)
       console.log('2 >= 2', 2 >= 2)

       console.log('5 >= \'10\'', 5 >= '10')
       console.log('1 >= true', 1 <= true)
       
       console.log('\'a\' > \'b\'', 'a' > 'b') //false
       console.log('\'12\' > \'2\'', '12' > '2') //false
       
    </script>
  </body>
</html>

```



### 7. 相等运算符

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>相等运算符</title>
  </head>
  <body>
    <script>
        /*
        ==：
            - 相等运算符
            - 当比较两个不同类型的值时，会先将其转换为相同的类型（通常转换为数值）再进行比较
            - null和undefined进行比较时，会返回true
            - Nan不和任何值相等，包括它自身
        ===：
            - 全等运算符
            - 两个不同类型的值比较时，不会自动进行类型转换
        
        !=：
            - 会自动进行类型转换
        
        !===：
            - 不会自动进行类型转换
        */

        console.log('1 == 2', 1 == 2) //false
        console.log('1 == \'1\'', 1 == '1') //true
        console.log('true == 1', true == '1') //true
        console.log('nul == undefined', null == undefined) //true
        console.log('NaN == NaN', NaN == NaN) //false
        
        console.log('1 === \'1\'', 1 === '1') //false
        console.log('null === undefined', null === undefined) //false
        
        console.log('1 != \'1\'', 1 != '1') //false
        console.log('1 !== \'1\'', 1 !== '1') //false
    </script>
  </body>
</html>

```



### 8. 条件运算符

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>条件运算符</title>
  </head>
  <body>
    <script>
        /*
        条件运算符（三目运算）：
            条件表达式 ? 表达式1 : 表达式2
            执行顺序：当条件表达式成立（为true）时，执行表达式1，结束；反之执行表达式2，结束
        */

        let a = 1
        let b = 20
        let result = a > b ? a : b
        console.log(result)
        
    </script>
  </body>
</html>

```



### 9. 运算符的优先级

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>运算符的优先级</title>
  </head>
  <body>
    <script>
        /*
        js中的运算优先级与数学运算类似，加减乘除
        mdn文档：https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Operators/Operator_precedence#%E6%B1%87%E6%80%BB%E8%A1%A8
        该文档的表格中，越靠前的，优先级越高
        */
    </script>
  </body>
</html>

```



## 四、流程控制

### 1. 代码块

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>代码块</title>
</head>
<body>
    <script>
        /*
        使用 {} 来创建代码块，可以用来将代码分组
            - 同一个代码块中的代码，要么都执行，要么都不执行
        
        let和var
            - 使用let声明的变量具有块作用域，无法在代码块之外访问到
            - 使用var声明的变量不具有作用域
        */

        {
            let a = 1
        }
        // console.log(a) //Uncaught ReferenceError: a is not defined
        

        {
            var b = 3
        }
        console.log(b) //3
        
    </script>
</body>
</html>
```



### 2. if语句

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>if语句</title>
  </head>
  <body>
    <script>
        /*
        流程控制语句可以用来改变程序执行的顺序
        1.条件判断语句
        2.条件分支语句
        3.循环语句
        */

        /*
        if语句
            - 语法：
                if (条件表达式) {
                    语句
                }
            - 执行流程：
                if语句在执行前会先对if后的条件表达式进行求值判断，如果为true，则执行if后的语句，反之不执行
        */
       let a = 1
       let b = 2
       if (a > b) {
        console.log('条件表达式成立！')
       }

       //if语句只会空值紧随其后的那一行代码，如果希望控制多行代码，使用{}代码块
       //if (a > b)
       //     console.log('a > b 成立')
       //     console.log('a和b比较大小')
       
       //一般情况下，if语句建议写完整写法
       if (a > b) {
            console.log('a > b 成立')
            console.log('a和b比较大小')
        }
        
        //如果表达式中的值不是布尔值，会先进行类型转换，再做判断
        if (100) {
            console.log('if (100) 表达式为真')
            
        }
    </script>
  </body>
</html>

```



### 3. if-else语句

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>if-else语句</title>
  </head>
  <body>
    <script>
        /*
        if-else语句：
            - 语法：
                if (条件表达式) {
                    语句
                } else {
                    语句
                }
            - 执行流程：
                if语句在执行前会先对if后的条件表达式进行求值判断
                如果为true，则执行if后的语句
                反之执行else中的语句
        */

        let score = 90
        if (score >= 90) {
            console.log('成绩优秀！')
        } else {
            console.log('成绩良好！')
            
        }

        /*
        if-else if-else语句：
            - 语法：
                if (条件表达式1) {
                    语句
                } else if (条件表达式2) {
                    语句
                } else if (条件表达式3) {
                    语句
                } else if (条件表达式4) {
                    语句
                    ......
                } else {
                    语句
                }
            - 执行流程：
                - 自上而下的依次对if后的条件表达式进行求职判断
                - 如果为true，则执行当前if后的语句，执行完则语句结束
                - 反之，会继续向下判断，直至找到true为止
                - 如果所有表达式都为false，则执行else中的语句
            - 注：不论有多少个if-else if-else，该语句中只会有一个代码块被执行
        */
       let age = 66
       if (age >= 90) {
        console.log('耄耋');
       } else if (age >= 80 && age < 90) {
        console.log('寿伞')
       } else if (age >= 70 && age < 80) {
        console.log('古稀')
       } else if (age >= 60 && age < 70) {
        console.log('还历')
       } else if (age >= 50 && age < 60) {
        console.log('知天命')
       } else if (age >= 40 && age < 50) {
        console.log('不惑')
       } else if (age >= 30 && age < 40) {
        console.log('而立')
       } else if (age >= 20 && age < 30) {
        console.log('加冠')
       } else {
        console.log('......')
       }
    </script>
  </body>
</html>

```



### 4. switch语句

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>switch语句</title>
  </head>
  <body>
    <script>
        /*
        switch语句：
            - 语法：
                switch (表达式) {
                    case 表达式:
                        ...
                    case 表达式:
                        ...
                    case 表达式:
                        ...
                }
            - 执行的顺序：
                在执行时会依次将switch后的表达式和case后的表达式进行全等判断
                    如果比较结果为true，则执行当前这个case后的代码
                    如果比较结果为false，则继续比较其他case后的表达式，直到找到true为止
            注：需注意case穿透问题，不要忘了写break
        */

        //根据用户输入的数字显示中文
        let num = +prompt('请输入一个数字')
        switch (num) {
            case 1:
                alert('一')
                break
            case 2:
                alert('二')
                break
            case 3:
                alert('三')
                break
            default:
                alert('暂时不能判断！')
                break
        }
    </script>
  </body>
</html>

```



### 5. 循环语句

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>循环语句</title>
  </head>
  <body>
    <script>
        /*
        循环语句：
            - 可以使指定的代码反复执行
            - js中的三种循环语句：while语句，do-while语句，for语句
            - while语句：
                - 语法：
                    while (条件表达式) {
                        ......
                    }
                - 执行流程：
                    先对条件表达式进行判断
                    如果为true，则执行循环体，执行完之后继续对条件表达式进行判断
                    如果为true则重复上述步骤，直到条件表达式为false时停止，此时循环结束
        */

        /*
        一般情况下，编写循环时需要三个条件：
        1. 初始表达式
        2. 条件表达式
        3. 更新表达式
        */
        //let a = 1
        //while (a < 10) {
        //    console.log('执行循环体', a)
        //    a++
        //}
        //console.log('循环结束')

        let b = 1
        while (true) {
            if (b > 5) {
                break
            }
            console.log('执行循环体', b)
            b++
        }
        console.log('循环结束')
        
    </script>
  </body>
</html>

```



### 6. do-while循环

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>do-while循环</title>
  </head>
  <body>
    <script>
        /*
        do-while循环:
            - 语法：
                do {
                    ......
                } while (条件表达式)
            - 执行顺序：
                先执行do后的循环体，再进行while后的条件表达式判断
                如果为true，则重复上述步骤
                如果为false，则循环终止
            - 与while的区别：
                while 先判断再执行
                do-while 先执行再判断

                实质区别：do-while可以确保循环至少执行一次
        */
       let a = 1
       do {
        console.log('执行循环体', a)
        a++
       } while (a < 5)
       console.log('循环执行结束')
    </script>
  </body>
</html>

```



### 7. for循环

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>for循环</title>
  </head>
  <body>
    <script>
        /*
        for循环：
            - 语法：
                for (初始化表达式; 条件表达式; 更新表达式) {
                    ......
                }
            - 执行顺序：
                1. 初始化变量
                2. 执行条件表达式，判断循环是否执行
                3. 条件表达式如果为true，则执行循环语句
                4. 更新表达式
                5. 重复步骤2，直到条件表达式的值为false
            - 注：
                初始化表达式在整个循环中，只会执行依次
                for循环中的三个表达式都可以省略（此时为死循环，没有循环结束节点）
                使用let初始化的变量是局部变量
        */
       for (let i = 0; i < 10; i++) {
        console.log(`第${i}次循环`)
       }

       //创建死循环的方式
       //while (true) {}
       //for (;;) {}
    </script>
  </body>
</html>

```



### 8. 循环嵌套

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>循环嵌套</title>
  </head>
  <body>
    <script>
      /*
        循环嵌套：在一个循环中嵌套其他的循环
        */
      for (let i = 0; i < 5; i++) {
        for (let j = 0; j < 5; j++) {
            if (j <= i) {
                document.write("*&nbsp;&nbsp;")
            }
        }
        document.write("<br>");
      }

      /*
        输出结果：
        *  
        *  *  
        *  *  *  
        *  *  *  *  
        *  *  *  *  *
      */
    </script>
  </body>
</html>

```



### 9. break和continue

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>break和continue</title>
</head>
<body>
    <script>
        /*
        break:
            - 用来终止switch和循环语句
            - 终止离他最近的循环
        */
       for (let i = 0; i < 5; i++) {
        console.log('外层循环', i)
        for (let j = 0; j < 5; j++) {
            console.log('内层循环', j)
            if (j === 2) {
                break
            }
        }
        console.log('--------------------')
       }

       /*
       continue：
            用来跳过当前的这次循环
       */
      for (let i = 0; i < 5; i++) {
        if (i === 3) {
            continue
        }
        console.log('continue循环测试', i)
      }
    </script>
</body>
</html>
```



## 五、对象

### 1. 对象

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>对象</title>
</head>
<body>
    <script>
        /*
        对象：
            - 是js中的一种复合数据类型，相当于是一个容器，可以存储不同类型的数据
        */

        let people = {
            name: '孙笑川',
            age: 33
        }
        console.log(people)
        
        /*
        向对象中添加属性：
            对象.属性名 = 属性值
        读取对象中的属性：
            对象.属性名
        */
       people.classNo = 1

       console.log('添加属性', people)

       console.log('你好，我叫', people.name)
       

       //修改属性
       people.name = '药水哥'
       console.log(people.name)

       //删除属性
       delete people.classNo
       console.log('删除属性', people)
       
       
    </script>
</body>
</html>
```



### 2. 对象的属性

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>对象的属性</title>
</head>
<body>
    <script>
        /*
        属性名：
            - 通常是一个字符串
            - 一般情况下属性名不建议取得太特殊，如关键字，特殊符号等等
            - 可以使用符号作为属性名
                使用symbol添加的属性一般是不希望被外界访问的属性
            - 使用 [] 操作属性时，可以使用变量
        属性值：
            - 对象的属性值可以是任意的数据类型
        */

        let people = Object()
        people.name = '孙笑川'
        people.age = 33
        people["classNo"] = 1
        console.log(people)

        let customSymbol = Symbol()
        people[customSymbol] = '使用symbol添加的属性'
        console.log('使用符号作为属性名', people)
        console.log('Symbol()', people[customSymbol])
        
        let variableName = 'job'
        people[variableName] = '主播' //等价于people["job"] = '主播'
        console.log(people[variableName]) //主播

        console.log('---------------------------')
        
        let animal = Object()
        animal.name = '狗子'
        animal.age = 1
        animal.info = Object()
        animal.info.address = '西伯利亚'
        console.log(animal)
        
        /*
        in 运算符：
            - 用来检查对象中是否含有某个属性
            - 语法： 属性名 in 对象
            - 返回结果为布尔值
        */
       console.log('animal对象有name这个属性吗?', 'name' in animal)
       
    </script>
</body>
</html>
```



### 3. 对象的字面量

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>对象的字面量</title>
  </head>
  <body>
    <script>
      /*
        对象字面量：
            - 可以直接使用{}来创建对象
            - 使用{}创建的对象，可以直接向里面添加属性
        语法：
            {
                属性名: 属性值
                [属性名]: 属性值
            }
      */
     let a = Object()
     let b = {
        name: '孙笑川',
        ["age"]: 33
     }
     console.log(a)
     console.log(b)
     
    </script>
  </body>
</html>

```



### 4. 枚举属性

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>枚举属性</title>
  </head>
  <body>
    <script>
        /*
        枚举属性：将对象中的所有属性全部获取
        for in语句：
            - 语法：
                for (let propName in [obejct]) {
                    ......
                }
            - 对象有几个属性，for-in的循环体就会执行几次
              每执行一次，就会将一个属性名赋值给我们所定义的变量propName(变量名根据具体场景来命名)
            - 注：并不是所有属性都额可以枚举，如关键字、符号
        */

        let people = {
            name: '孙笑川',
            age: 33,
            address: '四川成都',
            [Symbol()]: '符号添加的属性原则上不希望被访问到'
        }

        for (let propName in people) {
            console.log(propName, people[propName])
        }
    </script>
  </body>
</html>

```



### 5. 可变类型

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>可变类型</title>
</head>
<body>
    <script>
        /*
        - 原始值属于不可变类型（一旦创建就无法修改）
        - 在内存中不会去重复的创建原始值
        */
       let a = 1
       let b = 2
       console.log(a)
       console.log(b)

       console.log('-----------------------------')
       
       /*
       - 对象属于可变类型
       - 对象创建完成后，可以继续添加、删除属性
       - 注：如果两个变量同时指向同一个对象，通过一个变量修改其中的属性时，另一个变量也会产生影响
       */
      let obj = {
        name: '孙笑川',
        age: 33
      }

      let p1 = obj
      let p2 = obj
      console.log(p1)
      console.log(p2)
      console.log(p1 == p2);
      
      console.log('-----------------------------')
      
      p1.name = '药水哥'
      console.log(p1)
      console.log(p2)
    </script>
</body>
</html>
```



### 6. 变量和对象

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>变量和对象</title>
  </head>
  <body>
    <script>
        /*
        修改对象：
            - 修改对象时，如果有其他变量指向该对象，则修改后所有的变量都会受影响
        修改变量：
            - 只会影响当前的变量
        */
       let obj = {
        name: '孙笑川',
        age: 33
       }

       let p1 = obj
       let p2 = obj
       console.log(p1)
       console.log(p2)

       console.log('-----------------------')
       
       p2 = null //修改变量
       console.log(p2)

       console.log('-----------------------')

       p2 = {}
       p2.name = '药水哥' //修改对象
       console.log(p2)
       
       /*
       在使用变量存储对象时，容易遇到修改变量，而影响变量指向的对象的问题
       所以通常情况下，声明存储对象的变量时会使用 const 关键字
       注：const 只是禁止变量被重新赋值，而不会影响变量指向的对象中的属性的修改
       */
    </script>
  </body>
</html>

```



### 7. 方法

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>方法</title>
</head>
<body>
    <script>
        /*
        方法：
            - 当对象的其中一个属性指向一个函数时，该函数就称为该对象的方法
        */

        let obj = {}

        obj.name = '孙笑川'
        obj.age = 33
        obj.hobby = function() {
            console.log('抽象艺术直播！')
        }

        obj.hobby()
    </script>
</body>
</html>
```



## 六、函数

### 1. 函数

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>函数</title>
  </head>
  <body>
    <script>
        /*
        函数（Function）：
            - 函数也是一个对象
            - 具有其他对象所有的功能
            - 语法：
                function 函数名() {
                    ......
                }
            - 调用函数：
                函数对象()
            - 使用typeof检查函数对象时返回的是function

        */

        function test() {
            console.log('这是一个函数')
            
        }

        //console.log(test)
        
        //调用函数
        test()

        console.log('typeof test()', typeof test)
        
    </script>
  </body>
</html>

```



### 2. 函数的创建方式

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>函数的创建方式</title>
  </head>
  <body>
    <script>
        /*
        函数的创建方式：
            - 函数声明：
                function 函数名() {
                    ......
                }
            - 函数表达式：
                const 变量 = function() {
                    ......
                }
            - 箭头函数：
                () => {
                    ...... 
                }
        */

        //方式一：方式一：函数声明
        function test() {
            console.log('方式一：函数声明')
            
        }
        test()

        //方式二：函数表达式
        const test1 = function() {
            console.log('方式二：函数表达式')
            
        }
        test1()

        //方式三：箭头函数
        const test2 = () => {
            console.log('方式三：箭头函数')
            
        }
        test2()
    </script>
  </body>
</html>

```



### 3. 参数

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>参数</title>
  </head>
  <body>
    <script>
        /*
        形式参数：
            - 在定义函数时，可以在函数中指定数量不等的形式参数（形参）
            - 在函数中定义形参，等价于在函数内部声明了对应的变量，但没有赋值
        实际参数：
            - 在调用函数时，可以在函数的()中传递数量不等的参数（实际参数）
            - 实参会赋值给对应的形参
            - 注：
                如果实参多于形参，则多余的实参不会使用
                如果实参少于形参，则多于的形参值为undefined
        参数的类型：
            - 在js中不会检查参数的类型，可以传递任意类型的值作为参数
        
        1. 函数声明：
            function 函数名([参数]) {
                ......
            }
        2. 函数表达式：
            const 变量名 = function 函数名([参数]) {
                ......
            }
        3. 箭头函数：
            ([参数]) => {
                ......
            }
        */
       function sum(a, b) {
        console.log('两个数求和', a + b)
       }

       sum(1, 2)
    </script>
  </body>
</html>

```



### 4. 箭头函数的参数

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>箭头函数的参数</title>
  </head>
  <body>
    <script>
      /*
      
      */

      const test = (a, b) => {
        console.log('参数a:', a)
        console.log('参数b:', b)
      }

      //当箭头函数只有一个参数时，可以省略括号
      const test1 = a => {
        console.log('这个箭头函数只有一个参数：', a)
      }

      //定义参数时，可以指定默认值
      const test2 = (a = 1, b = 2) => {
        console.log('a:', a)
        console.log('b:', b)
      }

      test2(30) //此时在控制台打印的值为 30,2
    </script>
  </body>
</html>

```



### 5. 对象作为参数

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>对象作为参数</title>
  </head>
  <body>
    <script>
        function test(a) {

            a = {} //修改变量时，只会影响当前的变量

            a.name = '药水哥' //修改对象时，如果有其他变量指向该对象，则所有指向该对象的变量都会受到影响

            console.log('a', a)
        }

        let obj = {
            name: '孙笑川'
        }

        test(obj)

        console.log('obj', obj)
        
    </script>
  </body>
</html>

```



### 6. 函数作为参数

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>函数作为参数</title>
  </head>
  <body>
    <script>
        /*
        在js中，函数也是一个对象
        */
       function test(a) {
        console.log('a', a)
       }

       function test1(a) {
        console.log('这是一个名叫test1()的函数')
       }

       test(test1)

       test(() => console.log('这是一个箭头函数'))
    </script>
  </body>
</html>

```



### 7. 函数的返回值

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>函数的返回值</title>
  </head>
  <body>
    <script>
        function test(a) {
            //在函数中，可以使用return关键字来指定函数的返回值
            return a
        }

        //let result = test('孙笑川')
        //console.log(result)
        
        console.log(test('孙笑川'))
        
    </script>
  </body>
</html>

```



### 8. 箭头函数的返回值

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>箭头函数的返回值</title>
  </head>
  <body>
    <script>
        //完整写法
        //const test = (a, b) => {
        //    return a + b
        //}

        //简写（函数体中只有一行代码的情况下）
        const test = (a, b) => a + b

        console.log(test(1, 2))
        
        //注：如果在箭头函数后设置对象字面量为返回值时，对象字面量必须用()括起来，不然js解释器解析不了它是一个对象还是一个函数
        const test1 = () => ({name: '孙笑川'})
        console.log('test1', test1())
        
    </script>
  </body>
</html>

```



### 9. 作用域

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>作用域</title>
  </head>
  <body>
    <script>
        /*
        作用域：
            - 指一个变量的可见区域
            - 全局作用域：
                - 在网页运行时创建，在网页关闭时销毁
                - 所有直接编写到script标签中的代码都位于全局作用域中
                - 可以在任意位置访问
            - 局部作用域：
                - 块作用域：
                    - 在代码块执行时创建，执行完毕后销毁
                    - 在块作用域中声明的变量是局部变量，只能在块内部访问
        */

        let name = '孙笑川'

        console.log('全局作用域 name', name)
        

        {
            let obj = {
                name: '药水哥'
            }

            let a = 1

            console.log('局部作用域-块作用域 obj', obj)

            {
                console.log('局部作用域-块作用域 a', a)
                
            }
        }

    </script>
  </body>
</html>

```



### 10. 函数作用域

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>函数作用域</title>
  </head>
  <body>
    <script>
        /*
        函数作用域：
            - 也是一种局部作用域
            - 在函数调用时创建，调用结束后销毁
            - 函数每调用一次都会产生一个新的作用域
            - 在函数中定义的变量是局部变量
        */

        function test() {
            let a = 1
        }

        //test()
        //test()
        test()
    </script>
  </body>
</html>

```



### 11. 作用域链

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>作用域链</title>
  </head>
  <body>
    <script>
        /*
        作用域链：
            - 当使用一个变量时，js解释器会优先在当前作用域中寻找变量
            - 如果找到则直接使用，如果没找到继续往上一层作用域寻找
            - 当全局作用域中也没找到时，报错xxx is not defined
            - 变量在函数中使用时同理
        */

        {
            let a = 1
            {
                let a = 2
                {
                    let a = 3
                    console.log(a)
                }
            }
        }
    </script>
  </body>
</html>

```



### 12. window对象

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>window对象</title>
  </head>
  <body>
    <script>
        /*
        window对象：
            - 浏览器为我们提供了一个window对象，可以直接访问
            - 它代表的是浏览器窗口，通过该对象可以对浏览器窗口进行各种操作
            - 它还负责存储js中的内置对象和浏览器的宿主对象
            - window对象中的属性可以通过window对象访问，也可以直接访问
            - 函数可以认为是window对象的方法
        */

        //alert('孙笑川')
        //window.alert('孙笑川')
        //window.console.log('孙笑川')

        window.a = 10

        console.log('a', a) //向window对象中添加的属性会自动成为全局变量
        
        /*
        var 用来声明变量，作用和let相同，但var不具备块作用域
            - 在全局中使用var声明的变量，都会作为window对象的属性保存
            - 使用function声明的函数，都会作为window的方法保存
        */

        var b = 20 //等价于window.b = 20

        function test() {
            console.log('孙笑川')
        }

        window.test()

        let c = 1
        console.log('let c', window.c) //undefined

        let d = 2
        window.d = 3
        console.log('d', d) //打印的值为2，let和window声明的变量相同时，会优先找let
        

        //不推荐此种写法
        {
            e = 5 //在局部/块作用域中，如果没有使用var/let声明变量，则变量会自动成为window的属性（全局变量）
        }
        console.log('e', e)
        
    </script>
  </body>
</html>

```



### 13. 变量、函数的提升

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>变量、函数的提升</title>
  </head>
  <body>
    <script>
        /*
        变量的提升：
          - 使用var声明的变量，会在所有代码执行前被声明
        */
        console.log('a', a)
        
        var a = 1

        a = 2

        /*
        函数的提升：
          - 使用函数声明创建的函数，会在其他代码执行前被创建
        */

        test()

        function test() {
          console.log('函数的提升')
        }

        /*
        let声明的变量也会提升，但是在赋值之前，js解释器会禁止访问该变量
        */
       console.log('c', c) //Uncaught ReferenceError: Cannot access 'c' before initialization
       
       let c = 3

       //注：提升的主要作用是js在设计时考虑到内存分配优化的问题
    </script>
  </body>
</html>

```



### 14. debug

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>debug</title>
  </head>
  <body>
    <script>
        /*
        debug：
            - debugger 在代码中打一个断点
            - f12控制台 - source模块下直接打
        */
    </script>
  </body>
</html>

```



### 15. 立即执行函数

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>立即执行函数</title>
  </head>
  <body>
    <script>
        //在开发时，应尽量避免在全局作用域中编写代码
        //方式一：如果使用let声明变量，可以使用{}来创建块作用域
        {
            let a = 1
        }

        {
            let a = 2
        }

        /*
        方式二：使用立即执行函数（不能使用let/需兼容旧浏览器等情时）

        立即执行函数（IIFE）：
            - 它是一个匿名函数，且只会调用一次
            - 可以用它来创建一个一次性的函数作用域，避免变量冲突的题
        */
       (function() {
        let a = 1
        console.log('a', a)
       }())

    </script>
  </body>
</html>

```



### 16. this

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>this</title>
  </head>
  <body>
    <script>
        /*
        this:
            - 函数在执行时，js解释器每次都会传递进一个隐含的参数（this）
            - this会指向一个对象
                - this所指向的对象根据函数调用方式的不同而不同：
                  1. 以函数的形式调用，this指向的是window
                  2. 以方法的形式调用，this指向的是调用方法的对象
            - 通过this可以在方法中引用调用方法的对象
        */

        function test() {
            console.log('this', this)
            
        }
        test()

        const obj = {
            name: '孙笑川'
        }
        obj.test = test
        obj.test()

        const obj1 = {
            name: '药水哥',
            test
        }
        obj1.test()

        const obj2 = {
            name: '刘波',
            printName: function() {
                console.log('我的名字是', this.name)
            }
        }
        obj2.printName()
    </script>
  </body>
</html>

```



### 17. 箭头函数中的this

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>箭头函数中的this</title>
  </head>
  <body>
    <script>
        /*
        箭头函数：
            - 语法
                ([参数]) => {}
            
            - 无参箭头函数：
                () => {}

            - 一个参数：
                [参数] => {}

            - 多个参数：
                ([参数一], [参数二], ...) => {}

            - 函数体只有一行代码的函数：
                () => ...

            - 只返回一个对象的函数：
                () => ({...})

            - 有多行语句的函数：
                () => {
                    ......
                    return 返回值
                }
        
        箭头函数没有自己的this，它的this由外层作用域决定：
            - 箭头函数的this和它的调用方式无关，固定为window
        */

        function test() {
            console.log('test', this);
            
        }

        const test1 = () => {
            console.log('test21', this);
        }

        test() //打印结果 window
        test1() //打印结果 window

        const obj = {
            name: '孙笑川',
            test,
            test1,
            printName() {
                console.log('我的名字是', this.name)

                function innerTest() {
                    console.log('innerTest', this)
                }

                innerTest() //以函数形式调用，此处的this为window
            
                const innerTest1 = () => {
                    console.log('innerTest1', this)
                }

                innerTest1() //obj
            }
        }

        obj.test() //打印结果 obj
        obj.test1() //打印结果 window
        obj.printName()
    </script>
  </body>
</html>

```



### 18. 严格模式

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>严格模式</title>
  </head>
  <body>
    <script>
        /*
        js允许代码有两种模式：
            - 正常模式
                - 默认情况下，代码都运行在正常模式下
                - 能不报错的地方尽量不报错
                - 运行效率相对较低 
            - 严格模式
                - 禁止了一些正常模式下的语法
                - 更容易报错
                - 性能提升
            - 一般情况下建议使用严格模式（可以避免潜一些在的问题；提升性能）
        */

        //开启严格模式
        "use strict" //全局严格模式
        // a = 10 //Uncaught ReferenceError: a is not defined

        function test() {
            "use strict" //局部严格模式
        }
    </script>
  </body>
</html>

```



## 七、面向对象

### 1. 面向对象

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>面向对象</title>
  </head>
  <body>
    <script>
        /*
        面向对象编程（OOP）
            1. 程序是对现实世界的抽象
            2. 一个事物抽象到程序中就变成了对象（万物皆对象）
            3. 程序中的操作都是通过对象来完成

        一个事物通常由数据和功能组成
        一个对象由属性和方法组成
        事物的数据在程序中体现为对象的属性，功能体现为方法
        */
    </script>
  </body>
</html>

```



### 2. 类

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>类</title>
  </head>
  <body>
    <script>
        /*
        使用Obejct创建对象的问题：
            1. 无法区分不同类型的对象
            2. 批量创建对象时不方便
        
        在js中可以通过类 Class 来解决这个问题
            - 类是对象模板，可以将对象的属性和方法直接定义在类中，以此来创建对象
            - 语法：
                class 类名 {}
                const 类名 = class {}
            - 通过类创建对象
                new 类名()
            - 通过同一个类创建的对象，称为同类对象
                - 可以使用instanceof来检查指定对象是否是由某个类创建的
                - 由类创建的对象，称之为该类的实例
        */

        class Person {

        }

        //const Person1 = class {}

        class Animal {

        }

        const p1 = new Person()
        const p2 = new Person()

        const a1 = new Animal()
        console.log('p1 instanceof Person', p1 instanceof Person) //true
        console.log('a1 instanceof Person', a1 instanceof Person) //false
        
    </script>
  </body>
</html>

```



### 3. 属性

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>属性</title>
  </head>
  <body>
    <script>
        class People {
            /*
            类的代码块默认就是严格模式：
                类的代码块用于设置对象的属性（不是任意代码都能写在里面）
                实例的属性只能通过实例来访问
            */

           //实例属性，只能通过实例去访问，如 const p = new People() p.name
           name
           age = 33

           //静态属性（类属性），只能通过类去访问，如：People.classNo
           static classNo
           static hobby = '直播'
        }

        const p = new People()
        console.log('people', p)
        console.log('people.age:', p.age)
        console.log('People.classNo:', People.classNo)
        console.log('People.hobby:', People.hobby)
        
    </script>
  </body>
</html>

```



### 4. 方法

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>方法</title>
  </head>
  <body>
    <script>
        class People {
            name = '孙笑川'

            //添加方法，方式一
            sayHello = function() {

            }

            //添加方法，方式二（实例方法）
            sayHello1() {
                //实例方法中的this就是当前的实例
                console.log('大家好，我是：', this.name)
            }

            //添加方法，方式三（静态方法）
            static sayHello2() {
                //静态方法中的this就是当前的类
                console.log('这是一个静态方法')
                console.log('这是静态方法中的this:', this)
            }
        }

        const p = new People()
        //console.log('people:', p)
        //console.log('people name:', p.name)
        //p.sayHello1()
        People.sayHello2()
    </script>
  </body>
</html>

```



### 5. 构造函数

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>构造函数</title>
  </head>
  <body>
    <script>
        //直接指定属性值时，创建出来的对象的属性值都是一样的
        class People {
            name = '孙笑川'
            age = 33
            job = '直播'
            sayHello() {
                console.log('大家好，我是', this.name)
            }
        }

        class People1 {
            name
            age
            job

            //构造函数
            constructor(name, age, job) {
                //可以在构造函数中为实例属性赋值
                this.name = name
                this.age = age
                this.job = job
            }
        }

        const p1 = new People1('孙笑川', 33, '主播')
        const p2 = new People1('药水哥', 30, '主播')
        console.log('people1', p1)
        console.log('people2', p2)
        
    </script>
  </body>
</html>

```



### 6. 封装

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>封装</title>
  </head>
  <body>
    <script>
        /*
        面向对象的三大特征：
            封装
            继承
            多态
        */

        /*
        封装：
            - 对象可以看作是一个用来存储不同属性的容器
            - 对象不仅负责属性，还要负责数据的安全
            - 直接添加到对象中的属性并不安全，因为它们可以被随意的修改
            - 如何确保数据的安全：
                1. 属性私有化，属性名前加#
                2. 提供getter、setter方法来进行对数据的操作（控制属性的读写权限）
                    - 语法：
                        - getter
                            get 属性名() {
                                return this.#属性
                            }
                        - setter
                            set 属性名(参数) {
                                this.#属性名 = 参数
                            }
        */

        //优化前
        class People {
            name
            #age
            job
            #address //使用#开头，该属性就变成了私有化属性（只能在类的内部访问）

            constructor(name, age, job, address) {
                this.name = name
                this.#age = age
                this.job = job
                this.#address = address
            }

            setAge(age) {
                //控制年龄不能为负数
                if (age >= 0) {
                    this.#age = age
                }
            }

            //getter用于读取属性
            getAddress() {
                return this.#address
            }

            //setter用于设置属性
            setAddress(address) {
                this.#address = address
            }
        }

        const p = new People('孙笑川', 33, '主播', '四川成都')

        //p1.#address = '北京' //直接修改私有化属性时，编译器报错Property '#address' is not accessible outside class 'People' because it has a private identifier.
        
        console.log('address', p.getAddress())

        p.setAddress('北京')
        console.log('people', p)

        console.log('-----------------------------------------')
        
        //优化后
        class People1 {
            #name
            #age
            #job
            #address

            constructor(name, age, job, address) {
                this.#name = name
                this.#age = age
                this.#job = job
                this.#address = address
            }

            get name() {
                return this.#name
            }

            set name(name) {
                this.#name = name
            }
        }

        const p1 = new People1('孙笑川', 33, '主播', '四川成都')
        console.log('people1', p1)
        
        p1.name = '药水哥'

        console.log('people1(优化后的setter方法)', p1)
    </script>
  </body>
</html>

```



### 7. 多态

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>多态</title>
  </head>
  <body>
    <script>
        /*
        多态：
            - 在js中不会检查参数的类型，任何数据都可以作为参数来传递
            - 同一个方法的不同执行结果
        */

        class People {
            constructor(name) {
                this.name = name
            }
        }

        class Animal {
            constructor(name) {
                this.name = name
            }
        }

        const p = new People('孙笑川')
        const a = new Animal('旺财')
        console.log(p)
        console.log(a)
        
        function test(obj) {
            //if (obj instanceof People) {
                console.log('我的名字是：', obj.name)
            //}
        }
        test(p)
    </script>
  </body>
</html>

```



### 8. 继承

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>继承</title>
  </head>
  <body>
    <script>
        /*
        继承:
            - 使用extends关键字来完成继承
            - 父类：被继承的类；子类：继承的类
            - 通过继承可以减少重复的代码，并且可以在不修改一个类的前提下对其进行扩展
            - 可以使用super关键字来引用父类的属性和方法
            - OCP 开闭原则
        */

        class Animal {
            constructor(name) {
                this.name = name
            }

            say() {
                console.log('动物在叫~')
            }
        }

        class Dog extends Animal {
            constructor(name, age) {
                //重写父类的构造函数时，构造函数的第一行代码必须为super()
                super(name)
                this.age = age
            }

            //重写父类的方法
            say() {
                console.log('汪汪汪~')
            }
        }

        class Cat extends Animal {
            say() {
                console.log('喵喵喵~')
            }
        }

        const d = new Dog('旺财', 1)
        console.log(d)
        d.say()

        const c = new Cat('卡星五')
        console.log(c)
        c.say()
    </script>
  </body>
</html>

```



### 9. 对象的结构

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>对象的结构</title>
  </head>
  <body>
    <script>
        /*
        对象中存储属性的区域有两个：
            1. 对象自身
                - 直接通过对象添加的属性
                - 在对象中通过键值对形式添加的属性
            2. 原型对象（prototype）
                - 对象中还有一些内容，会存储到原型对象中
                - 对象中有一个属性用于存储原型对象 __proto__
                - 原型对象也能为对象存储属性
                    - 当访问对象中的属性和方法时，会优先访问对象自身中查找，如果没有，才会去原型对象中找
                - 会添加到原型对象中的情况：
                    - 在类中通过 xxx() {} 方式添加的方法
                    - 主动向原型中添加的属性/方法
        */

        class People {
            constructor(name, age) {
                this.name = name
                this.age = age
            }
        }

        const p = new People('孙笑川', 33)
        console.log(p)
        
    </script>
  </body>
</html>

```



### 10. 原型对象

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>原型对象</title>
  </head>
  <body>
    <script>
        class People {
            constructor(name, age) {
                this.name = name
                this.age = age
            }

            sayHello() {
                console.log('Hello')
            }
        }

        const p = new People('孙笑川', 33)
        console.log(p)

        // 通过 __proto__ 访问原型对象（不建议使用此方式）
        console.log(p.__proto__)

        // 通过 Object.getPrototypeOf() 访问原型对象
        console.log(Object.getPrototypeOf(p))
        
        /*
        原型对象中的数据：
            1. 对象中的数据（属性和方法等）
            2. constructor（对象的构造函数）
            注：原型对象也有原型，这样就构成了一条原型链（原型链的长度随对象的复杂程度不同而不同）
            People -> People.prototype -> Object.prototype -> null

        原型链：
            - 读取对象的属性时，会优先在对象自身的属性中查找
              如果对象中有，则使用，没有则向上往对象的原型中去找
              如果原型中有，则使用，没有则继续向上往原型的原型中去找
              直到找到Object对象的原型（Object对象的原型为null）
              此时如果依然没有找到，则返回undefined
            - 作用域链，是找变量的链，找不到会报错
            - 原型链，是找属性的链，找不到，会返回undefined
        */
    </script>
  </body>
</html>

```



### 11. 原型的作用

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>原型的作用</title>
  </head>
  <body>
    <script>
        class People {
            constructor(name, age) {
                this.name = name
                this.age = age
            }

            sayHello() {
                console.log('Hello')
            }
        }

        /*
        所有的同类型对象，它们的原型对象都是同一个（同类型对象的原型链是一样的）
        作用：
            原型相当于一个公共的区域，可以被所有该类的实例访问
            一个类中所有公共的属性/方法可以统一存储到原型中
        
        注：js中的继承通过原型来实现
        */
       const p = new People()
       const p1 = new People()
       console.log(p.__proto__ === p1.__proto__) // true
       
    </script>
  </body>
</html>

```



### 12. 修改原型

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>修改原型</title>
  </head>
  <body>
    <script>
        /*
        注：一般情况下，不会进行修改原型的操作
            实在要改的情况下，通过 类.prototype 的形式去修改（一修改就是修改所有实例的原型）
        */
        class People {
            constructor(name, age) {
                this.name = name
                this.age = age
            }

            sayHello() {
                console.log('Hello')
            }
        }

        const p = new People()
        const p1 = new People()

        //通过 对象.__proto__的形式 修改对象原型中的属性，对该类下的其他实例也会造成影响
        p.__proto__.fly = () => {
            console.log('fly......')
        }

        p1.fly() // p1有了飞的功能
    </script>
  </body>
</html>

```



### 13. instanceof和hasOwn

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>instanceof和hasOwn</title>
  </head>
  <body>
    <script>
        class People {

        }

        class Student extends People {

        }

        /*
        instanceof：检查一个对象是否是一个类的实例
            - instanceof检查的是对象的原型链上是否有该类的实例（只要有，则返回true）
        */
        const s = new Student()
        // student ---> People的实例 ---> Object实例 ---> Object原型
        // 注：Object是所有对象的原型
        //s.__proto__ 等价于 Student.prototype
        console.log('s instanceof People:', s instanceof People)
        console.log('s instanceof Student:', s instanceof Student)
        
        console.log('----------------分割线------------------')
        

        class Person {
            constructor(name, age) {
                this.name = name
                this.age = age
            }

            sayHello() {
                console.log('Hello')
            }
        }

        /*
        in:
            - 使用in运算符检查属性时，无论属性在对象自身还是在原型中，存在则会返回true
        对象.hasOwnProperty(属性名)：
            - 不推荐使用
            - 用于检查一个对象的自身是否含有某个属性/方法
        Object.hasOwn(对象, 属性名)：
            - 推荐使用
            - 同理hasOwnProperty方法
        */
        const p = new Person()
        console.log('\'name\' in p:', 'name' in p)
        console.log('\'sayHello()\' in p:', 'sayHello' in p)
        
        console.log('p.hasOwnProperty(\'name\'):', p.hasOwnProperty('name'))
        console.log('p.hasOwnProperty(\'sayHello()\'):', p.hasOwnProperty('sayHello'))
        
        console.log('Object.hasOwn(p, \'name\'):', Object.hasOwn(p, 'name'))
        
    </script>
  </body>
</html>

```



### 14. 旧类

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>旧类</title>
</head>
<body>
    <script>
        /*
        早期js中，直接通过函数来定义类
            - 通过 xxx() 的形式直接调用，那么这个函数为普通函数
            - 通过 new xxx() 的形式调用，那么这个函数为构造函数
        */

        // 等价于 class People {}
        //function People() {
        //
        //}

        //const p = new People()
        //console.log(p)

        var People = (function() {
            function People(name, age) {
                this.name = name
                this.age = age
            }

            // 向原型中添加属性/方法
            People.prototype.sayHello = function() {
                console.log('hello......')
            }

            // 添加静态属性/方法
            People.staticProperty = 'address'
            People.staticMethod = function() {}

            return People
        })()

        var p = new People('孙笑川', 33)
        console.log(p)
        
        console.log('-------------------分割线---------------------')
        
        
        var Animal = (function() {
            function Animal() {

            }

            return Animal
        })()

        var Cat = (function() {
            function Cat() {

            }

            // 继承Animal
            Cat.prototype = new Animal()

            return Cat
        })()

        var cat = new Cat()
        console.log(cat) //cat对象的原型为Animal
        
    </script>
</body>
</html>
```



### 15. new运算符

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>new运算符</title>
  </head>
  <body>
    <script>
        /*
        使用 new 运算符之后，发生了那些事情？
        mdn文档：https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/new#description

        1. Creates a blank, plain JavaScript object. For convenience, let's call it newInstance.
        2. Points newInstance's [[Prototype]] to the constructor function's prototype property, 
            if the prototype is an Object. Otherwise, newInstance stays as a plain object with Object.prototype as its [[Prototype]].
        3. Executes the constructor function with the given arguments, 
            binding newInstance as the this context (i.e. all references to this in the constructor function now refer to newInstance).
        4. If the constructor function returns a non-primitive, this return value becomes the result of the whole new expression. 
            Otherwise, if the constructor function doesn't return anything or returns a primitive, newInstance is returned instead. 
            (Normally constructors don't return a value, but they can choose to do so to override the normal object creation process.)
        
        1. 创建一个普通的js对象（Object对象 {}），并称其为新对象
        2. 将构造函数的 prototype 属性设置为新对象的原型
        3. 根据传入的实参执行构造函数，并将新对象设置为函数中的 this
        4. 如果构造函数返回的是一个非原始值，则该值会作为最终的返回值返回
            如果构造函数返回的是一个原始值或没有指定返回值，则以上步骤创建的新对象会作为最终的返回值返回
        */
    </script>
  </body>
</html>

```



## 八、数组

### 1. 简介

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>简介</title>
  </head>
  <body>
    <script>
        /*
        数组（Array）：
            - 数组也是一种复合数据类型，可以存储多个不同类型的数据
            - 存储的元素是有序的，每个元素都有自己的唯一索引
            - 创建数组：
                - const arr = new Array()
                - const arr = []
            - 向数组中添加元素：
                - 数组[索引] = 元素
            - 读取数组中的元素：
                - 数组[索引]
                - 使用下标索引读取一个不存在的元素时，会返回undefined
            - length方法：
                - 获取数组的长度
                - 返回结果是当前数组的最大索引 + 1
        */

        const arr = new Array()
        const arr1 = [] // 数组字面量

        arr[0] = 1
        arr1[2] = 3 //数组中间有空元素：非连续数组（实际开发过程中尽量避免）

        console.log('arr', arr)
        console.log('arr1', arr1)

        console.log('typeof arr', typeof arr) //object
        
        console.log('arr1.length', arr1.length)
        
    </script>
  </body>
</html>

```



### 2. 数组的遍历

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>数组的遍历</title>
  </head>
  <body>
    <script>
        //const arr = [1, 2, 'a', 3, {name: '孙笑川'}, () => {}]
        //console.log(arr)
        
        //创建数组时，尽量确保元素是相同的数据类型
        const arr = [1, 2, 3]

        for (let i = 0; i < arr.length; i++) {
            console.log(arr[i])
        }

        for (let i = arr.length - 1; i >= 0; i--) {
            console.log(arr[i])
        }
    </script>
  </body>
</html>

```



### 3. for-of语句

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>for-of语句</title>
  </head>
  <body>
    <script>
        /*
        for-of语句：
            - 可用于遍历可迭代的对象
            - 语法：
                for (变量 of 可迭代的对象) {
                    ......
                }
            - 执行流程：
                - for-of根据可迭代对象中的元素个数来执行循环体
                - 每次执行时都会将当前遍历到的元素赋值给变量
        */

        const arr = [1, 2, 3]
        for (let i of arr) {
            console.log(i)
        }

        for (let i of 'sunxiaochuan') {
            console.log(i)
        }
    </script>
  </body>
</html>

```



### 4. 数组的方法（非破坏性）

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>数组的方法（非破坏性）</title>
</head>
<body>
    <script>
        /*
        mdn文档：https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array
        例：
            - indexOf() 获取指定元素在数组中第一次出现的位置（返回值为该位置的下标索引）
            - lastIndexOf() 获取指定元素在数组中最后一次出现的位置
            - join() 将数组中的元素连接为一个字符串，可以指定使用什么连接符（默认为英文逗号 ','）
            - slice() 从指定索引位置截取数组（非破坏性方法，截取之后生成新的返回值，不影响原数组）
                - arr.slice(1) 传一个参数时，从指定索引位置开始截取s
                - arr.slice(1, 3) 传两个参数时为“左闭右开”区间
                - arr.slice(1, -1) 参数为负时，表示从后往前取，其他同理传两个参数时的“左闭右开”区间
                - arr.slice() 参数为空时，可以对数组进行浅拷贝
        */

        const arr = [1, 2, 3]
        console.log(arr.slice(1))
        
    </script>
</body>
</html>
```



### 5. 对象的复制

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>对象的复制</title>
  </head>
  <body>
    <script>
        /**/

        const arr = [1, 2, 3]

        // 调用slice()时，会产生一个新的数组，从而完成对数组的复制
        const arr1 = arr.slice()

        console.log(arr)
        console.log(arr1)

        console.log('arr === arr1:', arr === arr1)
        
    </script>
  </body>
</html>

```



### 6. 深拷贝和浅拷贝

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>浅拷贝和深拷贝</title>
  </head>
  <body>
    <script>
        /*
        浅拷贝（shallow copy）：
            - 一般情况下，对对象的拷贝都是浅拷贝
            - 只进行浅层拷贝（只拷贝一层）
            - 如果对象中存储的数据是原始值（基本类型），此时是否是深拷贝，浅拷贝不重要
            - 浅拷贝只会对对象本身进行复制，不会复制对象中的属性/元素
        深拷贝（deep copy）
            - 深拷贝不仅复制对象本身，还复制对象中的属性/元素
            - 通常情况下不经常使用深拷贝
        */

        const arr = [
            {
                name: '孙笑川',
                age: 33,
                info: {
                    address: '成都',
                    classNo: 1
                }
            },
            {
                name: '药水哥',
                age: 30,
                info: {
                    address: '湖北',
                    classNo: 2
                }
            }
        ]
        console.log('arr', arr)
        

        // 浅拷贝
        const arr1 = arr.slice()
        console.log('arr1', arr1)
        

        // 深拷贝
        const arr2 = structuredClone(arr)
        console.log('arr2', arr2)
    </script>
  </body>
</html>

```



### 7. 对象的复制

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>对象的复制</title>
  </head>
  <body>
    <script>
        const arr = [
            {
                name: '孙笑川',
                age: 33,
                info: {
                    address: '成都',
                    classNo: 1
                }
            },
            {
                name: '药水哥',
                age: 30,
                info: {
                    address: '湖北',
                    classNo: 2
                }
            }
        ]

        // slice() 方法
        const arr1 = arr.slice()
        console.log(arr === arr1)

        // [...xxx] 数组解构，也可以对数组进行复制
        const arr2 = [...arr]
        console.log(arr === arr2)
        
        // Object.assign(目标对象， 被复制的对象)
        const arr3 = Object.assign({}, arr)
        console.log('arr3', arr3)
        
        // 也可以用 ... 展开运算符对对象进行复制
        const arr4 = {...arr}
        console.log('arr4', arr4)

        //注：在进行复制时，如果有同名的属性或方法，前面的值会被后来的值覆盖
    </script>
  </body>
</html>

```



### 8. 数组的方法（破坏性）

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>数组的方法（破坏性）</title>
  </head>
  <body>
    <script>
        /*
        push()
            - 向数组的末尾添加一个或多个元素，并返回新数组的长度
        pop()
            - 删除数组的最后一个元素，并返被删除的元素
        unshit()
            - 向数组的开头添加一个或多个元素，并返回新数组的长度
        shit()
            - 删除数组的第一个元素，并返被删除的元素
        splice()
            - 可以删除、添加、插入、替换元素
            - 参数：
                1. 删除的起始位置
                2. 删除的数量
            - 返回值：
                被删除的元素
        reverse()
            - 反转数组
        */

        let result
        const arr = [1, 2, 3, 4]
        
        //result = arr.push(1)
        //console.log('push()', arr)
        //console.log('result', result)
        
        //result = arr.pop(1)
        //console.log('pop()', arr)
        //console.log('result', result)

        //result = arr.unshift(1)
        //console.log('unshift()', arr)
        //console.log('result', result)

        //result = arr.shift()
        //console.log('shift()', arr)
        //console.log('result', result)

        // 从下标索引为1的位置开始删除
        //result = arr.splice(1)
        //console.log('splice()', arr)
        //console.log('result', result)

        // 删除下标索引为1，2的元素，并从下标索引为1的位置开始插入元素
        //arr.splice(1, 2, 'abc', {name: '孙笑川'})
        //console.log('splice()', arr)

        // 从下标索引为2的位置开始插入元素
        //arr.splice(2, 0, 'abc', {name: '孙笑川'})
        //console.log('splice()', arr)

        arr.reverse()
        console.log('reverse()', arr)
        
    </script>
  </body>
</html>

```



### 9. 排序

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>排序</title>
  </head>
  <body>
    <script>
        const arr = [22, 2 , 5, 10, 3, 6, 9, 7, 13, 8]

        // 冒泡排序
        for (let i = 0; i < arr.length - 1; i++) {
            for (let j = 0; j < arr.length - i - 1; j++) {
                if (arr[j + 1] < arr[j]) {
                    let temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                }
            }
        }
        console.log('冒泡排序', arr)
        
        const arr1 = [22, 2 , 5, 10, 3, 6, 9, 7, 13, 8]
        // 选择排序
        for (let i = 0; i < arr1.length - 1; i++) {
            for (let j = i + 1; j < arr1.length - 1; j++) {
                if (arr[i] > arr[j]) {
                    let temp = arr[i]
                    arr[i] = arr[j]
                    arr[j] = temp
                }
            }
        }
        console.log('选择排序', arr1)
    </script>
  </body>
</html>

```



### 10. 闭包

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>闭包</title>
  </head>
  <body>
    <script>
        /*
        闭包：
            - 能访问到外部函数作用域中变量的函数
            - 什么时候使用：
                - 当需要隐藏一些不想被外部访问的内容时
            - 构成闭包的条件：
                1. 函数的嵌套
                2. 内部函数要引用外部函数的变量
                3. 内部函数要作为返回值返回
        */

        function test() {
            let num = 0
            return () => {
                num++
                console.log('第', num, '次调用函数...')
            }
        }

        const testFn = test()
        testFn()
    </script>
  </body>
</html>

```



### 11. 闭包的原理

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>闭包的原理</title>
  </head>
  <body>
    <script>
        let a = '全局变量a'

        /*
        函数作用域，在函数创建时就已经确定（词法作用域）
            - 和函数的调用位置无关
        闭包利用的就是词法作用域
        */
        function test() {
            console.log(a)
        }

        function test1() {
            let a = 'test1中的局部变量a'

            test()
        }
        //test1() // 打印结果：全局变量a

        function test2() {
            let a = 'test2中的局部变量a'

            function test3() {
                console.log(a)
            }

            return test3
        }
        let result = test2()
        result() // 打印结果：test2中的局部变量a
    </script>
  </body>
</html>

```



### 12. 闭包的注意事项

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>闭包的注意事项</title>
  </head>
  <body>
    <script>
        /*
        闭包的生命周期：
            1. 在外部函数调用时产生，调用一次产生一个新的闭包
            2. 在内部函数丢失时，闭包会消失（即内部函数被垃圾回收了）
        注意事项：
            - 用于隐藏一些不想被外部访问的内容
            - 闭包需要占用一定的内存空间
            - 类可以使用原型，而闭包不行
                - 执行次数较少时，使用闭包
                - 需要大量创建实例时，使用类
        */

        function test() {
            let num = 1
            return () => {
                num++
                console.log(num)
            }
        }

        let testFn = test() // 独立闭包
        let testFn1 = test() // 独立闭包

        testFn()
        testFn1()

        testFn = null
        testFn1 = null
    </script>
  </body>
</html>

```



### 13. 递归

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>递归</title>
  </head>
  <body>
    <script>
        /*
        递归：
            - 调用自身的函数称为递归（自己调用自己）
            - 构成条件：
                1. 基线条件（递归的终止条件）
                2. 递归条件（如何对问题进行拆分）
        */

        function recursion(num) {
            // 基线条件
            if (num === 1) {
                return 1
            }

            // 递归条件
            return recursion(num - 1) * num
        }

        let num = 5
        let result = recursion(num)
        console.log('recursion(', num, ')', result)
        
    </script>
  </body>
</html>

```



### 14. 数组的方法

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>数组的方法</title>
  </head>
  <body>
    <script>
        let arr = ['h', 'a', 'd', 'b', 'f', 'j', 'c']
        console.log('arr', arr)
        
        /*
        sort()
            - 对数组进行排序，会改变原数组（破坏性的）
            - 默认进行升序排列，且按照Unicode编码进行排序
            - 参数：
                - 可以传递一个回调函数，用于指定排序规则
                    - (a, b) => a - b) 升序
                    - (a, b) => b - a) 降序
        */
        arr = [1, 3, 9, 2, 5, 8, 22, 13] //阿拉伯数字排序时需指定排序规则
        console.log('arr.sort()', arr.sort((a, b) => a - b))

        /*
        foreach() 
            - 遍历数组
            - 需要一个回调函数作为参数，数组中有几个元素，回调函数就执行几次
                - 回调函数中有三个参数:
                    1. element 当前元素
                    2. index 当前元素的下标索引
                    3. array 被遍历的数组
        */
       arr = ['孙笑川', '药水哥', 'Giao哥']
       arr.forEach((element, index, array) => {
        console.log('index:', index, ',element:', element, ',array:', array)
       })

       /*
       filter()
           - 将数组中符合条件的元素保存到一个新数组中（过滤）
           - 需要一个回调函数作为参数，根据回调函数的条件判断当前元素是否符合条件
       */
       // 筛选出数组中的偶数
       arr = [1, 3, 9, 2, 5, 8, 22, 13]
       console.log('arr.filter()', arr.filter(item => item % 2 === 0))
       
       /*
       map()
            - 根据当前数组，生成一个新的数组（非破坏性的）
            - 需要一个回调函数作为参数，回调函数的返回值，会成为新数组中的元素
       */
       // 所有元素扩大两倍
       console.log('arr.map()', arr.map(item => item * 2))

       /*
       reduce()
            - 可以将数组中的所有元素整合为一个值（归约）
            - 参数：
                1. 回调函数，指定元素的合并规则
                2. 初始值（可选）
       */
       console.log('arr.reduce()', arr.reduce((a, b) => a + b)) // 63
       console.log('arr.reduce()', arr.reduce((a, b) => a + b, 1)) //64
    </script>
  </body>
</html>

```



### 15. 可变参数

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>可变参数</title>
  </head>
  <body>
    <script>
        /*
        arguments：
            - 是函数中的有一个隐含参数
            - 是一个类数组对象（伪数组）
            - 用来存储函数的实参
                - 无论当前函数是否定义形参，传入的实参都会存储到arguments对象中
        */

        function test() {
            //for (let i = 0; i < arguments.length; i++) {
            //    console.log(arguments[i])
            //}
            
            //for (const element of arguments) {
            //    console.log(element)
            //}

            // 报错，arguments是类数组对象，并不是一个数组对象
            //arguments.forEach(element => console.log(element))
        }
        test(1, '孙笑川', 'abc', () => {})

        function sum() {
            let result = 0
            for (let i = 0; i < arguments.length; i++) {
                result += arguments[i]
            }
            return result
        }

        let result = sum(1, 2, 3)
        console.log('sum(1, 2, 3)=', sum(1, 2, 3))
        
        /*
        可变参数：
            - 可以接收任意数量的实参，并将它们存储到一个数组中
            - 可变参数和普通参数一起使用时，可变参数需写到最后的位置
        */
       function test1(...args) {
            console.log('args', args)
       }
       test1()

       function test2(a, b, ...args) {
            console.log('args', args)
       }
       test2(1, 'a', () => {}, 'test2', 123)

       function sum1(...args) {
            return args.reduce((a, b) => a + b)
       }
       console.log('sum1(1, 2, 3)=', sum(1, 2, 3))
    </script>
  </body>
</html>

```



### 16. call和apply

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>call和apply</title>
  </head>
  <body>
    <script>
        /*
        根据函数的调用方式不同，this也不同：
            1. 以函数形式调用，this是window
            2. 以方法的形式调用，this是调用方法的对象
            3. 在构造函数中，this是新建的对象
            4. 箭头函数没有自己的this，由外层作用域决定
            5. 通过call()/apply()调用的函数，传入的第一个参数就是函数的this
        */

        function test() {
            console.log('函数执行了...')
            console.log(this)
            
        }

        /*
        call()/apply:
            - 调用函数
            - 指定函数中的this（第一个参数）
        */
        test.call({name: '孙笑川'})
        test.apply(123)
        console.log('-----------------分割线-------------------')
        
        function test1(a, b) {
            console.log('a:', a, 'b:', b, 'this:', this)
        }
        test1(123, 456)

        // 通过call()方法调用时，函数的实参需跟在第一个参数之后
        test1.call({name: '孙笑川'}, 123, 456)

        // 通过apply()方法调用时，函数的实参需要通过一个数组传递
        test1.apply({name: '孙笑川'}, [123, 456])
    </script>
  </body>
</html>

```



### 17. bind

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>bind</title>
  </head>
  <body>
    <script>
        /*
        根据函数的调用方式不同，this也不同：
            1. 以函数形式调用，this是window
            2. 以方法的形式调用，this是调用方法的对象
            3. 在构造函数中，this是新建的对象
            4. 箭头函数没有自己的this，由外层作用域决定
            5. 通过call()/apply()调用的函数，传入的第一个参数就是函数的this
            6. 通过bind返回的函数，this由bind的第一个参数指定（且指定之后无法修改，不会因为调用方式的不同而不同）
        */
        /*
        bind():
            - 用于创建一个新的函数
            - 可以为新函数绑定this
        */

        function test(a, b, c) {
            console.log('函数执行了...', this)
            console.log('a:', a, 'b:', b, 'c:', c)
        }

        //由bind绑定的this，实参绑定之后无法修改（仅针对返回的新函数）
        const newFn = test.bind({name: '孙笑川'}, 1, 2, 3)
        newFn()

        // 注：箭头函数的this也无法通过call(),apply(),bind()方法指定，只能通过外层作用域决定
    </script>
  </body>
</html>

```



## 九、内建对象

### 1. 数组的解构

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>数组的解构</title>
  </head>
  <body>
    <script>
        /*
        数组的解构
        */
        const arr = [1, 2, 3, 'abc']

        let [a, b, c, d, e] = arr // 声明变量的同时解构赋值
        console.log(a, b, c, d, e)
        console.log('------------------------------')
        
        const arr1 = [1, 2, 3, 4, 5, 6]
        let [f, g, ...h] = arr1
        console.log(f, g, h) // 1, 2, [3, 4, 5, 6]
        console.log('------------------------------')

        function test() {
            return [1, 2]
        }
        let [i, j] = test()
        console.log(i, j)
        console.log('------------------------------')
        
        // 可以通过解构赋值来快速交换两个变量的值
        let a1 = 1
        let a2 = 2
        ;[a1, a2] = [a2, a1] // 此处相当于新建了一个数组[2, 1]
        console.log('a1:', a1, 'a2:', a2)
        
        const arr3 = [['孙笑川', 33, '成都'], ['药水哥', 30, '武汉']]
        let [[name, age, address], people] = arr3
        console.log(name, age, address)
        console.log(people)
        
    </script>
  </body>
</html>

```



### 2. 对象的解构

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>对象的解构</title>
  </head>
  <body>
    <script>
        /*
        对象的解构:
            - 变量名需和对象的属性名一一对应
            - 如果不想一一对应，可以通过取别名的形式来进行解构
        */
       const people = {
            name: '孙笑川',
            age: 33,
            address: '成都'
       }
       let { name, age, address } = people // 声明变量的同时解构对象
       console.log(name, age, address)


       // 先声明变量，再解构
       let name1, age1, address1, gender1
       ({ name: name1, age: age1, address: address1, gender: gender1 = '男' } = people) // 变量名取别名进行解构
       console.log(name1, age1, address1, gender1)
    </script>
  </body>
</html>

```



### 3. 对象的序列化

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>对象的序列化</title>
  </head>
  <body>
    <script>
        /*
        js中对象的使用都是存放于计算机内存中的

        对象的序列化：
            - 指将对象转换为一个可以存储的格式
            - js中对对象的序列化通常是转换为字符串
            - 用途：
                - 作为数据交换的格式（不同语言之间）
                - 编写配置文件
            - 怎么序列化：
                - 通过JSON（JavaScript Object Notation）工具类
            - 编写JSON的注意事项：
                1. JSON字符串有两种形式：
                    - JSON对象 {}
                    - JSON 数组 []
                2. JSON字符串中的属性名必须用双引号引起来
                3. JSON中可以使用的属性值：
                    - 数字（Number）
                    - 字符串（String）
                    - 布尔值（Boolean）
                    - 空值（Null）
                    - 对象（Object {}）
                    - 数组（Array []）
                4. JSON字符串的最后一个属性的后面不能再加 , 逗号
        */

        const people = {
            name: '孙笑川',
            age: 33,
            address: '成都'
        }
        console.log(people)

        // 对象转JSON字符串
        const peopleStr = JSON.stringify(people)
        console.log(peopleStr)

        // JSON字符串转对象
        const peopleObj = JSON.parse(peopleStr)
        console.log(peopleObj)
        
        const str = '{"name": "药水哥", "age": 30, "address": "湖北"}'
        console.log(JSON.parse(str))
        
    </script>
  </body>
</html>

```



### 4. 使用JSON深拷贝

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>使用JSON深拷贝</title>
  </head>
  <body>
    <script>
        const people = {
            name: '孙笑川',
            age: 33,
            address: '成都',
            hobby: ['直播', '抽象'],
            friend: {
                name: '药水哥',
                age: 30
            }
        }

        // 浅拷贝
        const people1 = Object.assign({}, people)
        console.log('people1.friend === people.friend', people1.friend === people.friend)

        // structuredClone深拷贝
        const people2 = structuredClone(people)
        console.log('people2.friend === people.friend', people2.friend === people.friend)
        
        // JSON 深拷贝
        const people3 = JSON.parse(JSON.stringify(people))
        console.log('people3.friend === people.friend', people3.friend === people.friend)

    </script>
  </body>
</html>

```



### 5. Map

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Map</title>
  </head>
  <body>
    <script>
        /*
        Map:
            - 存储键值对
            - 创建：
                new Map()
            - 属性和方法：
                - map.size() 获取键值对的数量
                - map.set(key, value) 添加键值对
                - map.get(key) 根据key获取值
                - map.delete(key) 根据key删除指定数据
                - map.has(key) 判断map中是否包含指定key
                - map.clear() 清空map
        */

        const map = new Map()
        map.set(1, '孙笑川')
        map.set('2', '药水哥')
        map.set({name: 'key'}, 'Giao哥')
        console.log(map)
        
        console.log(map.get(1))
        
        map.delete(1)
        console.log(map)

        console.log(map.has('abc'))
        
    </script>
  </body>
</html>

```



### 6. Map补充

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Map补充</title>
  </head>
  <body>
    <script>
        const map = new Map()
        map.set('name', '孙笑川')
        map.set('age', 33)

        // map转数组
        const arr = Array.from(map)
        const arr1 = [...map] // 推荐使用
        console.log(arr)
        console.log(arr1)
        
        // 数组转map
        const arr3 = [['name', '药水哥'], ['age', 30]]
        const map1 = new Map(arr3)
        console.log(map1)
        
        // 遍历map
        for (const entry of map) {
            console.log(entry)
        }

        map.forEach((key, value) => {
            console.log('key:', key, 'value:', value)
        })

        // map.keys() 获取map的所有key
        // map.values() 获取map的所有value
    </script>
  </body>
</html>

```



### 7. Set

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Set</title>
  </head>
  <body>
    <script>
        /*
        Set:
            - 用来创建一个集合
            - 功能和数组类似（但不能存储重复的数据）
            - 创建方式：
              - new Set()
              - new Set([])
            - 常用方法：
              - size() 集合大小
              - add() 添加元素
              - has() 判断是否有指定元素
              - delete() 删除元素
        */

        const set = new Set()
        set.add(1)
        set.add('abc')
        set.add(1)
        console.log(set)
        
        console.log(set.has(1))

        for (const item of set) {
            console.log(item)
        }

        const arr = [...set]
        console.log(arr)
        
        // 数组去重
        const arr1 = [1, 2, 3, 3, 5, 2, 1, 6, 7, 2, 3, 8, 10, 2]
        const set1 = new Set(arr1)
        console.log([...set1])
        
    </script>
  </body>
</html>

```



### 8. Math

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Math</title>
  </head>
  <body>
    <script>
        /*
        Math:
            - MDN文档：https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Math
            - 常量：
                - Math.PI 圆周率
            - 常用方法：
                - Math.abs() 求绝对值
                - Math.min() 求最小值
                - Math.max() 求最大值
                - Math.pow(x, y) 求x的y次幂 等价于 x ** y
                - Math.sqrt() 开平方 等价于 x ** .5
                - Math.floor() 向下取整
                - Math.ceil() 向上取整
                - Math.round() 四舍五入
                - Math.trunc() 直接舍弃小数位取整
                - Math.random() 返回一个0-1之间的随机数（不包含1）
        */
    </script>
  </body>
</html>

```



### 9. Date

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Date</title>
  </head>
  <body>
    <script>
        /*
        Date:
            - js中所有和时间相关的数据都由Date对象来表示
        */

        let d = new Date()
        console.log(d)
        
        // 获取年份
        console.log(d.getFullYear())

        // 获取月份（月份索引 0 - 11）
        console.log(d.getMonth())

        // 获取日
        console.log(d.getDate())

        // 返回当前日期是周几（0 - 6，0表示周日）
        console.log(d.getDay())

        // 参数：年，月，日，时，分，秒，毫秒
        let newDate = new Date(2024, 11, 1, 10, 0 ,0)
        console.log(newDate)
        
        // 获取当前时间的时间戳（从1970年1月1日0时0分0秒开始）
        console.log(d.getTime())

        // 获取当前的时间戳
        console.log(Date.now())
        
    </script>
  </body>
</html>

```



### 10. 日期的格式化

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>日期的格式化</title>
  </head>
  <body>
    <script>
        /*
        toLocaleString():
            - 将日期转换为本地时间格式的字符串
            - 参数：
                1. 国家地区字符串，如zh-CN en-US
                2. 一个对象，通过这个对象设置日期的格式：
                    - dateStyle 日期格式 / timeStyle 时间格式：
                        - full
                        - long
                        - medium
                        -short
                    - hour12 12小时制显示：
                        - true
                        - false
                    - weekday 星期的显示方式：
                        - long
                        - short
                        - narrow
                    ......
        */
        let d = new Date()

        // 将日期转换为本地字符串
        console.log(d.toLocaleDateString())

        // 将时间转换为本地字符串
        console.log(d.toLocaleTimeString())

        // 将日期转换为本地字符串
        console.log(d.toLocaleString('zh-CN', {dateStyle: 'full', timeStyle: 'full'}))
    </script>
  </body>
</html>

```



### 11. 包装类

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>包装类</title>
  </head>
  <body>
    <script>
        /*
        在js中，除了直接创建原始值外，还可以创建原始值的对象：
            - new String()
            - new Number()
            ......
        
        在js中有5个包装类：
            - String
            - Number
            - Boolean
            - BigInt
            - Symbol
        
        通过包装类可以将一个原始值包装为一个对象
        当我们对一个原始值调用方法或属性时，js解释器会临时将原始值包装为对应的对象，然后调用其对应的属性或方法

        包装类一般情况下是js解释器使用的，不建议开发人员直接通过 new xxx()的形式调用包装类
        */

        let str = 'test'
        console.log(str)
        
        let str1 = new String('test')
        console.log(str1)
    </script>
  </body>
</html>

```



### 12. 正则表达式

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>正则表达式</title>
  </head>
  <body>
    <script>
        /*
        正则表达式：
            - 用于定义一个规则，并通过它检查指定字符串是否符合规则，或将符合规则的内容提取出来
        使用：
            1. 在正则表达式中，大部分字符可以直接写
            2. | 表示或
            3. [] 表示字符集
                - [a-z] 任意小写字母
                - [A-Z] 任意大写字母
                - [A-Za-z] 任意字母（不区分大小写）
                - [0-9] 任意数字
            4. [^] 表示不包含
                - [^a] 不包含a
            5. .表示除了换行符之外的任意字符
            6. 在正则中使用 \ 作为转义字符
            7. 其他字符集
                - \w 任意单词字符 等价于 [A-Za-z0-9_]
                - \W 除了任意单词字符 等价于 [^A-Za-z0-9_]
                - \d 任意数字 [0-9]
                - \D 除了数字 [^0-9]
                - \s 空格
                - \S 除了空格
                - \b 单词边界
                - \B 除了单词边界
            8. 开头和结尾
                - ^ 表示字符串的开头
                    - /^a/ 表示以a开头
                - $ 表示字符串的结尾
                    - /a$/ 表示以a结尾
        */

        // 通过构造函数创建，参数：1.正则表达式，2.匹配模式
        let reg = new RegExp('a', 'i')

        // 使用字面量创建 /正则/匹配模式
        reg = /a/i // 判断指定字符串中是否包含a，且忽略大小写判断

        console.log(reg)
        
        let str = 'a'
        console.log(reg.test(str)) //true

        // 匹配换行符外的任意字符
        reg = /./
        console.log(reg.test(str)) //true

        // 只匹配 .
        reg = /\./
        console.log(reg.test(str)) //false

        // 只匹配字符串abc，要求指定字符串必须和正则一致
        reg = /^abc$/
        str = 'abc'
        console.log(reg.test(str)) //true

        str = 'abcabc'
        console.log(reg.test(str)) //false
    </script>
  </body>
</html>

```



### 13. 正则表达式-量词

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>正则表达式-量词</title>
  </head>
  <body>
    <script>
        /*
        量词：
            - {m} 指定字符串是否包含m个需要匹配的字符串
            - {m,} 至少m个
            - {m, n} m-n个
            - + 至少一个（一个以上），等价于 {1,}
            - * 任意数量个指定字符（包含0个）
            - ? 0 - 1 个指定字符，等价于 {0, 1}
        */

        // 包含5个a
        let reg = /a{5}/

        let str = 'aaaaaaaa'
        console.log(reg.test(str)) //true

        // 以a开头和结尾，且只能包含5个a
        reg = /^a{5}$/
        console.log(reg.test(str)) //false
        
        // 量词只对前一个字符起作用，如果要包含指定字符串，需要使用括号
        reg = /^ab{3}$/
        str = 'abababc'
        console.log(reg.test(str)) //false

        // 使用括号
        reg = /^(ab){3}$/
        str = 'ababab'
        console.log(reg.test(str)) //true

        // 至少一个小写字母
        reg = /^[a-z]{1,}$/
        str = 'abcddff'
        console.log(reg.test(str)) //true

        // 1 - 3 个小写字母字符
        reg = /^[a-z]{1, 3}$/
        str = 'abcddff'
        console.log(reg.test(str)) //false

        // a之后至少一个b
        reg = /^ab+$/
        str = 'abb'
        console.log(reg.test(str)) //true

        // 任意个数个b，没有也行
        reg = /^ab*$/
        str = 'a'
        console.log(reg.test(str)) //true

        // 0 - 1个b
        reg = /^ab?$/
        str = 'abbb'
        console.log(reg.test(str)) //false
    </script>
  </body>
</html>

```



### 14. 正则表达式-exec方法

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>正则表达式-exec方法</title>
  </head>
  <body>
    <script>
        /*
        reg.exec()：提取字符串中符合正则表达式的内容
        */

        let str = 'aycaicaoiuatc'
        
        // 提取指定字符串中符合 a[x]c 格式的字符串
        // g 全局匹配
        let reg = /a[a-z]c/ig
        console.log(reg.exec(str))

        // 分组
        reg = /a([a-z])c/ig
        let result = reg.exec(str)
        console.log(result)
        console.log(result[0])
        console.log(result[1])
        
    </script>
  </body>
</html>

```



### 15. 正则表达式-字符串

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>正则表达式-字符串</title>
  </head>
  <body>
    <script>
        /*
        split():
            - 可以根据正则表达式来对一个字符串进行分割
        search():
            - 可以搜索符合正则表达式的内容第一次在指定字符串中出现的位置
        replace():
            - 根据正则表达式替换字符串中的内容
        match():
          - 根据正则表达式匹配字符串中符合要求的内容
        matchAll():
          - 根据正则表达式匹配字符串中符合要求的内容（必须设置 g 全局匹配，不然会报错）
          - 返回值为迭代器
        */

        let str = 'akljlkj23io11eeol110009ojkpklsd9'
        console.log(str.split(/[0-9]/))

        console.log(str.search(/\d/))
        
        console.log(str.replace(/\d/g, '测试'))
        
        console.log(str.match(/\d/g))
        
        let result = str.matchAll(/\d/g)
        for (let item of result) {
          console.log(item)
        }
    </script>
  </body>
</html>

```



## 十、DOM

### 1. document对象

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>document对象</title>
  </head>
  <body>
    <script>
        /*
        document对象:
            - 表示的是整个网页
            - 原型链：
                HTMLDocument -> Document -> Node -> EventTarget -> Object.prototype -> null
            - 部分属性：
                - document.documentElement  html根元素
                - document.head  head元素
                - document.title  title元素
                - document.body  body元素
                - document.links  页面中的所有超链接
                ......
            - mdn文档：https://developer.mozilla.org/en-US/docs/Web/API/Document
        */
    </script>
  </body>
</html>

```



### 2. 元素节点

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>元素节点</title>
  </head>
  <body>
    <script>
        /*
        元素节点：
            - 在网页中，每一个标签都是一个元素节点
            - 如何获取元素节点对象？
                1. 通过document对象获取
                2. 通过document对象创建
            - 通过document来获取已有的元素节点：
                - document.getElementById():
                    - 根据id获取一个
                - document.getElementsByClassName():
                    - 根据元素的class属性值获取一组元素节点对象
                    - 返回的是一个类数组对象，不是数组
                    - 返回结果是实时更新的（即当网页中添加了新的元素，返回结果也会同步实时更新）
                - document.getElementsByTagName():
                    - 根据标签名获取一组元素节点对象
                    - 同理getElementsByClassName()方法
                    - document.getElementsByTagName("*") 获取网页中的所有元素节点对象
                - document.getElementsByName():
                    - 根据name属性获取一组元素节点对象
                    - 同理getElementsByClassName()方法
                    - 常用于获取 form 表单中的元素节点对象
                - document.querySelectorAll():
                    - 根据选择器获取元素节点对象
                    - 返回结果是一个类数组对象（不会实时更新）
                - document.querySelector():
                    - 根据选择器获取网页中第一个符合条件的元素节点对象
            - 创建元素节点对象：
                - document.createElement():
                    - 根据标签名创建元素节点对象
                    - const div = document.createElement('div')
        */
    </script>
  </body>
</html>

```



### 3. 元素的属性和方法

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>元素的属性和方法</title>
  </head>
  <body>
    <script>
        /*
        通过元素节点对象获取其他节点的方法：
            - element.childNodes  获取当前元素的子节点（包含空白的子节点，文本，换行符等等）
            - element.children  获取当前元素的子元素
            - element.firstElementChild  获取当前元素的第一个子元素
            - element.lastElementChild  获取当前元素的最后一个子元素
            - element.nextElementSibling  获取当前元素的下一个兄弟元素
            - element.previousElementSibling  获取当前元素的前一个兄弟元素
            - element.parentNode  获取当前元素的父节点
            - element.tagName  获取当前元素的标签名
        */
    </script>
  </body>
</html>

```



### 4. 文本节点

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>文本节点</title>
  </head>
  <body>
    <script>
        /*
        网页中的所有文本内容都是文本节点对象
            - element.textContent 获取或修改元素的文本内容
                - 获取的是标签中的原始内容，不考虑css样式
            - element.innerText 获取或修改元素的文本内容
                - 当修改的内容中包含html元素时，会自动进行转义
                - 获取内容时，会考虑css的样式（展示什么获取的就是什么）
                - 读取css样式时，会触发网页的重排（计算css样式）
            - element.innerHtml 获取或修改元素中的html代码
                - 插入内容时，有xss注入风险
        */
    </script>
  </body>
</html>

```



### 5. 属性节点

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>属性节点</title>
  </head>
  <body>
    <input id="aaaa" class="aaa" type="text" name="username" value="admin"/>

    <script>
        /*
        属性节点（Attr）:
            - 如何操作属性节点：
                方式一：
                    - 读取：元素.属性名（注：class属性需要使用calssName来读取）
                    - 修改：元素.属性名 = 属性值
                方式二：
                    - 读取：元素.getAttribute(属性名)
                    - 修改：元素.setAttribute(属性名, 属性值)
                    - 删除：元素.removeAttribute(属性名)
        */

        const input = document.querySelector("[name=username]")
        console.log('input.type:', input.type)
        console.log('input.id:', input.id)
        console.log('input.class:', input.className)
        console.log('input.name:', input.name)
        console.log('input.value:', input.value)
        
        //input.value = '孙笑川'

        console.log('---------------------------------------');
        
        console.log('input.getAttribute(\'type\'):', input.getAttribute('type'))
        console.log('input.getAttribute(\'id\'):', input.getAttribute('id'))
        console.log('input.getAttribute(\'class\'):', input.getAttribute('class'))
        console.log('input.getAttribute(\'name\'):', input.getAttribute('name'))
        console.log('input.getAttribute(\'value\'):', input.getAttribute('value'))

        input.setAttribute('value', '药水哥')
        input.setAttribute('disabled', true)
        input.removeAttribute('disabled')
    </script>
  </body>
</html>

```



### 6. 事件

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>事件</title>
  </head>
  <body>
    <!-- <button id="btn" onclick="alert('直接在元素的属性中设置')">点一下</button> -->
    
    <button id="btn">点一下</button>
    <script>
        /*
        事件（Event）：
            - 用户与网页之间发生的交互行为
            - 可以通过为事件绑定响应函数，来完成与用户之间的交互
            - 绑定方式：
                1. 直接在元素的属性中设置
                2. 为元素的指定属性设置回调函数来绑定（一个事件只能绑定一个响应函数，后来者居上）
                3. 通过元素的 addEventListener() 方法来绑定（可以重复绑定，先绑定的谁，谁先执行）
        */

        const btn = document.getElementById('btn')
        //btn.onclick = function() {
        //    alert('为元素的指定属性设置回调函数来绑定')
        //}

        btn.addEventListener("click", function() {
            alert('通过元素的 addEventListener() 方法来绑定')
        })
    </script>
  </body>
</html>

```



### 7. 文档的加载

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>文档的加载</title>
    <script>
        // 方式二
        //window.onload = function() {
        //    const btn = document.getElementById('btn')
        //    console.log(btn)
        //}

        //window.addEventListener('load', function() {
        //   const btn = document.getElementById('btn')
        //   console.log(btn)
        //})

        // 方式三
        //document.addEventListener('DOMContentLoaded', function() {
        //    const btn = document.getElementById('btn')
        //    console.log(btn)
        //})
    </script>
    <script defer src="./script/index.js"></script>
  </head>
  <body>
    <button id="btn">点一下</button>
    <script>
        /*
        网页是自上而下加载的，如果将js代码编写到网页的上边（之前），js代码在执行时，网页还没有加载完毕，这时会出现无法获取DOM的情况

        解决方案：
            1. 将 script 标签放到 body 之后
                - 所有方式中优先级最高
            2. 将js代码写到 window.onload 的回调函数中
                - 网页中所有的文档加载完后才执行
            3. 将js代码写到 document 对象的 DOMContentLoaded 的回调函数中
                - 当前文档加载完毕之后触发（相比方式二执行时机更早）
            4. 将代码编写到外部的js文件中，然后以defer的形式引入
                - 相比方式三执行时机更早
        */

        // 方式一
        //const btn = document.getElementById('btn')
        //console.log(btn)
        
    </script>
  </body>
</html>

```



index.js

```js
const btn = document.getElementById('btn')
console.log(btn)
```



### 8. DOM的修改

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>DOM的修改</title>
  </head>
  <body>
    <button id="btn1">添加元素</button>
    <button id="btn2">删除元素</button>
    <button id="btn3">替换元素</button>
    <ul id="list">
        <li id="sxc">孙笑川</li>
        <li id="ysg">药水哥</li>
    </ul>
    <script>
        const list = document.getElementById('list')

        const btn1 = document.getElementById('btn1')
        btn1.onclick = function() {
            // 创建新的li
            const li = document.createElement('li')
            li.innerText = 'Giao哥'
            li.id = 'gg'

            // appendChild() 给节点添加子节点
            //list.appendChild(li)

            /*
            insertAdjacentElement()：
                - 向元素的指定位置添加元素
                - 参数：
                    1. 要添加的位置：
                        - beforeend 标签的最后
                        - afterbegin 标签的开始
                        - beforebegin 在当前元素的前边插入（即插入兄弟元素）
                        - afterend 在当前元素的后边插入（即插入兄弟元素）
                    2. 要添加的元素
            */
            //list.insertAdjacentElement('beforeend', li)

            // 同理 insertAdjacentElement() 方法
            list.insertAdjacentHTML('beforeend', '<li id="gg">Giao哥</>')
        }

        const btn2 = document.getElementById('btn2')
        btn2.onclick = function() {
            const sxc = document.getElementById('sxc')
            sxc.remove()
        }

        const btn3 = document.getElementById('btn3')
        btn3.onclick = function() {
            const sxc = document.getElementById('sxc')
            
            const li = document.createElement('li')
            li.innerText = 'Giao哥'
            li.id = 'gg'
            sxc.replaceWith(li)
        }
    </script>
  </body>
</html>

```



### 9. XSS注入问题

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>XSS注入问题</title>
  </head>
  <body>
    <script>
        /*
        在涉及到需要将用户输入的内容插入到网页中时，
        尽量使用 元素.innerText 或 元素.textContent 方法代替 元素.innerHTML 方法
        前两种方法在赋值时会进行转义
        */
    </script>
  </body>
</html>

```



### 10. 节点的复制

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>节点的复制</title>
</head>
<body>
    <button id="btn">复制节点</button>
    <ul id="list1">
        <li id="l1">孙笑川</li>
        <li id="l2">药水哥</li>
        <li id="l3">Giao哥</li>
    </ul>

    <ul id="list2">
        <li>刘波</li>
    </ul>

    <script>
        // 点击按钮后，复制list1的节点到list2
        const list1 = document.getElementById('list1')
        const list2 = document.getElementById('list2')

        const l1 = document.getElementById('l1')

        const btn = document.getElementById('btn')
        btn.onclick = function() {
            /*
            cloneNode() 方法：
                - 参数：
                    - false（默认值） 复制节点的所有属性，但不包括子节点
                    - true 复制所有信息，包括子节点
            */
           const newL1 = l1.cloneNode(true)
           newL1.id = 'newL1'
           list2.appendChild(newL1)
        }
    </script>
</body>
</html>
```



### 11. 修改css样式

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改css样式</title>
    <style>
        .box1 {
            width: 200px;
            height: 200px;
            background-color: lightblue;
        }

        .box2 {
            width: 300px;
            height: 300px;
            border: 1px black solid;
            background-color: lightgreen;
        }
    </style>
</head>
<body>
    <button id="btn">修改样式</button>
    <hr/>

    <div class="box1"></div>

    <script>
        // 点击按钮后修改box1的样式
        const box1 = document.getElementsByClassName('box1')[0]

        const btn = document.getElementById('btn')

        // 直接修改样式（不建议直接使用）
        // btn.onclick = function() {
        //     // 修改宽度
        //     // 元素.style.样式名（小驼峰） = 样式值
        //     box1.style.width = '300px'
        //     box1.style.backgroundColor = 'lightgreen'    
        // }

        // 通过class属性间接修改样式
        btn.onclick = function() {
            // 不建议这么操作
            // box1.className += ' box2'

            /*
            元素.classList:
                - 是一个对象，提供了对当前元素的 class 的各种操作方法
                - 常用方法：
                    - add(): 向元素中添加一个或多个 class
                    - remove(): 移除指定 class
                    - toggle(): 切换元素的class（一次只能操作一个）
                    - replace(): 替换 class
                    - contains(): 检查是否包含指定 class
            */
            // box1.classList.add('box2', 'box3')
            // box1.classList.remove('box2')
            // box1.classList.toggle('box2')
            // box1.classList.replace('box1', 'box2')
            console.log(box1.classList.contains('box1'))

        }
    </script>
</body>
</html>
```



### 12. 读取css样式

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>读取css样式</title>
    <style>
        .box1 {
            width: 200px;
            height: 200px;
            background-color: lightblue;
        }

        .box1::before {
            color: blueviolet;
            content: '测试';
        }
    </style>
</head>
<body>
    <button id="btn">读取样式</button>
    <hr/>

    <div class="box1"></div>

    <script>
        // 点击按钮后读取css样式
        const box1 = document.getElementsByClassName('box1')[0]

        const btn = document.getElementById('btn')
        btn.onclick = function() {
            /*
            getComputedStyle():
                - 读取指定元素所有生效的样式，以对象的形式返回
                - 参数：
                    1. 要获取样式的对象
                    2. 要获取的伪元素
                - 注：样式对象中返回的值不一定能直接拿来计算，
                        有可能是 auto 等等
            */
            const styleObj = getComputedStyle(box1)
            console.log(styleObj)

            const box1Before = getComputedStyle(box1, '::before')
            console.log(box1Before.content)
        }
    </script>
</body>
</html>
```



### 13. 通过属性读取样式

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>通过属性读取样式</title>
    <style>
        .box1 {
            width: 200px;
            height: 200px;
            background-color: lightblue;
            margin: 50px;
            padding: 50px;
            border: 2px black solid;
            overflow: auto;
        }

        .box2 {
            width: 100px;
            height: 500px;
            background-color: lightcoral;
        }
    </style>
</head>
<body>
    <button id="btn">读取样式</button>
    <hr/>
    <div id="box1" class="box1">
        <div id="box2" class="box2"></div>
    </div>

    <script>
        const btn = document.getElementById('btn')
        const box1 = document.getElementById('box1')

        btn.onclick = function() {
            /*
            获取元素内部的高度和宽度（包括内容区和内边距）：
                元素.clientWidth
                元素.clientHeight

            获取元素的可见框的大小（包括内容区，内/外边距）：
                元素.offsetHeight
                元素.offsetWidthyuan
            
            获取元素滚动区域的大小：
                元素.scrollHeight
                元素.scrollWidth
            
            获取元素的定位父元素：
                元素.offsetParent
                注：定位父元素：离当前元素最近的开启了定位的祖先元素，如果都没有开启，则返回body
            
            获取元素相对于其定位父元素的偏移量：
                元素.offsetTop
                元素.offsetLeft
            
            获取元素滚动条的偏移量：
                元素.scrollTop
                元素.scrollLeft
            */
            console.log(box1.scrollTop)
            console.log(box1.scrollLeft)

        }
    </script>
</body>
</html>
```



### 14. 事件

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>事件</title>
    <style>
        .box1 {
            width: 300px;
            height: 300px;
            background-color: lightblue;
            border: 1px black solid;
        }
    </style>
  </head>
  <body>
    <div id="box1" class="box1"></div>

    <script>
        /*
        事件（Event）：
            - 浏览器在事件触发时所创建的对象
            - 如：鼠标的坐标、键盘的按键等等
            - 浏览器在创建事件对象后，会将其作为响应函数的参数传递
              可以在事件的回调函数中定义一个形参来接收事件对象
        */

        const box1 = document.getElementById('box1')

        // box1.onmousemove = function(event) {
        //     console.log(event)
        // }

        // box1.onmousemove = event => {
        //     console.log(event)
        // }

        // box1.addEventListener('mousemove', function() {
        //     console.log(event)
        // })

        box1.addEventListener('mousemove', event => {
            console.log(event)
        })
        
    </script>
  </body>
</html>

```



### 15.事件对象

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>事件对象</title>
    <style>
        .box1 {
            width: 300px;
            height: 300px;
            background-color: lightblue;
            border: 1px black solid;
        }

        .box2 {
            width: 150px;
            height: 150px;
            background-color: lightcoral;
            border: 1px black solid;
        }

        .box3 {
            width: 75px;
            height: 75px;
            background-color: lightseagreen;
            border: 1px black solid;
        }
    </style>
  </head>
  <body>
    <div id="box1" class="box1">
        <div id="box2" class="box2">
            <div id="box3" class="box3"></div>
        </div>
    </div>

    <a id="test" href="https://www.bing.com" target="_blank">点击跳转</a>
    <script>
        /*
        DOM中有多种不同类型的事件对象：
            - 共同祖先 Event：
                - event.target 表示触发事件的对象
                - event.currentTarget 绑定事件的对象（与this同理）
                - event.stopPropagation() 停止事件的传递
                - event.preventDefault() 取消事件的默认行为（推荐使用）
            - 事件的冒泡（bubble）：
                - 指事件的向上传递
                - 当元素上的某个事件被触发后，其祖先元素上的相同的事件也被同时触发
        */
        const box1 = document.getElementById('box1')
        box1.onclick = event => {
            console.log('box1', event.target)
            alert('box1')
        }

        const box2 = document.getElementById('box2')
        box2.onclick = event => {
            console.log('box2', event.target)
            alert('box2')
        }

        const box3 = document.getElementById('box3')
        box3.onclick = event => {
            console.log('box3', event.target)

            // 停止冒泡事件
            event.stopPropagation()
            alert('box2')
        }

        const test = document.getElementById('test')
        test.addEventListener('click', event => {
            // 取消事件的默认行为
            event.preventDefault()
            
            alert('点击后不进行超链接跳转')
        })
    </script>
  </body>
</html>

```



### 16. 事件的冒泡

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>事件的冒泡</title>
    <style>
      .box1 {
        width: 75px;
        height: 75px;
        background-color: lightblue;
        border-radius: 50%;
        position: absolute;
      }

      .box2 {
        width: 300px;
        height: 300px;
        background-color: lightcoral;
      }

      .box3 {
        width: 200px;
        height: 200px;
        background-color: lightgreen;
      }

      .box4 {
        width: 150px;
        height: 150px;
        background-color: lightgray;
        position: absolute;
        bottom: 0;
      }
    </style>
  </head>
  <body>
    <div id="box1" class="box1"></div>

    <div id="box2" class="box2"></div>

    <div id="box3" class="box3" onclick="alert('box3')">
      <div id="box4" class="box4" onclick="alert('box4')"></div>
    </div>

    <script>
      // 使box1跟随鼠标移动
      document.addEventListener("mousemove", (event) => {
        box1.style.left = event.x + "px"
        box1.style.top = event.y + "px"
      })

      const box2 = document.getElementById("box2")
      box2.addEventListener("mousemove", (event) => {
        event.stopPropagation()
      })

      // 点击box4依然会触发事件冒泡，弹出box3，即事件的冒泡与元素的样式无关，只与结构有关
    </script>
  </body>
</html>

```



### 17. 事件委派

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>事件委派</title>
  </head>
  <body>
    <button id="btn">添加</button>

    <ul id="list">
      <li><a href="javascript:;">链接一</a></li>
      <li><a href="javascript:;">链接二</a></li>
      <li><a href="javascript:;">链接三</a></li>
      <li><a href="javascript:;">链接四</a></li>
    </ul>

    <script>
      const links = document.getElementsByTagName("a")
      // for (let i = 0; i < links.length; i++) {
      //     links[i].addEventListener('click', event => {
      //         alert(links[i].textContent)
      //     })
      // }

      const list = document.getElementById("list")
      const btn = document.getElementById("btn")
      btn.addEventListener("click", () => {
        list.insertAdjacentHTML("beforeend", `<li><a href="javascript:;">链接${Math.ceil(Math.random() * 10)}</a></li>`)
      })

      //需求：只进行一次事件绑定，即可让所有的超链接（不管是原有的还是新添加的）都具有指定的事件
      document.addEventListener("click", (event) => {
        if ([...links].includes(event.target)) {
            alert(event.target.textContent)
        }
      })

      /*
      事件委派：
        - 将原本绑定给多个元素的事件，统一绑定给document（此处以绑定给document为例，绑定的对象随业务场景的不同而不同）
        - 降低代码的复杂度
      */
    </script>
  </body>
</html>

```



### 18. 事件的捕获

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>事件的捕获</title>
    <style>
      .box1 {
        width: 300px;
        height: 300px;
        background-color: lightblue;
      }

      .box2 {
        width: 200px;
        height: 200px;
        background-color: lightcoral;
      }

      .box3 {
        width: 100px;
        height: 100px;
        background-color: lightgrey;
      }
    </style>
  </head>
  <body>
    <div id="box1" class="box1">
      <div id="box2" class="box2">
        <div id="box3" class="box3"></div>
      </div>
    </div>

    <script>
        /*
        事件的传播机制：
            - 在DOM中，分为三个阶段：
                1. 捕获阶段（由祖先元素向目标元素进行事件的捕获）
                2. 目标阶段（触发事件的对象）
                3. 冒泡阶段（由目标元素向祖先元素进行事件的冒泡）
                    - 默认情况下是在冒泡阶段触发事件
            - 事件的捕获：
                - 指事件从外向内的传导
                - document -> html -> body -> 具体元素
                - 默认情况下，事件不会再捕获阶段触发
                - 可以通过设置 元素.addEventListener() 方法的第三个参数为true来在捕获阶段触发事件（一般不这么使用）
        */
       const box1 = document.getElementById('box1')
       const box2 = document.getElementById('box2')
       const box3 = document.getElementById('box3')

       // 通过设置第三个参数为true，在捕获阶段触发点击事件
       // 此时 alert 的顺序为：box1 -> box2 -> box3
       // 一般情况下不会这么使用
       box1.addEventListener('click', event => {
        alert('box1 - ' + event.eventPhase)
        // event.eventPhase 事件触发的阶段
        // 1：捕获阶段 2：目标阶段 3：冒泡阶段
       }, true)

       box2.addEventListener('click', event => {
        alert('box2 - ' + event.eventPhase)
       }, true)

       box3.addEventListener('click', event => {
        alert('box3 - ' + event.eventPhase)
       }, true)
    </script>
  </body>
</html>

```



### 19. BOM对象

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>BOM对象</title>
  </head>
  <body>
    <script>
        /*
        BOM:
            - 浏览器对象模型
            - 通过BOM对象，可以完成对浏览器的各种操作
            - 五个BOM对象：
                - Window：浏览器窗口（全局对象）
                - Navigator：浏览器的对象
                - Location：浏览器的地址栏信息
                - History：浏览器的历史记录（控制浏览器的前进后退）
                - Screen：屏幕信息
        */
    </script>
  </body>
</html>

```



### 20. Navigator

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Navigator</title>
  </head>
  <body>
    <script>
        /*
        Navigator（浏览器的对象）：
            - mdn文档：https://developer.mozilla.org/en-US/docs/Web/API/Navigator
        */

        console.log(navigator.userAgent)
        
    </script>
  </body>
</html>

```



### 21. Location

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Location</title>
  </head>
  <body>
    <button id="btn">点一下</button>
    
    <script>
        /*
        Location（浏览器的地址栏信息）：
            - mdn文档：https://developer.mozilla.org/en-US/docs/Web/API/Location
        */
       const btn = document.getElementById('btn')
       btn.addEventListener('click', () => {
        // 可以直接给location赋值，表示跳转到指定地址
        // location = 'https://www.bing.com'
        
        // 等价于直接赋值
        //location.assign('https://www.bing.com')

        // 执行替换之后不能通过历史记录回退
        //location.replace('https://www.bing.com')

        // 刷新页面
        // location.reload()
        // 强制清缓存刷新 Ctrl + F5
        location.reload(true)
       })
    </script>
  </body>
</html>

```



### 22. History

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>History</title>
  </head>
  <body>
    <button id="btn">点一下</button>

    <script>
        /*
        History（浏览器的历史记录）：
            - mdn文档：https://developer.mozilla.org/en-US/docs/Web/API/History
        */

        const btn = document.getElementById('btn')
        btn.addEventListener('click', () => {
            // 后退
            // history.back()

            // 前进
            // history.forward()

            // 1：前进1，-1：后退1
            history.go(1)
        })
        console.log('历史记录的数量：', history.length)
        
    </script>
  </body>
</html>

```



### 23. 定时器

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>定时器</title>
</head>
<body>
    <div id="num"></div>
    <script>
        /*
        定时器：
            - 让代码在指定时间后执行
            - 设置方式：
                1. setTimeout():
                    - 参数：
                        1. 回调函数
                        2. 延迟的时间（单位毫秒）
                    - 关闭定时器：
                        - clearTimeout()
                2. setInterval():
                    - 每间隔一段时间，代码就执行一次
                    - 参数：
                        1. 回调函数
                        2. 延迟的时间（单位毫秒）
                    - 关闭定时器：
                        - clearInterval()
        */

        const timer = setTimeout(() => {
            alert('延迟1.5s再执行')
        }, 1500)

        // 关闭定时器
        clearTimeout(timer)

        const timer1 = setInterval(() => {
            alert('延迟2s再执行')
        }, 2000)

        clearInterval(timer1)

        // const num = document.getElementById('num')

        // let index = 0
        // setInterval(() => {
        //     index ++
        //     num.textContent = index
        // }, 2000)

        /*
        定时器的本质，即在指定时间后将函数添加到消息队列中
        注：函数实际的执行时间要看调用栈的情况
        */
        // console.time()
        // setTimeout(() => {
        //     console.timeEnd()
        //     console.log('setTimeout()定时器执行了...')

        //     // 打印结果为：
        //     // default: 5003.26904296875 ms
        //     // 定时器执行了...
        // }, 3000)

        // const begin = Date.now()
        // while (Date.now() - begin < 5000) {}

        /*
        setInterval()方法每隔一段时间就将函数添加到消息队列中，
        但函数执行的间隔时间受函数自身执行速度的影响，所以每次的间隔时间不一定是定时器中指定的间隔时间
        */
        // console.time()
        // setInterval(() => {
        //     console.timeEnd()
        //     console.log('setInterval()定时器执行了...')
        //     alert('setInterval()')
        //     console.time()
        // }, 3000)

        // 需求：确保函数每一次执行的间隔时间都相同
        // console.time()
        // setTimeout(function test() {
        //     console.timeEnd()
        //     console.log('确保函数每一次执行的间隔时间都相同')
        //     console.time()

        //     setTimeout(test, 3000)
        // }, 3000)

        // 打印结果为 222 \n 111
        // console.log(222)在主调用栈中，优先级最高
        // console.log(111)是在定时器等待0ms后将函数投递到消息队列中
        setTimeout(() => {
            console.log(111)
        }, 0)

        console.log(222)
        
    </script>
</body>
</html>
```



### 24. 事件循环

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>事件循环</title>
  </head>
  <body>
    <button id="btn">点一下</button>
    <button id="btn1">消息队列测试</button>
    <script>
        /*
        事件循环（Event Loop）：
            - 函数在每次执行时，都会产生一个执行环境
            - 执行环境负责存储函数执行时产生的一切数据
        
        调用栈（Call Stack）：
            - 后进先出（后来者居上）
            - 负责存储函数的执行环境
            - 当一个函数被调用时，它的执行环境会作为一个栈帧，
              插入到调用栈的栈顶，执行完之后改栈帧会自动弹出
        
        消息队列：
            - 先进先出
            - 负责存储将要执行的函数
            - 当触发一个事件时，其执行函数并不是直接添加到调用栈中
              即：事件触发后，js引擎将事件响应函数插入到消息队列中排队
        */

        function test() {
            console.log('执行test函数')
            
            function test1() {
                console.log('执行test1函数')
                
            }

            console.log('test执行完毕')
        }

        // test()

        const btn = document.getElementById('btn')
        btn.addEventListener('click', () => {
            alert('btn按钮')

            const begin = Date.now()
            while (Date.now() - begin < 3000) {}
        })

        const btn1 = document.getElementById('btn1')
        btn1.addEventListener('click', () => {
            alert('btn1按钮')
        })
    </script>
  </body>
</html>

```

