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

