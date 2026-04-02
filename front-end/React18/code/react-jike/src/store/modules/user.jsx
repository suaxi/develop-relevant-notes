import { createSlice } from '@reduxjs/toolkit'
import { getToken, setToken as _setToken, removeToken } from '@/utils'
import { login as loginApi, userInfo } from '@/api/user'

const userStore = createSlice({
  name: 'user',
  initialState: {
    token: getToken() || null,
    user: {}
  },
  reducers: {
    setToken(state, action) {
      state.token = action.payload
      _setToken(action.payload)
    },
    setUser(state, action) {
      state.user = action.payload
    },
    logout(state) {
      state.token = null
      state.user = {}
      removeToken()
    }
  }
})

const { setToken, setUser, logout } = userStore.actions
const userReducer = userStore.reducer

const login = (loginForm) => {
  return async (dispatch) => {
    const res = await loginApi(loginForm)
    dispatch(setToken(res.data.token))
  }
}

// user info
const getUser = () => {
  return async (dispatch) => {
    const res = await userInfo()
    dispatch(setUser(res.data))
  }
}

export { logout, getUser, login, setToken }
export default userReducer
