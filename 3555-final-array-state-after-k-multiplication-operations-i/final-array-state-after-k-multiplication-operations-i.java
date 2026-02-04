class Solution {
    public int[] getFinalState(int[] nums, int k, int multiplier) {
    
    while(k>0){
        int minindex=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]<nums[minindex]){
                minindex=i;
            }}
            nums[minindex]=nums[minindex]*multiplier;
            k--;}
    return nums;
    }
        
    }