class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int count = 0;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long sum = 0;
                    java.util.Deque<int[]> stack = new java.util.ArrayDeque<>();
                    sum += grid[i][j];
                    grid[i][j] = 0;
                    stack.push(new int[]{i, j});
                    while (!stack.isEmpty()) {
                        int[] cur = stack.pop();
                        for (int d = 0; d < 4; d++) {
                            int nr = cur[0] + dr[d], nc = cur[1] + dc[d];
                            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] > 0) {
                                sum += grid[nr][nc];
                                grid[nr][nc] = 0;
                                stack.push(new int[]{nr, nc});
                            }
                        }
                    }
                    if (sum % k == 0) count++;
                }
            }
        }
        return count;
    }
}