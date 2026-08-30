class Solution {
    public int missingInteger(int[] nums) {
        // Find the sum of the longest sequential prefix
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }

        // Find the smallest missing integer >= sum
        boolean found = true;

        while (found) {
            found = false;

            for (int num : nums) {
                if (num == sum) {
                    found = true;
                    sum++;
                    break;
                }
            }
        }

        return sum;
    }
}