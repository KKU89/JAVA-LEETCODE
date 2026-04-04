class Solution {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int studentsDoingHomework = 0;

        for (int i = 0; i < startTime.length; i++) {
            int start = startTime[i];
            int end = endTime[i];

            if (queryTime >= start && queryTime <= end) {
                studentsDoingHomework++;
            }
        }

        return studentsDoingHomework;
    }
}