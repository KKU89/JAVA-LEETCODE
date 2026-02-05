class Solution {
    public int countDigits(int num) {
        int count = 0;
        int k = num;

        while (k > 0) {
            int ld = k % 10;

            if (num % ld == 0) {
                count++;
            }

            k = k / 10;   
        }

        return count;
    }
}
