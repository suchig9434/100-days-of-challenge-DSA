class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int initial = image[sr][sc];
        if (initial == color) return image; // avoid infinite loop

        int[][] ans = image;
        int[] drow = {-1, 0, 1, 0};
        int[] dcol = {0, 1, 0, -1};

        dfs(sr, sc, ans, drow, dcol, initial, color);
        return ans;
    }

    private void dfs(int sr, int sc, int[][] ans, int[] drow, int[] dcol, int initial, int color) {
        ans[sr][sc] = color;
        int n = ans.length;
        int m = ans[0].length;

        for (int i = 0; i < 4; i++) {
            int r = sr + drow[i];
            int c = sc + dcol[i];

            if (r >= 0 && r < n && c >= 0 && c < m 
                && ans[r][c] == initial && ans[r][c] != color) {
                dfs(r, c, ans, drow, dcol, initial, color);
            }
        }
    }
}