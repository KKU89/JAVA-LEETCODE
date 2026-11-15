class Solution {
    public int numOfSubarrays(int[] arr) {
        int mod = 1_000_000_007;
        int even = 1, odd = 0, parity = 0;
        long ans = 0;
        for (int x : arr) {
            parity ^= (x & 1);
            if (parity == 1) {
                ans += even;
                odd++;
            } else {
                ans += odd;
                even++;
            }
            ans %= mod;
        }
        return (int) ans;
    }
}