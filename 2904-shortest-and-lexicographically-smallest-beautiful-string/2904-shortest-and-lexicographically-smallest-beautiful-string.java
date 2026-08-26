class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0;
        int ones = 0;

        int minLen = Integer.MAX_VALUE;
        String answer = "";

        for (int right = 0; right < n; right++) {

            if (s.charAt(right) == '1') {
                ones++;
            }

            // We only need windows containing exactly k ones
            while (ones == k) {

                // Try to make the window as short as possible
                while (left <= right && s.charAt(left) == '0') {
                    left++;
                }

                int len = right - left + 1;
                String current = s.substring(left, right + 1);

                // Update answer
                if (len < minLen ||
                    (len == minLen && current.compareTo(answer) < 0)) {

                    minLen = len;
                    answer = current;
                }

                // Remove the leftmost 1
                if (s.charAt(left) == '1') {
                    ones--;
                    left++;
                }
            }
        }

        return answer;
    }
}