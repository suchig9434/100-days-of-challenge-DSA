
class Solution {
    public int reverseDegree(String s) {
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            // Reverse alphabet value
            int reverseValue = 26 - (s.charAt(i) - 'a');

            // Position is i + 1 because it is 1-indexed
            int position = i + 1;

            sum += reverseValue * position;
        }

        return sum;
    }
}

