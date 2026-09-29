class Solution {
    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // Valid parentheses string must have even length
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // dp[i][j][balance]
        boolean[][][] dp = new boolean[m][n][m + n];

        // Starting cell
        if (grid[0][0] == '(') {
            dp[0][0][1] = true;
        } else {
            return false;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Skip starting cell
                if (i == 0 && j == 0) {
                    continue;
                }

                // Check all possible balances
                for (int balance = 0; balance < m + n; balance++) {

                    // Get new balance after current character
                    int newBalance;

                    if (grid[i][j] == '(') {
                        newBalance = balance + 1;
                    } else {
                        newBalance = balance - 1;
                    }

                    // Balance cannot be negative
                    if (newBalance < 0) {
                        continue;
                    }

                    // From top
                    if (i > 0 && dp[i - 1][j][balance]) {
                        dp[i][j][newBalance] = true;
                    }

                    // From left
                    if (j > 0 && dp[i][j - 1][balance]) {
                        dp[i][j][newBalance] = true;
                    }
                }
            }
        }

        // At the end, balance must be 0
        return dp[m - 1][n - 1][0];
    }
}