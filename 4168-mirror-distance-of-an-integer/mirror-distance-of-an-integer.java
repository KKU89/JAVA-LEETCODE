class Solution {
    public int mirrorDistance(int n) {
        int k=n;
        int rev=0;
        while(k>0){
            int ld=k%10;
            rev=(rev*10)+ld;
            k/=10;
        }return Math.abs(n-rev);
        
    }
}