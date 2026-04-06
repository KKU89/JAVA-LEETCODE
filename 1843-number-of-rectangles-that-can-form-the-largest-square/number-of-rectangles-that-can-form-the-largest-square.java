class Solution {
    public int countGoodRectangles(int[][] rectangles) {
        int maxLen = 0;

        for (int i = 0; i < rectangles.length; i++) {
            int l = rectangles[i][0];
            int w = rectangles[i][1];
            int side = Math.min(l, w);
            if (side > maxLen) {
                maxLen = side;
            }
        }

        int count = 0;

        for (int i = 0; i < rectangles.length; i++) {
            int l = rectangles[i][0];
            int w = rectangles[i][1];
            int side = Math.min(l, w);
            if (side == maxLen) {
                count++;
            }
        }

        return count;
    }
}