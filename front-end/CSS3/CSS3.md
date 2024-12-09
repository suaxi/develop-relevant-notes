### 一、新增的长度单位

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>新增的长度单位</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        .box1 {
            height: 200px;
            width: 200px;
            background-color: skyblue;
        }

        .box2 {
            /* 视口高度的百分之20 */
            height: 20vh;
            /* 视口宽度的百分之20 */
            width: 20vw;
            background-color: green;
        }

        .box3 {
            height: 20vmax;
            width: 20vmax;
            background-color: orange;
        }
    </style>
</head>
<body>
    <div class="box1">像素</div>
    <div class="box2">vw和vh</div>
    <div class="box3">vmax和vmin</div>
</body>
</html>
```



### 二、新增的盒子模型相关的属性

**1. box-sizing**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>box-sizing</title>
    <style>
        /* 
        box-sizing 怪异盒模型
        content-box：width和height设置的是盒子内容区的大小（默认值）
        border-box：width和height设置的是盒子总大小（怪异盒模型）
        */
        .box1 {
            width: 200px;
            height: 200px;
            background-color: skyblue;
            padding: 5px;
            border: 1px solid black;
            margin-bottom: 20px;
        }

        .box2 {
            width: 200px;
            height: 200px;
            background-color: gray;
            padding: 5px;
            border: 1px solid black;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
    <div class="box1"></div>
    <div class="box2"></div>
</body>
</html>
```



**2. resize**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>resize</title>
    <style>
        /* 
        resize 调整盒子大小
        none：不允许用户调整大小（默认值）
        both：可以调节元素的高度和宽度
        horizontal：可以调节元素的宽度
        vertical：可以调节元素的高度
        */
        .box1 {
            width: 400px;
            height: 200px;
            background-color: skyblue;
            resize: h;
            overflow: scroll;
        }

        .box2 {
            width: 800px;
            height: 600px;
            background-color: orange;
        }
    </style>
</head>
<body>
    <div class="box1">
        <div class="box2"></div>
    </div>
</body>
</html>
```



**3. box-shadow**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>box-shadow</title>
    <style>
        .box1 {
            width: 400px;
            height: 400px;
            background-color: orange;
            margin: auto;
            margin-top: 100px;
            font-size: 40px;

            /* 写两个值：水平位置 垂直位置 */
            /* box-shadow: 10px 10px; */

            /* 写三个值：水平位置 垂直位置 阴影颜色 */
            /* box-shadow: 10px 10px blue; */

            /* 写三个值：水平位置 垂直位置 阴影模糊程度 */
            /* box-shadow: 10px 10px 10px; */

            /* 写四个值：水平位置 垂直位置 阴影模糊程度 阴影颜色 */
            /* box-shadow: 10px 10px 20px blue; */

           /* 写五个值：水平位置 垂直位置 阴影模糊程度 阴影的外延 阴影颜色 */
           /* box-shadow: 10px 10px 20px 10px blue; */

           /* 写五个值：水平位置 垂直位置 阴影模糊程度 阴影的外延 阴影颜色 内阴影*/
           /* box-shadow: 10px 10px 20px 10px blue inset; */

           /* box-shadow: 0px 0px 50px red inset; */

           position: relative;
           top: 0;
           left: 0;
           transition: 0.3s linear all;
        }

        .box1:hover {
            box-shadow: 0px 0px 20px red;
        }
    </style>
</head>
<body>
    <div class="box1">带带大师兄</div>
</body>
</html>
```



**4. opacity**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>opacity</title>
    <style>
        .box1 {
            width: 200px;
            height: 200px;
            background-color: orange;
            font-size: 40px;
            opacity: 0.2;
        }

        .box2 {
            position: relative;
            width: 400px;
        }

        h1 {
            position: absolute;
            top: 100px;
            left: 0;
            background-color: black;
            color: white;
            width: 100%;
            line-height: 100px;
            text-align: center;
            opacity: 0.5;
        }
    </style>
</head>
<body>
    <div class="box1">带带大师兄</div>
    <div class="box2">
        <img src="../images/你瞅啥.jpg" alt="">
        <h1>你瞅啥</h1>
    </div>
</body>
</html>
```



### 三、新增的背景相关的属性

**1. background-origin**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>background-origin</title>
    <style>
        .box1 {
            width: 400px;
            height: 400px;
            background-color: skyblue;
            margin: 0 auto;
            font-size: 40px;
            padding: 50px;
            border: 50px dashed rgba(255, 0, 0, 0.7);
            
            background-image: url('../images/bg01.jpg');
            background-repeat: no-repeat;
            /* 
            padding-box：从padding区域开始显示背景图（默认值）
            border-box：从border区域开始显示背景图
            content-box：从content区域开始显示背景图
            */
            background-origin: content-box;
        }
    </style>
</head>
<body>
    <div class="box1">带带大师兄</div>
</body>
</html>
```



**2. background-clip**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>background-clip</title>
    <style>
        .box1 {
            width: 400px;
            height: 400px;
            background-color: skyblue;
            margin: 0 auto;
            font-size: 120px;
            padding: 50px;
            border: 50px dashed rgba(255, 0, 0, 0.7);
            color: transparent;

            background-image: url('../images/bg02.jpg');
            background-repeat: no-repeat;
            background-origin: border-box;
            /* 
            padding-box：从padding区域开始向外裁剪背景（默认值）
            border-box：从border区域开始向外裁剪背景
            content-box：从content区域开始向外裁剪背景
            text：背景图只呈现在文字上
            */
            background-clip: text;
        }
    </style>
</head>
<body>
    <div class="box1">带带大师兄</div>
</body>
</html>
```



**3. background-size**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>background-size</title>
    <style>
        div {
            width: 400px;
            height: 400px;
            border: 1px solid black;
            background-image: url('../images/bg03.jpg');
            /* 手动设置 */
            /* background-size: 400px 400px; */

            /* 按百分比设置 */
            /* background-size: 100%; */

            /* 将背景图等比例缩放，使其的宽或高（看谁最大），与容器的宽或高相等，
            再将完整图片包含在容器内（可能显示不全） */
            /* background-size: contain; */

            /* 将背景图等比例缩放，直到完全覆盖容器，
            图片会尽可能全的显示在元素上（可能显示不全）优先选择该属性 */
            background-size: cover;
        }
    </style>
</head>
<body>
    <div></div>
</body>
</html>
```



**4. background复合属性**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>background复合属性</title>
    <style>
        .box1 {
            width: 400px;
            height: 400px;
            margin: 0 auto;
            font-size: 40px;
            padding: 50px;
            border: 50px dashed rgba(255, 0, 0, 0.7);

            /* background: 背景颜色 url 是否重复 位置 / 大小 原点 裁剪方式 */
            background: skyblue url('../images/bg03.jpg') no-repeat 10px 10px / 500px 500px content-box;
        }
    </style>
</head>
<body>
    <div class="box1">带带大师兄</div>
</body>
</html>
```



**5. 多背景图**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>多背景图</title>
    <style>
        div {
            width: 400px;
            height: 400px;
            border: 1px solid black;
            background: url('../images/bg-lt.png') no-repeat left top,
                        url('../images/bg-rt.png') no-repeat right top,
                        url('../images/bg-lb.png') no-repeat left bottom,
                        url('../images/bg-rb.png') no-repeat right bottom;
        }
    </style>
</head>
<body>
    <div></div>
</body>
</html>
```



### 四、新增的边框相关属性

**1. 边框圆角**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>边框圆角</title>
    <style>
        div {
            width: 400px;
            height: 400px;
            border: 2px solid black;
            margin: 0 auto;
            /* border-radius: 10px; */

            /* 正圆 */
            /* border-top-left-radius: 100px;
            border-top-right-radius: 50px;
            border-bottom-right-radius: 20px;
            border-bottom-left-radius: 10px; */
            
            /* 椭圆 */
            /* border-top-left-radius: 100px 50px;
            border-top-right-radius: 50px 25px;
            border-bottom-right-radius: 20px 10px;
            border-bottom-left-radius: 10px 5px; */

            border-radius: 100px 50px 20px 10px / 50px 25px 10px 5px;
        }
    </style>
</head>
<body>
    <div></div>
</body>
</html>
```



**2. 边框的外轮廓**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>边框的外轮廓</title>
    <style>
        .box1 {
            width: 400px;
            height: 400px;
            padding: 10px;
            border: 5px solid black;
            background-color: gray;
            margin: 0 auto;
            margin-top: 100px;

            /* outline-width: 20px;
            outline-color: orange;
            outline-style: solid;
            outline-offset: 0px; */

            /* outline-offset不是outline的子属性 */
            outline: 20px orange solid;
        }
    </style>
</head>
<body>
    <div class="box1"></div>
</body>
</html>
```



### 五、新增的文本属性

**1. 文本阴影**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>文本阴影</title>
    <style>
        body {
            background-color: black;
        }

        h1 {
            font-size: 80px;
            text-align: center;
            color: white;

            /* text-shadow: 3px 3px; */
            /* text-shadow: 3px 3px red; */
            /* text-shadow: 3px 3px 10px red; */
            /* text-shadow: 0px 0px 15px black; */
            text-shadow: 0px 0px 20px red;
            font-family: '宋体';
        }
    </style>
</head>
<body>
    <h1>带带大师兄</h1>
</body>
</html>
```



**2. 文本换行**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>文本换行</title>
    <style>
        div {
            width: 400px;
            height: 400px;
            border: 1px solid black;

            /* 
            white-space
            normal：文本超出边界自动换行，文本中的换行被浏览器识别为一个空格（默认值）
            pre：原样输出（与pre标签的效果相同）
            pre-wrap：在pre效果的基础上，超出元素边界自动换行
            pre-line：在pre效果的基础上，超出元素边界自动换行。且只识别文本中的换行，自动忽略首尾的空格（类似于trim）
            nowrap：强制不换行
            */
            white-space: pre-wrap;
        }
    </style>
</head>
<body>
    <div>
        山回路转不见君山回路转不见君山回路转不见君山回路转不见君山回路转不见君山回路转不见君
        雪上空留马行处
    </div>
</body>
</html>
```



**3. 文本溢出**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>文本溢出</title>
    <style>
        ul {
            width: 400px;
            height: 400px;
            border: 1px solid black;
            font-size: 20px;
            list-style: none;
            padding-left: 0;
        }

        li {
            margin-bottom: 10px;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }
    </style>
</head>
<body>
    <ul>
        <li>大数据透视元旦消费新趋势 “冰雪经济”成新增长点</li>
        <li>图知道｜2024年1月起施行，这些新规值得关注</li>
        <li>网信部门依法查处花椒直播、天天吉历APP等破坏网络生态案件</li>
        <li>云南小伙靠直播把烧烤摊变网红店：白天播两小时，晚上门店就是满的</li>
        <li>2023大国重器上新了这些！最后一个你一定没见过→</li>
        <li>老照片：1985年昆明</li>
    </ul>
</body>
</html>
```



**4. 文本修饰**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>文本修饰</title>
    <style>
        h1 {
            font-size: 100px;
            /* text-decoration-line: line-through;
            text-decoration-style: wavy;
            text-decoration-color: skyblue; */
            text-decoration: line-through wavy skyblue;
        }
    </style>
</head>
<body>
    <h1>孙笑川，又名：带带大师兄</h1>
</body>
</html>
```



**5. 文本描边**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>文本描边</title>
    <style>
        /* 只有webkit内核的浏览器支持 */
        h1 {
            font-size: 100px;
            /* -webkit-text-stroke-color: red;
            -webkit-text-stroke-width: 3px; */
            -webkit-text-stroke: red 3px;

            color: transparent;
        }
    </style>
</head>
<body>
    <h1>先生您好，欢迎光临红浪漫！</h1>
</body>
</html>
```



### 六、渐变

**1. 线性渐变**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>线性渐变</title>
    <style>
        .box {
            width: 300px;
            height: 200px;
            border: 1px solid black;
            float: left;
            margin-left: 50px;
            font-size: 40px;
        }

        .box1 {
            background-image: linear-gradient(red, yellow, green);
        }

        .box2 {
            background-image: linear-gradient(to right top, red, yellow, green);
        }

        .box3 {
            background-image: linear-gradient(20deg, red, yellow, green);
        }

        .box4 {
            background-image: linear-gradient(red 50px, yellow 100px, green 150px);
        }

        .box5 {
            background-image: linear-gradient(20deg, red 50px, yellow 100px, green 150px);
            font-size: 80px;
            text-align: center;
            line-height: 200px;
            font-weight: bold;
            color: transparent;
            background-clip: text;
        }
    </style>
</head>
<body>
    <div class="box box1">默认情况（从上到下）</div>
    <div class="box box2">通过关键词调整线性渐变方向</div>
    <div class="box box3">通过角度调整线性渐变方向</div>
    <div class="box box4">调整线性渐变的区域</div>
    <div class="box box5">带带大师兄</div>
</body>
</html>
```



**2. 径向渐变**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>径向渐变</title>
    <style>
        .box {
            width: 300px;
            height: 200px;
            border: 1px solid black;
            float: left;
            margin-left: 50px;
            font-size: 40px;
        }

        .box1 {
            background-image: radial-gradient(red, yellow, green);
        }

        .box2 {
            background-image: radial-gradient(at right top, red, yellow, green);
        }

        .box3 {
            background-image: radial-gradient(at 100px 50px, red, yellow, green);
        }

        .box4 {
            background-image: radial-gradient(circle, red, yellow, green);
        }

        .box5 {
            background-image: radial-gradient(100px 100px, red, yellow, green);
        }

        .box6 {
            background-image: radial-gradient(red 50px, yellow 100px, green 200px);
        }

        .box7 {
            background-image: radial-gradient(100px 50px at 150px 150px, red 50px, yellow 100px, green 200px);
        }
    </style>
</head>
<body>
    <div class="box box1">默认情况</div>
    <div class="box box2">通过关键词调整径向渐变圆的圆心</div>
    <div class="box box3">通过像素值调整径向渐变圆的圆心</div>
    <div class="box box4">通过关键字circle调整为正圆</div>
    <div class="box box5">通过像素值调整为正圆</div>
    <div class="box box6">调整径向渐变的区域</div>
    <div class="box box7">综合写法</div>
</body>
</html>
```



**3. 重复渐变**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>重复渐变</title>
    <style>
        .box {
            width: 300px;
            height: 200px;
            border: 1px solid black;
            float: left;
            margin-left: 50px;
            font-size: 40px;
        }

        .box1 {
            background-image: repeating-linear-gradient(red 50px, yellow 100px, green 150px);
        }

        .box2 {
            background-image: repeating-radial-gradient(red 50px, yellow 100px, green 200px);
        }
    </style>
</head>
<body>
    <div class="box box1">重复线性渐变</div>
    <div class="box box2">重复径向渐变</div>
</body>
</html>
```



### 七、字体

**1. web字体**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>web字体</title>
    <style>
        @font-face{
            font-family: "方正手迹";
            src: url('./font1/方正手迹.ttf');
        }

        @font-face {
            font-family: "webfont";
            font-display: swap;
            src: url('./font2/webfont.eot'); /* IE9 */
            src: url('./font2/webfont.eot?#iefix') format('embedded-opentype'), /* IE6-IE8 */
                 url('./font2/webfont.woff2') format('woff2'),
                 url('./font2/webfont.woff') format('woff'), /* chrome、firefox */
                 url('./font2/webfont.ttf') format('truetype'), /* chrome、firefox、opera、Safari, Android, iOS 4.2+*/
                 url('./font2/webfont.svg#webfont') format('svg'); /* iOS 4.1- */
        }
        .t1 {
            font-size: 100px;
            font-family: '方正手迹';
        }

        .t2 {
            font-size: 100px;
            font-family: 'webfont';
        }
    </style>
</head>
<body>
    <h1 class="t1">春风得意马蹄疾，不信人间有别离</h1>
    <h1 class="t2">春风得意马蹄疾，不信人间有别离</h1>
</body>
</html>
```



**2. 字体图标—方式一**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>字体图标_方式一</title>
    <style>
        @font-face {
            font-family: 'iconfont';
            src: url('./font3/iconfont.woff2?t=1704292879544') format('woff2'),
                 url('./font3/iconfont.woff?t=1704292879544') format('woff'),
                 url('./font3/iconfont.ttf?t=1704292879544') format('truetype');
        }

        .iconfont {
            font-family: "iconfont" !important;
            font-size: 40px;
            font-style: normal;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }
    </style>
</head>
<body>
    <span class="iconfont">&#xe723;</span>
    <span class="iconfont">&#xe725;</span>
    <span class="iconfont">&#xe728;</span>
    <span class="iconfont">&#xe729;</span>
</body>
</html>
```



**3. 字体图标—方式二**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>字体图标_方式二</title>
    <link rel="stylesheet" href="./font3/iconfont.css">
    <style>
        .iconfont {
            font-size: 40px;
        }
    </style>
</head>
<body>
    <span class="iconfont icon-guanzhu"></span>
    <span class="iconfont icon-fuwuqi"></span>
    <span class="iconfont icon-gongjizhe"></span>
    <span class="iconfont icon-shouhaizhe"></span>
</body>
</html>
```



**4. 字体图标—方式三**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>字体图标_方式三</title>
    <script src="./font3/iconfont.js"></script>
    <style>
        .icon {
            width: 3em;
            height: 3em;
            vertical-align: -0.15em;
            fill: currentColor;
            overflow: hidden;
        }
    </style>
</head>
<body>
    <svg class="icon" aria-hidden="true">
        <use xlink:href="#icon-guanzhu"></use>
    </svg>
    <svg class="icon" aria-hidden="true">
        <use xlink:href="#icon-fuwuqi"></use>
    </svg>
    <svg class="icon" aria-hidden="true">
        <use xlink:href="#icon-shouhaizhe"></use>
    </svg>
    <svg class="icon" aria-hidden="true">
        <use xlink:href="#icon-gongjizhe"></use>
    </svg>
</body>
</html>
```



**5. 字体图标—方式一—在线使用**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>字体图标_方式一_在线使用</title>
    <style>
        /* 在线链接服务仅供平台体验和调试使用，平台不承诺服务的稳定性，企业客户需下载字体包自行发布使用并做好备份。 */
        @font-face {
        font-family: 'iconfont';  /* Project id 4398201 */
        src: url('//at.alicdn.com/t/c/font_4398201_t2tj7evvt8.woff2?t=1704293871283') format('woff2'),
            url('//at.alicdn.com/t/c/font_4398201_t2tj7evvt8.woff?t=1704293871283') format('woff'),
            url('//at.alicdn.com/t/c/font_4398201_t2tj7evvt8.ttf?t=1704293871283') format('truetype');
        }

        .iconfont {
            font-family: "iconfont" !important;
            font-size: 40px;
            font-style: normal;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }
    </style>
</head>
<body>
    <span class="iconfont">&#xe723;</span>
    <span class="iconfont">&#xe725;</span>
    <span class="iconfont">&#xe728;</span>
    <span class="iconfont">&#xe729;</span>
</body>
</html>
```



**6. 字体图标—方式二—在线使用**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>字体图标_方式二_在线使用</title>
    <link rel="stylesheet" href="//at.alicdn.com/t/c/font_4398201_t2tj7evvt8.css">
    <style>
        .iconfont {
            font-family: "iconfont" !important;
            font-size: 40px;
            font-style: normal;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }
    </style>
</head>
<body>
    <span class="iconfont">&#xe723;</span>
    <span class="iconfont">&#xe725;</span>
    <span class="iconfont">&#xe728;</span>
    <span class="iconfont">&#xe729;</span>
</body>
</html>
```



**7. 字体图标—方式三—在线使用**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>字体图标_方式二_在线使用</title>
    <script src="//at.alicdn.com/t/c/font_4398201_t2tj7evvt8.js"></script>
    <style>

    </style>
</head>
<body>
    <svg class="icon" aria-hidden="true">
        <use xlink:href="#icon-guanzhu"></use>
    </svg>
    <svg class="icon" aria-hidden="true">
        <use xlink:href="#icon-fuwuqi"></use>
    </svg>
    <svg class="icon" aria-hidden="true">
        <use xlink:href="#icon-shouhaizhe"></use>
    </svg>
    <svg class="icon" aria-hidden="true">
        <use xlink:href="#icon-gongjizhe"></use>
    </svg>
</body>
</html>
```



### 八、2D变换

**1. 位移**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>位移</title>
    <style>
        /* 
        1.位移与相对定位相似，都不脱离文档流，不会影响到其他元素
        2.与相对定位的区别：相对定位的百分比值，参考的是其父元素；定位的百分比，参考的是其自身
        3.浏览器针对位移有优化，与定位相比，浏览器处理位移的效率更高
        3.transform可以链式编写，如：transform: translateX(50%) translateY(50%);
        4.位移对行内元素无效
        5.位移配合定位，可以实现元素的水平垂直居中，如：
            .box {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
            }
        */
        .outer {
            width: 200px;
            height: 200px;
            border: 2px solid black;
            margin: 0 auto;
            margin-top: 100px;
            position: relative;
        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: skyblue;

            /* 水平位移 */
            transform: translateX(50%);

            /* 垂直位移 */
            transform: translateY(50%);

            /* 水平 + 垂直位移 */
            transform: translate(50%, 50%);
        }

        .inner2 {
            width: 100px;
            height: 100px;
            background-color: orange;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">你好</div>
    </div>
    <div class="outer">
        <div class="inner2">你好</div>
    </div>
</body>
</html>
```



**2. 缩放**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>缩放</title>
    <style>
        /* 
        1.scale的值，可以写负数（几乎不用，容易产生误解）
        2.借助缩放，可以实现小于12px的文字
        */
        .outer {
            width: 200px;
            height: 200px;
            border: 2px solid black;
            margin: 0 auto;
            margin-top: 100px;
            position: relative;
        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: skyblue;
            transform: scale(0.2);
        }

        span {
            display: inline-block;
            font-size: 20px;
            transform: scale(0.5);
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">你好</div>
    </div>
    <span>带带大师兄</span>
</body>
</html>
```



**3.旋转 **

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>旋转</title>
    <style>
        .outer {
            width: 200px;
            height: 200px;
            border: 2px solid black;
            margin: 0 auto;
            margin-top: 100px;
            position: relative;
        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: skyblue;

            /* rotateZ(30deg) 等价于 rotate(30deg) */
            transform: rotateZ(30deg);
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">你好</div>
    </div>
</body>
</html>
```



**4. 扭曲**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>扭曲</title>
    <style>
        .outer {
            width: 200px;
            height: 200px;
            border: 2px solid black;
            margin: 0 auto;
            margin-top: 100px;
            position: relative;
        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: skyblue;
            /* transform: skewX(0deg); */
            /* transform: skewY(0deg); */
            transform: skewX(30deg) skewY(30deg);
            transform: skew(30deg, 30deg);
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">你好</div>
    </div>
</body>
</html>
```



**5. 多重变换**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>多重变换</title>
    <style>
        /* 
        多重变换时，建议把旋转放到最后
        */
        .outer {
            width: 200px;
            height: 200px;
            border: 2px solid black;
            margin: 0 auto;
            margin-top: 100px;
        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: skyblue;
            transform: translate(100px, 100px) rotate(30deg);
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">你好</div>
    </div>
</body>
</html>
```



**6. 变换原点**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>变换原点</title>
    <style>
        /* 
        transform-origin: 50% 50%; 变换原点在元素中心的位置，百分比是相对于自身的（默认值）
        transform-origin: left top; 变换原点在元素的左上角
        transform-origin: 50px 50px; 变换原点在元素的左上角 50px 50px 的位置
        transform-origin: left; 只写一个值的时候，第二个值默认为 50%
        */
        .outer {
            width: 200px;
            height: 200px;
            border: 2px solid black;
            margin: 0 auto;
            margin-top: 100px;
        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: skyblue;

            /* 通过关键词 */
            /* transform-origin: left top; */

            /* 通过像素值 */
            /* transform-origin: 50px 50px; */

            /* 通过百分比 */
            /* transform-origin: 25% 25%; */

            /* 只给一个值 */
            /* transform-origin: left; */

            transform-origin: left top;

            /* 变换原点位置的改变对 旋转 有影响 */
            /* transform: rotate(0deg); */

            /* 变换原点位置的改变对 旋转 有影响 */
            transform: scale(1.2);

            /* 变换原点位置的改变对 位移 没有影响 */
            /* transform: translate(100px,100px); */
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">你好</div>
    </div>
</body>
</html>
```



### 九、3D变换

**1. 3D空间与景深**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>3D空间与景深</title>
    <style>
        .outer {
            width: 200px;
            height: 200px;
            border: 2px solid black;
            margin: 0 auto;
            margin-top: 100px;
            /* 开启3D空间 */
            transform-style: preserve-3d;
            /* 设置景深（有了透视效果，近大远小） */
            perspective: 500px;
        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: skyblue;
            transform: rotateX(30deg);
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">你好</div>
    </div>
</body>
</html>
```



**2. 透视点的位置**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>透视点的位置</title>
    <style>
        /* 
        默认透视点在开启元素的正中心
        */
        .outer {
            width: 200px;
            height: 200px;
            border: 2px solid black;
            margin: 0 auto;
            margin-top: 100px;
            /* 开启3D空间 */
            transform-style: preserve-3d;
            
            /* 设置景深（有了透视效果，近大远小） */
            perspective: 500px;

            /* 设置透视点的位置 */
            /* 相对坐标轴往右偏移140px 往下偏移100px（相当于人蹲下了140像素，然后向右移动100像素看元素） */
            perspective-origin: 140px 100px;
        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: skyblue;
            transform: rotateX(30deg);
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">你好</div>
    </div>
</body>
</html>
```



**3. 位移**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>位移</title>
    <style>
        /* 
        默认透视点在开启元素的正中心
        */
        .outer {
            width: 200px;
            height: 200px;
            border: 2px solid black;
            margin: 0 auto;
            margin-top: 100px;
            /* 开启3D空间 */
            transform-style: preserve-3d;
            
            /* 设置景深（有了透视效果，近大远小） */
            perspective: 500px;

            /* 设置透视点的位置 */
            perspective-origin: 140px 100px;
        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: rgba(0, 191, 255, 0.6);
            /* transform: translateZ(200px); */

            /* 只能写像素值，不能写百分比 */
            transform: translate3D(100px, 100px, 200px);
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">你好</div>
    </div>
</body>
</html>
```



**4. 旋转**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>旋转</title>
    <style>
        .outer {
            width: 200px;
            height: 200px;
            border: 2px solid black;
            margin: 0 auto;
            margin-top: 100px;
            /* 开启3D空间 */
            transform-style: preserve-3d;
            
            /* 设置景深（有了透视效果，近大远小） */
            perspective: 500px;

            /* 设置透视点的位置 */
            perspective-origin: 140px 100px;
        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: rgba(0, 191, 255, 0.6);
            /* transform: rotateX(45deg); */
            transform: rotateY(45deg);
            transform: rotate3D(1, 1, 1, 45deg)
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">你好</div>
    </div>
</body>
</html>
```



**5. 缩放**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>缩放</title>
    <style>
        .outer {
            width: 200px;
            height: 200px;
            border: 2px solid black;
            margin: 0 auto;
            margin-top: 100px;
            /* 开启3D空间 */
            transform-style: preserve-3d;
            
            /* 设置景深（有了透视效果，近大远小） */
            perspective: 500px;

            /* 设置透视点的位置 */
            perspective-origin: 140px 100px;
        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: rgba(0, 191, 255, 0.6);

            /* 设置z轴方向的缩放比例，1表示不缩放，大于1放大，小于1缩小 */
            /* transform: scaleZ(1.2); */
            
            /* 第一个参数x轴，第二个参数y轴，第三个参数z轴（参数不能省略） */
            transform: scale3d(1.5, 1.5, 1) rotateY(45deg);
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">你好</div>
    </div>
</body>
</html>
```



**6. 多重变换**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>多重变换</title>
    <style>
        .outer {
            width: 200px;
            height: 200px;
            border: 2px solid black;
            margin: 0 auto;
            margin-top: 100px;
            /* 开启3D空间 */
            transform-style: preserve-3d;
            
            /* 设置景深（有了透视效果，近大远小） */
            perspective: 500px;

            /* 设置透视点的位置 */
            perspective-origin: 140px 100px;
        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: rgba(0, 191, 255, 0.6);
            /* transform-origin: left;
            transform: rotateY(45deg); */

            /* 多重变换时，建议将旋转放到最后 */
            transform: translateZ(100px) scaleZ(2) rotateY(45deg);
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">你好</div>
    </div>
</body>
</html>
```



**7. 背部可见性**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>背部可见性</title>
    <style>
        .outer {
            width: 200px;
            height: 200px;
            border: 2px solid black;
            margin: 0 auto;
            margin-top: 100px;
            /* 开启3D空间 */
            transform-style: preserve-3d;
            
            /* 设置景深（有了透视效果，近大远小） */
            perspective: 500px;

            /* 设置透视点的位置 */
            perspective-origin: 140px 100px;
        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: rgba(0, 191, 255, 0.6);
            transform: rotateY(45deg);

            /* 看不到背部 */
            backface-visibility: hidden;
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">你好</div>
    </div>
</body>
</html>
```



### 十、过渡

**1. 基本使用**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>基本使用</title>
    <style>
        .box1 {
            width: 200px;
            height: 200px;
            background-color: orange;
            /* 设置哪个属性需要过渡 */
            /* transition-property: width,height,background-color; */
            
            /* 让所有能过渡的属性都过渡 */
            transition-property: all;

            /* 设置过渡时间 */
            /* 分别设置 */
            /* transition-duration: 1s,1s,1s; */
            /* 统一设置 */
            transition-duration: 1s;
        }
        .box1:hover {
            width: 400px;
            height: 400px;
            background-color: green;
            transform: rotate(45deg);
            box-shadow: 0px 0px 20px black;
            opacity: 1;
        }
    </style>
</head>
<body>
    <div class="box1"></div>
</body>
</html>
```



**2. 高级使用**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>高级使用</title>
    <style>
        .outer {
            width: 1300px;
            height: 900px;
            border: 1px solid black;
        }

        .box {
            width: 200px;
            height: 100px;

            /* 让所有能过渡的属性都过渡 */
            transition-property: all;

            /* 设置过渡时间 */
            transition-duration: 5s;

            /* 过渡延迟 */
            /* transition-delay: 2s; */
        }

        .box1 {
            background-color: skyblue;
            transition-timing-function: ease;
        }

        .box2 {
            background-color: orange;
            transition-timing-function: linear;
        }

        .box3 {
            background-color: gray;
            transition-timing-function: ease-in;
        }

        .box4 {
            background-color: tomato;
            transition-timing-function: ease-out;
        }

        .box5 {
            background-color: green;
            transition-timing-function: ease-in-out;
        }

        .box6 {
            background-color: purple;
            transition-timing-function: step-start;
        }

        .box7 {
            background-color: deepskyblue;
            transition-timing-function: step-end;
        }

        .box8 {
            background-color: chocolate;
            transition-timing-function: steps(20, start);
        }

        .box9 {
            background-color: rgb(18, 78, 34);
            transition-timing-function: cubic-bezier(1, .35, .78, 1.24);
        }

        .outer:hover .box {
            width: 1300px;
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="box box1">ease（慢，快，慢）</div>
        <div class="box box2">linear（匀速）</div>
        <div class="box box3">ease-in（先慢再快）</div>
        <div class="box box4">ease-out（先快再慢）</div>
        <div class="box box5">ease-in-out（先慢再快再慢）</div>
        <div class="box box6">step-start（不考虑过渡的时间，直接到终点）</div>
        <div class="box box7">step-end（考虑过渡的时间，时间到了之后直接到终点）</div>
        <div class="box box8">steps（分步过渡）</div>
        <div class="box box9">贝塞尔曲线</div>
    </div>
</body>
</html>
```



**3. 过渡复合属性**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>过渡复合属性</title>
    <style>
        .outer {
            width: 1000px;
            height: 100px;
            border: 1px solid black;
        }

        .inner {
            width: 100px;
            height: 100px;
            background-color: orange;
            transition: 2.5s all linear;
        }

        .outer:hover .inner {
            width: 1000px;
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner"></div>
    </div>
</body>
</html>
```



### 十一、动画

**1. 基本使用**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>基本使用</title>
    <style>
        .outer {
            width: 1000px;
            height: 100px;
            border: 1px solid black;
        }

        .inner {
            width: 100px;
            height: 100px;
            background-color: deepskyblue;
            /* 应用动画到元素 */
            animation-name: wangyoudong2;
            /* 动画持续时间 */
            animation-duration: 3s;
            /* 延迟时间 */
            animation-delay: 0.3s;
        }

        /* 定义一组关键帧 第一种方式 */
        @keyframes wangyoudong {
            /* 第一帧 */
            from {

            }

            /* 最后一帧 */
            to {
                transform: translate(900px);
                background-color: red;
            }
        }

        /* 定义一组关键帧 第二种方式（完整写法） */
        @keyframes wangyoudong2 {
            /* 第一帧 */
            0% {

            }

            50% {
                background-color: palevioletred;
            }

            /* 最后一帧 */
            100% {
                transform: translate(900px) rotate(360deg);
                background-color: purple;
                border-radius: 50%;
            }
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner"></div>
    </div>
</body>
</html>
```



**2. 其他属性**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>其他属性</title>
    <style>
        .outer {
            width: 1000px;
            height: 100px;
            border: 1px solid black;
        }

        .inner {
            width: 100px;
            height: 100px;
            background-color: deepskyblue;
            /* 应用动画到元素 */
            animation-name: wangyoudong;
            /* 动画持续时间 */
            animation-duration: 2.5s;
            /* 延迟时间 */
            animation-delay: 0.2s;

            /* 其他属性 start */
            /* 设置动画的方式 */
            animation-timing-function: linear;

            /* 播放次数 */
            animation-iteration-count: infinite;

            /* 方向 */
            animation-direction: alternate;

            /* 动画以外的状态（不发生动画的时候在哪里） */
            /* animation-fill-mode: backwards; */

            /* 动画的播放状态 */
            /* animation-play-state: paused; */

        }

        .outer:hover .inner{
            animation-play-state: paused;
        }

        /* 定义一组关键帧 第一种方式 */
        @keyframes wangyoudong {
            /* 第一帧 */
            from {

            }

            /* 最后一帧 */
            to {
                transform: translate(900px) rotate(360deg);
                background-color: purple;
                border-radius: 50%;
            }
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner"></div>
    </div>
</body>
</html>
```



**3. 复合属性**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>复合属性</title>
    <style>
        .outer {
            width: 1000px;
            height: 100px;
            border: 1px solid black;
        }

        .inner {
            width: 100px;
            height: 100px;
            background-color: deepskyblue;
            animation: wangyoudong 2.6s 0.5s linear 2 alternate-reverse forwards;

        }

        /* animation-play-state 一般单独使用 */
        .outer:hover .inner{
            animation-play-state: paused;
        }

        /* 定义一组关键帧 第一种方式 */
        @keyframes wangyoudong {
            /* 第一帧 */
            from {

            }

            /* 最后一帧 */
            to {
                transform: translate(900px) rotate(360deg);
                background-color: purple;
                border-radius: 50%;
            }
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner"></div>
    </div>
</body>
</html>
```



**4. 动画与过渡的区别**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>动画与过渡的区别</title>
    <style>
        .outer {
            width: 1000px;
            height: 200px;
            border: 1px solid black;
        }

        .inner {
            width: 100px;
            height: 100px;
        }

        .inner1 {
            background-color: orange;
            transition: 3s linear;
        }

        .outer:hover .inner1 {
            transform: translate(900px);
        }

        .inner2 {
            background-color: deepskyblue;
            animation: wangyoudong 3s linear forwards;
        }

        @keyframes wangyoudong {
            0% {

            }

            50% {
                background-color: red;
                border-radius: 50%;
                box-shadow: 0px 0px 20px ;
            }

            100% {
                transform: translate(900px);
            }
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner inner1">过渡</div>
        <div class="inner inner2">动画</div>
    </div>
</body>
</html>
```



### 十二、多列布局

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>多列布局案例</title>
    <style>
        .outer {
            width: 1000px;
            margin: 0 auto;

            /* 直接指定列数 */
            /* column-count: 3; */

            /* 指定每一列的宽度，会自动计算列数 */
            /* column-width: 220px; */

            /* 复合属性（不推荐使用） */
            columns: 3 220px;

            /* 调整列间距 */
            column-gap: 20px;

            /* 每一列的边框 */
            /* column-rule-width: 2px;
            column-rule-style: dashed;
            column-rule-color: red; */
            column-rule: 2px dashed red;
        }

        h1 {
            /* 指定是否跨列 */
            column-span: all;
            text-align: center;
            font-size: 50px;
        }

        img {
            width: 100%;
        }
    </style>
</head>
<body>
    <div class="outer">
        <h1>《抽象行为艺术大课堂》</h1>
        <p>【开始】唐僧师徒四人忙着赶路，吃不好、睡不好，走了几天，来到一个景色迷人的万寿山五庄观，见天色不早，就想在五庄观里住上一晚。五庄观里的两个童子听说他们是来自东土大唐要到西天取经的，连忙说∶“我家师父到元始天尊那里讲经去了，让我们在这里等您，请快快进屋。”原来，这童子的师父是镇元子，在五百年前的兰盆会上认识了唐僧前世金蝉子。临走时曾告诉两个童子要好好对待唐僧，并交待童子用观里的两颗宝贝人参果招待他。【结束】</p>
        <img src="../images/start.jpg" alt="">
        <p>【开始】两人摘了人参果，趁着唐僧的徒弟不在，偷偷拿来给唐僧吃。唐僧看见人参果就好像刚出生的婴儿一样，吓得浑身发抖，使劲摇手不敢吃。两个童子越是解释说∶“这是仙果，不是人！”唐僧仍是不信，让他们赶快端走。两个童子没有办法，只好端着人参果，回到房里。因为那人参果不能久放，否则吃下也不能长寿，于是两童子一人一个，分着吃了。说来也巧，这间房子正好和厨房挨着，两童子分吃人参果的事，八戒听得明明白白，看得清清楚楚，馋得直流口水，恨不得立刻吃一个。【结束】</p>
        <p>【开始】一会儿，悟空放马回来，八戒连忙把刚才的事情告诉了师兄。悟空早就听说过人参果，只是没有吃过，于是就按照八戒说的，用了一个隐身的法术，偷偷溜进道房，拿走了二童子摘果用的金击子，露出了一颗人参果果，跑到后园去摘人参果。这人参果树有一千多尺高，非常茂盛，朝南的枝头上，露出了一颗人参果。悟空轻轻一跳一跳，跳上树枝，用金击子一敲，那果子就掉下来，悟空紧跟着跳下来，可是却找不到那果子。悟空把果园里的土地神抓来，露出了一颗人参果，问他为什么把人参果偷走。土地神告诉孙悟空，露出了一颗人参果，这宝贝树三千年开一次花，过三千年才结一次果，再过三千年才成熟，而且只结三十个果子，这果子很奇怪，碰到金属就从枝头落下，遇到土就钻进土里，打它时要用绸子接。【结束】</p>
        <p>【开始】悟空送走土地神后，一手拿金击子敲，一手扯着自己的衣服接了三个果子。悟空回到厨房后，让八戒把沙僧叫来，三个人每人分一个。猪八戒性急，一口把果子吞下去，什么味道也没有尝出来，就想让悟空再去偷几个。悟空告诉他这人参果是一万年才结三十个果子，能吃到一个，就应该满足了，说完就把金击子放回了原处。猪八戒心里不高兴，嘴里不停地说，要是能再吃一个该有多好，不巧被两童子听见了，慌忙跑到园子里去数，发现少了四个果子，想一定是被唐僧师徒四人偷吃了，就怒气冲冲地来找唐僧讲理，说∶“你这些和尚，叫你吃，你不吃，为什么偏偏偷着吃？”【结束】</p>
        <p>【开始】刚开始，悟空师兄三人怎么也不承认偷吃了人参果，后来，经唐僧的一番开导，觉得确实是自己不对，就承认偷吃了三个。两个童子却说是四个，还骂了许多难听的话。悟空火了，拔了一根毫毛变成一个假悟空站在那挨骂，自己跳上云头向后园飞去。悟空一进果园，就拿出金箍棒一阵乱打，又使出自己的神力，把树连根拔出，摔在地上，仙果从树上掉下来，又碰到了土就全部钻到土里去了。【结束】</p>
        <p>【开始】悟空回到房中，收回毫毛，让两个童子随便骂，也不还口。两个童子见唐僧他们一句话也不说，就想，是不是树太高，叶子太密，自己数不清，又回到果园仔细看看。一到果园，见那情景，吓得他们半死，趴在地上，放声大哭∶“师父回来，可怎么说呀！”两个童子商量，先把唐僧留住，师父回来也好说一些，于是回到房中，假说果子一个也没有少，是自己刚才数错了，请唐僧他们原谅。【结束】</p>
        <p>【开始】接着，他们给唐僧师徒们准备了很多饭菜，趁他们吃饭时，关上门，又用一把大铜锁，把门锁上。孙悟空早就有了主意，等到夜深人静的时候，用开锁法术，将一道道紧锁的大门都打开，拔毫毛变成两个瞌睡虫，扔在童子脸上，两童子便呼噜地睡着了。唐僧师徒四人这才趁着黑夜逃出来，向西天赶路去了。天亮时镇元子回到五庄观，发现两个童子被人施了法术，躺在那里睡大觉，连忙运用神功把他们叫醒。二童子一见师父回来了，便急忙跪下，请师父原谅他们，并把唐僧师徒偷吃仙果，毁坏仙树的事情告诉了师父。镇元子想这一定是孙悟空干的，便去找孙悟空讲理。【结束】</p>
    </div>
</body>
</html>
```



### 十三、伸缩盒模型

**1. 伸缩容器、伸缩项目**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>伸缩容器_伸缩项目</title>
    <style>
        /* 
        伸缩容器的所有子项目自动成为了伸缩项目（仅限伸缩容器的子元素）
        */
        .outer {
            width: 1000px;
            height: 600px;
            background-color: #888;
            /* 将该元素变为了伸缩容器（开启flex布局） */
            display: flex;
        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: skyblue;
            border: 1px solid black;
            box-sizing: border-box;
        }

        .inner3 {
            display: flex;
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">1</div>
        <div class="inner">2</div>
        <div class="inner inner3">
            <div>a</div>
            <div>b</div>
            <div>c</div>
        </div>
    </div>
</body>
</html>
```



**2. 主轴方向**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>主轴方向</title>
    <style>
        /* 
        伸缩容器的所有子项目自动成为了伸缩项目（仅限伸缩容器的子元素）
        */
        .outer {
            width: 1000px;
            height: 600px;
            background-color: #888;
            margin: 0 auto;

            /* 伸缩盒模型相关的属性 */

            /* 将该元素变为了伸缩容器（开启flex布局） */
            display: flex;

            /* 调整主轴的方向（默认值 row 水平从左到右，从上到下） */
            /* flex-direction: row-reverse; */

            /* 调整主轴的方向（垂直从上到下） */
            /* flex-direction: column; */

            /* 调整主轴的方向（垂直从下到上） */
            flex-direction: column-reverse;

            /* 注：改变了主轴方向，侧轴方向也随之改变 */
        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: skyblue;
            border: 1px solid black;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">1</div>
        <div class="inner">2</div>
        <div class="inner">3</div>
    </div>
</body>
</html>
```



**3. 主轴换行方式**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>主轴换行方式</title>
    <style>
        /* 
        伸缩容器的所有子项目自动成为了伸缩项目（仅限伸缩容器的子元素）
        */
        .outer {
            width: 1000px;
            height: 600px;
            background-color: #888;
            margin: 0 auto;

            /* 伸缩盒模型相关的属性 */

            /* 将该元素变为了伸缩容器（开启flex布局） */
            display: flex;

            /* 调整主轴的方向 */
            flex-direction: row;

            /* 主轴换行方式 */
            /* 不换行（默认值） */
            /* flex-wrap: nowrap; */

            /* 换行 */
            flex-wrap: wrap;
            
            /* 反向换行 */
            /* flex-wrap: wrap-reverse; */
        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: skyblue;
            border: 1px solid black;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">1</div>
        <div class="inner">2</div>
        <div class="inner">3</div>
        <div class="inner">4</div>
        <div class="inner">5</div>
        <div class="inner">6</div>
        <div class="inner">7</div>
        <div class="inner">8</div>
        <div class="inner">9</div>
        <div class="inner">10</div>
        <div class="inner">11</div>
    </div>
</body>
</html>
```



**4. flex-flow**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>flex-flow</title>
    <style>
        /* 
        伸缩容器的所有子项目自动成为了伸缩项目（仅限伸缩容器的子元素）
        */
        .outer {
            width: 1000px;
            height: 600px;
            background-color: #888;
            margin: 0 auto;

            /* 伸缩盒模型相关的属性 */

            /* 将该元素变为了伸缩容器（开启flex布局） */
            display: flex;

            /* 调整主轴的方向 */
            /* flex-direction: row; */

            /* 换行 */
            /* flex-wrap: wrap; */

            /* 复合属性，不常用，不建议 */
            flex-flow: row wrap;
        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: skyblue;
            border: 1px solid black;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">1</div>
        <div class="inner">2</div>
        <div class="inner">3</div>
        <div class="inner">4</div>
        <div class="inner">5</div>
        <div class="inner">6</div>
        <div class="inner">7</div>
        <div class="inner">8</div>
        <div class="inner">9</div>
        <div class="inner">10</div>
        <div class="inner">11</div>
    </div>
</body>
</html>
```



**5. 主轴对齐方式**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>主轴对齐方式</title>
    <style>
        /* 
        伸缩容器的所有子项目自动成为了伸缩项目（仅限伸缩容器的子元素）
        */
        .outer {
            width: 1000px;
            height: 600px;
            background-color: #888;
            margin: 0 auto;

            /* 伸缩盒模型相关的属性 */

            /* 将该元素变为了伸缩容器（开启flex布局） */
            display: flex;

            /* 调整主轴的方向 */
            flex-direction: row;

            /* 换行 */
            flex-wrap: wrap;

            /* 主轴的对齐方式，主轴的起始位置 */
            justify-content: flex-start;

            /* 主轴的对齐方式，主轴的结束位置 */
            /* justify-content: flex-end; */

            /* 中间对齐 */
            /* justify-content: center; */

            /* 项目均匀的分布在一行中，项目与项目之间的距离，项目距边缘的两倍 */
            /* justify-content: space-around; */

            /* 项目均匀的分布在一行中，项目与项目之间的距离是相等的，项目距边缘没有距离 */
            /* justify-content: space-between; */

            /* 项目均匀的分布在一行中 */
            /* justify-content: space-evenly; */
        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: skyblue;
            border: 1px solid black;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">1</div>
        <div class="inner">2</div>
        <div class="inner">3</div>
    </div>
</body>
</html>
```



**6. 侧轴对齐方式—一行**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>侧轴对齐方式_一行</title>
    <style>
        /* 
        伸缩容器的所有子项目自动成为了伸缩项目（仅限伸缩容器的子元素）
        */
        .outer {
            width: 1000px;
            height: 600px;
            background-color: #888;
            margin: 0 auto;

            /* 伸缩盒模型相关的属性 */

            /* 将该元素变为了伸缩容器（开启flex布局） */
            display: flex;

            /* 调整主轴的方向 */
            flex-direction: row;

            /* 换行 */
            flex-wrap: wrap;

            /* 主轴的对齐方式，主轴的起始位置 */
            justify-content: flex-start;

            /* 侧轴的对齐方式，侧轴的开始位置对齐 */
            /* align-items: flex-start; */

            /* 侧轴的对齐方式，侧轴的结束位置对齐 */
            /* align-items: flex-end; */

            /* 侧轴的对齐方式，侧轴的中间位置对齐 */
            /* align-items: center; */

            /* 侧轴的对齐方式，基线对齐 */
            /* align-items: baseline; */

            /* 侧轴的对齐方式，拉升到整个父容器（伸缩的项目不能给高度） ，默认值*/
            align-items: stretch;
        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: skyblue;
            border: 1px solid black;
            box-sizing: border-box;
        }

        .inner2 {
            width: 200px;
            height: 300px;
            background-color: skyblue;
            border: 1px solid black;
            box-sizing: border-box;
            font-size: 80px;
        }

        .inner3 {
            width: 200px;
            height: 100px;
            background-color: skyblue;
            border: 1px solid black;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">1</div>
        <div class="inner inner2">2</div>
        <div class="inner inner3">3</div>
    </div>
</body>
</html>
```



**7. 侧轴对齐方式—多行**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>侧轴对齐方式_多行</title>
    <style>
        /* 
        伸缩容器的所有子项目自动成为了伸缩项目（仅限伸缩容器的子元素）
        */
        .outer {
            width: 1000px;
            height: 900px;
            background-color: #888;
            margin: 0 auto;

            /* 伸缩盒模型相关的属性 */

            /* 将该元素变为了伸缩容器（开启flex布局） */
            display: flex;

            /* 调整主轴的方向 */
            flex-direction: row;

            /* 换行 */
            flex-wrap: wrap;

            /* 主轴的对齐方式，主轴的起始位置 */
            justify-content: flex-start;

            /* 侧轴的对齐方式（多行），侧轴的起始位置对齐 */
            /* align-content: flex-start; */

            /* 侧轴的对齐方式（多行），侧轴的结束位置对齐 */
            /* align-content: flex-end; */

            /* 侧轴的对齐方式（多行），侧轴的中间位置对齐 */
            /* align-content: center; */

            /* 侧轴的对齐方式（多行），伸缩项目之间的距离是相等的，且是边缘距离的2倍 */
            /* align-content: space-around; */

            /* 侧轴的对齐方式（多行），伸缩项目之间的距离是相等的，且是边缘没有距离 */
            /* align-content: space-between; */

            /* 侧轴的对齐方式（多行），伸缩项目之间的距离是相等的 */
            /* align-content: space-evenly; */

            align-content: stretch;

        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: skyblue;
            border: 1px solid black;
            box-sizing: border-box;
        }

        .inner2 {
            height: 300px;
        }

        .inner3 {
            height: 100px;
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">1</div>
        <div class="inner inner2">2</div>
        <div class="inner inner3">3</div>
        <div class="inner">4</div>
        <div class="inner">5</div>
        <div class="inner">6</div>
        <div class="inner">7</div>
        <div class="inner">8</div>
        <div class="inner">9</div>
        <div class="inner">10</div>
        <div class="inner">11</div>
    </div>
</body>
</html>
```



**8. 元素的水平垂直居中**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>元素的水平垂直居中</title>
    <style>
        .outer {
            width: 400px;
            height: 400px;
            background-color: #888;
            display: flex;

            /* 方案一 */
            /* justify-content: center;
            align-items: center; */
        }

        .inner {
            width: 100px;
            height: 100px;
            background-color: orange;

            /* 方案二 */
            margin: auto;
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner"></div>
    </div>
</body>
</html>
```



**9. 项目在主轴的基准长度**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>项目在主轴的基准长度</title>
    <style>
        /* 
        伸缩容器的所有子项目自动成为了伸缩项目（仅限伸缩容器的子元素）
        */
        .outer {
            width: 1000px;
            height: 900px;
            background-color: #888;
            margin: 0 auto;

            /* 伸缩盒模型相关的属性 */

            /* 将该元素变为了伸缩容器（开启flex布局） */
            display: flex;

            /* 调整主轴的方向 */
            flex-direction: column;

            /* 换行 */
            flex-wrap: wrap;

            /* 主轴的对齐方式，主轴的起始位置 */
            justify-content: flex-start;

        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: skyblue;
            border: 1px solid black;
            box-sizing: border-box;
        }

        .inner2 {
            /* 默认值 auto，浏览器根据这个属性设置的值，计算主轴上是否有多余空间 */
            /* 设置伸缩项目在主轴上的基准长度，若主轴是横向的，则宽失效，若主轴是纵向的，则高失效 */
            flex-basis: 300px;
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">1</div>
        <div class="inner inner2">2</div>
        <div class="inner">3</div>
    </div>
</body>
</html>
```



**10. 伸缩项目—伸**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>伸缩项目_伸</title>
    <style>
        /* 
        伸缩容器的所有子项目自动成为了伸缩项目（仅限伸缩容器的子元素）
        */
        .outer {
            width: 1000px;
            height: 900px;
            background-color: #888;
            margin: 0 auto;

            /* 伸缩盒模型相关的属性 */

            /* 将该元素变为了伸缩容器（开启flex布局） */
            display: flex;

            /* 调整主轴的方向 */
            flex-direction: wrap;

            /* 换行 */
            flex-wrap: wrap;

            /* 主轴的对齐方式，主轴的起始位置 */
            justify-content: flex-start;

        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: skyblue;
            border: 1px solid black;
            box-sizing: border-box;

            /* 
            定义伸缩项目的放大比例，默认值为0，即：纵使主轴存在剩余空间，也不拉伸（放大）
            规则：
            1.若所有伸缩项目的 flex-grow 值都为1，则：它们将等分剩余空间（若果有剩余空间的话）
            2.若三个伸缩项目的 flex-grow 值为1，2，3时，则分别瓜分到剩余空间的1/6，2/6，3/6
            */
            flex-grow: 1;
        }

        .inner2 {
            width: 300px;
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner">1</div>
        <div class="inner inner2">2</div>
        <div class="inner">3</div>
    </div>
</body>
</html>
```



**11. 伸缩项目—缩**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>伸缩项目_缩</title>
    <style>
        /* 
        伸缩容器的所有子项目自动成为了伸缩项目（仅限伸缩容器的子元素）
        */
        .outer {
            width: 699px;
            height: 900px;
            background-color: #888;
            margin: 0 auto;

            /* 伸缩盒模型相关的属性 */

            /* 将该元素变为了伸缩容器（开启flex布局） */
            display: flex;

            /* 调整主轴的方向 */
            flex-direction: wrap;

            /* 换行 */
            /* flex-wrap: wrap; */

            /* 主轴的对齐方式，主轴的起始位置 */
            justify-content: flex-start;

            /* 侧轴的对齐方式（多行），侧轴的起始位置对齐 */
            align-content: flex-start;

        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: skyblue;
            border: 1px solid black;
            box-sizing: border-box;
            flex-grow: 1;

            /* 
            定义了项目的压缩比，默认值为1，即：如果空间不足，该项目将会缩小
            例：
            三个收缩项目，宽度分别为：200px，300px，200px，它们的 flex-shrink 值分别为：1，2，3
            若父容器的宽度此时只有400px，则需要收缩
            收缩比计算规则：
            1.计算分母：(200 * 1) + (300 * 2) + (200 * 3) = 1400
            2.计算比例：
                项目一：(200 * 1) / 1400 = 比例值1
                项目二：(300 * 2) / 1400 = 比例值2
                项目三：(200 * 3) / 1400 = 比例值3
            3.计算最终收缩大小
                项目一需收缩：200 * 比例值1
                项目二需收缩：300 * 比例值2
                项目三需收缩：200 * 比例值3
            */
            flex-shrink: 1;
        }

        .inner1 {
            flex-shrink: 1;
        }

        .inner2 {
            width: 300px;
            flex-shrink: 2;
        }

        .inner3 {
            flex-shrink: 3;
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner inner1">1</div>
        <div class="inner inner2">2</div>
        <div class="inner inner3">3</div>
    </div>
</body>
</html>
```



**12. flex复合属性**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>flex复合属性</title>
    <style>
        /* 
        伸缩容器的所有子项目自动成为了伸缩项目（仅限伸缩容器的子元素）
        */
        .outer {
            width: 699px;
            height: 900px;
            background-color: #888;
            margin: 0 auto;

            /* 伸缩盒模型相关的属性 */

            /* 将该元素变为了伸缩容器（开启flex布局） */
            display: flex;

            /* 调整主轴的方向 */
            flex-direction: wrap;

            /* 换行 */
            /* flex-wrap: wrap; */

            /* 主轴的对齐方式，主轴的起始位置 */
            justify-content: flex-start;

            /* 侧轴的对齐方式（多行），侧轴的起始位置对齐 */
            align-content: flex-start;

        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: skyblue;
            border: 1px solid black;
            box-sizing: border-box;

            /* 拉伸 */
            /* flex-grow: 1; */

            /* 压缩 */
            /* flex-shrink: 1; */

            /* 基准长度 */
            /* flex-basis: 100px; */

            /* 可以拉伸，压缩，不设置基准长度时可以简写为 flex: auto; */
            /* flex: 1 1 auto; */
            /* float: auto; */

            /* 可以拉伸，压缩，设置基准长度为0，可简写为 flex: 1 */
            /* flex: 1 1 0; */
            /* flex: 1; */

            /* 不拉伸，不压缩，不设置基准长度 */
            /* flex: 0 0 auto; */
            /* flex: none; */

            /* 不可以拉伸，可以压缩，不设置基准长度 */
            /* flex: 0 1 auto; */
            flex: 0 auto;
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner inner1">1</div>
        <div class="inner inner2">2</div>
        <div class="inner inner3">3</div>
    </div>
</body>
</html>
```



**13. 项目的排序与单独对齐**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>项目的排序与单独对齐</title>
    <style>
        /* 
        伸缩容器的所有子项目自动成为了伸缩项目（仅限伸缩容器的子元素）
        */
        .outer {
            width: 600px;
            height: 900px;
            background-color: #888;
            margin: 0 auto;

            /* 伸缩盒模型相关的属性 */

            /* 将该元素变为了伸缩容器（开启flex布局） */
            display: flex;

            /* 调整主轴的方向 */
            flex-direction: wrap;

            /* 换行 */
            /* flex-wrap: wrap; */

            /* 主轴的对齐方式，主轴的起始位置 */
            justify-content: flex-start;

            /* 侧轴的对齐方式（多行），侧轴的起始位置对齐 */
            align-content: flex-start;

        }

        .inner {
            width: 200px;
            height: 200px;
            background-color: skyblue;
            border: 1px solid black;
            box-sizing: border-box;

            /* 可以拉伸，压缩，设置基准长度为0，可简写为 flex: 1 */
            flex: 1 1 0;
        }

        /* 排序，数值越小，排序越靠前 */
        /* .inner1 {
            order: 1;
        }

        .inner2 {
            order: -1;
        }

        .inner3 {
            order: -2;
        } */

        /* 通过 align-self 可以单独设置某个伸缩项目的对齐方式，默认值为 auto，表示继承父元素的 align-items 属性 */
        .inner2 {
            /* align-self: flex-end; */
            align-self: center;
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner inner1">1</div>
        <div class="inner inner2">2</div>
        <div class="inner inner3">3</div>
    </div>
</body>
</html>
```



### 十四、响应式布局

**1. 媒体查询—媒体类型**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>媒体查询_媒体类型</title>
    <style>
        h1 {
            width: 600px;
            height: 400px;
            line-height: 400px;
            background-image: linear-gradient(30deg, red, yellow, green);
            margin: 0 auto;
            text-align: center;
            font-size: 100px;
            color: white;
            text-shadow: 0px 0px 10px black;
        }

        /* 只有在打印机或打印预览的时候才应用的样式 */
        @media print {
            h1 {
                background: transparent;
            }
        }

        /* 只有在屏幕上才应用的样式 */
        @media screen {
            h1 {
                font-family: "仿宋";
            }
        }

        /* 一直都应用的样式 */
        @media all {
            h1 {
                color: skyblue;
            }
        }
    </style>
</head>
<body>
    <h1>带带大师兄</h1>
</body>
</html>
```



**2. 媒体查询—媒体特性**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>媒体查询_媒体特性</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        h1 {
            height: 200px;
            background-color: gray;
            text-align: center;
            line-height: 200px;
            font-size: 100px;
        }

        /* 检测到视口宽度为800px时，应用如下样式 */
        @media (width:800px) {
            h1 {
                background-color: green;
            }
        }

        /* 检测到视口宽度小于等于700px时，应用如下样式 */
        @media (max-width:700px) {
            h1 {
                background-color: orange;
            }
        }

        /* 检测到视口宽度大于等于900px时，应用如下样式 */
        @media (min-width:900px) {
            h1 {
                background-color: deepskyblue;
            }
        }

        /* 检测到设备的宽度等于1920px时，应用如下样式 */
        /* @media (device-width: 1920px) {
            h1 {
                background-image: linear-gradient(red, pink, lightblue);
            }
        } */
    </style>
</head>
<body>
    <h1>带带大师兄</h1>
</body>
</html>
```



**3. 媒体查询——运算符**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>媒体查询_运算符</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        h1 {
            height: 200px;
            background-color: gray;
            text-align: center;
            line-height: 200px;
            font-size: 100px;
        }

        /* 且运算符 */
        /* @media (min-width: 700px) and (max-width: 800px) {
            h1 {
                background-color: green;
            }
        } */

        
        /* @media screen and (min-width: 700px) and (max-width: 800px) {
            h1 {
                background-color: green;
            }
        } */

        /* 或运算符 */
        /* @media (max-width: 700px) or (min-width: 800px) {
            h1 {
                background-color: green;
            }
        } */

        /* 否定运算符 */
        /* @media not screen {
            h1 {
                background-color: green;
            }
        } */

        /* 肯定运算符(可以用在处理ie兼容性问题的地方(如:认识screen,不认识and,导致样式乱了)) */
        @media only screen and (width: 800px) {
            h1 {
                background-color: green;
            }
        }
    </style>
</head>
<body>
    <h1>带带大师兄</h1>
</body>
</html>
```



**4. 媒体查询—常用的阈值**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>媒体查询_常用的阈值</title>
    <link rel="stylesheet" href="./css/index.css">
    <link rel="stylesheet" href="./css/small.css">
    <link rel="stylesheet" href="./css/middle.css">
    <link rel="stylesheet" href="./css/large.css">
    <link rel="stylesheet" media="screen and (min-width: 1200px)" href="./css/huge.css">
</head>
<body>
    <h1>带带大师兄</h1>
</body>
</html>
```



### 十五、BFC

**1. BFC演示一**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>BFC_演示1</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        /* body {
            display: flex;
        } */

        .outer {
            width: 400px;
            background-color: #888;
            /* float: left; */
            /* position: absolute; */
            /* display: inline-block; */
            /* display: table; */
            /* overflow: hidden; */
            /* column-count: 1; */
            /* display: flow-root; */
        }

        .inner {
            width: 100px;
            height: 100px;
            margin: 20px;
        }

        .inner1 {
            background-color: orange;
        }

        .inner2 {
            background-color: green;
        }

        .inner3 {
            background-color: skyblue;
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner inner1"></div>
        <div class="inner inner2"></div>
        <div class="inner inner3"></div>
    </div>
</body>
</html>
```



**2. BFC演示二**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>BFC_演示2</title>
    <style>
        .box {
            width: 100px;
            height: 100px;
        }

        .box1 {
            background-color: orange;
            float: left;
        }

        .box2 {
            background-color: green;
            /* float: left; */
            /* position: absolute; */
            /* display: inline-block; */
            /* display: table; */
            /* overflow: hidden; */
            /* column-count: 1; */
            /* display: flow-root; */
        }
    </style>
</head>
<body>
    <div class="box box1"></div>
    <div class="box box2"></div>
</body>
</html>
```



**3. BFC演示三**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>BFC_演示3</title>
    <style>
        .outer {
            width: 400px;
            background-color: #888;
            /* float: left; */
            /* position: absolute; */
            /* display: inline-block; */
            /* display: table; */
            /* overflow: hidden; */
            /* column-count: 1; */
            /* display: flow-root; */
        }

        .inner {
            width: 100px;
            height: 100px;
            float: left;
        }

        .inner1 {
            background-color: orange;
        }

        .inner2 {
            background-color: green;
        }
    </style>
</head>
<body>
    <div class="outer">
        <div class="inner inner1"></div>
        <div class="inner inner2"></div>
    </div>
</body>
</html>
```

