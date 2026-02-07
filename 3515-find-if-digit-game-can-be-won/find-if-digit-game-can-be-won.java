class Solution {
    public boolean canAliceWin(int[] nums) {

        int singleSum = 0;
        int doubleSum = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] < 10) {
                singleSum = singleSum + nums[i];
            }
            else {
                doubleSum = doubleSum + nums[i];
            }
        }

        if (singleSum > doubleSum) {
            return true;
        }

        if (doubleSum > singleSum) {
            return true;
        }

        return false;
    }
}