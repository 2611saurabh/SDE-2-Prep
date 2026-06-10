package Graph;

import java.util.*;

// Driver class
public class FindIfPathExistInGraph1971 {

    public static void main(String[] args) {

        // Create object of Solution class
        Solution solution = new Solution();

        // Number of vertices in graph
        int n = 3;

        // Edge list representation of graph
        // 0 ----- 1 ----- 2
        int[][] edges = {
                {0, 1},
                {1, 2}
        };

        // Starting node
        int source = 0;

        // Destination node
        int destination = 2;

        // Call validPath() and store the answer
        boolean result = solution.validPath(n, edges, source, destination);

        // Print whether path exists or not
        System.out.println("Path exists : " + result);
    }
}

class Solution {

    public boolean validPath(int n, int[][] edges, int source, int destination) {

        // Create adjacency list
        // Key   -> Node
        // Value -> List of neighbours
        HashMap<Integer, List<Integer>> adj = new HashMap<>();

        // Initialize every node with an empty neighbour list
        // Time Complexity : O(V)
        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<>());
        }

        // Build the graph from edge list
        // Since graph is undirected,
        // add both u -> v and v -> u
        // Time Complexity : O(E)
        for (int i = 0; i < edges.length; i++) {

            // First node of edge
            int u = edges[i][0];

            // Second node of edge
            int v = edges[i][1];

            // Add v in u's neighbour list
            adj.get(u).add(v);

            // Add u in v's neighbour list
            adj.get(v).add(u);
        }

        // Keeps track of visited nodes
        // visited[i] = true means node i has already been visited
        // Space Complexity : O(V)
        boolean[] visited = new boolean[n];

        // Start DFS from source node
        return dfs(adj, visited, source, destination);
    }

    public boolean dfs(HashMap<Integer, List<Integer>> adj,
                       boolean[] visited,
                       int source,
                       int destination) {

        // Mark current node as visited
        visited[source] = true;

        // Base case:
        // If current node becomes destination,
        // path exists
        if (source == destination) {
            return true;
        }

        // Traverse all neighbours of current node
        for (int neighbour : adj.get(source)) {

            // Visit neighbour only if it has not been visited
            if (!visited[neighbour]) {

                // Recursively search from neighbour
                if (dfs(adj, visited, neighbour, destination)) {

                    // If path found from any neighbour,
                    // immediately return true
                    return true;
                }
            }
        }

        // No path found from current node
        return false;
    }
}

/*
-------------------- Example --------------------

Input:
n = 3

edges =
{
    {0,1},
    {1,2}
}

source = 0
destination = 2

Graph:

0 ---- 1 ---- 2

DFS Traversal:

dfs(0)
|
|-- dfs(1)
    |
    |-- dfs(2)
            source == destination
            return true

Output:
Path exists : true

-------------------------------------------------

Time Complexity:

1. Building adjacency list  : O(E)
2. DFS traversal            : O(V + E)

Overall Time Complexity:

O(V + E)

where
V = number of vertices
E = number of edges

-------------------------------------------------

Space Complexity:

Adjacency List : O(V + E)
Visited Array  : O(V)
Recursion Stack: O(V)

Overall Space Complexity:

O(V + E)

-------------------------------------------------
*/
