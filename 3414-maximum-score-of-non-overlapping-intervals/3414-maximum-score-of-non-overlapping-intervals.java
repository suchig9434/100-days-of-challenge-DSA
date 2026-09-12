import java.util.*;

class Solution {

    static class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    // DP state: best score and lexicographically smallest indices
    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                    intervals.get(i).get(0),
                    intervals.get(i).get(1),
                    intervals.get(i).get(2),
                    i
            );
        }

        // Sort by starting position
        Arrays.sort(arr, (a, b) -> {
            if (a.l != b.l)
                return Integer.compare(a.l, b.l);
            return Integer.compare(a.idx, b.idx);
        });

        // dp[k][i] = best result using intervals from i onward
        State[][] dp = new State[5][n + 1];

        // Base case: choosing 0 intervals gives score 0
        for (int i = 0; i <= n; i++) {
            dp[0][i] = new State(0, new ArrayList<>());
        }

        for (int k = 1; k <= 4; k++) {

            dp[k][n] = new State(0, new ArrayList<>());

            for (int i = n - 1; i >= 0; i--) {

                // Option 1: skip current interval
                State skip = dp[k][i + 1];

                // Option 2: take current interval
                int next = findNext(arr, i);

                State nextState = dp[k - 1][next];

                List<Integer> takeIndices =
                        new ArrayList<>(nextState.indices);

                takeIndices.add(arr[i].idx);

                // Keep indices sorted for lexicographical comparison
                Collections.sort(takeIndices);

                long takeScore = arr[i].w + nextState.score;

                State take = new State(takeScore, takeIndices);

                dp[k][i] = better(skip, take);
            }
        }

        // We can choose UP TO 4 intervals.
        State answer = dp[0][0];

        for (int k = 1; k <= 4; k++) {
            answer = better(answer, dp[k][0]);
        }

        int[] result = new int[answer.indices.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = answer.indices.get(i);
        }

        return result;
    }

    // Find first interval whose left > current right.
    // Since boundaries are considered overlapping,
    // we need arr[mid].l > arr[i].r
    static int findNext(Interval[] arr, int i) {

        int low = i + 1;
        int high = arr.length;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (arr[mid].l > arr[i].r) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    // Return the state having:
    // 1. Maximum score
    // 2. If scores are equal, lexicographically smallest indices
    static State better(State a, State b) {

        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        if (lexicographicallySmaller(a.indices, b.indices)) {
            return a;
        }

        return b;
    }

    static boolean lexicographicallySmaller(
            List<Integer> a,
            List<Integer> b) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        // If one is prefix of the other,
        // shorter array is lexicographically smaller.
        return a.size() < b.size();
    }
}