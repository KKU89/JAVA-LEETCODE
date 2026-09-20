class Solution {
    public int reverseDegree(String s) {
        int totalSum = 0;
        for (int i = 0; i < s.length(); i++) {
            int reversedAlphabetIndex = 26 - (s.charAt(i) - 'a');
            int stringIndex = i + 1;
            totalSum += reversedAlphabetIndex * stringIndex;
        }
        return totalSum;
    }
}