import classNames from 'classnames'
import { useMemo, useState } from 'react'
import './index.scss'
import { billTypeToName } from '@/constants'

const DailyBill = ({ date, billList }) => {
  const dayResult = useMemo(() => {
    const pay = billList
      .filter((item) => item.type === 'pay')
      .reduce((x, y) => x + y.money, 0)
    const income = billList
      .filter((item) => item.type === 'income')
      .reduce((x, y) => x + y.money, 0)
    return {
      pay,
      income,
      total: pay + income
    }
  }, [billList])

  // 当日帐单列表展示
  const [dayBillVisible, setDayBillVisible] = useState(false)
  return (
    <div className={classNames('dailyBill')}>
      <div className="header">
        <div className="dateIcon">
          <span className="date">{date}</span>
          <span
            className={classNames('arrow', dayBillVisible && 'expand')}
            onClick={() => setDayBillVisible(!dayBillVisible)}
          ></span>
        </div>
        <div className="oneLineOverview">
          <div className="pay">
            <span className="type">支出</span>
            <span className="money">{dayResult.pay.toFixed(2)}</span>
          </div>
          <div className="income">
            <span className="type">收入</span>
            <span className="money">{dayResult.income.toFixed(2)}</span>
          </div>
          <div className="balance">
            <span className="money">{dayResult.total.toFixed(2)}</span>
            <span className="type">结余</span>
          </div>
        </div>
      </div>
      {/* 单日列表 */}
      <div
        className="billList"
        style={{ display: dayBillVisible ? 'block' : 'none' }}
      >
        {billList.map((item) => {
          return (
            <div className="bill" key={item.id}>
              {/* 图标 */}
              {/* <Icon type={item.useFor} /> */}
              <div className="detail">
                <div className="billType">{billTypeToName[item.useFor]}</div>
              </div>
              <div className={classNames('money', item.type)}>
                {item.money.toFixed(2)}
              </div>
            </div>
          )
        })}
      </div>
    </div>
  )
}
export default DailyBill
