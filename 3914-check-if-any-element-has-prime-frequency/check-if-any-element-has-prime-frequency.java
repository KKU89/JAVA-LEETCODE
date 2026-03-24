class Solution {
    public boolean checkPrimeFrequency(int[] nums) {
        int[] freq = new int[101];

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            freq[num]++;
        }

        for (int i = 0; i < freq.length; i++) {
            int count = freq[i];

            if (isPrime(count)) {
                return true;
            }
        }

        return false;
    }

    public boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}