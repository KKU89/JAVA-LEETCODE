class Solution {
    static final int MOD = 1000000007;
    int[] sum;
    int[] sq;
    int[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new int[4 * n + 5];
        sq = new int[4 * n + 5];
        lazy = new int[4 * n + 5];

        int[] last = new int[100001];
        for (int i = 0; i < last.length; i++) {
            last[i] = -1;
        }

        long ans = 0;
        for (int r = 0; r < n; r++) {
            int x = nums[r];
            int p = last[x];
            update(1, 0, n - 1, p + 1, r, 1);
            ans = (ans + sq[1]) % MOD;
            last[x] = r;
        }

        return (int) ans;
    }

    void update(int node, int l, int r, int ql, int qr, int val) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }

        push(node, l, r);
        int mid = (l + r) >>> 1;
        update(node * 2, l, mid, ql, qr, val);
        update(node * 2 + 1, mid + 1, r, ql, qr, val);
        pull(node);
    }

    void apply(int node, int l, int r, int val) {
        long len = r - l + 1;
        long v = val;
        long s = sum[node];
        long q = sq[node];

        long newSum = (s + v * len) % MOD;
        long newSq = (q + 2L * v % MOD * s % MOD + v * v % MOD * len % MOD) % MOD;

        sum[node] = (int) newSum;
        sq[node] = (int) newSq;
        lazy[node] = (int) ((lazy[node] + v) % MOD);
    }

    void push(int node, int l, int r) {
        if (lazy[node] != 0 && l != r) {
            int mid = (l + r) >>> 1;
            apply(node * 2, l, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    void pull(int node) {
        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }
}