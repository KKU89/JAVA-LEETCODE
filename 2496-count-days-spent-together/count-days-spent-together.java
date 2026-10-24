class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int aliceArrive = toDay(arriveAlice, days);
        int aliceLeave = toDay(leaveAlice, days);
        int bobArrive = toDay(arriveBob, days);
        int bobLeave = toDay(leaveBob, days);
        int start = Math.max(aliceArrive, bobArrive);
        int end = Math.min(aliceLeave, bobLeave);
        return Math.max(0, end - start + 1);
    }

    private int toDay(String date, int[] days) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3));
        int result = day;
        for (int i = 0; i < month - 1; i++) {
            result += days[i];
        }
        return result;
    }
}