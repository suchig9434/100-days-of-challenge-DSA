
class Solution {
public:
    int sortableIntegers(vector<int>& nums) {
        int n = nums.size();

        vector<int> sorted = nums;
        sort(sorted.begin(), sorted.end());

        long long ans = 0;

        // Check every divisor of n
        for (int k = 1; k <= n; k++) {
            if (n % k != 0)
                continue;

            bool valid = true;

            // Check each block of length k
            for (int start = 0; start < n && valid; start += k) {
                vector<int> pattern(
                    sorted.begin() + start,
                    sorted.begin() + start + k
                );

                // Build KMP prefix array
                vector<int> pi(k, 0);

                for (int i = 1; i < k; i++) {
                    int j = pi[i - 1];

                    while (j > 0 && pattern[i] != pattern[j])
                        j = pi[j - 1];

                    if (pattern[i] == pattern[j])
                        j++;

                    pi[i] = j;
                }

                // Search pattern in the cyclic block
                int j = 0;
                bool found = false;

                for (int i = 0; i < 2 * k - 1; i++) {
                    int x = nums[start + (i % k)];

                    while (j > 0 && x != pattern[j])
                        j = pi[j - 1];

                    if (x == pattern[j])
                        j++;

                    if (j == k) {
                        found = true;
                        break;
                    }
                }

                if (!found)
                    valid = false;
            }

            if (valid)
                ans += k;
        }

        return static_cast<int>(ans);
    }
};