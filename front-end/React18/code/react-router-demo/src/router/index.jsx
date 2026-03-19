import Login from '../components/Login/index'
import Article from '../components/Article/index'
import { createBrowserRouter } from 'react-router-dom'

const router = createBrowserRouter([
  {
    path: '/login',
    element: <Login />
  },
  {
    path: '/article',
    element: <Article />
  }
])

export default router
