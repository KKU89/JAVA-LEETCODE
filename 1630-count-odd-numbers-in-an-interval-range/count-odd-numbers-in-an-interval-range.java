class Solution {
    public int countOdds(int low, int high) {

        int oddsTillHigh = (high + 1) / 2;
        int oddsBeforeLow = low / 2;

        return oddsTillHigh - oddsBeforeLow;
    }
}
