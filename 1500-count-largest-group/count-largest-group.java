class Solution {
    public int countLargestGroup(int n) {
        int[] counts = new int[37];
        int maxGroupSize = 0;
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            int temp = i;
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }
            counts[sum]++;
            maxGroupSize = Math.max(maxGroupSize, counts[sum]);
        }
        int largestGroupCount = 0;
        for (int size : counts) {
            if (size == maxGroupSize) {
                largestGroupCount++;
            }
        }
        return largestGroupCount;
    }
}