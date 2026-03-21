import { useParams, useSearchParams } from 'react-router-dom'

const Article = () => {
  // const [params] = useSearchParams()
  // const name = params.get('name')
  // const age = params.get('age')

  const params = useParams()
  const name = params.name
  const age = params.age
  return (
    <div>
      文章
      {name}
      {age}
    </div>
  )
}

export default Article
