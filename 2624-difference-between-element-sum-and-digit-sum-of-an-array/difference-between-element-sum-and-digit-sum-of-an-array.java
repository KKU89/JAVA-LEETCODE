class Solution {
    public int differenceOfSum(int[] nums) {
        int sum=0;
        int digitsum=0;
        for(int i=0;i<nums.length;i++){
            sum +=nums[i];
            int num=nums[i];
            while(num>0){
                int ld=num%10;
                digitsum +=ld;
                num /=10;
            }
            
        }return Math.abs(sum-digitsum);
        
    }
}