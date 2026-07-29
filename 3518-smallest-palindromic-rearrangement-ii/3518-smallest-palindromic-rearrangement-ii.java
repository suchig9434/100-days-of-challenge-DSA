import java.util.*;

class Solution {

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        String mid = "";
        List<Integer> half = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                mid = String.valueOf((char) ('a' + i));
            }
            half.add(freq[i] / 2);
        }

        int halfLen = s.length() / 2;

        if (countWays(half, halfLen, k) < k) {
            return "";
        }

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {

            for (int c = 0; c < 26; c++) {

                if (half.get(c) == 0) continue;

                half.set(c, half.get(c) - 1);

                long ways = countWays(half, halfLen - pos - 1, k);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    break;
                } else {
                    k -= ways;
                    half.set(c, half.get(c) + 1);
                }
            }
        }

        String right = new StringBuilder(left).reverse().toString();

        return left.toString() + mid + right;
    }

    private long countWays(List<Integer> cnt, int rem, int limit) {

        long ans = 1;

        int left = rem;

        for (int x : cnt) {
            if (x == 0) continue;

            ans *= combinationLimited(left, x, limit);

            if (ans > limit) return limit;

            left -= x;
        }

        return Math.min(ans, (long) limit);
    }

    private long combinationLimited(int n, int r, int limit) {
        if (r > n) return 0;
        r = Math.min(r, n - r);

        long res = 1;

        for (int i = 1; i <= r; i++) {
            res = res * (n - r + i) / i;
            if (res > limit) return limit;
        }

        return res;
    }
}