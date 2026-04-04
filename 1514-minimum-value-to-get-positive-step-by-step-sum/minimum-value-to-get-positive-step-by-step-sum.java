class Solution {
    public int minStartValue(int[] nums) {
        int runningSum = 0;
        int minimumSum = 0;

        for (int num : nums) {
            runningSum += num;
            
            if (runningSum < minimumSum) {
                minimumSum = runningSum;
            }
        }

        int startValue = 1 - minimumSum;
        return startValue;
    }
}