import { request } from '@/utils'
import type { Res } from './common'

export type Channel = {
  id: number
  name: string
}

type ChannelRes = {
  channels: Channel[]
}

export function list() {
  return request<Res<ChannelRes>>({
    url: 'channels',
    method: 'GET'
  })
}
