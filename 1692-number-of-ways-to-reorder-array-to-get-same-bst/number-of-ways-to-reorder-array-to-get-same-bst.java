import java.util.*;

class Solution {
    private static final int MOD = 1000000007;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        long[][] comb = new long[n + 1][n + 1];
        comb[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            comb[i][0] = 1;
            comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        long ways = dfs(list, comb);
        return (int)((ways - 1 + MOD) % MOD);
    }

    private long dfs(List<Integer> nums, long[][] comb) {
        if (nums.size() <= 1) {
            return 1;
        }
        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 1; i < nums.size(); i++) {
            int x = nums.get(i);
            if (x < root) {
                left.add(x);
            } else {
                right.add(x);
            }
        }
        long ways = comb[nums.size() - 1][left.size()];
        ways = ways * dfs(left, comb) % MOD;
        ways = ways * dfs(right, comb) % MOD;
        return ways;
    }
}