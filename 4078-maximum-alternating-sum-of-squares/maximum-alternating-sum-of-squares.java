
class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        int[] abs = new int[n];

        for (int i = 0; i < n; i++) {
            abs[i] = Math.abs(nums[i]);
        }

        Arrays.sort(abs);

        int minusCount = n / 2;
        long ans = 0;

        for (int i = 0; i < n; i++) {
            long sq = (long) abs[i] * abs[i];

            if (i < minusCount) {
                ans -= sq;
            } else {
                ans += sq;
            }
        }

        return ans;
    }
}