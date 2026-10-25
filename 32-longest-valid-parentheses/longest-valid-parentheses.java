class Solution {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        int[] stack = new int[s.length() + 1];
        int top = 0;
        stack[top] = -1;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack[++top] = i;
            } else {
                top--;
                if (top < 0) {
                    stack[++top] = i;
                } else {
                    maxLen = Math.max(maxLen, i - stack[top]);
                }
            }
        }

        return maxLen;
    }
}