import { createBrowserRouter } from 'react-router-dom'
import Layout from '@/views/Layout'
import Month from '@/views/Month'
import Year from '@/views/Year'
import New from '@/views/New'

const router = createBrowserRouter([
  {
    path: '/',
    element: <Layout />,
    children: [
      {
        // path: 'month',
        index: true,
        element: <Month />
      },
      {
        path: 'year',
        element: <Year />
      }
    ]
  },
  {
    path: '/new',
    element: <New />
  }
])

export default router
