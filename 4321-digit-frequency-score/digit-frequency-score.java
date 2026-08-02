class Solution {
    public int digitFrequencyScore(int n) {
        int[] freq = new int[10];
        int temp = n;
        while (temp > 0) {
            freq[temp % 10]++;
            temp /= 10;
        }
        int score = 0;
        for (int d = 0; d < 10; d++) {
            if (freq[d] > 0) {
                score += d * freq[d];
            }
        }
        return score;
    }
}