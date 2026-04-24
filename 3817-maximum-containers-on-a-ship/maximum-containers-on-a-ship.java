class Solution {
    public int maxContainers(int n, int w, int maxWeight) {
        long totalSlots = (long) n * n;
        long weightLimitCount = (long) maxWeight / w;
        return (int) Math.min(totalSlots, weightLimitCount);
    }
}