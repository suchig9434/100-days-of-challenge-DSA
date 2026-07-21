class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();

        int originalOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                originalOnes++;
            }
        }

        String t = "1" + s + "1";

        ArrayList<Character> chars = new ArrayList<>();
        ArrayList<Integer> lens = new ArrayList<>();

        // Run-Length Encoding
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);

            if (chars.isEmpty() || chars.get(chars.size() - 1) != c) {
                chars.add(c);
                lens.add(1);
            } else {
                lens.set(lens.size() - 1, lens.get(lens.size() - 1) + 1);
            }
        }

        int ans = originalOnes;

        // Check every surrounded 1-block
        for (int i = 1; i < chars.size() - 1; i++) {
            if (chars.get(i) == '1' &&
                chars.get(i - 1) == '0' &&
                chars.get(i + 1) == '0') {

                int leftZeros = lens.get(i - 1);
                int onesLen = lens.get(i);
                int rightZeros = lens.get(i + 1);

                int mergedZeros = leftZeros + onesLen + rightZeros;

                ans = Math.max(ans, originalOnes - onesLen + mergedZeros);
            }
        }

        return ans;
    }
}