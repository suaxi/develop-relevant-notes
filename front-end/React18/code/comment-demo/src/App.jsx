import './App.scss'
import ble from '/images/ble.jpg'
import zjl from '/images/zjl.jpg'
import xs from '/images/xs.jpg'
import { useMemo, useRef, useState } from 'react'
import classNames from 'classnames'
import { v4 as uuidv4 } from 'uuid'
import dayjs from 'dayjs'

// 评论列表数据
const defaultList = [
  {
    rpid: 3,
    user: {
      uid: '13258165',
      avatar: zjl,
      uname: '周杰伦'
    },
    content: '哎哟，不错哦',
    ctime: '10-18 08:15',
    like: 100
  },
  {
    rpid: 2,
    user: {
      uid: '36080105',
      avatar: xs,
      uname: '许嵩'
    },
    content: '我寻你千百度 日出到迟暮',
    ctime: '11-13 11:29',
    like: 99
  },
  {
    rpid: 1,
    user: {
      uid: '30009257',
      avatar: ble,
      uname: '孙笑川'
    },
    content: '凶手找到了@带带大师兄',
    ctime: '10-19 09:00',
    like: 66
  }
]

const user = {
  uid: '30009257',
  avatar: ble,
  uname: '药水哥'
}

const tabs = [
  { type: 'hot', text: '最热' },
  { type: 'time', text: '最新' }
]

const App = () => {
  // 评论列表
  const [commentList, setCommentList] = useState(defaultList)

  // 删除评论
  const deleteCommentHandler = (id) => {
    if (window.confirm('确定删除这条评论吗？')) {
      setCommentList(commentList.filter((item) => item.rpid !== id))
    }
  }

  // tab 标签
  const [type, setType] = useState('hot')
  const tabChangeHandler = (type) => {
    setType(type)
  }
  const displayList = useMemo(() => {
    const list = [...commentList]
    if (type === 'hot') {
      return list.sort((o1, o2) => o2.like - o1.like)
    }
    if (type === 'time') {
      const year = new Date().getFullYear()
      return list.sort(
        (o1, o2) =>
          new Date(`${year}-${o1.ctime}`) - new Date(`${year}-${o2.ctime}`)
      )
    }
    return list
  }, [commentList, type])

  // 发布评论
  const [content, setContent] = useState('')
  const contentInputRef = useRef(null)
  const publishHandler = () => {
    if (!content) {
      return
    }

    setCommentList((prev) => [
      ...prev,
      {
        rpid: uuidv4(),
        user: {
          uid: user.uid,
          avatar: ble,
          uname: user.uname
        },
        content,
        ctime: dayjs(new Date()).format('MM-DD HH:mm'),
        like: 100
      }
    ])

    // 清空输入框
    setContent('')
    // 重新聚焦
    contentInputRef.current.focus()
  }

  return (
    <div className="app">
      {/* 导航 Tab */}
      <div className="reply-navigation">
        <ul className="nav-bar">
          <li className="nav-title">
            <span className="nav-title-text">评论</span>
            {/* 评论数量 */}
            <span className="total-reply">{10}</span>
          </li>
          <li className="nav-sort">
            {tabs.map((item) => (
              <span
                key={item.type}
                className={classNames('nav-item', {
                  active: type === item.type
                })}
                onClick={() => tabChangeHandler(item.type)}
              >
                {item.text}
              </span>
            ))}
          </li>
        </ul>
      </div>

      <div className="reply-wrap">
        <div className="box-normal">
          <div className="reply-box-avatar">
            <div className="bili-avatar">
              <img className="bili-avatar-img" src={ble} alt="用户头像" />
            </div>
          </div>
          <div className="reply-box-wrap">
            <textarea
              className="reply-box-textarea"
              placeholder="发布一条评论"
              ref={contentInputRef}
              value={content}
              onChange={(e) => setContent(e.target.value)}
            />
            <div className="reply-box-send">
              <div className="send-text" onClick={publishHandler}>
                发布
              </div>
            </div>
          </div>
        </div>
        <div className="reply-list">
          {displayList.map((item) => (
            <div key={item.rpid} className="reply-item">
              <div className="root-reply-avatar">
                <div className="bili-avatar">
                  <img
                    className="bili-avatar-img"
                    alt={item.user.uname}
                    src={item.user.avatar}
                  />
                </div>
              </div>

              <div className="content-wrap">
                <div className="user-info">
                  <div className="user-name">{item.user.uname}</div>
                </div>
                <div className="root-reply">
                  <span className="reply-content">{item.content}</span>
                  <div className="reply-info">
                    <span className="reply-time">{item.ctime}</span>
                    <span className="reply-time">点赞数:{item.like}</span>
                    {/* 条件渲染 */}
                    {user.uid === item.user.uid && (
                      <span
                        className="delete-btn"
                        onClick={() => deleteCommentHandler(item.rpid)}
                      >
                        删除
                      </span>
                    )}
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  )
}

export default App
