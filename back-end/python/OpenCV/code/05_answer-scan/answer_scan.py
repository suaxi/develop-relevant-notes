import numpy as np
import cv2

# 答案
answer = {0: 1, 1: 4, 2: 0, 3: 3, 4: 1}


def get_points(pts):
    rect = np.zeros((4, 2), dtype="float32")

    # 按顺序寻找4个点的坐标（左上，右上，右下，左下）
    # 左上，右下
    s = pts.sum(axis=1)
    rect[0] = pts[np.argmin(s)]
    rect[2] = pts[np.argmax(s)]

    # 右上，左下
    diff = np.diff(pts, axis=1)
    rect[1] = pts[np.argmin(diff)]
    rect[3] = pts[np.argmax(diff)]
    return rect


def point_transform(image, pts):
    # 获取输入坐标点
    rect = get_points(pts)
    (tl, tr, br, bl) = rect

    # 计算输入的w、h，找到最大的
    widthA = np.sqrt(((br[0] - bl[0]) ** 2) + ((br[1] - bl[1]) ** 2))
    widthB = np.sqrt(((tr[0] - tl[0]) ** 2) + ((tr[1] - tl[1]) ** 2))
    maxWidth = max(int(widthA), int(widthB))

    heightA = np.sqrt(((tr[0] - br[0]) ** 2) + ((tr[1] - br[1]) ** 2))
    heightB = np.sqrt(((tl[0] - bl[0]) ** 2) + ((tl[1] - bl[1]) ** 2))
    maxHeight = max(int(heightA), int(heightB))

    # 变换后的坐标位置
    dst = np.array([
        [0, 0],
        [maxWidth - 1, 0],
        [maxWidth - 1, maxHeight - 1],
        [0, maxHeight - 1]],
        dtype="float32")

    # 计算变换矩阵
    M = cv2.getPerspectiveTransform(rect, dst)
    warped = cv2.warpPerspective(image, M, (maxWidth, maxHeight))
    return warped


def sort_contours(cnts, method="left-to-right"):
    reverse = False
    i = 0
    if method == "right-to-left" or method == "bottom-to-top":
        reverse = True
    if method == "top-to-bottom" or method == "bottom-to-top":
        i = 1
    boundingBoxes = [cv2.boundingRect(c) for c in cnts]
    (cnts, boundingBoxes) = zip(*sorted(zip(cnts, boundingBoxes),
                                        key=lambda b: b[1][i], reverse=reverse))
    return cnts, boundingBoxes


def cv_show(title, img):
    cv2.imshow(title, img)
    cv2.waitKey(0)
    cv2.destroyAllWindows()


# 图像预处理
image = cv2.imread("images/answer1.png")
contours_img = image.copy()
gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
blur = cv2.GaussianBlur(gray, (5, 5), 0)
cv2.imshow("blur", blur)
edged = cv2.Canny(blur, 75, 200)
cv2.imshow("edged", edged)

# 轮廓检测
cnts = cv2.findContours(edged.copy(), cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)[1]
cv2.drawContours(contours_img, cnts, -1, (0, 0, 255), 3)
cv2.imshow("contours_img", contours_img)
docCnt = None
if len(cnts) > 0:
    # 根据轮廓大小排序
    cnts = sorted(cnts, key=cv2.contourArea, reverse=True)
    for c in cnts:
        peri = cv2.arcLength(c, True)
        approx = cv2.approxPolyDP(c, 0.02 * peri, True)
        # 检测到4个角时（即完整的答题卡）进行透视变换
        if len(approx) == 4:
            docCnt = approx
            break

# 透视变换
warped = point_transform(gray, docCnt.reshape(4, 2))
cv2.imshow("warped", warped)

# 阈值处理（让OpenCV自行选择合适的阈值）
thresh = cv2.threshold(warped, 0, 255, cv2.THRESH_BINARY_INV | cv2.THRESH_OTSU)[1]
cv2.imshow("thresh", thresh)
thresh_contours = thresh.copy()

# 获取答案选项的轮廓
cnts = cv2.findContours(thresh.copy(), cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)[1]
cv2.drawContours(thresh_contours, cnts, -1, (0, 0, 255), 3)
cv2.imshow("thresh_contours", thresh_contours)
question_cnts = []
for c in cnts:
    (x, y, w, h) = cv2.boundingRect(c)
    # 选项的外接矩形
    ar = w / float(h)
    if w >= 20 and h >= 20 and ar >= 0.9 and ar <= 1.1:
        question_cnts.append(c)
question_cnts = sort_contours(question_cnts, method="top-to-bottom")[0]
correct = 0

# 遍历每题的选项
for (q, i) in enumerate(np.arange(0, len(question_cnts), 5)):
    cnts = sort_contours(question_cnts[i:i + 5])[0]
    bubbled = None
    for (j, c) in enumerate(cnts):
        # mask
        mask = np.zeros(thresh.shape, dtype="uint8")
        cv2.drawContours(mask, [c], -1, 255, -1)
        cv_show("mask", mask)
        # 通过计算非零点数量判断该选项是否被选中
        mask = cv2.bitwise_and(thresh, thresh, mask=mask)
        total = cv2.countNonZero(mask)

        if bubbled is None or total > bubbled[0]:
            bubbled = (total, j)

    color = (0, 0, 255)
    k = answer[q]

    # 判断正确
    if k == bubbled[1]:
        color = (0, 255, 0)
        correct += 1

    # 标记正确答案
    cv2.drawContours(warped, [cnts[k]], -1, color, 3)

score = (correct / len(answer)) * 100
print("score: {:.2f}".format(score))
cv2.putText(warped,
            "score: {:.2f}".format(score),
            (10, 30),
            cv2.FONT_HERSHEY_SIMPLEX,
            0.9,
            (0, 0, 255),
            2)

cv2.imshow("answer", image)
cv2.imshow("result", warped)
cv2.waitKey(0)
