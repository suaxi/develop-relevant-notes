import Layout from '@/views/Layout/index'
import Login from '@/views/Login/index'
import { createBrowserRouter } from 'react-router-dom'
import { Auth } from '@/components/Auth'
import Home from '@/views/Home'
import Article from '@/views/Article'
import Publish from '@/views/Publish'

const router = createBrowserRouter([
  {
    path: '/',
    element: (
      <Auth>
        <Layout />
      </Auth>
    ),
    children: [
      {
        // path: 'home',
        index: true,
        element: <Home />
      },
      {
        path: 'article',
        element: <Article />
      },
      {
        path: 'publish',
        element: <Publish />
      }
    ]
  },
  {
    path: '/login',
    element: <Login />
  }
])

export default router
