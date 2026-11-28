class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];
        for (int[] it : items) freq[it[0]]++;
        int[] multiples = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            int sum = 0;
            for (int m = f; m <= n; m += f) sum += freq[m];
            multiples[f] = sum;
        }
        int[] c = new int[n];
        for (int i = 0; i < n; i++) c[i] = multiples[items[i][0]] - 1;

        Map<Long, Long> countMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (c[i] > 0) {
                long p = items[i][1];
                countMap.put(p, countMap.getOrDefault(p, 0L) + c[i]);
            }
        }
        int m = countMap.size();
        long[] priceArr = new long[m];
        long[] cntArr = new long[m];
        int idx = 0;
        for (Map.Entry<Long, Long> e : countMap.entrySet()) {
            priceArr[idx] = e.getKey();
            cntArr[idx] = e.getValue();
            idx++;
        }
        Integer[] order = new Integer[m];
        for (int i = 0; i < m; i++) order[i] = i;
        final long[] priceRef = priceArr;
        Arrays.sort(order, (a, b) -> Long.compare(priceRef[a], priceRef[b]));

        long[] price = new long[m];
        long[] cnt = new long[m];
        Map<Long, Integer> priceToIdx = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int old = order[i];
            price[i] = priceArr[old];
            cnt[i] = cntArr[old];
            priceToIdx.put(price[i], i);
        }
        long[] prefCnt = new long[m + 1];
        long[] prefCost = new long[m + 1];
        for (int i = 0; i < m; i++) {
            prefCnt[i + 1] = prefCnt[i] + cnt[i];
            prefCost[i + 1] = prefCost[i] + cnt[i] * price[i];
        }
        long[] base = getHigh((long) budget, m - 1, -1, 0, price, cnt, prefCnt, prefCost);
        long ans = 2 * base[0];
        for (int i = 0; i < n; i++) {
            long p = items[i][1];
            long ci = c[i];
            long activationCost = ci * p;
            if (activationCost > budget) continue;
            long remaining = budget - activationCost;
            long threshold = 2 * p;
            int first = lowerBound(price, threshold);
            int hi = first - 1;
            int exIdx = -1;
            long exCount = 0;
            if (ci > 0) {
                exIdx = priceToIdx.get(p);
                exCount = ci;
            }
            long[] hc = getHigh(remaining, hi, exIdx, exCount, price, cnt, prefCnt, prefCost);
            long H = hc[0];
            long C = hc[1];
            long L = (remaining - C) / p;
            long total = 2 * ci + 2 * H + L;
            if (total > ans) ans = total;
        }
        return (int) ans;
    }

    private long[] getHigh(long budget, int hi, int exIdx, long exCount,
                           long[] price, long[] cnt,
                           long[] prefCnt, long[] prefCost) {
        if (budget < 0 || hi < 0) return new long[]{0, 0};
        int low = -1, high = hi;
        while (low < high) {
            int mid = (low + high + 1) >>> 1;
            long cost = prefCost[mid + 1];
            if (exIdx != -1 && exIdx <= mid) cost -= exCount * price[exIdx];
            if (cost <= budget) low = mid;
            else high = mid - 1;
        }
        int g0 = low;
        long costFull = (g0 >= 0) ? prefCost[g0 + 1] : 0;
        long countFull = (g0 >= 0) ? prefCnt[g0 + 1] : 0;
        if (exIdx != -1 && exIdx <= g0) {
            costFull -= exCount * price[exIdx];
            countFull -= exCount;
        }
        long rem = budget - costFull;
        long H = countFull;
        long C = costFull;
        int g1 = g0 + 1;
        if (g1 <= hi) {
            long avail = cnt[g1];
            if (exIdx == g1) avail -= exCount;
            if (avail > 0) {
                long take = Math.min(avail, rem / price[g1]);
                H += take;
                C += take * price[g1];
            }
        }
        return new long[]{H, C};
    }

    private int lowerBound(long[] arr, long target) {
        int l = 0, r = arr.length - 1;
        int ans = arr.length;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] >= target) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}