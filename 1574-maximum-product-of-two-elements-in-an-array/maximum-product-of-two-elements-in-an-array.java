class Solution {
    public int maxProduct(int[] nums) {
        
        int largest = nums[0];
        int secondLargest = nums[1];
        
        if (secondLargest > largest) {
            int temp = largest;
            largest = secondLargest;
            secondLargest = temp;
        }

        for (int i = 2; i < nums.length; i++) {
            int current = nums[i];

            if (current > largest) {
                secondLargest = largest;
                largest = current;
            } 
            else if (current > secondLargest) {
                secondLargest = current;
            }
        }

        int result = (largest - 1) * (secondLargest - 1);
        return result;
    }
}