from imutils import contours
import numpy as np
import cv2
import utils

# 信用卡类型
BANK_CARD_TYPE = {
    "3": "American Express",
    "4": "Visa",
    "5": "MasterCard",
    "6": "Discover Card"
}


def cv_show(name, img):
    cv2.imshow(name, img)
    cv2.waitKey(0)
    cv2.destroyAllWindows()


# 读取模板
template = cv2.imread("images/ocr_template.png")
# cv_show("template", template)

ref = cv2.cvtColor(template, cv2.COLOR_BGR2GRAY)
# cv_show("template_gray", ref)

ref = cv2.threshold(ref, 10, 255, cv2.THRESH_BINARY_INV)[1]
# cv_show("binary", ref)

# 计算轮廓
# 参数：二值图，轮廓，要保留的部分
ref_, refCnts, hierarchy = cv2.findContours(ref.copy(), cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

cv2.drawContours(template, refCnts, -1, (0, 0, 255), 3)
# cv_show("template", template)
print(np.array(refCnts).shape)
refCnts = utils.sort_contours(refCnts, method="left-to-right")[0]
digits = {}

for (i, c) in enumerate(refCnts):
    # 计算外接矩形并进行resize
    (x, y, w, h) = cv2.boundingRect(c)
    roi = ref[y:y + h, x:x + w]
    roi = cv2.resize(roi, (57, 88))
    digits[i] = roi

# 初始化卷积核
rectKernel = cv2.getStructuringElement(cv2.MORPH_RECT, (9, 3))
sqKernel = cv2.getStructuringElement(cv2.MORPH_RECT, (5, 5))

# 读取输入图像，预处理
image = cv2.imread("images/credit_card_01.png")
# cv_show("image", image)
image = utils.resize(image, width=300)
gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
# cv_show("gray", gray)

# 礼帽操作，突出更明亮的区域
tophat = cv2.morphologyEx(gray, cv2.MORPH_TOPHAT, rectKernel)
# cv_show("tophat", tophat)

gradX = cv2.Sobel(tophat, ddepth=cv2.CV_32F, dx=1, dy=0, ksize=-1)

gradX = np.absolute(gradX)
(minVal, maxVal) = (np.min(gradX), np.max(gradX))
gradX = (255 * ((gradX - minVal) / (maxVal - minVal)))
gradX = gradX.astype('uint8')
print(np.array(gradX).shape)
# cv_show("gradX", gradX)

# 通过闭运算（先膨胀，再腐蚀），将数字连在一起
gradX = cv2.morphologyEx(gradX, cv2.MORPH_CLOSE, rectKernel)
# cv_show("gradX", gradX)

# 通过OpenCV THRESH_OTSU 自动寻找合适的阈值
thresh = cv2.threshold(gradX, 0, 255, cv2.THRESH_BINARY | cv2.THRESH_OTSU)[1]

# 再次执行闭运算
thresh = cv2.morphologyEx(thresh, cv2.MORPH_CLOSE, sqKernel)
# cv_show("thresh", thresh)

# 计算轮廓
thresh_, threshCnts, hierarchy = cv2.findContours(thresh.copy(), cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
cnts = threshCnts
cur_img = image.copy()
cv2.drawContours(cur_img, cnts, -1, (0, 0, 255), 3)
# cv_show("cur_img", cur_img)
locs = []

for (i, c) in enumerate(cnts):
    # 计算轮廓
    (x, y, w, h) = cv2.boundingRect(c)
    ar = w / float(h)

    # 选择合适的区域，这里以四个数字为一组为例
    if ar > 2.5 and ar < 4.0:
        if (w > 40 and w < 55) and (h > 10 and h < 20):
            locs.append((x, y, w, h))

# 将符合的轮廓从左至右排序
locs = sorted(locs, key=lambda x: x[0])
output = []

for (i, (gX, gY, gW, gH)) in enumerate(locs):
    groupOutput = []

    # 根据坐标提取每一个组（提取时范围稍微往外移）
    group = gray[gY - 5:gY + gH + 5, gX - 5:gX + gW + 5]
    # cv_show("group", group)

    group = cv2.threshold(group, 0, 255, cv2.THRESH_BINARY | cv2.THRESH_OTSU)[1]
    # cv_show("group", group)

    # 计算每一组的轮廓
    group_, digitsCnts, hierarchy = cv2.findContours(group.copy(), cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
    digitsCnts = contours.sort_contours(digitsCnts, method="left-to-right")[0]

    # 计算每一组中的每一个数值
    for c in digitsCnts:
        # 当前数值的轮廓并进行resize
        (x, y, w, h) = cv2.boundingRect(c)
        roi = group[y:y + h, x:x + w]
        roi = cv2.resize(roi, (57, 88))
        # cv_show("roi", roi)

        # 计算匹配度（得分）
        scores = []

        for (digit, digitROI) in digits.items():
            result = cv2.matchTemplate(roi, digitROI, cv2.TM_CCOEFF)
            (_, score, _, _) = cv2.minMaxLoc(result)
            scores.append(score)
        groupOutput.append(str(np.argmax(scores)))

    # 绘制轮廓
    cv2.rectangle(image, (gX - 5, gY - 5), (gX + gW + 5, gY + gH + 5), (0, 0, 255), 1)
    cv2.putText(image, "".join(groupOutput), (gX, gY - 15), cv2.FONT_HERSHEY_SIMPLEX, 0.65, (0, 0, 255), 2)

    # 得到结果
    output.extend(groupOutput)

# 打印结果
print("Bank Card Type:{}".format(BANK_CARD_TYPE[output[0]]))
print("Bank Card:{}".format("".join(output)))
cv_show("Image", image)
