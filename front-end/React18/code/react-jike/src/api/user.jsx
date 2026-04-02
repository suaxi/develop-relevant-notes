import { request } from '@/utils'

export function login(formData) {
  return request({
    url: '/authorizations',
    method: 'POST',
    data: formData
  })
}

export function userInfo() {
  return request({
    url: '/user/profile',
    method: 'GET'
  })
}
