class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;

        int max = 0;
        for (int x : nums) {
            if (x > max) max = x;
        }

        int N = n + 1;
        int[] s = new int[N];
        for (int i = 0; i < n; i++) s[i] = nums[i];
        s[n] = 0;

        int size = Math.max(max + 1, N);
        int[] cnt = new int[size];
        int[] p = new int[N];
        int[] c = new int[N];

        for (int i = 0; i < N; i++) cnt[s[i]]++;
        for (int i = 1; i <= max; i++) cnt[i] += cnt[i - 1];
        for (int i = N - 1; i >= 0; i--) p[--cnt[s[i]]] = i;

        c[p[0]] = 0;
        int classes = 1;
        for (int i = 1; i < N; i++) {
            if (s[p[i]] != s[p[i - 1]]) classes++;
            c[p[i]] = classes - 1;
        }

        int[] pn = new int[N];
        int[] cn = new int[N];

        for (int h = 0; (1 << h) < N; h++) {
            int len = 1 << h;
            for (int i = 0; i < N; i++) {
                int v = p[i] - len;
                if (v < 0) v += N;
                pn[i] = v;
            }

            java.util.Arrays.fill(cnt, 0, classes, 0);
            for (int i = 0; i < N; i++) cnt[c[pn[i]]]++;
            for (int i = 1; i < classes; i++) cnt[i] += cnt[i - 1];
            for (int i = N - 1; i >= 0; i--) p[--cnt[c[pn[i]]]] = pn[i];

            cn[p[0]] = 0;
            int newClasses = 1;
            for (int i = 1; i < N; i++) {
                int cur1 = c[p[i]];
                int cur2 = c[(p[i] + len) % N];
                int prev1 = c[p[i - 1]];
                int prev2 = c[(p[i - 1] + len) % N];
                if (cur1 != prev1 || cur2 != prev2) newClasses++;
                cn[p[i]] = newClasses - 1;
            }

            int[] tmp = c;
            c = cn;
            cn = tmp;
            classes = newClasses;
        }

        int[] sa = new int[n];
        for (int i = 1; i < N; i++) sa[i - 1] = p[i];

        int[] rankPos = new int[n];
        for (int i = 0; i < n; i++) rankPos[sa[i]] = i;

        int[] lcp = new int[n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            int r = rankPos[i];
            if (r == 0) {
                k = 0;
                continue;
            }
            int j = sa[r - 1];
            while (i + k < n && j + k < n && nums[i + k] == nums[j + k]) k++;
            lcp[r] = k;
            if (k > 0) k--;
        }

        int ans = n;
        for (int r = 0; r < n; r++) {
            int prev = r > 0 ? lcp[r] : 0;
            int next = r + 1 < n ? lcp[r + 1] : 0;
            int need = Math.max(prev, next) + 1;
            int suffixLen = n - sa[r];
            if (need <= suffixLen && need < ans) ans = need;
        }

        return ans;
    }
}