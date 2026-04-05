class Solution {
    public int specialArray(int[] nums) {
        int n = nums.length;

        for (int x = 0; x <= n; x = x + 1) {
            int count = 0;

            for (int i = 0; i < n; i = i + 1) {
                if (nums[i] >= x) {
                    count = count + 1;
                }
            }

            if (count == x) {
                return x;
            }
        }

        return -1;
    }
}