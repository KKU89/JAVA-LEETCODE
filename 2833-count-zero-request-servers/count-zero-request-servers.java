import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int m = logs.length;
        int qn = queries.length;

        int[][] qs = new int[qn][2];
        for (int i = 0; i < qn; i++) {
            qs[i][0] = queries[i];
            qs[i][1] = i;
        }

        Arrays.sort(qs, (a, b) -> Integer.compare(a[0], b[0]));

        int[] freq = new int[n + 1];
        int distinct = 0;
        int left = 0;
        int right = 0;
        int[] ans = new int[qn];

        for (int[] q : qs) {
            int time = q[0];
            int idx = q[1];

            while (right < m && logs[right][1] <= time) {
                int server = logs[right][0];
                if (freq[server] == 0) distinct++;
                freq[server]++;
                right++;
            }

            int low = time - x;

            while (left < m && logs[left][1] < low) {
                int server = logs[left][0];
                freq[server]--;
                if (freq[server] == 0) distinct--;
                left++;
            }

            ans[idx] = n - distinct;
        }

        return ans;
    }
}