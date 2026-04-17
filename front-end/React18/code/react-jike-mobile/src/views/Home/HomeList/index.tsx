import { Image, InfiniteScroll, List } from 'antd-mobile'
import { useEffect, useState } from 'react'
import { list, type ArticleRes } from '@/api/article'
import { useNavigate } from 'react-router-dom'

type Props = {
  channelId: string
}

const HomeList = (props: Props) => {
  const navigate = useNavigate()
  const { channelId } = props
  // 文章列表
  const [articles, setArticles] = useState<ArticleRes>({
    results: [],
    pre_timestamp: '' + new Date().getTime()
  })
  useEffect(() => {
    const getArticles = async () => {
      const res = await list({
        channel_id: channelId,
        timestamp: '' + new Date().getTime()
      })
      setArticles(res.data.data)
    }
    getArticles()
  }, [channelId])

  // 瀑布流加载
  const loadMore = async () => {
    const res = await list({
      channel_id: channelId,
      timestamp: articles.pre_timestamp
    })

    if (res.data.data.results.length === 0) {
      setHasMore(false)
    }

    setArticles({
      // 拼接新旧数据
      results: [...articles.results, ...res.data.data.results],
      pre_timestamp: res.data.data.pre_timestamp
    })
  }

  // 瀑布流开关
  const [hasMore, setHasMore] = useState(true)
  return (
    <>
      <List>
        {articles.results.map((item) => (
          <List.Item
            key={item.art_id}
            prefix={
              <Image
                src={item.cover?.images?.[0]}
                style={{ borderRadius: 20 }}
                fit="cover"
                width={40}
                height={40}
              />
            }
            description={item.pubdate}
            onClick={() => navigate(`/detail?id=${item.art_id}`)}
          >
            {item.title}
          </List.Item>
        ))}
      </List>
      <InfiniteScroll loadMore={loadMore} hasMore={hasMore} threshold={10} />
    </>
  )
}

export default HomeList
