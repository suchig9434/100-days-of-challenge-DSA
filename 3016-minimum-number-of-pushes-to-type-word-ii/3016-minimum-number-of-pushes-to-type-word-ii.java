import java.util.*;

class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];

        // Count frequency of each letter
        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Sort frequencies in descending order
        Integer[] arr = new Integer[26];
        for (int i = 0; i < 26; i++) {
            arr[i] = freq[i];
        }

        Arrays.sort(arr, Collections.reverseOrder());

        int ans = 0;

        for (int i = 0; i < 26; i++) {
            if (arr[i] == 0) break;

            int pushes = (i / 8) + 1;
            ans += arr[i] * pushes;
        }

        return ans;
    }
}