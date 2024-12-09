### 一、新增的语义化标签

**1. 布局标签**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>新增布局标签</title>
</head>
<body>
    <!-- 头部 -->
    <header>
        <h1>xxx商城</h1>
    </header>
    <hr>
    <!-- 主导航 -->
    <nav>
        <a href="#">首页</a>
        <a href="#">订单</a>
        <a href="#">购物车</a>
        <a href="#">我的</a>
    </nav>
    <!-- 主要内容 -->
    <div class="page-content">
        <article>
            <h2>感动中国人物</h2>
            <section>
                <h3>第一名：孙笑川</h3>
                <p>男，籍贯四川，33岁</p>
            </section>
            <section>
                <h3>第二名：药水哥</h3>
                <p>真名刘波，男，籍贯湖北，30岁</p>
            </section>
            <section>
                <h3>第三名：Giao哥</h3>
                <p>男，籍贯河南，29岁</p>
            </section>
        </article>
        <!-- 侧边栏导航 -->
        <aside style="float: right;">
            <nav>
                <ul>
                    <li><a href="#">秒杀专区</a></li>
                    <li><a href="#">会员专区</a></li>
                    <li><a href="#">优惠券专区</a></li>
                    <li><a href="#">品牌专区</a></li>
                </ul>
            </nav>
        </aside>
    </div>
    <hr>
    <footer>
        <nav>
            <a href="#">友情链接1</a>
            <a href="#">友情链接2</a>
            <a href="#">友情链接3</a>
            <a href="#">友情链接4</a>
        </nav>
    </footer>
</body>
</html>
```



**2. 状态标签**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>新增的状态标签</title>
</head>
<body>
    <span>手机电量：</span>
    <meter min="0" max="100" value="5" low="10" high="20" optimum="90"></meter>
    <span>当前进度：</span>
    <progress max="100" value="80"></progress>
</body>
</html>
```



**3. 列表标签**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>新增的列表标签</title>
</head>
<body>
    <form action="#">
        <input type="text" list="myData">
        <button>搜索</button>
    </form>
    <datalist id="myData">
        <option value="孙笑川">孙笑川</option>
        <option value="药水哥">药水哥</option>
        <option value="Giao哥">Giao哥</option>
    </datalist>
    <hr>
    <details>
        <summary>如何一夜暴富</summary>
        <p>白日做梦</p>
    </details>
</body>
</html>
```



**4. 文本标签**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>新增的文本标签</title>
</head>
<body>
    <ruby>
        <span>魑魅魍魉</span>
        <rt>chī mèi wǎng liǎng</rt>
    </ruby>
    <hr>
    <p>Lorem ipsum <mark>dolor</mark> sit amet consectetur adipisicing elit. Ipsa in quae molestias id nesciunt consequatur ex deserunt enim reiciendis obcaecati!</p>
</body>
</html>
```



### 二、表单相关的新增

**1. 表单控件属性**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>新增的表单控件属性</title>
</head>
<body>
    <form action="">
        账号：<input 
                type="text" 
                name="account" 
                placeholder="请输入账号" 
                required 
                autofocus 
                autocomplete="on"
                pattern="\w{6}"
              >
        <br>
        密码：<input type="password" name="pwd" placeholder="请输入密码" required>
        <br>
        性别：
            <input type="radio" value="male" name="gender" required>男
            <input type="radio" value="female" name="gender">女
        <br>
        爱好：
            <input type="checkbox" value="smoke" name="hobby">抽烟
            <input type="checkbox" value="drink" name="hobby" required>喝酒
            <input type="checkbox" value="perm" name="hobby">烫头
        <br>
        其他：<textarea name="other" placeholder="请输入" required></textarea>
        <br>
        <button>提交</button>
    </form>
</body>
</html>
```



**2. input新增的type属性值**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>input新增的type属性值</title>
</head>
<body>
    <form action="" novalidate>
        邮箱：<input type="email" name="email" required>
        <br>
        网址：<input type="url" name="url">
        <br>
        数值：<input type="number" name="number" step="2" max="100" min="10" required>
        <br>
        搜索：<input type="search" name="keyword">
        <br>
        电话：<input type="tel" name="tel">
        <br>
        范围：<input type="range" name="range" max="100" min="10">
        <br>
        颜色：<input type="color" name="color">
        <br>
        日期：<input type="date" name="date">
        <br>
        月份：<input type="month" name="month">
        <br>
        周：<input type="week" name="week">
        <br>
        时间：<input type="time" name="time1">
        <br>
        日期+时间：<input type="datetime-local" name="time2">
        <br>
        <button type="submit">提交</button>
    </form>
</body>
</html>
```



### 三、新增的多媒体标签

**1.  视频标签**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>新增的视频标签</title>
    <style>
        video {
            width: 600px;
        }
    </style>
</head>
<body>
    <video src="./小电影.mp4" controls muted loop poster="./封面.png" preload="auto"></video>
</body>
</html>
```



**2. 音频标签**

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>新增的音频标签</title>
</head>
<body>
    <audio src="./小曲.mp3" controls autoplay loop preload="auto"></audio>
</body>
</html>
```



### 四、新增的全局属性

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>新增的全局属性</title>
    <style>
        div {
            width: 600px;
            height: 200px;
            border: 1px solid black;
            font-size: 20px;
        }

        .box1 {
            background-color: skyblue;
        }

        .box2 {
            margin-top: 10px;
            background-color: green;
        }
    </style>
</head>
<body>
    <div class="box1" contenteditable="true" spellcheck="true">Lor1em ipsum, dolor sit amet consectetur adipisicing elit. Nostrum, repudiandae assumenda sed quod voluptates delectus similique dignissimos enim! Officiis, repellat.</div>
    <div class="box2" draggable="true" ondragend="go(event)" data-a="1" data-b="2">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Nostrum, repudiandae assumenda sed quod voluptates delectus similique dignissimos enim! Officiis, repellat.</div>
    <script>
        function go(e) {
            alert(e.x)
        }
    </script>
</body>
</html>
```



### 五、兼容性处理

*使用 html5shiv.js*

```html
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>兼容性处理</title>
    <!--[if lt ie9]>
    <script src="./html5shiv.js"></script>
    <![endif]-->
    <style>
        header {
            background-color: orange;
        }

        footer {
            height: 100px;
            line-height: 100px;
            background-color: green;
            text-align: center;
        }
    </style>
</head>
<body>
    <!-- 头部 -->
    <header>
        <h1>xxx商城</h1>
    </header>
    <hr>
    <!-- 主导航 -->
    <nav>
        <a href="#">首页</a>
        <a href="#">订单</a>
        <a href="#">购物车</a>
        <a href="#">我的</a>
    </nav>
    <!-- 主要内容 -->
    <div class="page-content">
        <article>
            <h2>感动中国人物</h2>
            <section>
                <h3>第一名：孙笑川</h3>
                <p>男，籍贯四川，33岁</p>
            </section>
            <section>
                <h3>第二名：药水哥</h3>
                <p>真名刘波，男，籍贯湖北，30岁</p>
            </section>
            <section>
                <h3>第三名：Giao哥</h3>
                <p>男，籍贯河南，29岁</p>
            </section>
        </article>
        <!-- 侧边栏导航 -->
        <aside style="float: right;">
            <nav>
                <ul>
                    <li><a href="#">秒杀专区</a></li>
                    <li><a href="#">会员专区</a></li>
                    <li><a href="#">优惠券专区</a></li>
                    <li><a href="#">品牌专区</a></li>
                </ul>
            </nav>
        </aside>
    </div>
    <hr>
    <footer>
        <nav>
            <a href="#">友情链接1</a>
            <a href="#">友情链接2</a>
            <a href="#">友情链接3</a>
            <a href="#">友情链接4</a>
        </nav>
    </footer>
</body>
</html>
```

