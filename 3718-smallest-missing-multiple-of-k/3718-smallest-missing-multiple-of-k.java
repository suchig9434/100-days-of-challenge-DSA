class Solution {
    public int missingMultiple(int[] nums, int k) {
        boolean[] present = new boolean[101];

        for (int num : nums) {
            present[num] = true;
        }

        for (int multiple = k; ; multiple += k) {
            if (multiple > 100 || !present[multiple]) {
                return multiple;
            }
        }
    }
}