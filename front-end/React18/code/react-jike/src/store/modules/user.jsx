import { createSlice } from '@reduxjs/toolkit'
import { request } from '@/utils'

const userStore = createSlice({
  name: 'user',
  initialState: {
    token: null
  },
  reducers: {
    setToken(state, action) {
      state.token = action.payload
    }
  }
})

const { setToken } = userStore.actions
const userReducer = userStore.reducer

const login = (loginForm) => {
  return async (dispatch) => {
    const res = request.post('/authorizations', loginForm)
    dispatch(setToken(res.token))
  }
}

export { login, setToken }
export default userReducer
