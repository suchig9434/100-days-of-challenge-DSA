class Solution {
    // Grid dimensions
    private int rows;
    private int cols;
    private int[][] grid;

    public int closedIsland(int[][] grid) {
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.grid = grid;
      
        int closedIslandCount = 0;
      
        // Traverse the entire grid to find unvisited land cells
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 0) {  // Found unvisited land
                    // Check if this island is closed (doesn't touch border)
                    closedIslandCount += dfs(row, col);
                }
            }
        }
      
        return closedIslandCount;
    }
    private int dfs(int row, int col) {
        // Check if current cell is not on the border (closed island condition)
        // If on border, this island touches the edge and is not closed
        int isClosed = (row > 0 && row < rows - 1 && col > 0 && col < cols - 1) ? 1 : 0;
      
        // Mark current land cell as visited by changing it to water
        grid[row][col] = 1;
      
        // Direction vectors for moving up, right, down, left
        int[] directions = {-1, 0, 1, 0, -1};
      
        // Explore all 4 adjacent cells
        for (int k = 0; k < 4; k++) {
            int nextRow = row + directions[k];
            int nextCol = col + directions[k + 1];
            if (nextRow >= 0 && nextRow < rows && 
                nextCol >= 0 && nextCol < cols && 
                grid[nextRow][nextCol] == 0) {
                isClosed &= dfs(nextRow, nextCol);
            }
        }
      
        return isClosed;
    }
}
