class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        
        return solve(text1, text2, m - 1, n - 1, memo);
    }
    
    private int solve(String s1, String s2, int i, int j, int[][] memo) {
        if (i < 0 || j < 0) {
            return 0;
        }
        
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        
        if (s1.charAt(i) == s2.charAt(j)) {
            return memo[i][j] = 1 + solve(s1, s2, i - 1, j - 1, memo);
        }
        
        return memo[i][j] = Math.max(
            solve(s1, s2, i - 1, j, memo),
            solve(s1, s2, i, j - 1, memo)
        );
    }
}