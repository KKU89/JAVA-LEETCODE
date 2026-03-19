class Solution {
    public int smallestRangeI(int[] nums, int k) {
        int smallest=Integer.MAX_VALUE;
        int largest=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>largest){
                largest =nums[i];
            }if(nums[i]<smallest){
                smallest =nums[i];
            }
        }return Math.max(0, largest - smallest - 2*k);

        
    }
}