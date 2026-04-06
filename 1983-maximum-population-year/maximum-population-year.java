class Solution {
    public int maximumPopulation(int[][] logs) {
        int[] arr = new int[101];
        for (int i = 0; i < logs.length; i++) {
            arr[logs[i][0] - 1950]++;
            arr[logs[i][1] - 1950]--;
        }
        int max = 0, year = 1950, curr = 0;
        for (int i = 0; i < 101; i++) {
            curr += arr[i];
            if (curr > max) {
                max = curr;
                year = 1950 + i;
            }
        }
        return year;
    }
}