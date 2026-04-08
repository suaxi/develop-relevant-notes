import { request } from '@/utils'

export function channels() {
  return request({
    url: '/channels',
    method: 'GET'
  })
}

export function add(formData) {
  return request({
    url: '/mp/articles?draft=false',
    method: 'POST',
    data: formData
  })
}

export function update(formData) {
  return request({
    url: `/mp/articles/${formData.id}?draft=false`,
    method: 'PUT',
    data: formData
  })
}

export function list(params) {
  return request({
    url: '/mp/articles',
    method: 'GET',
    params
  })
}

export function del(id) {
  return request({
    url: `/mp/articles/${id}`,
    method: 'DELETE'
  })
}

export function getArticle(id) {
  return request({
    url: `/mp/articles/${id}`,
    method: 'GET'
  })
}
