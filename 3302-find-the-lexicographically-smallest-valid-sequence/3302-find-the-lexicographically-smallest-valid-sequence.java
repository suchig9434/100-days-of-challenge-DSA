class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] ans = new int[m];

        /*
         * last[j] = the last index in word1 where
         * word2[j] occurs.
         *
         * We build it from right to left so that
         * last[j] represents a possible position for word2[j]
         * while keeping the remaining characters after it.
         */
        int[] last = new int[m];
        java.util.Arrays.fill(last, -1);

        int i = n - 1;
        int j = m - 1;

        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
            i--;
        }

        /*
         * canSkip = true means we have not used
         * our one allowed character modification yet.
         */
        boolean canSkip = true;

        i = 0;
        j = 0;

        while (i < n && j < m) {

            // Normal matching character
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            }

            // Use our one allowed mismatch
            else if (canSkip &&
                    (j == m - 1 || i < last[j + 1])) {

                ans[j] = i;
                j++;
                canSkip = false;
            }

            i++;
        }

        // Could not find enough indices
        if (j < m) {
            return new int[0];
        }

        return ans;
    }
}