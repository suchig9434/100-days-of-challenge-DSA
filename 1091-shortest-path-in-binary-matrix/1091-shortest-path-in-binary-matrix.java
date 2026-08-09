class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;
        if (n == 1) return 1;

        int[][] dist = new int[n][n];
        for (int[] row : dist) java.util.Arrays.fill(row, Integer.MAX_VALUE);

        int[] dr = {-1,-1,-1,0,0,1,1,1};
        int[] dc = {-1,0,1,-1,1,-1,0,1};

        java.util.Queue<int[]> q = new java.util.LinkedList<>();
        q.add(new int[]{0,0});
        dist[0][0] = 1;

        while (!q.isEmpty()) {
            int[] t = q.poll();
            int r = t[0], c = t[1];
            int d = dist[r][c];

            for (int i = 0; i < 8; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 0) {
                    if (d + 1 < dist[nr][nc]) {
                        dist[nr][nc] = d + 1;
                        if (nr == n-1 && nc == n-1) return d + 1;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }

        return -1;
    }
}