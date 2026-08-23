class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;

        int leftSum = 0, rightSum = 0;
        int leftQ = 0, rightQ = 0;

        for (int i = 0; i < half; i++) {
            char c = num.charAt(i);

            if (c == '?') {
                leftQ++;
            } else {
                leftSum += c - '0';
            }
        }

        for (int i = half; i < n; i++) {
            char c = num.charAt(i);

            if (c == '?') {
                rightQ++;
            } else {
                rightSum += c - '0';
            }
        }

        int qDiff = leftQ - rightQ;
        int sumDiff = leftSum - rightSum;

        // If the number of '?' is equal, Bob can always mirror Alice.
        if (qDiff == 0) {
            return sumDiff != 0;
        }

        // Bob can win only when the difference can be exactly balanced.
        if (qDiff % 2 != 0) {
            return true;
        }

        return sumDiff + (qDiff / 2) * 9 != 0;
    }
}