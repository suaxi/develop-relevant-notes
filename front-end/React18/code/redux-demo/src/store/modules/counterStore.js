import { createSlice } from '@reduxjs/toolkit'

const counterStore = createSlice({
  name: 'counter',
  initialState: {
    count: 0
  },
  reducers: {
    increment(state) {
      state.count++
    },
    decrement(state) {
      state.count--
    },
    addByNum(state, action) {
      state.count += action.payload
    }
  }
})

const { increment, decrement, addByNum } = counterStore.actions
const reducer = counterStore.reducer
export { increment, decrement, addByNum }
export default reducer
