class Solution {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i = i + 1) {
            int sum = 0;

            if (k > 0) {
                for (int j = 1; j <= k; j = j + 1) {
                    sum = sum + code[(i + j) % n];
                }
            } else if (k < 0) {
                for (int j = 1; j <= -k; j = j + 1) {
                    sum = sum + code[(i - j + n) % n];
                }
            } else {
                sum = 0;
            }

            result[i] = sum;
        }

        return result;
    }
}