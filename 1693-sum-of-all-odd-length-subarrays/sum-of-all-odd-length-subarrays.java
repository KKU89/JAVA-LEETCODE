class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int result = 0;

        for (int i = 0; i < n; i++) {
            int total = (i + 1) * (n - i);
            int odd = (total + 1) / 2;
            result += arr[i] * odd;
        }

        return result;
    }
}