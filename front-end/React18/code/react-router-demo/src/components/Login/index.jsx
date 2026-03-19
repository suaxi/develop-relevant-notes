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
    </div>
  )
}

export default Login
