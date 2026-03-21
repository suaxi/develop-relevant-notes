import Login from '../components/Login/index'
import Article from '../components/Article/index'
import { createBrowserRouter } from 'react-router-dom'
import Layout from '../components/Layout/index'
import Board from '../components/Board/index'
import About from '../components/About/index'
import NotFound from '../components/NotFound'

const router = createBrowserRouter([
  {
    path: '/',
    element: <Layout />,
    children: [
      {
        // path: 'board',
        index: true,
        element: <Board />
      },
      {
        path: 'about',
        element: <About />
      }
    ]
  },
  {
    path: '/login',
    element: <Login />
  },
  {
    path: '/article/:name/:age',
    element: <Article />
  },
  {
    path: '*',
    element: <NotFound />
  }
])

export default router
