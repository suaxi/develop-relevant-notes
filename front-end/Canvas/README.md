## Canvas

*参考 b站 叩丁狼 Canvas 课程*

### 一、基础使用

#### （1）通过 html 标签创建

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>通过html标签创建</title>
</head>
<body>
    <canvas id="canvas" width="500" height="500"></canvas>
    <script>
        // 1. 获取 canvas 画布
        const canvas = document.getElementById('canvas')
        
        // 2. 获取 context 对象（画笔）
        const context = canvas.getContext('2d')
        
        // 3. 画一个正方形
        // fillRect(x, y, width, height)
        context.fillRect(50, 40, 200, 200)
    </script>
</body>
</html>
```



#### （2）通过 js 创建

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>通过js创建</title>
</head>
<body>
    <script>
        // 1. 创建 canvas 画布
        const canvas = document.createElement('canvas')
        // 设置宽高
        canvas.width = 500
        canvas.height = 500
        document.body.append(canvas)

        // 2. 获取 context 对象（画笔）
        const context = canvas.getContext('2d')

        // 3. （同第一节）画一个正方形
        context.fillRect(60, 60, 200, 200)
    </script>
</body>
</html>
```



#### （3）canvas 宽高的设置

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>canvas宽高的设置</title>
    <!-- <style>
        canvas {
            width: 500px;
            height: 500px
        }
    </style> -->
</head>
<body>
    <script>
        // 注意：不建议使用 CSS 样式表设置 canvas 的宽高，样式会出问题
        // 1. 创建 canvas 画布
        const canvas = document.createElement('canvas')
        // 设置宽高
        canvas.width = 500
        canvas.height = 500
        document.body.append(canvas)

        // 2. 获取 context 对象（画笔）
        const context = canvas.getContext('2d')

        // 3. （同第一节）画一个正方形
        context.fillRect(60, 60, 200, 200)
    </script>
</body>
</html>
```



### 二、线条绘制

#### （1）直线

步骤：

- 使用 `moveTO` 方法把画笔移动到直线起点

- 使用 `lineTo` 方法把画笔移动到直线终点

- 使用 `stroke` 方法让画笔绘制线条

  ```html
  <!DOCTYPE html>
  <html lang="en">
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>直线</title>
  </head>
  <body>
      <script>
          // 1. 创建 canvas 画布
          const canvas = document.createElement('canvas')
          // 设置宽高
          canvas.width = 500
          canvas.height = 500
          document.body.append(canvas)
  
          // 2. 获取 context 对象（画笔）
          const context = canvas.getContext('2d')
  
          // 3. 绘制直线
          // 3.1 起点
          context.moveTo(100, 100)
          // 3.2 终点
          context.lineTo(200, 220)
          // 3.3 调用画线的方法
          context.stroke()
      </script>
  </body>
  </html>
  ```

  

#### （2）折线

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>折线</title>
</head>
<body>
    <script>
        // 1. 创建 canvas 画布
        const canvas = document.createElement('canvas')
        // 设置宽高
        canvas.width = 500
        canvas.height = 500
        document.body.append(canvas)

        // 2. 获取 context 对象（画笔）
        const context = canvas.getContext('2d')

        // 3. 绘制折线
        // 3.1 起点
        context.moveTo(100, 100)
        // 3.2 使用 lineTo 依次经过转折点
        context.lineTo(200, 220)
        context.lineTo(300, 100)
        context.lineTo(400, 210)
        context.lineTo(500, 250)
        // 3.3 调用画线的方法
        context.stroke()
    </script>
</body>
</html>
```



#### （3）lineWidth 设置线条的宽度

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>lineWidth设置线条的宽度</title>
</head>
<body>
    <script>
        // 1. 创建 canvas 画布
        const canvas = document.createElement('canvas')
        // 设置宽高
        canvas.width = 500
        canvas.height = 500
        document.body.append(canvas)

        // 2. 获取 context 对象（画笔）
        const context = canvas.getContext('2d')

        // 3. 画线
        context.moveTo(100, 100)
        context.lineTo(200, 220)

        // 4.设置线条的宽度（粗细）
        context.lineWidth = 3

        // 5.画线
        context.stroke()

        context.lineTo(300, 100)
        // 注意：画完线之后如果有新的路径，要调用 stroke 方法才会绘制新的路径
        context.stroke()
    </script>
</body>
</html>
```



#### （4）strokeStyle 修改线条的颜色

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>strokeStyle修改线条的颜色</title>
</head>
<body>
    <script>
        // 1. 创建 canvas 画布
        const canvas = document.createElement('canvas')
        // 设置宽高
        canvas.width = 500
        canvas.height = 500
        document.body.append(canvas)

        // 2. 获取 context 对象（画笔）
        const context = canvas.getContext('2d')

        // 3. 画线
        context.moveTo(100, 100)
        context.lineTo(200, 220)

        context.lineWidth = 3

        // 4.修改线条的颜色
        context.strokeStyle = '#add8e6'

        // 5.画线
        context.stroke()

        context.lineTo(300, 100)
        // 注意：画完线之后如果有新的路径，要调用 stroke 方法才会绘制新的路径
        context.stroke()
    </script>
</body>
</html>
```

