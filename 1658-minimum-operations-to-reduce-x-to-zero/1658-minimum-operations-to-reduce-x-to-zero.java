class Solution {
    public int minOperations(int[] nums, int x) {

        int n = nums.length;

        // Find total sum
        long total = 0;

        for (int num : nums) {
            total += num;
        }

        // Sum of elements that we want to keep
        long target = total - x;

        // If target is negative, impossible
        if (target < 0) {
            return -1;
        }

        // If target is 0, remove all elements
        if (target == 0) {
            return n;
        }

        int left = 0;
        long sum = 0;
        int maxLength = -1;

        // Sliding window
        for (int right = 0; right < n; right++) {

            sum += nums[right];

            // Reduce window if sum becomes greater than target
            while (sum > target && left <= right) {
                sum -= nums[left];
                left++;
            }

            // If window sum equals target
            if (sum == target) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        // No subarray found
        if (maxLength == -1) {
            return -1;
        }

        // Elements outside the subarray are removed
        return n - maxLength;
    }
}