import { getArticle, type ArticleDetail } from '@/api/article'
import { NavBar } from 'antd-mobile'
import { useEffect, useState } from 'react'
import { useNavigate, useSearchParams } from 'react-router-dom'

const Detail = () => {
  const navigate = useNavigate()
  const [articleDetail, setArticleDetail] = useState<ArticleDetail | null>(null)
  const [params] = useSearchParams()
  const id = params.get('id')
  useEffect(() => {
    const getArticleDetail = async () => {
      const res = await getArticle(id!)
      setArticleDetail(res.data.data)
    }
    getArticleDetail()
  }, [id])

  // 正式返回数据之前，使用 loading 占位，同时抑制 dangerouslySetInnerHTML 类型警告
  if (!articleDetail) {
    return <div>loading...</div>
  }

  return (
    <div>
      <NavBar onBack={() => navigate(-1)}>{articleDetail?.title}</NavBar>
      <div>
        <p>作者：{articleDetail.aut_name}</p>
        <p>发布时间：{articleDetail.pubdate}</p>
      </div>
      <hr />
      <div
        dangerouslySetInnerHTML={{
          __html: articleDetail?.content
        }}
      ></div>
    </div>
  )
}

export default Detail
