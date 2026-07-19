class Solution {
public:
    int myAtoi(string s) {
        int i = 0, n = s.size();

        // Step 1: Skip leading whitespaces
        while (i < n && s[i] == ' ')
            i++;

        // Step 2: Check sign
        int sign = 1;
        if (i < n && (s[i] == '+' || s[i] == '-')) {
            if (s[i] == '-')
                sign = -1;
            i++;
        }

        // Step 3 & 4: Convert digits with overflow check
        long long num = 0;
        while (i < n && isdigit(s[i])) {
            int digit = s[i] - '0';

            // Check overflow
            if (num > (INT_MAX - digit) / 10) {
                return (sign == 1) ? INT_MAX : INT_MIN;
            }

            num = num * 10 + digit;
            i++;
        }

        return sign * num;
    }
};