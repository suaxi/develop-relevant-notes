## 基于Python与OpenCV的图像解析处理

*参考 b 站人工智能算法工程师 up 主*

### 一、环境配置

#### 1. 安装

Anaconda下载地址：https://www.anaconda.com/download

安装后使用 `Python3.6` 虚拟环境及 `opencv-python 3.4.1.15` 版本

```bash
# 注：以管理员身份运行 Anaconda Prompt
conda create -p C:\ProgramData\anaconda3\envs\py36 python=3.6
conda activate C:\ProgramData\anaconda3\envs\py36
pip install opencv-python==3.4.1.15

# 验证 opncv
cd /d C:\ProgramData\anaconda3\envs\py36
python
ipmort cv2
cv2.__version__
```

![1.导入并验证cv2](static/1.安装/1.导入并验证cv2.png)



安装拓展

```bash
pip install opencv-contrib-python==3.4.1.15
```



#### 2. Notebook

安装并切换 `Python3.6` 内核

```bash
# 继续在刚刚的 Anaconda Prompt 命令行窗口中执行以下代码
pip install ipykernel
python -m ipykernel install --user --name py36 --display-name "Python 3.6 (py36)"
```

打开 Jupyter Notebook，通过 http://localhost:8888/tree 可以访问到网页版 Notebook

新建一个python测试文件，在顶部的 kernel 选项卡中可找到切换内核选项，切换至刚刚安装的 3.6 版本

![2.Notebook](static/1.安装/2.Notebook.png)



### 二、图像基本操作

#### 1. 图像的读取与显示

- cv2.IMREAD_COLOR 彩色图像
- cv2.IMGREAD_GRAYSCALE 灰度图像

```python
import cv2 # OpenCV 读取的格式是 BGR
import matplotlib.pyplot as plt
import numpy as np
%matplotlib inline

img = cv2.imread('test.jpg')

# 执行 img 回车可显示该图像的数据
array([[[189, 150, 188],
        [189, 150, 188],
        [188, 149, 187],
        ...,
        [189, 147, 188],
        [188, 146, 187],
        [189, 146, 189]],

       [[189, 150, 188],
        [188, 149, 187],
        [188, 149, 187],
        ...,
        [189, 147, 188],
        [188, 146, 187],
        [189, 146, 189]],

       [[188, 149, 187],
        [188, 149, 187],
        [188, 149, 187],
        ...,
        [189, 147, 188],
        [188, 146, 187],
        [189, 146, 189]],

       ...,

       [[189, 150, 188],
        [189, 150, 188],
        [189, 150, 188],
        ...,
        [189, 147, 188],
        [188, 146, 187],
        [189, 146, 189]],

       [[189, 150, 188],
        [189, 150, 188],
        [189, 150, 188],
        ...,
        [189, 147, 188],
        [188, 145, 188],
        [189, 146, 189]],

       [[189, 150, 188],
        [189, 150, 188],
        [189, 150, 188],
        ...,
        [189, 147, 188],
        [188, 145, 188],
        [189, 146, 189]]], dtype=uint8)
```

```python
# 图像的显示
cv2.imshow('image', img)
# 等待时间，毫秒级0表示任意键终止
cv2.waitKey(0)
cv2.destroyAllWindows()
```

![1.读取图像并显示](static/2.图像基本操作/1.读取图像并显示.png)

```python
def cv_show(name, img):
    cv2.imshow(name, img)
    cv2.waitKey(0)
    cv2.destroyAllWindows()
```

```python
# 执行 img.shape 可输出该图像的 shape 数据
img.shape
(295, 300, 3)
```

```python
# 读取灰度图
img = cv2.imread('test.jpg', cv2.IMREAD_GRAYSCALE)
img
array([[166, 166, 165, ..., 164, 163, 164],
       [166, 165, 165, ..., 164, 163, 164],
       [165, 165, 165, ..., 164, 163, 164],
       ...,
       [166, 166, 166, ..., 164, 163, 164],
       [166, 166, 166, ..., 164, 163, 164],
       [166, 166, 166, ..., 164, 163, 164]], dtype=uint8)
```

```python
# 图像的显示（灰度图）
cv2.imshow('image', img)
# 等待时间，毫秒级0表示任意键终止
cv2.waitKey(0)
cv2.destroyAllWindows()
```

![1.读取图像并显示](static/2.图像基本操作/2.读取图像并显示（灰度）.png)

```python
# 保存
cv2.imwrite('test1.jpg', img)
```

```python
type(img)
numpy.ndarray
```



#### 2. 读取视频

- cv2.VideoCapture 可以捕获摄像头，用数字来控制不同的设备，如：0,1
- 如果是视频文件，可以直接指定路径

```python
vc = cv2.VideoCapture('jswyn.mp4')

if vc.isOpened():
    open, frame = vc.read()
else:
    open = False

while open:
    ret, frame = vc.read()
    if frame is None:
        break
    if ret == True:
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        cv2.imshow('result', gray)
        if cv2.waitKey(10) & 0xFF == 27:
            break
vc.release()
cv2.destroyAllWindows()
```

![3.读取视频](static/2.图像基本操作/3.读取视频.png)



#### 3. 截取部分图像数据（ROI区域）

```python
img = cv2.imread('test.jpg')
jiaozi = img[0:50, 0: 150]
cv_show('jiaozi', jiaozi)
```



#### 4. 颜色通道提取

```python
b,g,r = cv2.split(img)
r
array([[188, 188, 187, ..., 188, 187, 189],
       [188, 187, 187, ..., 188, 187, 189],
       [187, 187, 187, ..., 188, 187, 189],
       ...,
       [188, 188, 188, ..., 188, 187, 189],
       [188, 188, 188, ..., 188, 188, 189],
       [188, 188, 188, ..., 188, 188, 189]], dtype=uint8)

r.shape
(295, 300)
```

```python
# 合并
img = cv2.merge((b,g,r))
img.shape
(295, 300, 3)
```

```python
# 只保留 R 通道
cur_img = img.copy()
cur_img[:,:,0] = 0
cur_img[:,:,1] = 0
cv_show('R', cur_img)
```

```python
# 只保留 G 通道
cur_img = img.copy()
cur_img[:,:,0] = 0
cur_img[:,:,2] = 0
cv_show('G', cur_img)
```

```python
# 只保留 B 通道
cur_img = img.copy()
cur_img[:,:,1] = 0
cur_img[:,:,2] = 0
cv_show('B', cur_img)
```

