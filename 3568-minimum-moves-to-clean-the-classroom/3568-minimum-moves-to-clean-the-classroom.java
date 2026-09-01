import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int[][] id = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(id[i], -1);
        }

        int sr = 0, sc = 0;
        int litter = 0;

        // Find starting position and litter
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } 
                else if (ch == 'L') {
                    id[i][j] = litter++;
                }
            }
        }

        // Required variable
        String[] lumetarkon = classroom;

        // No litter
        if (litter == 0) {
            return 0;
        }

        int masks = 1 << litter;

        /*
         * mask represents litter which is NOT collected.
         * Initially all litter is uncollected.
         */
        int startMask = masks - 1;

        /*
         * visited[row][col][energy][mask]
         */
        boolean[][][][] visited =
            new boolean[m][n][energy + 1][masks];

        Queue<int[]> q = new ArrayDeque<>();

        // row, col, currentEnergy, mask
        q.offer(new int[]{sr, sc, energy, startMask});

        visited[sr][sc][energy][startMask] = true;

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        int moves = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                int[] cur = q.poll();

                int r = cur[0];
                int c = cur[1];
                int e = cur[2];
                int mask = cur[3];

                // All litter collected
                if (mask == 0) {
                    return moves;
                }

                // Cannot move if energy is 0
                if (e == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // Outside grid
                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n) {
                        continue;
                    }

                    // Obstacle
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    int newEnergy;
                    int newMask = mask;

                    // Reset area
                    if (classroom[nr].charAt(nc) == 'R') {
                        newEnergy = energy;
                    } 
                    else {
                        newEnergy = e - 1;
                    }

                    // Collect litter
                    if (classroom[nr].charAt(nc) == 'L') {
                        newMask &= ~(1 << id[nr][nc]);
                    }

                    // Visit new state
                    if (!visited[nr][nc][newEnergy][newMask]) {

                        visited[nr][nc][newEnergy][newMask] = true;

                        q.offer(new int[]{
                            nr,
                            nc,
                            newEnergy,
                            newMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}