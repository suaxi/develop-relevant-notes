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

export type ArticleDetail = {
  art_id: string
  attitude: number
  aut_id: string
  aut_name: string
  aut_photo: string
  comm_count: number
  content: string
  is_collected: boolean
  is_followed: boolean
  like_count: number
  pubdate: string
  read_count: number
  title: string
}

export function list(params: ReqParams) {
  return request<Res<ArticleRes>>({
    url: '/articles',
    method: 'GET',
    params
  })
}

export function getArticle(id: string) {
  return request<Res<ArticleDetail>>({
    url: `/articles/${id}`,
    method: 'GET'
  })
}
