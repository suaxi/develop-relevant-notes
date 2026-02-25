import { createSlice } from '@reduxjs/toolkit'
import axios from 'axios'

const foodsStore = createSlice({
  name: 'foods',
  initialState: {
    foodsList: []
  },
  reducers: {
    setFoodsList(state, action) {
      state.foodsList = action.payload
    }
  }
})

const { setFoodsList } = foodsStore.actions

const getFoodsList = () => {
  return async (dispatch) => {
    const res = await axios.get('http://localhost:3000/takeaway')
    dispatch(setFoodsList(res.data))
  }
}

export { getFoodsList }

const reducer = foodsStore.reducer
export default reducer
