import { NavBar, DatePicker } from 'antd-mobile'
import { useEffect, useMemo, useState } from 'react'
import './index.scss'
import classNames from 'classnames'
import dayjs from 'dayjs'
import { useSelector } from 'react-redux'
import _ from 'lodash'
import DailyBill from './components/Day'

const Month = () => {
  // 日期选择器显示状态
  const [dateVisible, setDateVisible] = useState(false)

  // 当前选中的日期
  const [currentDate, setCurrentDate] = useState(() => {
    return dayjs(new Date()).format('YYYY-MM')
  })

  // 当前月份的账单列表
  const [currentMonthBillList, setCurrentMonthBillList] = useState([])

  const billList = useSelector((state) => state.bill.billList)
  const monthGroup = useMemo(() => {
    return _.groupBy(billList, (item) => {
      return dayjs(item.date).format('YYYY-MM')
    })
  }, [billList])
  const monthResult = useMemo(() => {
    const pay = currentMonthBillList
      .filter((item) => item.type === 'pay')
      .reduce((x, y) => x + y.money, 0)
    const income = currentMonthBillList
      .filter((item) => item.type === 'income')
      .reduce((x, y) => x + y.money, 0)
    return {
      pay,
      income,
      total: pay + income
    }
  }, [currentMonthBillList])

  // 当前月数据
  useEffect(() => {
    setCurrentMonthBillList(monthGroup[currentDate] || [])
  }, [monthGroup])

  // 当前月按日分组
  const dayGroup = useMemo(() => {
    const groupData = _.groupBy(currentMonthBillList, (item) => {
      return dayjs(item.date).format('YYYY-MM-DD')
    })
    const keys = Object.keys(groupData).sort()
    return {
      keys,
      groupData
    }
  }, [currentMonthBillList])

  const dataPickerConfirm = (date) => {
    const dateStr = dayjs(date).format('YYYY-MM')
    setCurrentDate(dateStr)
    setCurrentMonthBillList(monthGroup[dateStr])
    setDateVisible(false)
  }
  return (
    <div className="monthlyBill">
      <NavBar className="nav" backArrow={false}>
        月度收支
      </NavBar>
      <div className="content">
        <div className="header">
          {/* 时间切换区域 */}
          <div className="date" onClick={() => setDateVisible(true)}>
            <span className="text">{currentDate}月账单</span>
            {/* 思路：根据当前弹框打开的状态控制expand类名是否存在 */}
            <span
              className={classNames('arrow', dateVisible && 'expand')}
            ></span>
          </div>
          {/* 统计区域 */}
          <div className="twoLineOverview">
            <div className="item">
              <span className="money">{monthResult.pay.toFixed(2)}</span>
              <span className="type">支出</span>
            </div>
            <div className="item">
              <span className="money">{monthResult.income.toFixed(2)}</span>
              <span className="type">收入</span>
            </div>
            <div className="item">
              <span className="money">{monthResult.total.toFixed(2)}</span>
              <span className="type">结余</span>
            </div>
          </div>
          {/* 时间选择器 */}
          <DatePicker
            className="kaDate"
            title="记账日期"
            precision="month"
            visible={dateVisible}
            onCancel={() => setDateVisible(false)}
            onConfirm={dataPickerConfirm}
            onClose={() => setDateVisible(false)}
            max={new Date()}
          />
        </div>
        {/* 单日列表统计 */}
        {dayGroup.keys.map((item) => {
          return (
            <DailyBill
              key={item}
              date={item}
              billList={dayGroup.groupData[item]}
            />
          )
        })}
      </div>
    </div>
  )
}

export default Month
