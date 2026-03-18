class Solution {
    public boolean checkDivisibility(int n) {
        int num = n;
        int sum = 0;
        int pro = 1;

        while (num > 0) {
            int digit = num % 10;
            sum = sum + digit;
            pro = pro * digit;
            num /= 10;
        }

        return n % (sum + pro) == 0;
    }
}