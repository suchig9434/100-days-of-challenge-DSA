class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        int[] freq = new int[26];

        // Count characters in s
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        String ans = "";

        // Try every position as the first position
        // where our answer becomes greater than target.
        for (int i = 0; i < n; i++) {

            // Find the smallest character greater than target[i]
            int start = target.charAt(i) - 'a' + 1;

            for (int c = start; c < 26; c++) {

                if (freq[c] > 0) {
                    int[] temp = freq.clone();

                    // Use this greater character
                    temp[c]--;

                    StringBuilder sb = new StringBuilder();

                    // Same prefix as target
                    for (int j = 0; j < i; j++) {
                        sb.append(target.charAt(j));
                    }

                    // Make current character greater
                    sb.append((char) ('a' + c));

                    // Put remaining characters in sorted order
                    for (int k = 0; k < 26; k++) {
                        while (temp[k] > 0) {
                            sb.append((char) ('a' + k));
                            temp[k]--;
                        }
                    }

                    String candidate = sb.toString();

                    // Keep the smallest candidate
                    if (ans.equals("") || candidate.compareTo(ans) < 0) {
                        ans = candidate;
                    }

                    break;
                }
            }

            // Continue only if target[i] is available
            int x = target.charAt(i) - 'a';

            if (freq[x] == 0) {
                break;
            }

            freq[x]--;
        }

        return ans;
    }
}