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
    }
  }
})

export const { setBillList } = billStore.actions

const getBillList = () => {
  return async (dispatch) => {
    const res = await axios.get('http://localhost:3000/ka')
    dispatch(setBillList(res.data))
  }
}

export { getBillList }

export default billStore.reducer
