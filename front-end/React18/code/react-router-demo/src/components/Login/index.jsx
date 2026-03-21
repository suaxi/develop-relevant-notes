import { Link, useNavigate } from 'react-router-dom'
const Login = () => {
  const naigate = useNavigate()
  return (
    <div>
      登录
      {/* 声明式导航 */}
      <Link to="/article">跳转到文章页面</Link>
      {/* 编程式导航 */}
      <button onClick={() => naigate('/article')}>跳转到文章页面</button>
      <button onClick={() => naigate('/article?name="孙笑川"&age=33')}>
        searchParams传参
      </button>
      <button onClick={() => naigate('/article/孙笑川/33')}>params传参</button>
    </div>
  )
}

export default Login
