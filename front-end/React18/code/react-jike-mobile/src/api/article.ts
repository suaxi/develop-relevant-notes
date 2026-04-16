import { request } from '@/utils'
import type { Res } from './common'

export type Article = {
  art_id: string
  title: string
  aut_id: string
  comm_count: number
  pubdate: string
  aut_name: string
  is_top: number
  cover: {
    type: number
    images: string[]
  }
}

export type ArticleRes = {
  results: Article[]
  pre_timestamp: string
}

export type ReqParams = {
  channel_id: string
  timestamp: string
}

export function list(params: ReqParams) {
  return request<Res<ArticleRes>>({
    url: '/articles',
    method: 'GET',
    params
  })
}
