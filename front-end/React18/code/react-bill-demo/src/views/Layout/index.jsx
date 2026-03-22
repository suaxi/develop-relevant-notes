import { TabBar } from 'antd-mobile'
import { Outlet, useNavigate } from 'react-router-dom'
import { useEffect } from 'react'
import { useDispatch } from 'react-redux'
import { getBillList } from '@/store/modules/billStore'
import {
  BillOutline,
  CalculatorOutline,
  AddCircleOutline
} from 'antd-mobile-icons'
import './index.scss'

const tabs = [
  {
    key: '/',
    title: '月度账单',
    icon: <BillOutline />
  },
  {
    key: '/year',
    title: '年度账单',
    icon: <CalculatorOutline />
  },
  {
    key: '/new',
    title: '记账',
    icon: <AddCircleOutline />
  }
]

const Layout = () => {
  const dispatch = useDispatch()
  useEffect(() => {
    dispatch(getBillList())
  }, [])

  const navigate = useNavigate()
  const tabChange = (path) => {
    navigate(path)
  }
  return (
    <div className="layout">
      <div className="container">
        <Outlet />
      </div>
      <div className="footer">
        <TabBar onChange={tabChange}>
          {tabs.map((item) => (
            <TabBar.Item key={item.key} icon={item.icon} title={item.title} />
          ))}
        </TabBar>
      </div>
    </div>
  )
}

export default Layout
