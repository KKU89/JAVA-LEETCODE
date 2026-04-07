class Solution {
    public int[] distributeCandies(int candies, int num_people) {
        int[] result = new int[num_people];
        int give = 1;
        int i = 0;
        while (candies > 0) {
            if (candies < give) {
                result[i] += candies;
                break;
            }
            result[i] += give;
            candies -= give;
            give++;
            i++;
            if (i == num_people) {
                i = 0;
            }
        }
        return result;
    }
}