package Graph;

public class NumberOfProvinces547 {

    public int findCircleNum(int[][] isConnected) {
        // n represents the total number of cities (nodes) in the graph
        int n = isConnected.length;

        // visited array to keep track of cities that have already been explored
        boolean[] visited = new boolean[n];

        // Counter to track the number of connected components (provinces)
        int count = 0;

        // Iterate through each city to ensure every component is visited
        for (int i = 0; i < n; i++) {
            // If the city hasn't been visited yet, it belongs to a new province
            if (!visited[i]) {
                count++; // Increment the province count

                // Start a Depth First Search (DFS) to mark all reachable cities in this province
                dfs(isConnected, visited, i);
            }
        }

        // Return the total number of connected provinces found
        return count;
    }

    // Helper method to perform DFS traversal and mark connected nodes
    public void dfs(int[][] isConnected, boolean[] visited, int source) {
        // Mark the current city as visited
        visited[source] = true;

        // Traverse through all possible neighbor cities
        for (int j = 0; j < isConnected[0].length; j++) {
            // If there is a direct connection to city 'j' and it hasn't been visited yet
            if (isConnected[source][j] == 1 && !visited[j]) {
                // Recursively visit city 'j'
                dfs(isConnected, visited, j);
            }
        }
    }

    // Main method to test and run the solution locally in IntelliJ
    public static void main(String[] args) {
        NumberOfProvinces547 solver = new NumberOfProvinces547();

        // Test Case: 3 cities where City 0 and City 1 are connected, City 2 is isolated.
        // Matrix graph representation:
        // [1, 1, 0] -> City 0 connects to 0, 1
        // [1, 1, 0] -> City 1 connects to 0, 1
        // [0, 0, 1] -> City 2 connects only to itself
        int[][] isConnected = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };

        // Execute the method
        int provinces = solver.findCircleNum(isConnected);

        // Print the output
        System.out.println("Total Number of Provinces: " + provinces);
        // Expected Output: 2
    }

    /*
     * TIME COMPLEXITY: O(N^2)
     * - We visit every city exactly once because of the 'visited' array guard.
     * - For each city, the 'dfs' function iterates through all N potential neighbors in its row.
     * - Therefore, the algorithm processes an N x N matrix, leading to a quadratic time complexity.
     * * SPACE COMPLEXITY: O(N)
     * - The 'visited' array requires O(N) space.
     * - In the worst-case scenario (where all cities are connected in a single straight line),
     * the recursive call stack for DFS can grow up to O(N) deep.
     */
}