class Solution {
    public int countSymmetricIntegers(int low, int high) {

        int count = 0;

        for (int i = low; i <= high; i++) {

            int num = i;
            int digits = 0;

            while (num > 0) {
                digits = digits + 1;
                num = num / 10;
            }

            if (digits % 2 != 0) {
                continue;
            }

            int half = digits / 2;
            int leftSum = 0;
            int rightSum = 0;

            num = i;

            for (int j = 0; j < half; j++) {
                int ld = num % 10;
                rightSum = rightSum + ld;
                num = num / 10;
            }

            for (int j = 0; j < half; j++) {
                int ld = num % 10;
                leftSum = leftSum + ld;
                num = num / 10;
            }

            if (leftSum == rightSum) {
                count = count + 1;
            }
        }

        return count;
    }
}
