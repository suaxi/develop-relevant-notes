import { Image, List } from 'antd-mobile'
import { useEffect, useState } from 'react'
import { list, type ArticleRes } from '@/api/article'

type Props = {
  channelId: string
}

const HomeList = (props: Props) => {
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
          >
            {item.title}
          </List.Item>
        ))}
      </List>
    </>
  )
}

export default HomeList
