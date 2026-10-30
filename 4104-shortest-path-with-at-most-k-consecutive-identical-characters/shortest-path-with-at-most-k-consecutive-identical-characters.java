class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
        }

        int base = k + 1;
        long[] dist = new long[n * base];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        int startState = 1;
        dist[startState] = 0;
        pq.offer(new long[]{0, startState});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int state = (int) cur[1];

            if (d != dist[state]) continue;

            int u = state / base;
            int run = state % base;

            if (u == n - 1) {
                return (int) d;
            }

            char cu = labels.charAt(u);

            for (int[] e : adj[u]) {
                int v = e[0];
                int w = e[1];

                int nextRun = labels.charAt(v) == cu ? run + 1 : 1;
                if (nextRun > k) continue;

                int nextState = v * base + nextRun;
                long nd = d + w;

                if (nd < dist[nextState]) {
                    dist[nextState] = nd;
                    pq.offer(new long[]{nd, nextState});
                }
            }
        }

        return -1;
    }
}