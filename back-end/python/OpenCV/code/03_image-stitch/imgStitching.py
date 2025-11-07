from Stitcher import Stitcher
import cv2

imageA = cv2.imread("images/left.png")
imageB = cv2.imread("images/right.png")

# 拼接
stitcher = Stitcher()
(result, vis) = stitcher.stitch([imageA, imageB], showMatches=True)

cv2.imshow("imageA", imageA)
cv2.imshow("imageB", imageB)
cv2.imshow("Keypoint Matches", vis)
cv2.imshow("result", result)
cv2.waitKey(0)
cv2.destroyAllWindows()
