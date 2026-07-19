class Solution {
public:
    string smallestSubsequence(string s) {
        vector<int> lastIndex(26);
        vector<bool> visited(26, false);

        // Store last occurrence of each character
        for (int i = 0; i < s.size(); i++) {
            lastIndex[s[i] - 'a'] = i;
        }

        string result = "";

        for (int i = 0; i < s.size(); i++) {
            char ch = s[i];

            // Skip if already included
            if (visited[ch - 'a'])
                continue;

            // Maintain lexicographical order
            while (!result.empty() &&
                   result.back() > ch &&
                   lastIndex[result.back() - 'a'] > i) {
                visited[result.back() - 'a'] = false;
                result.pop_back();
            }

            result.push_back(ch);
            visited[ch - 'a'] = true;
        }

        return result;
    }
};