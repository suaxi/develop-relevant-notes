import { useState, useEffect } from 'react'
import { channels } from '@/api/article'

function useChannels() {
  const [channelList, setChannelList] = useState([])
  useEffect(() => {
    const getChannelList = async () => {
      const res = await channels()
      setChannelList(res.data.channels)
    }
    getChannelList()
  }, [])
  return {
    channelList
  }
}

export { useChannels }
