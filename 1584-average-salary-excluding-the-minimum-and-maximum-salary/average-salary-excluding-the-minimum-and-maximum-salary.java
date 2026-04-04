class Solution {
    public double average(int[] salary) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < salary.length; i++) {
            int s = salary[i];
            if (s < min) min = s;
            if (s > max) max = s;
            sum += s;
        }

        return (double)(sum - min - max) / (salary.length - 2);
    }
}