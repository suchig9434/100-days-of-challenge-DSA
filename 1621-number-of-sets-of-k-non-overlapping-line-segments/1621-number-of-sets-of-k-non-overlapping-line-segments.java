class Solution {
    public int numberOfSets(int n, int k) {

        final long MOD = 1_000_000_007L;

        int N = n + k - 1;
        int R = 2 * k;

        // factorial[i] = i!
        long[] factorial = new long[N + 1];

        factorial[0] = 1;

        for (int i = 1; i <= N; i++) {
            factorial[i] = factorial[i - 1] * i % MOD;
        }

        // C(N, R)
        long numerator = factorial[N];
        long denominator1 = factorial[R];
        long denominator2 = factorial[N - R];

        // Modular inverse using Fermat's Little Theorem
        long ans = numerator;
        ans = ans * power(denominator1, MOD - 2, MOD) % MOD;
        ans = ans * power(denominator2, MOD - 2, MOD) % MOD;

        return (int) ans;
    }

    // Calculates a^b % MOD
    private long power(long a, long b, long MOD) {

        long result = 1;

        while (b > 0) {

            if ((b & 1) == 1) {
                result = result * a % MOD;
            }

            a = a * a % MOD;
            b >>= 1;
        }

        return result;
    }
}