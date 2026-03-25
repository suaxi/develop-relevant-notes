import { createSlice } from '@reduxjs/toolkit'
import axios from 'axios'

const billStore = createSlice({
  name: 'bill',
  initialState: {
    billList: []
  },
  reducers: {
    setBillList(state, action) {
      state.billList = action.payload
    },
    addBill(state, action) {
      state.billList.push(action.payload)
    }
  }
})

export const { setBillList, addBill } = billStore.actions

const getBillList = () => {
  return async (dispatch) => {
    const res = await axios.get('http://localhost:3000/ka')
    dispatch(setBillList(res.data))
  }
}

const addBillList = (data) => {
  return async (dispatch) => {
    const res = await axios.post('http://localhost:3000/ka', data)
    dispatch(addBill(res.data))
  }
}

export { getBillList, addBillList }

export default billStore.reducer
