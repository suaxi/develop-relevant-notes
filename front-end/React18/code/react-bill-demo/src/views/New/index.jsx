import { Button, DatePicker, Input, NavBar } from 'antd-mobile'
import Icon from '@/components/Icon'
import './index.scss'
import classNames from 'classnames'
import { billListData } from '@/constants'
import { useNavigate } from 'react-router-dom'
import { useState } from 'react'
import { useDispatch } from 'react-redux'
import { addBillList } from '@/store/modules/billStore'
import dayjs from 'dayjs'

const New = () => {
  const navigate = useNavigate()
  const dispatch = useDispatch()

  // 账单类型：支出、收入
  const [billType, setBillType] = useState('pay')
  // 账单金额
  const [money, setMoney] = useState(0)
  // 账单类型
  const [useFor, setUseFor] = useState('')
  // 日期选择器
  const [datePickerVisible, setDatePickerVisible] = useState(false)
  // 账单日期
  const [billDate, setBillDate] = useState()

  // 设置金额
  const moneyChange = (value) => {
    setMoney(value)
  }

  // 保存账单
  const saveBill = () => {
    const amount = Number(money) || 0
    const billData = {
      type: billType,
      money: billType === 'pay' ? -amount : amount,
      date: billDate || new Date(),
      useFor: useFor
    }
    dispatch(addBillList(billData))
  }

  // 日期选择确认
  const dateConfirm = (date) => {
    setBillDate(date)
    setDatePickerVisible(false)
  }

  return (
    <div className="keepAccounts">
      <NavBar className="nav" onBack={() => navigate(-1)}>
        记一笔
      </NavBar>

      <div className="header">
        <div className="kaType">
          <Button
            shape="rounded"
            className={classNames(billType === 'pay' ? 'selected' : '')}
            onClick={() => setBillType('pay')}
          >
            支出
          </Button>
          <Button
            className={classNames(billType === 'income' ? 'selected' : '')}
            shape="rounded"
            onClick={() => setBillType('income')}
          >
            收入
          </Button>
        </div>

        <div className="kaFormWrapper">
          <div className="kaForm">
            <div className="date">
              <Icon type="calendar" className="icon" />
              <span className="text" onClick={() => setDatePickerVisible(true)}>
                {dayjs(billDate).format('YYYY-MM-DD')}
              </span>
              <DatePicker
                className="kaDate"
                title="记账日期"
                max={new Date()}
                visible={datePickerVisible}
                onConfirm={dateConfirm}
                onCancel={() => setDatePickerVisible(false)}
                onClose={() => setDatePickerVisible(false)}
              />
            </div>
            <div className="kaInput">
              <Input
                className="input"
                placeholder="0.00"
                type="number"
                value={money}
                onChange={moneyChange}
              />
              <span className="iconYuan">¥</span>
            </div>
          </div>
        </div>
      </div>

      <div className="kaTypeList">
        {billListData[billType].map((item) => {
          return (
            <div className="kaType" key={item.type}>
              <div className="title">{item.name}</div>
              <div className="list">
                {item.list.map((item) => {
                  return (
                    <div
                      className={classNames(
                        'item',
                        useFor === item.type ? 'selected' : ''
                      )}
                      key={item.type}
                      onClick={() => setUseFor(item.type)}
                    >
                      <div className="icon">
                        <Icon type={item.type} />
                      </div>
                      <div className="text">{item.name}</div>
                    </div>
                  )
                })}
              </div>
            </div>
          )
        })}
      </div>

      <div className="btns">
        <Button className="btn save" onClick={saveBill}>
          保 存
        </Button>
      </div>
    </div>
  )
}

export default New
