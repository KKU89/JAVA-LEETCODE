import java.util.*;

class Solution {
    private int m;
    private int[] bitCnt;
    private long[] bitSum;
    private int[] vals;

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int unique = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                sorted[unique++] = sorted[i];
            }
        }

        vals = Arrays.copyOf(sorted, unique);
        m = unique;
        bitCnt = new int[m + 1];
        bitSum = new long[m + 1];

        int[] comp = new int[n];
        for (int i = 0; i < n; i++) {
            comp[i] = Arrays.binarySearch(vals, nums[i]) + 1;
        }

        long[] cost = new long[n - x + 1];
        long totalSum = 0;

        for (int i = 0; i < x; i++) {
            add(comp[i], 1, nums[i]);
            totalSum += nums[i];
        }

        for (int start = 0; start <= n - x; start++) {
            if (start > 0) {
                int out = start - 1;
                int in = start + x - 1;
                add(comp[out], -1, -(long) nums[out]);
                totalSum -= nums[out];
                add(comp[in], 1, nums[in]);
                totalSum += nums[in];
            }

            int rank = (x + 1) / 2;
            int idx = kth(rank);
            long median = vals[idx - 1];
            int cntLE = prefixCnt(idx);
            long sumLE = prefixSum(idx);

            cost[start] = median * cntLE - sumLE
                    + (totalSum - sumLE) - median * (x - cntLE);
        }

        long INF = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1];

        for (int t = 1; t <= k; t++) {
            long[] cur = new long[n + 1];
            Arrays.fill(cur, INF);

            for (int pos = 1; pos <= n; pos++) {
                cur[pos] = cur[pos - 1];

                if (pos >= x && prev[pos - x] != INF) {
                    cur[pos] = Math.min(cur[pos], prev[pos - x] + cost[pos - x]);
                }
            }

            prev = cur;
        }

        return prev[n];
    }

    private void add(int i, int dc, long ds) {
        for (; i <= m; i += i & -i) {
            bitCnt[i] += dc;
            bitSum[i] += ds;
        }
    }

    private int prefixCnt(int i) {
        int sum = 0;
        for (; i > 0; i -= i & -i) {
            sum += bitCnt[i];
        }
        return sum;
    }

    private long prefixSum(int i) {
        long sum = 0;
        for (; i > 0; i -= i & -i) {
            sum += bitSum[i];
        }
        return sum;
    }

    private int kth(int k) {
        int idx = 0;
        int step = Integer.highestOneBit(m);

        for (; step > 0; step >>= 1) {
            int next = idx + step;
            if (next <= m && bitCnt[next] < k) {
                idx = next;
                k -= bitCnt[next];
            }
        }

        return idx + 1;
    }
}