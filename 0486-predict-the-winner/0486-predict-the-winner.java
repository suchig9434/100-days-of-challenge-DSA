class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        Integer[][] memo = new Integer[n][n];
        return helper(nums, 0, n - 1, memo) >= 0;
    }

    private int helper(int[] nums, int i, int j, Integer[][] memo) {
        if (i == j) return nums[i];

        if (memo[i][j] != null) return memo[i][j];

        int pickLeft = nums[i] - helper(nums, i + 1, j, memo);
        int pickRight = nums[j] - helper(nums, i, j - 1, memo);

        return memo[i][j] = Math.max(pickLeft, pickRight);
    }
}