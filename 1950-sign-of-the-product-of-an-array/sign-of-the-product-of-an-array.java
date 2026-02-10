class Solution {
    public int arraySign(int[] nums) {
       int negno=0;
    for(int i=0;i<nums.length;i++){
        if(nums[i]==0){
            return 0;
        }
        if(nums[i]<0){
            negno +=1;
        }}if(negno %2==0){
            return 1;
        }else return -1;
    }
    }
