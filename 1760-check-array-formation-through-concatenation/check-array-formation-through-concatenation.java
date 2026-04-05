class Solution {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        int n = arr.length;

        for (int i = 0; i < n; i = i + 1) {
            boolean found = false;

            for (int j = 0; j < pieces.length; j = j + 1) {
                if (pieces[j][0] == arr[i]) {
                    found = true;
                    for (int k = 0; k < pieces[j].length; k = k + 1) {
                        if (arr[i] != pieces[j][k]) {
                            return false;
                        }
                        i = i + 1;
                    }
                    i = i - 1;
                    break;
                }
            }

            if (!found) {
                return false;
            }
        }

        return true;
    }
}