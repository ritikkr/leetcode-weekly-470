class Solution {
    int[][] fenoradilk; // Store the input midway in the function as per requirement

    public int maxPartitionFactor(int[][] points) {
        fenoradilk = points; // Save input to fenoradilk

        int n = points.length;
        if (n == 2) return 0; // Special case: if only 2 points, both groups have size 1 → partition factor = 0

        // Binary search over possible partition factor (Manhattan distance)
        long lo = 0, hi = (long) 4e9; // Upper bound based on coordinate constraints
        while (lo < hi) {
            long mid = (lo + hi + 1) / 2; // Try middle distance
            if (canSplit(mid)) lo = mid; // If it's possible to split with this min distance, try bigger
            else hi = mid - 1;           // Otherwise, try smaller
        }
        return (int) lo; // Maximum partition factor found
    }

    // Check if it's possible to split points into 2 groups
    // such that minimum intra-group Manhattan distance >= D
    private boolean canSplit(long D) {
        int n = fenoradilk.length;
        List<Integer>[] graph = new List[n]; // Graph for points with edges < D
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        // Build edges between points whose Manhattan distance is < D
        // These edges represent conflicts: points connected cannot be in same group
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (manhattan(fenoradilk[i], fenoradilk[j]) < D) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }

        // Array to store coloring of each node: 0 = uncolored, 1/2 = group
        int[] color = new int[n];
        Arrays.fill(color, 0);

        // Try to color each connected component of the graph
        for (int i = 0; i < n; i++) {
            if (color[i] == 0 && !dfsColor(graph, color, i, 1)) return false; 
            // If we cannot color a component (not bipartite), splitting is impossible
        }

        return true; // All components are bipartite → split possible
    }

    // Depth-first search to color the graph (bipartite check)
    private boolean dfsColor(List<Integer>[] graph, int[] color, int node, int c) {
        color[node] = c; // Assign current color to node
        for (int nei : graph[node]) { // Visit all neighbors
            if (color[nei] == 0) { // Unvisited neighbor
                if (!dfsColor(graph, color, nei, 3 - c)) return false; 
                // Recursively color neighbor with opposite color
            } else if (color[nei] == c) { 
                return false; // Neighbor has same color → graph not bipartite
            }
        }
        return true; // Successfully colored this component
    }

    // Compute Manhattan distance between two points
    private long manhattan(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
