class Solution {
    public int projectionArea(int[][] grid) {
        int n = grid.length;
        int totalArea = 0;
        for (int i = 0; i < n; i++) {
            int maxInRow = 0;
            int maxInCol = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    totalArea++;
                }
                maxInRow = Math.max(maxInRow, grid[i][j]);
                maxInCol = Math.max(maxInCol, grid[j][i]);
            }
            totalArea += maxInRow + maxInCol;
        }
        return totalArea;
    }
}