### 一、Vue

1. 想让Vue工作，就必须创建一个Vue实例，且要传入一个配置对象

2. root容器中的代码依然符合html规范

3. root容器中的代码被称为Vue模板



### 二、模板语法

1. 插值语法

   用于解析标签体内容，

   `{{xxx}}`  xxx是js表达式，且可以直接读取到data中的所有属性

2. 指令语法

   用于解析标签（包括：标签属性、标签体内容、绑定事件）

   例：`v-bind:href="xxx"` 或简写为`:href="xxx"`



### 三、数据绑定

1. 单向绑定v-bind：数据只能从data流向页面
2. 双向绑定v-model：data <===> 页面，双向

注：双向绑定一般应用在表单类元素上（input、select等）

​		`v-model:value`可以简写为`v-model`，因为它默认收集的就是value的值



### 四、el与data的两种写法

**el的两种写法：**

1. new Vue时配置el属性
2. 先创建Vue实例，之后通过`vm.$mount('#root')`指定el的值

**data的两种写法：**

1. 对象式
2. 函数式（使用组件时，必须使用函数式）

注：由Vue管理的函数，不能写箭头函数（作用域）



### 五、MVVM模型

1. M：Model模型，对应data中的数据
2. V：View视图，模板
3. VM：ViewModel视图模型，Vue实例对象

data中所有的属性，最后都出现在了vm身上；vm身上所有的属性及Vue原型上的所有属性，在Vue模板中都可以直接使用



### 六、数据代理

1. Vue中的数据代理：

   通过vm对象来代理data对象中属性的操作（读/写）

2. 优点：

   方便操作data中的数据

3. 基本原理：

   通过Object.defineProperty()把data对象中所有属性添加到vm上，同时指定getter/setter，在getter/setter内部去操作（读/写）data中对应的属性



### 七、事件处理

**1.事件的基本使用**

1. 使用`v-on:click="xxx"` 或`@click="xxx"`绑定事件，xxx是事件名
2. 事件的回调需要配置在methods对象中，最终会在vm上
3. methods中配置的函数，不要用箭头函数，this的作用域会被改变
4. methods中配置的函数都是是被Vue所管理的函数，this的指向是vm或组件实例对象
5. `@click="xxx"` 和 `@click="xxx($event)"` 的作用一样，后者可以传参



**2.事件修饰符**

1. prevent：阻止默认事件（常用）；
2. stop：阻止事件冒泡（常用）；
3. once：事件只触发一次（常用）；
4. capture：使用事件的捕获模式；
5. self：只有event.target是当前操作的元素时才触发事件；
6. passive：事件的默认行为立即执行，无需等待事件回调执行完毕；



**3.键盘事件**

1. 常用的按键别名
   + 回车 enter
   + 删除 delete （捕获”删除“和”退格“按键）
   + 退出 esc
   + 空格 space
   + 换行 tab（必须配合`keydown`使用）
   + 上 up
   + 下 down
   + 左 left
   + 右 right
2. Vue未提供别名的按键，可以使用原始的key值绑定，需转换为kebab-case（短横线命名）
3. 系统修饰键：ctrl、alt、shift、meta
   1. 配合keyup使用：按下修饰键的同时，再按下其他键，随后释放其他键，事件才被触发
   2. 配合keydown使用：正常触发事件
4. 可以使用keycode指定具体的按键
5. `Vue.config.keyCode.自定义键名 = 键码`，可以定制按键别名



### 八、计算属性

1. 定义：要用的属性不存在，要通过已有属性计算得来

2. 原理：底层借助了Object.defineproperty方法提供的getter和setter

3. get什么时候执行：

   + 初次读取时
   + 当依赖的属性发生改变时会被再次调用

4. 优点：与methods相比，内部有缓存机制（复用），效率更高，方便调试

5. 注：计算属性最终会出现在vm身上，直接读取使用即可；

   ​		如果计算属性要被修改，必须写set函数去响应修改，且set中要引起计算时依赖的数据发生改变；



### 九、监视属性

1. 当被监视的属性发生变化时，回调函数自动调用，进行相关操作
2. 监视的属性必须存在，才能进行监视
3. 两种写法：
   + new Vue时传入watch配置
   + 通过vm.$watch监视



**深度监视：**

1. Vue中的watch默认不监测对象内部值得改变（一层）；
2. 配置`deep:true`可以监测对象内部值改变（多层）

注：Vue自身可以监测对象内部值得改变，但Vue提供的watch默认不可以

​		使用watch时应根据数据的具体结构，决定是否采用深度监视



**computed与watch的区别：**

1. computed能完成的功能，watch也能完成；watch能完成的功能，computed不一定能完成，如：watch可以进行异步操作
2. 所有被Vue管理的函数，最好写成普通函数，这时**this**的指向才是**vm**或**组件实例对象**
3. 所有不被Vue管理的函数（定时器回调，ajax的回调，Promise的回调等），最好写成箭头函数，这样this的指向才是**vm**或**组件实例对象**（涉及Vue原型链）



### 十、绑定样式

#### 1. class样式

语法：`:class="xxx"`，xxx可以是字符串、对象、数组

字符串适用于：类名不确定，要动态获取；

对象使用于：要绑定的样式个数、名字不确定；

数组适用于：要绑定的样式个数、名字确定，但需要动态决定是否使用；



#### 2. style样式

语法：`:style="{fontSize: xx + 'px'}"`，xxx为动态值

​			`:style:="[a,b]"`，ab为数组形式



### 十一、条件渲染

#### 1. v-if

语法：`v-if="表达式"`

​			`v-else-if="表达式"`

​			`v-else`

适用于：切换频率较低的场景

特点：不展示的DOM元素直接被移除

注：`v-if`可以和`v-else-if、v-else`一起使用，但要求结构完整，不能被中间打断



#### 2. v-show

语法：`v-show="表达式"`

适用于：切换频率较高的场景

特点：不展示的DOM元素不会被移除，仅仅是使用样式隐藏



### 十二、列表渲染

#### 1. 基本列表

v-for指令：

1. 用于展示列表数据
2. 语法：`v-for="(item,index) in list" :key="index"`
3. 可以遍历数组，对象，字符串，指定次数



#### 2. key的原理

1. 虚拟DOM中key的作用：

   key是虚拟DOM对象的标识，当状态中的数据发生变化时，Vue会根据**新数据**生成**新的虚拟DOM**，随后进行新旧虚拟DOM的**差异比较（diff算法）**；

2. 对比规则

   （1）旧虚拟DOM中找到了与新虚拟DOM相同的key

   + 若虚拟DOM中内容没变，直接使用之前的真实DOM；

   + 若虚拟DOM中内容改变，则生成新的真实DOM，随后替换页面中之前的真实DOM

   （2）旧虚拟DOM中没有找到与新虚拟DOM相同的key

   + 创建新的真实DOM，随后渲染到页面

3. 用index作为key可能引发的问题

   （1）若对数据进行**逆序**添加、删除等**破坏原有顺序**的操作，会产生没必要的真实DOM更新（界面没问题，但效率低）

   （2）如果结构中还包含输入类的DOM，会产生错误的DOM更新，界面出现问题

4. 如何选择key

   （1）建议使用每条数据的唯一标识作为key

   （2）如果不存在对数据进行**逆序**添加、删除等**破坏原有顺序**的操作，仅用于渲染列表进行展示，也可以使用index下标索引作为key

   

#### 3. Vue监视数据的原理

1. Vue会监视data中所有层次的数据

2. 如何监测对象中的数据？

   通过`setter`，在`new Vue`时就传入要监测的数据

   （1）对象中后追加的属性，Vue默认不做响应式处理

   （2）针对（1）中的问题，可以使用如下API：

   ```vue
   Vue.set(target, propertyName/index, value) 或
   vm.$set(target, propertyName/index, value)
   ```

3. 如何监测数组中的数据？

   通过包裹数组更新元素的方法实现，即：

   （1）调用原生对应的方法

   （2）重新解析模板 ---> 更新页面

4. 修改数组元素的方法

   （1）API：`push(),pop(),shift(),unshift(),ssplice(),sort(),reverse()`

   （2）`Vue.set()`或`vm.$set`

   注：`Vue.set()`或`vm.$set` **不能**给`vm`或`vm`的根数据对象添加属性



### 十三、收集表单数据

1. `<input type="text">`，`v-model`收集的是value值，用户输入的就是value值；

2. `<input type="radio">`，`v-model`收集的是value值，且要给标签配置value属性；

3. `<input type="checkbox">`

   （1）如果没有配置input的value属性，那么收集的是`checked`（勾选/未勾选，布尔值）；

   （2）配置了input的value属性：

   ​			a. `v-model`的初始值是非数组，那么收集的是`checked`（勾选/未勾选，布尔值）；

   ​			b. 反之，初始值是数组，那么收集的就是value组成的数组

4. 注：`v-model`的三个修饰符：

  **lazy：**失去焦点再收集数据；

  **number：**输入字符串转为有效的数字；

  **trim：**输入首尾空格过滤



### 十四、过滤器

**定义：**对要显示的数据进行特点格式化后再显示

**语法：**

1. 注册过滤器：`Vue.filter(name,callback)` 或 `new Vue(filters:{})`
2. 使用：`{{xxx | filterName}}`（全局注册） 或 `v-bind:xxx="xxx | filterName"` （局部过滤器）

**注：**

1. 过滤器可以接受额外的参数，多个过滤器可以串联（使用管道符 `|`连接 ），并按顺序执行
2. 没有改变原有的数据，是产生新的对应的数据



### 十五、内置指令

#### 1. v-text

作用：向其所在的节点中渲染文本内容

注：v-text会替换掉节点中的内容，{{xxx}}则不会



#### 2. v-html

作用：向指定节点渲染包含html结构的内容

与插值语法的区别：1. v-html会替换掉节点中所有的内容，{{xxx}}不会；2. v-html可以识别html结构

注：存在安全性问题，1. 在网站上动态渲染任意html很危险，容易导致XSS攻击；2. 提交内容上不使用html



#### 3. v-cloak

定义：本质是一个特殊属性（没有值），Vue实例创建完毕并接管容器后，会删掉`v-cloak`属性

使用css配合该指令，可以解决网速慢等原因引起的页面直接展示{{xxx}}的问题



#### 4. v-once

定义：`v-once`所在节点在初次动态渲染后，就视为静态内容了

以后数据的改变不会引起`v-once`所在结构的更新，可用于性能优化



#### 5. v-pre

定义：跳过其所在节点的编译过程

可以利用该指令跳过：没有使用指令语法、插值语法的节点，加快编译



### 十六、自定义指令

**语法：**

1. 局部指令

   ```vue
   new Vue({
   	directives: {指令名:配置对象}
   })
   或
   new Vue({
   	directives: {指令名:回调函数}
   })
   ```

2. 全局指令

   ```vue
   Vue.directive(指令名,配置对象)
   或
   Vue.directive(指令名,回调函数)
   ```

**配置对象中常用的三个回调：**

1. bind：指令与元素成功绑定时调用
2. inserted：指令所在元素被插入页面时调用
3. update：指令所在的模板被重新解析时调用

**注：**

1. 指令定义时不加 **`v-`**，但使用时要加 **`v-`**
2. 指令名如果是多个单词，要使用`kebab-case`（短横杠）命名方式，不使用`camelCase`命名方式



### 十七、生命周期

*beforeCreate、created、beforeMount、mounted、beforeUpdate、updated、beforeDestroy、destroyed* 还有三个待更新

**定义：**

1. 又名：生命周期回调函数、生命周期函数、生命周期钩子；
2. Vue在关键时刻帮我们调用的一些特殊名称的函数；
3. 名称不可更改，函数的具体内容根据实际情况编写；
4. 生命周期函数中的`this`指向的是**vm** 或 **组件实例对象**



**常用的生命周期钩子：**

1. mounted：发送ajax请求、启动定时器、绑定自定义事件、订阅消息等；
2. beforeDestroy：清除定时器、解绑自定义事件、取消订阅等；



**关于销毁Vue实例：**

1. 销毁后Vue开发者工具看不到任何信息；
2. 销毁后自定义事件会失效，但原生DOM事件依然有效；
3. 一般不在`beforeDestroy`操作数据，在这个钩子函数中即使操作了数据，也不会再触发更新流程



### 十八、组件

*概念：局部功能或代码的集合*

#### 1.使用组件的三个步骤

（1）定义（创建组件）

（2）注册组件

（3）使用组件（写组件标签）



#### 2. 如何定义一个组件

使用`Vue.extend(options)`创建，其中`options` 和 `new Vue(options)`传入的**options几乎一致**，区别如下：

（1）组件中不写`el`，因为最终所有的组件都要经过一个vm管理，由`vm`中的 `el`决定服务于哪个容器；

（2）data必须写成函数，避免组件被复用时数据存在引用关系；

注：使用`template`可以配置组件结构



#### 3. 如何注册组件

（1）局部注册：`new Vue()`时传入`components`选项；

（2）全局注册：`Vue.component('组件名', 组件位置)`



#### 4. 编写组件标签

`<demo></demo>`



#### 5. 几个注意事项

（1）组件名：

+ 一个单词组成：
  + 首字母小写 demo
  + 首字母大写 Demo
+ 多个单词组成
  + kebab-case：my-demo
  + CamelCase（大驼峰）：MyDemo（需在vue-cli脚手架环境中）
+ 注：组件名尽可能回避html关键字；可以使用name配置项指定组件的名称



（2）组件标签

+ 第一种写法：<demo></demo>

+ 第二种写法：<demo/>

  ==注：第二种写法如果不在脚手架环境中，会导致后续组件无法渲染==



（3）简写形式

`const demo = Vue.extend(options)` 可简写为：`const demo = options`



#### 6. VueComponent

（1）demo组件本质是一个名为VueComponent的构造函数，由`Vue.extend`生成；

（2）只需写`<demo>` 或 `<demo></demo>`，Vue解析时会帮我们创建demo组件的实例对象；

（3）每次调用Vue.extend，返回的都是一个全新的VueComponent

（4）关于**this**指向

+ 组件配置中：data，methods，watch，computed等函数中，它们的this指向**均是VueComponent**
+ `new Vue(options)`配置中：data，methods，watch，computed等函数中，它们的this指向**均是Vue实例对象**

（5）VueComponent简称组件实例对象（vc）



#### 7. 一个重要的内置关系

````vue
VueComponent.prototype.__proto__ === Vue.prototype
````

为什么要有这个关系：**让组件实例对象（vc）可以访问到Vue原型上的属性和方法**