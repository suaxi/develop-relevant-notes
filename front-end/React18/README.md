## React 18

### 一、搭建开发环境

官网快速开始文档 - 创建一个 React 项目：

https://18.react.dev/learn/creating-a-react-app

```bash
npx create-react-app react-demo

npm start
```



### 二、JSX 基础

#### 1. 概念

JSX（JS的语法扩展）是JavaScript 和 XML（HTML）的缩写，表示在 js 代码中编写 HTML 模板结构，它是 React 中编写 UI 模板的方式



#### 2. 使用 js 表达式

在 JSX 中可以通过大括号语法 {} 识别 js 中的表达式，如：变量、函数调用、方法调用等

- 使用引号传递字符串
- 使用 js 变量
- 函数调用和方法调用
- 使用 js 对象

```jsx
const index = 1

function getName() {
  return '孙笑川'
}
function App() {
  return (
    <div className="App">
      这是 React Demo

      {/* 使用引号传递字符串 */}
      {'这是 React Demo'}

      {/* 使用 js 变量 */}
      {index}

      {/* 函数调用 */}
      {getName()}

      {/* 方法调用 */}
      {new Date().getDate()}

      {/* 使用js对象 */}
      <div style={{ color: 'red' }}>这是 React Demo</div>
    </div>
  );
}

export default App;

```



#### 3. 列表渲染

```jsx
const list = [
  {id: '1', name: '孙笑川'},
  {id: '2', name: '药水哥'},
  {id: '3', name: '刘波'}
]

function App() {
  return (
    <div className="App">
      {/* 渲染列表 */}
      <ul>
        {list.map(item => <li key={item.id}>{item.name}</li>)}
      </ul>
    </div>
  );
}

export default App;

```



#### 4. 条件渲染

通过逻辑运算符、三元表达式实现基础的条件渲染

```jsx
const flag = true

function App() {
  return (
    <div className="App">
      {/* 逻辑运算 */}
      {flag && <span>这是 span 标签</span>}

      {/* 三元运算 */}
      {!flag ? <span>这是 span 标签</span> : <p>这是 p 标签</p>}
    </div>
  );
}

export default App;

```



#### 5. 复杂条件渲染

通过自定义函数 + if 判断语句实现

```jsx
const type = 1 // 0 1 2

function getType() {
  if (type === 0) {
    return <div>孙笑川</div>
  }
  if (type === 1) {
    return <div>药水哥</div>
  }
  if (type === 2) {
    return <div>刘波</div>
  }
}

function App() {
  return (
    <div className="App">
      {/* 复杂条件渲染 */}
      {getType()}
    </div>
  );
}

export default App;

```

