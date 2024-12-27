## vue-cli

### 一、脚手架分析

关于不同版本的Vue：

1. vue.js是完整版的Vue，包含核心功能+模板解析器；vue.runtime.xxx.js是运行版的Vue，只包含核心功能
2. 运行时Vue需要使用**render函数**接收到的`createElement`去指定具体内容



### 二、ref属性

1. 被用来给元素或子组件注册引用信息（id的替代者）；
2. 应用在html标签上获取的是真实DOM元素，应用在组件标签上是组件实例对象（vc）；
3. 使用方式：
   + 标识：`<h1 ref="xxx">孙笑川</h1>` 或 `<Demo ref="xxx"/>`
   + 获取：`this.$refs.xxx`



### 三、props配置

功能：让组件接收外部传过来的数据

1. 传递数据：`<Demo name="xxx"/>`

2. 接收数据：

   ```vue
   //1.简单接收
   props: ['name','sex','age']
   
   //2.接收的同时对是数据进行类型限制
   props: {
      name: String,
      sex: String,
      age: Number,
    }
   
   //3.类型限制，默认值指定，是否必传
   props: {
     name: {
       type: String,
       required: true
     },
     sex: {
       type: String,
       required: true
     },
     age: {
       type: Number,
       default: 99
     }
   }
   ```

注：props是只读的，Vue底层会监测你对propos的修改，如果进行了修改，会发出警告；若业务中确实需要修改，可以将该属性复制到data中并定义，修改后定义的数据



### 四、mixin混入

功能：可以把多个组件共用的配置提取成一个混入对象

使用方式：

1. 定义混合

   ```vue
   {
       data() {
       ...
       },
       methods: {
       xxx
       }
   }
   ```

   

2. 使用

（1）全局引入：`Vue.mixin(xxx)`

（2）局部混入：`mixins: [xxx]`



### 五、插件

功能：用于增强Vue

本质：包含`install`方法的一个对象，`install`的第一个参数是`vue`，第二个以后的参数是插件使用者传递的参数

定义插件：

```js
//定义plugins.js
export default {
    install(Vue,x,y,z) {
        console.log('插件被调用了', Vue,x,y,z)

        //全局过滤器
        Vue.filter('mySlice', function (value) {
            return value.slice(0,3)
        })

        //自定义全局指令
        Vue.directive('fbind', {
            bind(element, binding) {
                element.value = binding.value
            },
            inserted(element) {
                element.focus()
            },
            update(element, binding) {
                element.value = binding.value
            }
        })

        //全局混入
        Vue.mixin({
            data() {
                return {
                    x: 100,
                    y: 200
                }
            }
        })

        //给Vue原型上添加一个方法
        Vue.prototype.hello = () => {
            alert('给Vue原型上添加一个方法')
        }
    }
}
```

使用插件：

在`main.js`中`import`之后使用`Vue.use(plugin)`



### 六、scoped样式

作用：让样式在局部生效，防止冲突

写法：`<style scoped>`



### 七、TodoList案例总结

#### 1. 组件化编码流程

（1）拆分静态组件：按照功能点拆分，命名尽量避开关键字

（2）实现动态组件：考虑好数据的存放位置（是一个组件在用还是一些组件在用？）

+ 一个组件在用：数据放组件自身即可
+ 一些组件在用：放在他们共同的父组件身上（<font color='red'>状态提升</font>>）

（3）实现交互：从绑定事件开始



#### 2. props

（1）父传子

（2）子传父（要求父先给子一个回调函数）



#### 3. v-model注意事项

（1）`v-model`绑定的值不能是props传过来的值，props传递的值是**只读**属性

（2）props传递的值若是对象类型，修改对象中的属性时Vue不会报错（未监测到），但不推荐这么做



### 八、webStorage

1. 存储内容一般5MB左右（不同浏览器之间存在差异）；

2. 浏览器端通过`window.sessionStorage` 或 `window.localStorage`属性来实现本地存储机制

3. API

   （1）`xxxStorage.setItem(key,value)` 该方法接受一个键和值作为参数，会把键值对添加到存储中，如果键名已存在，会更新其对应的值；

   （2）`xxxStorage.getItem(key)` 返回键名对应的值；

   （3）`xxxStorage.removeItem(key)` 从存储中删除对应的值；

   （4）`xxxStorage.clear()` 清空存储中的所有数据；

4. 注：（1）`sessionStorage`存储的数据会随浏览器关闭而消失；

      ​		（2）`localStorage`存储的数据需要手动清除；

      ​		（3）`xxxStorage.getItem(key)` 如果对应的值不存在，则返回null；

      ​		（4）`JSON.parse(null)`的结果依然为null；



### 九、组件的自定义事件

1. 定义：一种组件间的通信方式，适用于：子传父

2. 使用场景：A是父组件，B是子组件，B想给A传数据，那么就要在A中给B绑定自定义事件（<font color='red'>事件的回调在A中</font>）

3. 绑定自定义事件：

   （1）方式一：

   ```vue
   <Demo @getName="getName"/>
   或
   <Demo v-on:getName="getName"/>
   ```

   （2）方式二：

   ```vue
   <Demo ref="getName"/>
   
   mounted() {
   	//this.xxx为回调方法
   	this.$refs.getName.$on('getName', this.xxx)
   }
   ```

   （3）若想让自定义事件只触发一次，可以使用once修饰符或$once方法

4. 触发自定义事件：`this.$emit('getName', 参数)`

5. 解绑自定义事件：

      解绑一个：`this.$off('getName')`

      解绑多个：`this.$off(['getName', 'getAge'])`

6. 组件上可以使用`native`修饰符**绑定原生DOM事件**

7. 注：通过`this.$refs.getName.$on('getName', this.xxx)`绑定自定义事件时，<font color='red'>回调要么配置在methods中，要么用箭头函数</font>



### 十、全局事件总线（GlobalEventBus）

1. 定义：一种组件间的通信方式，适用于**任意组件间通信**

2. 安装全局事件总线

   ```vue
   new Vue({
       ......
       beforeCreate() {
           //安装全局事件总线
           Vue.prototype.$bus = this
       }
   })
   ```

3. 使用：

   （1）接收数据：

   A组件想接收数据，则在A组件中给`$bus`绑定自定义事件，且**回调留在A组件自身**

   ```vue
   methods: {
       demo(data) {
           ......
       }
   }
   
   mounted() {
       this.$bus.$on('xxx', this.demo)
   }
   ```

   （2）提供数据：

   ```vue
   this.$bus.$emit('xxx', data)
   ```

4. 注：最好在`beforeDestroy`钩子函数中，使用`$off('xxx')`解绑当前组件所用到的自定义事件



### 十一、消息订阅与发布（pubsub）

1. 定义：一种组件间的通信方式，适用于**任意组件间通信**
2. 引入：`npm i pubsub-js`
3. 接收数据：

```vue
methods() {
    demo(msgName,data) {
        ......
    }
}

mounted() {
    //订阅消息
    this.pubId = pubsub.subscribe('xxx', this.demo)
}
```

4. 提供数据：`pubsub.publish('xxx', data)`
5. 注：最好在`beforeDestroy`钩子函数中，使用`pubsub.unsubscribe(this.pubId)`取消订阅



### 十二、nextTick

1. 语法：`this.$nextTick(回调函数)`
2. 作用：在下一次DOM更新结束后执行指定的回调
3. 什么时候用？当数据改变后，需要基于更新后的新DOM进行某些操作时，要在`nextTick`所指定的回调函数中执行



### 十三、过度与动画

1. 作用：再插入、更新或移除DOM元素时，在合适的时候给元素添加样式名

2. 语法：

   （1）准备样式

   + 元素进入的样式
     + v-enter：进入的起点
     + v-enter-active：进入过程中
     + v-enter-to：进入的终点
   + 元素离开的样式
     + v-leave：离开的起点
     + v-leave-active：离开过程中
     + v-leave-to：离开的终点

   （2）使用`<transition>`包裹要过度的元素，并配置`name`属性

   ```vue
   <transition name="demo">
       <h1 v-show="isShow">
           孙笑川
       </h1>
   </transition>
   ```

   （3）注：若有多个元素需要过度，则需要使用：`<transition-group>`，且每个元素都需要指定`key`值



### 十四、配置代理

#### 方式一

在`vue.config.js`中添加如下配置：

```js
devServer: {
    proxy: 'http://localhost:5000'
}
```

注：（1）该方式配置简单，但不能配置多个代理，且不能灵活的控制请求是否走代理

​		（2）当请求了前端不存在的资源时，那么该请求会转发给服务器（优先匹配前端资源）



#### 方式二

```js
devServer: {
  proxy: {
    '/api': {
      target: 'http://localhost:5000',
      //路径重写
      pathRewrite: {'^/api':''},
      ws: true,
      //用于空值请求头中的host值（默认值为true）
      changeOrigin: true
    },
    '/demo': {
      target: 'http://localhost:5001',
      pathRewrite: {'^/demo':''},
      ws: true,
      changeOrigin: true
    }
  }
}
```

注：可以配置多个代理和灵活的控制请求是否走代理（请求时必须加上前缀）



### 十五、插槽

1. 定义：让父组件可以向子组件指定位置插入html结构，也是一种组件间通信方式（父 ---> 子）

2. 分类：默认、具名、作用域

3. 使用方式：

   （1）默认插槽

   ```vue
   <!-- 父组件 -->
   <Category>
       <div>
           html结构
       </div>
   </Category>
   
   <!-- 子组件 -->
   <template>
     <div class="category">
       <!-- 定义一个插槽，当使用者没有传递具体结构时，使用默认值填充 -->
       <slot>默认插槽</slot>
     </div>
   </template>
   ```

   （2）具名插槽

   ```vue
   <!-- 父组件 -->
   <Category>
       <template v-slot:center>
           <div>
               html结构1
           </div>
       </template>
       
       <template v-slot:footer>
           <div>
               html结构2
           </div>
       </template>
   </Category>
   
   <!-- 子组件 -->
   <template>
     <div>
       <!-- 定义一个插槽，当使用者没有传递具体结构时，使用默认值填充 -->
       <slot name="center">默认插槽</slot>
       <slot name="footer">默认插槽</slot>
     </div>
   </template>
   ```

   （3）作用域插槽

   ​	a. 定义：数据在组件的自身，但数据生成的结构**由组件的使用者决定**（list数据在Category 组件中，但使用数据遍历出来的结构由App组件决定）

   ​	b. 使用：

   ```vue
   <!-- 父组件 -->
   <Category>
       <template slot-scope="{games}">
           <ul>
               <!-- 无序列表 -->
               <li v-for="(item,index) in games" :key="index">{{ item }}</li>
           </ul>
       </template>
   </Category>
   
   <Category>
       <template slot-scope="{games}">
           <ol>
               <!-- 有序列表 -->
               <li v-for="(item,index) in games" :key="index">{{ item }}</li>
           </ol>
       </template>
   </Category>
   
   <!-- 子组件 -->
   <template>
     <div>
       <!-- 定义一个插槽，当使用者没有传递具体结构时，使用默认值填充 -->
       <slot :games="games">默认插槽</slot>
     </div>
   </template>
   
   <script>
   export default {
     name: "MyCategory",
     // 数据在组件自身
     data() {
       return {
         games: ['魔兽争霸', 'QQ飞车', 'Dota自走棋', '地下城与勇士']
       }
     }
   }
   </script>
   ```

   注：作用域插槽也可以添加`name`属性，使用时同理具名插槽




### 十六、Vuex

#### 1. 概念

实现集中式状态（数据）管理的一个插件，对vue应用中多个组件的共享状态进行集中式的管理（读/写），也是一种组件间通信的方式（适用于任意组件间通信）

#### 2. 搭建vuex环境

1. **安装（以vue2为例）**：`npm i vuex@3`
2. **index.js**

```js
import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

//用于响应组件中的动作
const actions = {
    
}

//用于操作数据
const mutations = {
    
}

//用于存储数据
const state = {
    
}

//创建并暴露store
export default new Vuex.Store({
    actions,mutations,state
})
```

**main.js**

```js
import Vue from 'vue'
import App from './App.vue'
import store from './store'

//关闭生产提示
Vue.config.productionTip =false

new Vue({
    el: '#app',
    render: h => h(App),
    store
})
```

#### 3. 基本使用

（1）配置`actions`,`mutations`,`state`

```js
import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

//用于响应组件中的动作
const actions = {
    increment(context,value) {
        console.log('actions中的increment被调用')
        context.commit('INCREMENT', value)
    },
    decrement(context,value) {
        context.commit('DECREMENT', value)
    },
    incrementOdd(context,value) {
        if (context.state.sum % 2) {
            context.commit('INCREMENTODD', value)
        }
    },
    incrementWait(context,value) {
       setTimeout(() => {
           context.commit('INCREMENTWAIT', value)
       }, 500)
    }
}

//用于操作数据
const mutations = {
    INCREMENT(state,value) {
        console.log('mutations中的INCREMENT被调用')
        state.sum += value
    },
    DECREMENT(state,value) {
        state.sum -= value
    },
    INCREMENTODD(state,value) {
        state.sum += value
    },
    INCREMENTWAIT(state,value) {
        state.sum += value
    }

}

//用于存储数据
const state = {
    //当前的和
    sum: 0,
}

//创建并暴露store
export default new Vuex.Store({
    actions,mutations,state
})
```

（2）组件中读取`vuex`中的数据：`$store.state.sum`

（3）组件中修改`vuex`中的数据：`$store.dispatch('方法名（小写）', 数据)` 或 `this.$store.commit('方法名（大写）', 数据)`

​	注：若没有网络请求或其他业务逻辑，组件中可以跳过`actions`（即不写`dispatch`），直接（调用`mutations`）`commit`



#### 4. getters的使用

（1）概念：当`state`中的数据需要经过加工后再使用时，可以使用`getters`

（2）在`store`中追加`getters`配置

```js
//用于加工state中的数据
const getters = {
    bigSum(state) {
        return state.sum * 10
    }
}

//创建并暴露store
export default new Vuex.Store({
    actions,mutations,state,getters
})
```

（3）组件中读取数据：`$store.gettes.bigSum`



#### 5. 四个map方法的使用

（1）**mapState**（映射`state`中的属性为计算属性）

```js
//借助mapState生成计算属性，从state中读取数据
//对象写法
...mapState({sum:'sum',school:'school',subject:'subject'})

//数组写法
...mapState(['sum','school','subject']),
```



（2）**mapGetters**（映射`getters`中的数据为计算属性）

```js
//借助mapGetters生成计算属性，从getter中读取数据
//对象写法
...mapGetters({bigSum:'bigSum'})

//数组写法
...mapGetters(['bigSum'])
```



（3）**mapMutations** 用于帮助生成与`mutations`对话的方法，即：包含`$store.commit(xxx)`的函数

```js
//借助mapMutations生成对应的方法，方法中会调用commit去联系mutations
//对象写法
...mapMutations({increment:'INCREMENT',decrement:'DECREMENT'}),

//数组写法
...mapMutations(['INCREMENT','DECREMENT']),
```



（4）**mapActions **用于帮助生成与`actions`对话的方法，即：包含`$store.dispatch(xxx)`的函数

```js
//借助mapActions生成对应的方法，方法中会调用dispatch去联系actions
//对象写法
...mapActions({incrementOdd:'incrementOdd',incrementWait:'incrementWait'}),

//数组写法
...mapActions(['incrementOdd','incrementWait'])
```

注：使用`mapMutations`与`mapActions `时，若需要传递参数，**需要在模板中绑定事件时就传递**，否则默认的传参为时间对象



#### 6. 模块化、命名空间

（1）目的：便于代码维护，数据分类更加明确

（2）修改`store/index.js`

**index.js**

```js
import Vue from 'vue'
import Vuex from 'vuex'
//求和相关
import countOptions from './count'
//人员管理相关
import personOptions from './person'

Vue.use(Vuex)

//创建并暴露store
export default new Vuex.Store({
    modules: {
        countOptions, personOptions
    }
})
```

**count.js**

```js
export default {
    //开启命名空间
    namespaced: true,
    actions: {
        ......
    },
    mutations: {
        ......
    },
    state: {
        ......
    },
    getters: {
        ......
    },
}
```

**person.js**

```js
import axios from "axios";
import {nanoid} from "nanoid";

export default {
    namespaced: true,
    actions: {
        ......
    },
    mutations: {
        ......
    },
    state: {
        ......
    },
    getters: {
        ......
    },
}
```



（3）开启命名空间后：

+ 组件读取`state`数据

  ```js
  //方式一：直接读取
  this.$store.state.personOptions.personList
  //方式二：借助map方法
  ...mapState('personOptions', ['personList'])
  ```

+ 组件读取`getters`数据

  ```js
  //方式一：直接读取
  this.$store.getters['personOptions/firstPersonName']
  //方式二：借助map方法
  ...mapGetters('countOptions', ['bigSum'])
  ```

+ 组件调用`dispatch`方法

  ```js
  //方式一：直接调用
  this.$store.dispatch('personOptions/addPerson', personObj)
  //方式二：借助map方法
  //参数在模板中绑定事件时就传递
  ...mapActions('countOptions',['incrementOdd','incrementWait'])
  ```

+ 组件调用`commit`方法

  ```js
  //方式一：直接调用
  this.$store.commit('personOptions/ADD_PERSON', personObj)
  //方式二：借助map方法
  //参数在模板中绑定事件时就传递
  ...mapMutations('countOptions',{increment:'INCREMENT',decrement:'DECREMENT'}),
  ```



### 十七、路由

概念：一个路由（route）就是一组映射关系（key - value，key为路径，value为组件）

，多个路由需要路由器（router）进行管理



#### 1. 基本使用

（1）安装（以vue2为例）：`npm i vue-router@3`

（2）使用插件：`Vue.use(VueRouter)`

（3）路由配置：

```js
//路由配置
import VueRouter from 'vue-router'
import About from '@/components/About'
import Home from '@/components/Home'

export default new VueRouter( {
    routes: [
        {
            path: '/about',
            component: About
        },
        {
            path: '/about',
            component: Home
        }
    ]
})
```

（3）路由切换：

```vue
<router-link class="list-group-item" active-class="active" to="/about">About</router-link>
```

（4）指定页面展示位置：

```vue
<router-view></router-view>
```



#### 2. 几个注意点

（1）路由组件一般放在`views`文件夹，一般组件放在`components`文件夹

（2）通过切换，“隐藏”了的路由组件会被**销毁**，需要的时候再**挂载**；

（3）每个路由组件都有自己的**$route**属性，存储对应的路由信息；

（4）整个应用只有一个**router**



#### 3. 嵌套路由

（1）使用`children`配置项：

```js
export default new VueRouter( {
    routes: [
        {
            path: '/about',
            component: About
        },
        {
            path: '/home',
            component: Home,
            children: [
                {
                    //子路由之前不写左斜杠“/”
                    path: 'news',
                    component: News
                },
                {
                    path: 'message',
                    component: Message
                }
            ]
        }
    ]
})
```

（2）跳转（要写全路径）

```vue
<router-link class="list-group-item" to="/home/news">News</router-link>
```



#### 4. 路由的query参数

（1）传递参数

```vue
<!-- 跳转路由并携带query参数，to的字符串写法 -->
<router-link :to="`/home/message/detail?id=${m.id}&title=${m.title}`">{{m.title}}</router-link>

<!-- 跳转路由并携带query参数，to的对象写法 -->
<router-link :to="{
    path: '/home/message/detail',
    query: {
        id: m.id,
        title: m.title
        }
    }">
    {{m.title}}
</router-link>
```

（2）接收参数

```vue
this.$route.query.id
this.$route.query.title
```



#### 5. 命名路由

（1）作用：简化路由的跳转

（2）基本使用：

+ 命名

  ```js
  {
      path: '/home',
      component: Home,
      children: [
          {
              //子路由之前不写左斜杠“/”
              path: 'news',
              component: News
          },
          {
              path: 'message',
              component: Message,
              children: [
                  {
                      name: 'detail',
                      path: 'detail',
                      component: Detail,
                  }
              ]
          }
      ]
  }
  ```

+ 简化跳转

  ```vue
  <!-- 简化前 -->
  <router-link to="/home/message/detail">详情</router-link>
  
  <!-- 简化后，直接通过名字跳转 -->
  <router-link :to="{name:'detail'}">详情</router-link>
  
  <!-- 简化写法携带参数 -->
  <router-link :to="{
    name: 'detail',
    query: {
    id: m.id,
    title: m.title
    }
  }">详情</router-link>
  ```



#### 6. 路由的params参数

（1）配置路由，声明接收`params`参数

```js
{
    path: '/home',
    component: Home,
    children: [
        {
            //子路由之前不写左斜杠“/”
            path: 'news',
            component: News
        },
        {
            path: 'message',
            component: Message,
            children: [
                {
                    name: 'detail',
                    //使用占位符声明接收params参数
                    path: 'detail/:id/:title',
                    component: Detail,
                }
            ]
        }
    ]
}
```

（2）传递参数**（携带params时，必须使用路由名字，不能使用路径）**

```vue
<!-- 跳转路由并携带params参数，to的字符串写法 -->
<!-- <router-link :to="`/home/message/detail/${m.id}/${m.title}`">详情</router-link> -->

<!-- 跳转路由并携带params参数，to的对象写法 -->
<router-link :to="{
  name: 'detail',
  //携带params时，必须使用路由名字，不能使用路径
  params: {
  id: m.id,
  title: m.title
  }
}">详情</router-link>
```

（3）接收参数

```vue
this.$route.params.id
this.$route.params.title
```



#### 7. 路由的props参数

作用：方便路由组件接收参数

```js
{
    path: 'message',
    component: Message,
    children: [
        {
            name: 'detail',
            path: 'detail',
            component: Detail,
            //props的第一种写法，值为对象，该对象中的所有key-value都会以props的形式传给Detail组件
            //props: {a:1,b:"B"}

            //props的第二种写法，值为布尔值，若值位真，就会把该路由组件收到的所有params参数，以props的形式传给Detail组件
            //props: true

            //props的第三种写法，值为函数
            /* props({$route}) {
                                return {
                                    id: $route.query.id,
                                    title: $route.query.title,
                                }
                            } */

            //简写
            props({query:{id,title}}) {
                return {
                    id,title
                }
            }
        }
    ]
}
```



#### 8. `<router-link>`的replace属性

（1）作用：控制路由跳转时操作浏览器历史记录的模式

（2）浏览器历史记录有两种写入方式（默认为`push`）：

+ `push`：追加历史记录
+ `replace`：替换当前记录

（3）使用：`<router-link replace></router-link>`



#### 9. 编程式路由导航

（1）作用：不借助`<router-link>`实现路由跳转

（2）使用：

```js
<!-- push -->
this.$router.push({
    name: 'detail',
    query: {
        id: m.id,
        title: m.title
    }
})

<!-- replace -->
    this.$router.replace({
    name: 'detail',
    query: {
        id: m.id,
        title: m.title
    }
})

<!-- 前进 -->
this.$router.back()

<!-- 后退 -->
this.$router.forward()

<!-- 前进/后退x步（x步长） -->
this.$router.go(x)
```



#### 10. 缓存路由组件

（1）作用：让不展示的路由组件保持挂载，不销毁

（2）使用：

```vue
<!--1.考虑要缓存的路由在哪个组件展示
	2.include写的必须是组件名
	3.缓存多个组件时使用双向绑定和数组形式即可，:inclue="['x','y']"
-->
<keep-alive include="MyNews">
    <router-view></router-view>
</keep-alive>
```



#### 11. 两个新的生命周期钩子

（1）作用：**路由组件所独有**，用于捕获路由组件的激活状态

（2）`activated`：路由组件激活时被触发

​		 `deactivated`：反之，路由组件失活时被触发

（3）标题淡化Demo：

```js
//激活
activated() {
    console.log('News组件激活')
    this.timer = setInterval(() => {
        console.log('@')
        this.opacity -= 0.01
        if (this.opacity <= 0) {
            this.opacity = 1
        }
    })
},

//失活
deactivated() {
    console.log('News组件失活')
    clearInterval(this.timer)
}
```



#### 12. 路由守卫

（1）作用：对路由进行权限控制

（2）分类：全局、独享、组件内守卫

（3）全局守卫

```js
//全局前置路由守卫，初始化及每次路由切换之前被调用
router.beforeEach((to, from, next) => {
    console.log('前置路由守卫', to, from)
    //鉴权
    if (to.meta.isAuth) {
        if (localStorage.getItem('token') === '1') {
            next()
        } else {
            alert('暂无查看权限！')
        }
    } else {
        next()
    }
})

//全局后置路由守卫，初始化及每次路由切换之后被调用
router.afterEach((to, from) => {
    console.log('后置路由守卫', to, from)
    document.title = to.meta.title || 'Vue2 Demo'
})
```

（4）独享路由守卫

```js
beforeEnter(to,from,next) {
    console.log('独享路由守卫', to, from)
    //鉴权
    if (to.meta.isAuth) {
        if (localStorage.getItem('token') === '1') {
            next()
        } else {
            alert('暂无查看权限！')
        }
    } else {
        next()
    }
}
```

（5）组件内守卫

```js
//通过路由规则，进入该组件时被调用
beforeRouteEnter(to, from, next) {
    console.log('About组件beforeRouteEnter', to, from)
    //鉴权
    if (to.meta.isAuth) {
        if (localStorage.getItem('token') === '1') {
            next()
        } else {
            alert('暂无查看权限！')
        }
    } else {
        next()
    }
},

//通过路由规则，离开该组件时被调用
beforeRouteLeave(to, from, next) {
    console.log('About组件beforeRouteLeave', to, from)
    next()
}
```



#### 13. 路由器的两种工作模式

（1）`hash`值：#号及其后面的内容

（2）`hash`值不会包含在`HTTP`请求中，即`hash`值不会带给后台服务器

（3）`hash`与`history`的区别：

+ `hash`值地址栏会带着#号，兼容性好
+ `history`模式地址干净美观，兼容性相较于`hash`略差，项目上线部署时需解决刷新404的问题