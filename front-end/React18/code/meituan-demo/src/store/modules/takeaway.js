import { createSlice } from '@reduxjs/toolkit'
import axios from 'axios'

const foodsStore = createSlice({
  name: 'foods',
  initialState: {
    foodsList: [],
    activeIndex: 0,
    cartList: []
  },
  reducers: {
    setFoodsList(state, action) {
      state.foodsList = action.payload
    },
    changeActiveIndex(state, action) {
      state.activeIndex = action.payload
    },
    addCart(state, action) {
      const item = state.cartList.find((item) => item.id === action.payload.id)
      if (item) {
        item.count = (item.count ?? 0) + 1
      } else {
        state.cartList.push({
          ...action.payload,
          count: 1
        })
      }
    },
    increCount(state, action) {
      const item = state.cartList.find((item) => item.id === action.payload)
      if (item.count && item.count === 999) {
        return
      }
      item.count = (item.count ?? 0) + 1
    },
    decreCount(state, action) {
      const item = state.cartList.find((item) => item.id === action.payload)
      if (item.count === 0) {
        return
      }
      item.count = (item.count ?? 0) - 1
    },
    clearCart(state) {
      state.cartList = []
    }
  }
})

const {
  setFoodsList,
  changeActiveIndex,
  addCart,
  increCount,
  decreCount,
  clearCart
} = foodsStore.actions

const getFoodsList = () => {
  return async (dispatch) => {
    const res = await axios.get('http://localhost:3000/takeaway')
    dispatch(setFoodsList(res.data))
  }
}

export {
  getFoodsList,
  changeActiveIndex,
  addCart,
  increCount,
  decreCount,
  clearCart
}

const reducer = foodsStore.reducer
export default reducer
