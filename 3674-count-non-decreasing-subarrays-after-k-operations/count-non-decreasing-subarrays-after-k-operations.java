class Solution {
    class Node {
        Node left, right;
        int max;
        int len;
        int[] pref;
        long[] suff;
    }

    int[] nums;
    Node root;
    long[] ps;

    public long countNonDecreasingSubarrays(int[] nums, int k) {
        this.nums = nums;
        int n = nums.length;

        ps = new long[n + 1];
        for (int i = 0; i < n; i++) {
            ps[i + 1] = ps[i] + nums[i];
        }

        root = build(0, n - 1);

        long ans = 0;
        int l = 0;

        for (int r = 0; r < n; r++) {
            while (l <= r && cost(l, r) > k) {
                l++;
            }
            ans += r - l + 1;
        }

        return ans;
    }

    Node build(int l, int r) {
        Node node = new Node();
        node.len = r - l + 1;
        node.pref = new int[node.len];
        node.suff = new long[node.len + 1];

        if (l == r) {
            node.max = nums[l];
            node.pref[0] = nums[l];
            node.suff[0] = nums[l];
            return node;
        }

        int mid = (l + r) / 2;
        node.left = build(l, mid);
        node.right = build(mid + 1, r);
        node.max = Math.max(node.left.max, node.right.max);

        int idx = 0;

        for (int i = 0; i < node.left.len; i++) {
            node.pref[idx++] = node.left.pref[i];
        }

        for (int i = 0; i < node.right.len; i++) {
            node.pref[idx++] = Math.max(node.left.max, node.right.pref[i]);
        }

        for (int i = node.len - 1; i >= 0; i--) {
            node.suff[i] = node.suff[i + 1] + node.pref[i];
        }

        return node;
    }

    long cost(int l, int r) {
        long[] cur = {0};
        long sumMax = query(root, 0, nums.length - 1, l, r, cur);
        long sum = ps[r + 1] - ps[l];
        return sumMax - sum;
    }

    long query(Node node, int nl, int nr, int ql, int qr, long[] cur) {
        if (ql <= nl && nr <= qr) {
            long m = cur[0];
            long add;

            if (m >= node.max) {
                add = m * node.len;
            } else {
                int idx = upperBound(node.pref, m);
                add = m * idx + node.suff[idx];
            }

            if (node.max > cur[0]) {
                cur[0] = node.max;
            }

            return add;
        }

        int mid = (nl + nr) / 2;
        long res = 0;

        if (ql <= mid) {
            res += query(node.left, nl, mid, ql, qr, cur);
        }

        if (qr > mid) {
            res += query(node.right, mid + 1, nr, ql, qr, cur);
        }

        return res;
    }

    int upperBound(int[] arr, long val) {
        int lo = 0;
        int hi = arr.length;

        while (lo < hi) {
            int mid = (lo + hi) >>> 1;

            if (arr[mid] <= val) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
    }
}