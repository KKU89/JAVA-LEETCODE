class Solution {
    public int[] getSneakyNumbers(int[] nums) {

        int[] freq = new int[nums.length];
        int[] ans = new int[2];
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            freq[num] = freq[num] + 1;

            if (freq[num] == 2) {
                ans[index] = num;
                index = index + 1;
            }
        }

        return ans;
    }
}