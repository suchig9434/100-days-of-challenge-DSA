 class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // 1. Build the adjacency list for the directed graph
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj.get(u).add(new int[]{v, w});
        }

        // 2. Initialize the distance matrix
        // dist[u][c] stores the min weight to reach node 'u' with consecutive streak 'c'
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // 3. Min-Heap tracking elements as: [current_weight, current_node, consecutive_count]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        // Start state: node 0, total weight 0, initial character streak count is 1
        pq.offer(new int[]{0, 0, 1});
        dist[0][1] = 0;

        // 4. Dijkstra search loop
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0];
            int u = curr[1];
            int c = curr[2];

            // If we reached the target node, return the weight immediately
            if (u == n - 1) {
                return d;
            }

            // Skip outdated states
            if (d > dist[u][c]) {
                continue;
            }

            // Explore neighbors
            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int w = neighbor[1];

                // Determine the consecutive streak count for the next node
                int nextC = (labels.charAt(v) == labels.charAt(u)) ? (c + 1) : 1;

                // Only proceed if the streak condition is valid
                if (nextC <= k) {
                    if (d + w < dist[v][nextC]) {
                        dist[v][nextC] = d + w;
                        pq.offer(new int[]{d + w, v, nextC});
                    }
                }
            }
        }

        return -1;
    }
}
