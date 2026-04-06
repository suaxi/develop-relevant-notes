import {
  Card,
  Breadcrumb,
  Form,
  Button,
  Radio,
  Input,
  Upload,
  Space,
  Select,
  message
} from 'antd'
import { PlusOutlined } from '@ant-design/icons'
import { Link } from 'react-router-dom'
import './index.scss'

import ReactQuill from 'react-quill'
import 'react-quill/dist/quill.snow.css'
import { useState } from 'react'
import { submit as submitApi } from '@/api/article'
import { useChannels } from '@/hooks/useChannels'

const { Option } = Select

const Publish = () => {
  // 文章频道
  const { channelList } = useChannels()

  // 保存文章
  const submit = (formData) => {
    const article = {
      title: formData.title,
      content: formData.content,
      cover: {
        type: coverType,
        images: imageList?.map((item) => item.response.data.url)
      },
      channel_id: formData.channel_id
    }
    submitApi(article)
  }

  // 文件上传
  const [imageList, setImageList] = useState([])
  const onFileUploadChange = (info) => {
    setImageList(info.fileList)
  }

  // 切换封面图片类型
  const [coverType, setCoverType] = useState(1)
  const onCoverTypeChange = (e) => {
    setCoverType(e.target.value)
  }
  return (
    <div className="publish">
      <Card
        title={
          <Breadcrumb
            items={[
              { title: <Link to={'/'}>首页</Link> },
              { title: '发布文章' }
            ]}
          />
        }
      >
        <Form
          labelCol={{ span: 4 }}
          wrapperCol={{ span: 16 }}
          initialValues={{ type: 1 }}
          onFinish={submit}
        >
          <Form.Item
            label="标题"
            name="title"
            rules={[{ required: true, message: '请输入文章标题' }]}
          >
            <Input placeholder="请输入文章标题" style={{ width: 400 }} />
          </Form.Item>
          <Form.Item
            label="频道"
            name="channel_id"
            rules={[{ required: true, message: '请选择文章频道' }]}
          >
            <Select placeholder="请选择文章频道" style={{ width: 400 }}>
              {channelList.map((item) => (
                <Option key={item.id} value={item.id}>
                  {item.name}
                </Option>
              ))}
            </Select>
          </Form.Item>
          <Form.Item label="封面">
            <Form.Item name="type">
              <Radio.Group onChange={onCoverTypeChange}>
                <Radio value={1}>单图</Radio>
                <Radio value={3}>三图</Radio>
                <Radio value={0}>无图</Radio>
              </Radio.Group>
            </Form.Item>
            {coverType > 0 && (
              <Upload
                listType="picture-card"
                showUploadList
                action={'http://geek.itheima.net/v1_0/upload'}
                name="image"
                onChange={onFileUploadChange}
                maxCount={coverType}
                // fileList={imageList}
              >
                <div style={{ marginTop: 8 }}>
                  <PlusOutlined />
                </div>
              </Upload>
            )}
          </Form.Item>
          <Form.Item
            label="内容"
            name="content"
            rules={[{ required: true, message: '请输入文章内容' }]}
          >
            <ReactQuill
              className="publish-quill"
              theme="snow"
              placeholder="请输入文章内容"
            />
          </Form.Item>
          <Form.Item wrapperCol={{ offset: 4 }}>
            <Space>
              <Button size="large" type="primary" htmlType="submit">
                发布文章
              </Button>
            </Space>
          </Form.Item>
        </Form>
      </Card>
    </div>
  )
}

export default Publish
