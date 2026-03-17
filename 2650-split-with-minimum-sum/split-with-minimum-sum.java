class Solution {
    public int splitNum(int num) {
        char[] arr = String.valueOf(num).toCharArray();
        java.util.Arrays.sort(arr);

        int num1 = 0;
        int num2 = 0;

        for (int i = 0; i < arr.length; i++) {
            int digit = arr[i] - '0';

            if (i % 2 == 0) {
                num1 = num1 * 10 + digit;
            } else {
                num2 = num2 * 10 + digit;
            }
        }

        return num1 + num2;
    }
}