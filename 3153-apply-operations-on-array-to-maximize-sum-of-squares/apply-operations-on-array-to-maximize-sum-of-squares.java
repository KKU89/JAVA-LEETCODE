class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int MOD = 1_000_000_007;
        long[] vals = new long[k];

        for (int b = 0; b < 32; b++) {
            int cnt = 0;
            for (int x : nums) {
                if (((x >> b) & 1) == 1) {
                    cnt++;
                }
            }
            int lim = Math.min(cnt, k);
            for (int i = 0; i < lim; i++) {
                vals[i] += (1L << b);
            }
        }

        long ans = 0;
        for (int i = 0; i < k; i++) {
            long v = vals[i] % MOD;
            ans = (ans + v * v) % MOD;
        }

        return (int) ans;
    }
}