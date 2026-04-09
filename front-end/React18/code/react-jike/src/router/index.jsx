import Layout from '@/views/Layout/index'
import Login from '@/views/Login/index'
import { createBrowserRouter } from 'react-router-dom'
import { Auth } from '@/components/Auth'
// import Home from '@/views/Home'
// import Article from '@/views/Article'
// import Publish from '@/views/Publish'
import { lazy, Suspense } from 'react'

const Home = lazy(() => import('@/views/Home'))
const Article = lazy(() => import('@/views/Article'))
const Publish = lazy(() => import('@/views/Publish'))

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
        element: (
          <Suspense fallback={'加载中...'}>
            <Home />
          </Suspense>
        )
      },
      {
        path: 'article',
        element: (
          <Suspense fallback={'加载中...'}>
            <Article />
          </Suspense>
        )
      },
      {
        path: 'publish',
        element: (
          <Suspense fallback={'加载中...'}>
            <Publish />
          </Suspense>
        )
      }
    ]
  },
  {
    path: '/login',
    element: <Login />
  }
])

export default router
