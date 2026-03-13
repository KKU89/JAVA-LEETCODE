class Solution {
    public int countPartitions(int[] nums) {
        int totalsum=0;
        for(int i=0;i<nums.length;i++){
            totalsum= totalsum+nums[i];
        }if(totalsum%2==0){
            return nums.length-1;
        }else{
            return 0;
        }

        
    }
}