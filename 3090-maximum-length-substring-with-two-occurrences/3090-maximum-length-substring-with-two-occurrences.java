class Solution {
    public int maximumLengthSubstring(String s) {
        int[] freq = new int[26];
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            int idx = s.charAt(right) - 'a';
            freq[idx]++;

            // If current character appears more than twice,
            // shrink the window.
            while (freq[idx] > 2) {
                freq[s.charAt(left) - 'a']--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}