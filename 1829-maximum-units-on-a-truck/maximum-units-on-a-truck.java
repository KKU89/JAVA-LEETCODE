class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        java.util.Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int totalUnits = 0;
        int remainingCapacity = truckSize;

        for (int i = 0; i < boxTypes.length; i = i + 1) {
            int boxesToTake = Math.min(boxTypes[i][0], remainingCapacity);
            totalUnits = totalUnits + boxesToTake * boxTypes[i][1];
            remainingCapacity = remainingCapacity - boxesToTake;
            if (remainingCapacity == 0) {
                break;
            }
        }

        return totalUnits;
    }
}