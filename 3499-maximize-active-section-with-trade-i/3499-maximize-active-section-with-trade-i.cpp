class Solution {
public:
    int maxActiveSectionsAfterTrade(string s) {
        int n = s.size();

        int originalOnes = 0;
        for (char c : s)
            if (c == '1')
                originalOnes++;

        string t = "1" + s + "1";

        vector<pair<char, int>> runs;

        // Run-length encoding
        for (char c : t) {
            if (runs.empty() || runs.back().first != c)
                runs.push_back({c, 1});
            else
                runs.back().second++;
        }

        int ans = originalOnes;

        // Check every surrounded 1-block
        for (int i = 1; i + 1 < runs.size(); i++) {
            if (runs[i].first == '1' &&
                runs[i - 1].first == '0' &&
                runs[i + 1].first == '0') {

                int leftZeros = runs[i - 1].second;
                int onesLen = runs[i].second;
                int rightZeros = runs[i + 1].second;

                int mergedZeros = leftZeros + onesLen + rightZeros;

                ans = max(ans, originalOnes - onesLen + mergedZeros);
            }
        }

        return ans;
    }
};