class Solution {

    public long findKthSmallest(int[] coins, int k) {
        long low = 1;
        long high = (long) coins[0] * k;

        // Binary search
        while (low < high) {
            long mid = low + (high - low) / 2;

            if (count(mid, coins) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    // Count numbers <= x divisible by at least one coin
    private long count(long x, int[] coins) {
        int n = coins.length;
        long result = 0;

        // Inclusion-exclusion over all subsets
        for (int mask = 1; mask < (1 << n); mask++) {

            long lcm = 1;
            int bits = 0;
            boolean valid = true;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;

                    lcm = lcm(lcm, coins[i]);

                    // LCM greater than x cannot contribute
                    if (lcm > x) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!valid) continue;

            long cnt = x / lcm;

            if (bits % 2 == 1) {
                result += cnt;
            } else {
                result -= cnt;
            }
        }

        return result;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}