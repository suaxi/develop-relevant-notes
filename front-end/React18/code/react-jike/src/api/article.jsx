import { request } from '@/utils'

export function channels() {
  return request({
    url: '/channels',
    method: 'GET'
  })
}

export function submit(formData) {
  return request({
    url: '/mp/articles?draft=false',
    method: 'POST',
    data: formData
  })
}
