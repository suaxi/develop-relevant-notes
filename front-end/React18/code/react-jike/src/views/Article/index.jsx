import { Link, useNavigate } from 'react-router-dom'
import {
  Card,
  Breadcrumb,
  Form,
  Button,
  Radio,
  DatePicker,
  Select,
  Popconfirm
} from 'antd'
// 引入汉化包 时间选择器显示中文
import locale from 'antd/es/date-picker/locale/zh_CN'

import { Table, Tag, Space } from 'antd'
import { EditOutlined, DeleteOutlined } from '@ant-design/icons'
import img404 from '@/assets/error.png'
import { useChannels } from '@/hooks/useChannels'
import { useEffect, useState } from 'react'
import { list, del } from '@/api/article'

const { Option } = Select
const { RangePicker } = DatePicker

const Article = () => {
  // 文章频道
  const { channelList } = useChannels()

  // 文章审核状态
  const articleStatus = {
    1: <Tag color="warning">待审核</Tag>,
    2: <Tag color="success">审核通过</Tag>
  }

  const columns = [
    {
      title: '封面',
      dataIndex: 'cover',
      width: 120,
      render: (cover) => {
        return (
          <img src={cover.images[0] || img404} width={80} height={60} alt="" />
        )
      }
    },
    {
      title: '标题',
      dataIndex: 'title',
      width: 220
    },
    {
      title: '状态',
      dataIndex: 'status',
      render: (data) => articleStatus[data]
    },
    {
      title: '发布时间',
      dataIndex: 'pubdate'
    },
    {
      title: '阅读数',
      dataIndex: 'read_count'
    },
    {
      title: '评论数',
      dataIndex: 'comment_count'
    },
    {
      title: '点赞数',
      dataIndex: 'like_count'
    },
    {
      title: '操作',
      render: (data) => {
        return (
          <Space size="middle">
            <Button type="primary" shape="circle" icon={<EditOutlined />} />
            <Popconfirm
              title="提示"
              description="确认删除?"
              onConfirm={() => onDeleteArticleConfirm(data.id)}
              okText="确定"
              cancelText="取消"
            >
              <Button
                type="primary"
                danger
                shape="circle"
                icon={<DeleteOutlined />}
              />
            </Popconfirm>
          </Space>
        )
      }
    }
  ]

  // 查询参数
  const [queryParams, setQueryParams] = useState({
    status: '',
    channel_id: '',
    begin_pubdate: null,
    end_pubdate: null,
    page: 1,
    per_page: 10
  })
  const [count, setCount] = useState(0)

  // 文章列表
  const [articleList, setArticleList] = useState([])
  useEffect(() => {
    async function getArticleList() {
      const res = await list(queryParams)
      setArticleList(res.data.results)
      setCount(res.data.total_count)
    }
    getArticleList()
  }, [queryParams])

  const onFinish = (formData) => {
    setQueryParams({
      ...queryParams,
      status: formData.status,
      channel_id: formData.channel_id,
      begin_pubdate: formData.date?.[0].format('YYYY-MM-DD'),
      end_pubdate: formData.date?.[1].format('YYYY-MM-DD')
    })
  }

  const onPageChange = (pageNum, pageSize) => {
    setQueryParams({
      ...queryParams,
      page: pageNum,
      per_page: pageSize
    })
  }

  // 删除文章
  const onDeleteArticleConfirm = (id) => {
    del(id).then(() => {
      setQueryParams({
        ...queryParams
      })
    })
  }
  return (
    <div>
      <Card
        title={
          <Breadcrumb
            items={[
              { title: <Link to={'/'}>首页</Link> },
              { title: '文章列表' }
            ]}
          />
        }
        style={{ marginBottom: 20 }}
      >
        <Form initialValues={{ status: '' }} onFinish={onFinish}>
          <Form.Item label="状态" name="status">
            <Radio.Group>
              <Radio value={''}>全部</Radio>
              <Radio value={1}>待审核</Radio>
              <Radio value={2}>审核通过</Radio>
            </Radio.Group>
          </Form.Item>

          <Form.Item label="频道" name="channel_id">
            <Select placeholder="请选择文章频道" style={{ width: 120 }}>
              {channelList.map((item) => (
                <Option key={item.id} value={item.id}>
                  {item.name}
                </Option>
              ))}
            </Select>
          </Form.Item>
          <Form.Item label="日期" name="date">
            {/* 传入locale属性 控制中文显示*/}
            <RangePicker locale={locale}></RangePicker>
          </Form.Item>

          <Form.Item>
            <Button type="primary" htmlType="submit" style={{ marginLeft: 40 }}>
              筛选
            </Button>
          </Form.Item>
        </Form>
      </Card>
      <Card title={`根据筛选条件共查询到 ${count} 条结果：`}>
        <Table
          rowKey="id"
          columns={columns}
          dataSource={articleList}
          pagination={{
            total: count,
            pageSize: queryParams.per_page,
            onChange: onPageChange
          }}
        />
      </Card>
    </div>
  )
}

export default Article
