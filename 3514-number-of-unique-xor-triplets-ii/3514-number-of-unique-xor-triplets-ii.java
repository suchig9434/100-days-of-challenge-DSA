class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int MAX = 2048; // 2^11

        // pairXor[x] = true if x can be obtained by XOR of two elements
        boolean[] pairXor = new boolean[MAX];

        int n = nums.length;

        // Compute all possible XORs of two elements
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                pairXor[nums[i] ^ nums[j]] = true;
            }
        }

        // Store unique triplet XOR values
        boolean[] tripletXor = new boolean[MAX];

        for (int x = 0; x < MAX; x++) {
            if (!pairXor[x]) continue;

            for (int num : nums) {
                tripletXor[x ^ num] = true;
            }
        }

        int count = 0;
        for (boolean exists : tripletXor) {
            if (exists) count++;
        }

        return count;
    }
}