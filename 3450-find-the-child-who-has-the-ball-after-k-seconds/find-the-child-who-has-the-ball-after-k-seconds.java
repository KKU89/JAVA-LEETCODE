class Solution {
    public int numberOfChild(int n, int k) {
        int cycle = 2 * (n - 1);
        k = k % cycle;

        if (k <= n - 1) {
            return k;
        } else {
            return cycle - k;
        }
    }
}