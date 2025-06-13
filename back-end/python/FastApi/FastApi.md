## FastApi

*参考 B站苑昊老师 `FastApi` 课程*

### 一、QuickStart

#### 1. 安装

```bash
# fastapi
pip install fastapi

# uvicorn
pip install uvicorn
```

#### 2. 测试 demo

##### （1）main.py

```python
from fastapi import FastAPI

app = FastAPI()


@app.get("/")
async def index():
    return "Hello World!"
```



##### （2）启动

```bash
# 通过命令行
uvicorn main:app --reload
```

```python
# 通过 main 函数
if __name__ == '__main__':
    import uvicorn

    uvicorn.run("main:app", host="127.0.0.1", port=8088, reload=True)
```



##### （3）接口文档

启动后通过 http://127.0.0.1:8088/docs 即可访问 `swagger`

![swagger文档](static/1.快速开始/swagger文档.png)


