class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = Integer.MAX_VALUE / 4;
        int[][] cost = new int[n][n + 1];
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(cost[i], INF);
        }

        for (int l = 0; l < n; l++) {
            for (int r = l + 2; r <= n; r++) {
                int length = r - l;
                int best = INF;
                for (int d = 1; d < length; d++) {
                    if (length % d != 0) {
                        continue;
                    }
                    int cur = 0;
                    int cnt = length / d;
                    for (int i = 0; i < d; i++) {
                        for (int t = 0; t < cnt / 2; t++) {
                            char a = s.charAt(l + i + t * d);
                            char b = s.charAt(l + i + (cnt - 1 - t) * d);
                            if (a != b) {
                                cur++;
                            }
                        }
                    }
                    best = Math.min(best, cur);
                }
                cost[l][r] = best;
            }
        }

        int[][] dp = new int[k + 1][n + 1];
        for (int i = 0; i <= k; i++) {
            java.util.Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            for (int i = 2 * p; i <= n; i++) {
                int best = INF;
                for (int j = 2 * (p - 1); j <= i - 2; j++) {
                    if (dp[p - 1][j] != INF && cost[j][i] != INF) {
                        best = Math.min(best, dp[p - 1][j] + cost[j][i]);
                    }
                }
                dp[p][i] = best;
            }
        }

        return dp[k][n];
    }
}