class Solution {
    public int trap(int[] height) {
        int n=height.length;
        int leftheight[]=new int[n];
        leftheight[0]=height[0];
        for(int i=1;i<n;i++){
            leftheight[i]=Math.max(leftheight[i-1],height[i]);
            }
        int rightheight[]=new int[n];
         rightheight[n-1]=height[n-1];
        for(int i=n-2;i>=0;i--){
            rightheight[i]=Math.max(rightheight[i+1],height[i]);           
        }
        int trappedwater=0;
        for(int i=0;i<n;i++){
            int waterlevel =Math.min(rightheight[i],leftheight[i]);
            trappedwater +=(waterlevel-height[i]);

        }return trappedwater;

    }
}