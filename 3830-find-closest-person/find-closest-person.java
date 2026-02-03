class Solution {
    public int findClosest(int x, int y, int z) {
      return Integer.compare(Math.abs(x-z), Math.abs(y-z)) < 0 ? 1 :
       Integer.compare(Math.abs(x-z), Math.abs(y-z)) > 0 ? 2 : 0;

            
        

    }
}