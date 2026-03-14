class Solution {
    public int sumOfEncryptedInt(int[] nums) {
        int sum = 0;

        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int largest = 0;
            int temp = num;
            int encrypted = 0;

            while(temp > 0) {
                int rem = temp % 10;
                if(rem > largest) {
                    largest = rem;
                }
                temp /= 10;
            }

            temp = num;

            while(temp > 0) {
                encrypted = encrypted * 10 + largest;
                temp /= 10;
            }

            sum += encrypted;
        }

        return sum;
    }
}