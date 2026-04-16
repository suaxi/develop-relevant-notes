import { list, type Channel } from '@/api/channel'
import { useEffect, useState } from 'react'

function useTabs() {
  const [channels, setChannels] = useState<Channel[]>([])
  useEffect(() => {
    const getChannels = async () => {
      const res = await list()
      setChannels(res.data.data.channels)
    }
    getChannels()
  }, [])

  return {
    channels
  }
}

export { useTabs }
