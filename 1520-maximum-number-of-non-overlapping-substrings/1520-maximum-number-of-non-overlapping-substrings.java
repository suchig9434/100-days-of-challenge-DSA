import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        // First and last occurrence of each character
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        // Store valid intervals [start, end]
        List<int[]> intervals = new ArrayList<>();

        for (int c = 0; c < 26; c++) {

            // Character doesn't exist
            if (last[c] == -1) {
                continue;
            }

            int start = first[c];
            int end = last[c];

            boolean valid = true;

            // Expand the interval
            for (int i = start; i <= end; i++) {

                int current = s.charAt(i) - 'a';

                // This character occurs before our start,
                // so we cannot include all its occurrences.
                if (first[current] < start) {
                    valid = false;
                    break;
                }

                // We must include all occurrences
                // of this character.
                end = Math.max(end, last[current]);
            }

            if (valid) {
                intervals.add(new int[]{start, end});
            }
        }

        // Sort by ending position
        intervals.sort((a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }

            // For same ending position, shorter interval first
            return Integer.compare(a[0], b[0]);
        });

        List<String> answer = new ArrayList<>();

        int previousEnd = -1;

        for (int[] interval : intervals) {

            int start = interval[0];
            int end = interval[1];

            // Non-overlapping
            if (start > previousEnd) {
                answer.add(s.substring(start, end + 1));
                previousEnd = end;
            }
        }

        return answer;
    }
}