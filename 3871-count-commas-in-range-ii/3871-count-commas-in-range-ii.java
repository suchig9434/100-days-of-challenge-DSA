class Solution {
    public long countCommas(long n) {
        long ans = 0;
        long start = 1000;
        long commas = 1;

        while (start <= n) {
            long end;

            if (start > n / 1000) {
                end = n;
            } else {
                end = start * 1000 - 1;
            }

            long count = end - start + 1;
            ans += count * commas;

            if (start > n / 1000) {
                break;
            }

            start *= 1000;
            commas++;
        }

        return ans;
    }
}