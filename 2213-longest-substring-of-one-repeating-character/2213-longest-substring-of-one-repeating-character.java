import java.util.*;

class Solution {
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;

        char[] arr = s.toCharArray();
        int[] ans = new int[k];

        // Map: start index -> end index of a same-character segment
        TreeMap<Integer, Integer> segments = new TreeMap<>();

        // Build initial segments
        int start = 0;

        for (int i = 1; i <= n; i++) {
            if (i == n || arr[i] != arr[i - 1]) {
                segments.put(start, i - 1);
                start = i;
            }
        }

        // Multiset of segment lengths
        TreeMap<Integer, Integer> lengthCount = new TreeMap<>();

        for (Map.Entry<Integer, Integer> entry : segments.entrySet()) {
            int len = entry.getValue() - entry.getKey() + 1;
            lengthCount.put(len, lengthCount.getOrDefault(len, 0) + 1);
        }

        for (int q = 0; q < k; q++) {
            int index = queryIndices[q];
            char newChar = queryCharacters.charAt(q);

            // If character is already the same, nothing changes
            if (arr[index] == newChar) {
                ans[q] = lengthCount.lastKey();
                continue;
            }

            // Find the segment containing index
            Map.Entry<Integer, Integer> entry =
                    segments.floorEntry(index);

            int segStart = entry.getKey();
            int segEnd = entry.getValue();
            int oldLength = segEnd - segStart + 1;

            // Remove old segment
            segments.remove(segStart);
            removeLength(lengthCount, oldLength);

            // Split the old segment around index
            if (segStart <= index - 1) {
                segments.put(segStart, index - 1);
                addLength(lengthCount, index - segStart);
            }

            if (index + 1 <= segEnd) {
                segments.put(index + 1, segEnd);
                addLength(lengthCount, segEnd - index);
            }

            // Change character
            arr[index] = newChar;

            // Create segment for the new character
            int newStart = index;
            int newEnd = index;

            // Check left segment
            Map.Entry<Integer, Integer> left =
                    segments.lowerEntry(index);

            if (left != null && arr[left.getValue()] == newChar) {
                newStart = left.getKey();

                int len = left.getValue() - left.getKey() + 1;
                removeLength(lengthCount, len);

                segments.remove(left.getKey());
            }

            // Check right segment
            Map.Entry<Integer, Integer> right =
                    segments.higherEntry(index);

            if (right != null && arr[right.getKey()] == newChar) {
                newEnd = right.getValue();

                int len = right.getValue() - right.getKey() + 1;
                removeLength(lengthCount, len);

                segments.remove(right.getKey());
            }

            // Add merged segment
            segments.put(newStart, newEnd);
            addLength(lengthCount, newEnd - newStart + 1);

            // Longest segment
            ans[q] = lengthCount.lastKey();
        }

        return ans;
    }

    private void addLength(TreeMap<Integer, Integer> map, int len) {
        map.put(len, map.getOrDefault(len, 0) + 1);
    }

    private void removeLength(TreeMap<Integer, Integer> map, int len) {
        int count = map.get(len);

        if (count == 1) {
            map.remove(len);
        } else {
            map.put(len, count - 1);
        }
    }
}