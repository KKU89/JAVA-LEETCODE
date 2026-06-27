class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put((long) num, counts.getOrDefault((long) num, 0) + 1);
        }

        int maxLen = 0;

        if (counts.containsKey(1L)) {
            int count1 = counts.get(1L);
            if (count1 % 2 == 0) {
                count1--;
            }
            maxLen = count1;
        }

        for (long x : counts.keySet()) {
            if (x == 1L) continue;

            long curr = x;
            int count = 0;
            while (counts.containsKey(curr) && counts.get(curr) >= 2) {
                count += 2;
                curr *= curr;
            }

            if (counts.containsKey(curr)) {
                count += 1;
            } else {
                count -= 1;
            }
            maxLen = Math.max(maxLen, count);
        }

        return maxLen;
    }
}