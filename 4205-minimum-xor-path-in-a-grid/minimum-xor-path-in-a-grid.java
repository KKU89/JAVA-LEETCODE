import java.util.BitSet;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        BitSet[][] dp = new BitSet[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                BitSet cur = new BitSet(1024);

                if (i == 0 && j == 0) {
                    cur.set(grid[0][0]);
                } else {
                    BitSet prev = new BitSet(1024);

                    if (i > 0) prev.or(dp[i - 1][j]);
                    if (j > 0) prev.or(dp[i][j - 1]);

                    int value = grid[i][j];

                    for (int b = prev.nextSetBit(0); b >= 0; b = prev.nextSetBit(b + 1)) {
                        cur.set(b ^ value);
                    }
                }

                dp[i][j] = cur;
            }
        }

        return dp[m - 1][n - 1].nextSetBit(0);
    }
}