class Solution {
    public int largestInteger(int[] nums, int k) {

        int n = nums.length;
        int[] count = new int[51];

        // Count how many subarrays of size k contain each number
        for (int i = 0; i <= n - k; i++) {

            boolean[] seen = new boolean[51];

            for (int j = i; j < i + k; j++) {
                seen[nums[j]] = true;
            }

            // Each number is counted only once per subarray
            for (int x = 0; x <= 50; x++) {
                if (seen[x]) {
                    count[x]++;
                }
            }
        }

        // Find the largest number appearing in exactly one subarray
        for (int x = 50; x >= 0; x--) {
            if (count[x] == 1) {
                return x;
            }
        }

        return -1;
    }
}