import { Link } from 'react-router-dom'
import { Outlet } from 'react-router-dom'

const Layout = () => {
  return (
    <div>
      <h1>一级路由 Layout</h1>
      <Link to="/board">看板</Link>
      <br />
      <Link to="/about">关于</Link>

      {/* 二级路由出口 */}
      <Outlet />
    </div>
  )
}

export default Layout
