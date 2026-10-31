class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        final int MOD = 1000000007;

        int[][] right = new int[m][n];
        int[][] down = new int[m][n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (j + 1 >= n) {
                    right[i][j] = -1;
                } else if (grid[i][j + 1] == 0) {
                    right[i][j] = i * n + j + 1;
                } else {
                    right[i][j] = down[i][j + 1];
                }

                if (i + 1 >= m) {
                    down[i][j] = -1;
                } else if (grid[i + 1][j] == 0) {
                    down[i][j] = (i + 1) * n + j;
                } else {
                    down[i][j] = right[i + 1][j];
                }
            }
        }

        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = dp[i][j];
                if (val == 0 || grid[i][j] == 1) continue;

                int idx = right[i][j];
                if (idx != -1) {
                    int ni = idx / n;
                    int nj = idx % n;
                    dp[ni][nj] += val;
                    if (dp[ni][nj] >= MOD) dp[ni][nj] -= MOD;
                }

                idx = down[i][j];
                if (idx != -1) {
                    int ni = idx / n;
                    int nj = idx % n;
                    dp[ni][nj] += val;
                    if (dp[ni][nj] >= MOD) dp[ni][nj] -= MOD;
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}
