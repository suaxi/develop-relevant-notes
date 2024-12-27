## Vue3

### 一、常用Compsition API

#### 1. setup

Vue3.0中一个新的配置项，值为一个函数，组件中所用到的数据、方法等均配置在setup中，有两种返回结果：

（1）返回一个对象：则对象中的属性、方法可以直接在模板中使用（常用）

（2）返回一个渲染函数：可以自定义渲染内容

（3）注：`setup` 不能是一个 `async` 函数，因为返回值不再是 `return` 的对象，而是 `Promise`，模板看不到 `return`对象中的属性（后期可以是返回 `Promise` 实例，但需要 `Suspense` 和异步组件的配合）



#### 2. ref函数

+ 作用：定义一个响应式的数据

+ 语法：`const xxx= ref(initValue)`
  + 创建一个包含响应式数据的引用对象（reference对象，简称ref对象）
  + 在js中操作ref对象：`xxx.value`
  + 在模板中读取时不需要手动写`.value`，直接`{{xxx}}`

+ 补充：
  + 接收的数据可以是基本类型，也可以是对象类型
  + 基本类型数据：响应式依然通过 `Object.defineProperty()` 的 `get `与`set` 完成
  + 对象类型的数据：内部求助Vue3.0的 `reactive` 函数来实现



#### 3. reactive函数

+ 作用：定义一个对象类型的响应式数据（基本类型使用ref）
+ 语法：`const 代理对象 = reactive(源对象)` 接收一个对象或数组，返回一个代理对象（即Proxy的实例对象，简称Proxy对象）
+ `reactive` 定义的响应式数据是深层次的
+ 内部基于 `ES6` 的 `Proxy` 实现，通过代理对象操作源对象内部数据进行操作



#### 4. Vue3.0的响应式

+ 实现原理：

  + 通过Proxy（代理）：拦截对象中任意属性的变化（增删查改）

  + 通过Reflect（反射）：对源对象的属性进行操作

  + Demo

    ```javascript
    //源数据
    let person = {
        name: '孙笑川',
        age: 33
    }
    
    const p = new Proxy(person, {
        //读取时调用
        get(target,propName) {
            console.log(`p身上的${propName}属性被读取了`)
            return Reflect.get(target, propName)
        },
        //修改/追加时调用
        set(target,propName,value) {
            console.log(`p身上的${propName}属性被修改了，执行页面更新`)
            Reflect.set(target, propName, value)
        },
        //删除时调用
        deleteProperty(target, propName) {
            console.log(`p身上的${propName}属性被删除了，执行页面更新`)
            return Reflect.deleteProperty(target, propName)
        }
    })
    ```



#### 5. reactive与ref的区别

+ 从定义数据的角度
  + `ref` 定义**基本数据类型**
  + `reactive` 定义**对象或数组类型**
  + `ref`也可以定义*对象或数组类型*，底层会通过 `reactive`转为代理对象
+ 从原理的角度分析
  + `ref` 通过 `Object.defineProperty()` 的 `get` 与 `set` 方法来实响应式（数据劫持）
  + `reactive` 通过 `Proxy` 来实响应式（数据劫持），并通过 `Reflect` 操作**源对象**内部的数据
+ 从使用角度
  + `ref` 定义的数据：操作数据时需要 `.value`，在模板中使用时不需要 `.value`
  + `reactive` 定义的数据：操作时直接使用即可



#### 6. setup的两个注意点

+ setup执行的时机
  + 在 `beforeCreate` 之前执行一次，`this` 是 `undefined`
+ setup的参数
  + props：值为对象，包含组件外部传递过来，且组件内部声明接收了的属性
  + context：上下文对象
    + attrs：值为对象，包含组件外部传递过来，但没有在 `props` 配置中声明的属性，相当于 `this.$attrs`
    + slots：收到的插槽内容，相当于 `this.$slots`
    + emit：分发自定义事件的函数，相当于 `this.$emit`



#### 7. computed函数

+ 与Vue2.x中一致

+ 示例：

  ```vue
  import {computed, reactive} from "vue";
  
  export default {
    name: 'MyDemo',
    setup() {
      //数据
      let person = reactive({
        firstName: '孙',
        lastName: '笑川'
      })
  
      //计算属性（简写）
      // person.fullName = computed(() => {
      //   return person.firstName + '-' + person.lastName
      // })
  
      //计算属性（完整写法）
      person.fullName = computed({
        get() {
          return person.firstName + '-' + person.lastName
        },
        set(value) {
          const nameArr = value.split('-')
          person.firstName = nameArr[0]
          person.lastName = nameArr[1]
        }
      })
  
      return {
        person
      }
    }
  }
  ```



#### 8. watch函数

+ 与Vue2.x中一致

+ 注意：

  + 监视 `reactive`定义的响应式数据时，`oldValue` 无法正确获取，强制开启了深度监视（`deep`配置失效）
  + 监视 `reactive`定义的响应式数据中的某个属性（对象）时：`deep` 配置有效

  ```vue
  //情况一：监视ref定义的一个数据
  watch(sum, (newValue, oldValue) => {
  	console.log('sum变了',newValue, oldValue)
  })
  
  //情况二：监视ref定义的多个数据
  watch([sum, msg], (newValue, oldValue) => {
  	console.log('sum变了', newValue, oldValue)
  }, {immediate:true})
  
  /*
  情况三：监视reactive定义的一个数据中的全部属性
  注意：1.此处无法正确获取oldValue
  2.强制开启了深度监视（deep配置无效）
  */
  watch(person, (newValue, oldValue) => {
  	console.log('person变了',newValue, oldValue)
  }, {deep: false}) //此处的deep配置无效
  
  //情况四：监视reactive定义的一个数据中的某个属性
  watch(() => person.name, (newValue, oldValue) => {
  	console.log('person变了',newValue, oldValue)
  })
  
  //情况五：监视reactive定义的一个数据中的某些属性
  watch([() => person.name, () => person.age], (newValue, oldValue) => {
  	console.log('person变了',newValue, oldValue)
  })
  
  //特殊情况
  watch(() => person.job, (newValue, oldValue) => {
  	console.log('person变了',newValue, oldValue)
  }, {deep:true}) //此处由于监视的是reactive定义的对象中某个属性，所以deep配置有效
  ```



#### 9. watchEffect函数

+ watch与watchEffect的区别：

  + `watch` 既要指明监视的属性，也要指明监视的回调
  + `watchEffect` 无需指明监视的属性，在监视的回调中用到哪个属性，那就监视哪个属性

+ watchEffect与computed相似：

  + `computed` 注重计算出来的值（回调函数的返回值），必须写返回值
  + `watchEffect` 更注重过程（回调函数的函数体），不用写返回值

  ```vue
  //watchEffect所指定的回调中用到的数据只要发生变化，则直接重新执行回调
  watchEffect(() => {
      const a = sum.value
      const b = person.job.j1.salary
      console.log('watchEffect配置的回调执行了')
  })
  ```




#### 10. 生命周期

```vue
<script>
import {onBeforeMount, onBeforeUnmount, onBeforeUpdate, onMounted, onUnmounted, onUpdated, ref} from "vue";

export default {
  name: 'MyDemo',
  setup() {
    //数据
    let sum = ref(0)

    //通过组合式API的形式使用
    onBeforeMount(() => {
      console.log('---onBeforeMount---')
    })
    onMounted(() => {
      console.log('---onMounted---')
    }),
    onBeforeUpdate(() => {
      console.log('---onBeforeUpdate---')
    }),
    onUpdated(() => {
      console.log('---onUpdated---')
    }),
    onBeforeUnmount(() => {
      console.log('---onBeforeUnmount---')
    }),
    onUnmounted(() => {
      console.log('---onUnmounted---')
    })

    return {
      sum
    }
  },
  //通过配置项的形式使用生命周期钩子
  /* beforeCreate() {
    console.log('---beforeCreate---')
  },
  created() {
    console.log('---created---')
  },
  beforeMount() {
    console.log('---beforeMount---')
  },
  mounted() {
    console.log('---mounted---')
  },
  beforeUpdate() {
    console.log('---beforeUpdate---')
  },
  updated() {
    console.log('---updated---')
  },
  beforeUnmount() {
    console.log('---beforeUnmount---')
  },
  unmounted() {
    console.log('---unmounted---')
  } */
}
</script>
```



#### 11. 自定义hook函数

+ 本质是一个函数，把 `setup` 中使用的Composition API进行了封装
+ 类似于Vue2.x中的`mixin`
+ 优势：代码复用，逻辑清晰



#### 12. toRef

+ 作用：创建一个 `ref` 对象，其 `value` 值指向一个对象中的某个属性
+ 语法：`const name = toRef(person, 'name')`
+ 应用：要将响应式对象中的某个属性单独提供给外部使用时
+ 扩展：`toRef` 与 `toRefs` 功能一致，但可以批量创建多个 `ref` 对象，语法：`toRefs(person)`



### 二、其他Composition API

#### 1. shallowReactive与shallowRef

+ shallowReactive：只处理对象最外层属性的响应式（浅响应式）
+ shallowRef：只处理基本数据类型的响应式，不进行对象类型的响应式处理
+ 使用场景：
  + 如果有一个对象数据，结构比较深，但变化时只是第一层（外层）属性变化 ---> shallowReactive 
  + 如果有一个对象数据，后续功能不会修改该对象中的属性，而是生成一个新的对象来替换 ---> shallowRef



#### 2. readOnly与shallowReadOnly

+ readOnly：让一个响应式数据变为只读的（深只读）
+ shallowReadOnly：让一个响应式数据变为只读的（浅只读）
+ 应用场景：不希望数据被修改时（如：使用的数据是别人传过来的）



#### 3. toRaw与markRaw

+ toRow：
  + 作用：将一个由 `reactive` 生成的**响应式对象**转为**普通对象**
  + 使用场景：用于读取响应式对象对应的普通对象，对这个普通普通对象的所有操作，不会引起页面更新
+ markRaw：
  + 作用：标记一个对象，使其永远不会成为响应式对象
  + 应用场景：
    + 有些值不应被设置为响应式的，如复杂的第三方类库
    + 当渲染的对象是不可变数据源的复杂/大列表时，跳过响应式转换可以提高性能



####  4. customRef

+ 作用：自定义 `ref` ，并对其依赖项跟踪和更新触发进行显示控制

+ 实现防抖效果：

  ```vue
  <template>
    <br>
    <input type="text" v-model="keyWord">
    <h3>{{keyWord}}</h3>
  </template>
  
  <script>
  import {customRef, ref} from "vue";
  
  export default {
    name: 'MyDemo',
    setup() {
      //数据
      //Vue提供的ref
      //let keyWord = ref('你好')
  
      function myRef(value, delay) {
        return customRef((track, trigger) => {
          let timer
          return {
            get() {
              console.log(`有人从自定义的myRef容器中读取了数据，value:[${value}]`)
              //通知Vue追踪value的变化
              track()
              //2.读取
              return value
            },
            set(newValue) {
              console.log(`有人修改了自定义myRef容器中的数据，newValue:[${newValue}]`)
              clearTimeout(timer)
              timer = setTimeout(() => {
                //1.设置值
                value = newValue
                //3.通知Vue重新解析模板
                trigger()
              }, delay)
            }
          }
        })
      }
  
      //使用自定义的ref
      let keyWord = myRef('你好', 500)
  
      return {
        keyWord
      }
    }
  }
  </script>
  
  <style scoped>
  
  </style>
  ```




#### 5. provide与inject

+ 作用：实现**祖组件**与**后代组件**间的通信

+ 实现：

  ```vue
  <!-- 祖组件 -->
  setup() {
  	......
      let car = reactive({name: '马自达', price: '10w'})
      //给后代组件传递数据
      provide('car', car)
  	......
  }
  
  <!-- 后代组件 -->
  setup() {
  	......
      let car = inject('car')
      return {car}
  	......
  }
  ```



#### 6. 响应式数据的判断

+ isRef：检查是否是 `ref` 对象
+ isReactive：检查是否是 `reactive` 对象
+ isReadOnly：检查一个对象是否是由 `readOnly` 创建的**只读代理对象**
+ isProxy：检查一个对象是否是由 `reactive` 或 `readOnly` 创建的代理对象



### 三、Composition API的优势

#### 1. Options API存在的问题

在传统 `Options API` 中，新增或修改一个需求，需要分别在 `data, methods, computed` 中修改



#### 2. Composition APi的优势

可以更方便、严谨的组织业务代码、逻辑，让相关功能的代码更加有序的组织在一起



### 四、新的组件

#### 1. Fragment

+ Vue2：组件必须有一个根标签
+ Vue3：组件可以没有根标签，内部会将多个标签包含在一个 `Fragment` 虚拟元素中
+ 减少标签层间，性能优化



#### 2. Teleport

+ 概念：将 `组件html结构` 移动到指定位置

  ```vue
  <teleport to="body">
      <div v-if="isShow" class="mask">
          <div class="dialog">
              <h3>你好</h3>
              <h4>孙笑川</h4>
              <h4>药水哥</h4>
              <h4>Giao哥</h4>
              <button @click="isShow = false">关闭弹窗</button>
          </div>
      </div>
  </teleport>
  ```

  

#### 3. Suspense

+ 等待异步组件时渲染定义的缺省内容，提升用户体验

+ 实现：

  + 引入异步组件

  ```vue
  import {defineAsyncComponent} from "vue";
  const Child = defineAsyncComponent(() => import('./components/Child'))
  ```

  + 使用 `Suspense` 包裹组件，并配置好 `default` 和 `fallback`
  ```vue
  <template>
    <div class="app">
      <h3>App组件</h3>
    <Suspense>
        <template v-slot:default>
          <Child/>
        </template>
        <template v-slot:fallback>
          <h3>加载中...</h3>
        </template>
      </Suspense>
    </div>
  </template>
  ```
  
  
  