class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;

        // best[i] = minimum length of a valid subarray
        // completely inside indices 0 to i
        int[] best = new int[n];

        int INF = n + 1;
        for (int i = 0; i < n; i++) {
            best[i] = INF;
        }

        int left = 0;
        long sum = 0;
        int answer = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            // Shrink window if sum becomes greater than target
            while (sum > target && left <= right) {
                sum -= arr[left];
                left++;
            }

            // If current window has sum = target
            if (sum == target) {
                int len = right - left + 1;

                // Check if there is a previous non-overlapping subarray
                if (left > 0 && best[left - 1] != INF) {
                    answer = Math.min(answer, len + best[left - 1]);
                }
            }

            // Carry forward the best subarray found so far
            if (right > 0) {
                best[right] = best[right - 1];
            }

            // Update with current subarray
            if (sum == target) {
                int len = right - left + 1;
                best[right] = Math.min(best[right], len);
            }
        }

        return answer == INF ? -1 : answer;
    }
}