class Solution {
    public int distributeCandies(int[] candyType) {
        int capacity = candyType.length/2;
        Arrays.sort(candyType);
        int diff=1;
        for(int i=1;i<candyType.length;i++){
            if(candyType[i] != candyType[i-1]){
                diff++;
            }}
        if(diff<=capacity){
            return diff;
        }else{
            return capacity;
        }
        }}

        
    
