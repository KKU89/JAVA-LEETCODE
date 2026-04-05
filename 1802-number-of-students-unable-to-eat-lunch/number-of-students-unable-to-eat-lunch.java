class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        int count0 = 0;
        int count1 = 0;

        for (int i = 0; i < students.length; i = i + 1) {
            if (students[i] == 0) {
                count0 = count0 + 1;
            } else {
                count1 = count1 + 1;
            }
        }

        for (int i = 0; i < sandwiches.length; i = i + 1) {
            if (sandwiches[i] == 0) {
                if (count0 > 0) {
                    count0 = count0 - 1;
                } else {
                    return count1;
                }
            } else {
                if (count1 > 0) {
                    count1 = count1 - 1;
                } else {
                    return count0;
                }
            }
        }

        return 0;
    }
}