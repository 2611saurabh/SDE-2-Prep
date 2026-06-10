package Graph;


import java.util.*;

// Driver class
public class FindCenterOfStarGraph1791 {

    public static void main(String[] args) {

        // Create object of Solution class
        Solution1791 solution = new Solution1791();

        // Input edges of the star graph
        int[][] edges = {
                {1, 2},
                {2, 3},
                {4, 2}
        };

        // Call the method and store the center node
        int center = solution.findCenter(edges);

        // Print the answer
        System.out.println("Center of Star Graph : " + center);

        System.out.println("Centr of Star graph using optmial solution : "+ solution.findCenterOptimal(edges));
    }
}

class Solution1791{

    public int findCenter(int[][] edges) {

        // Create adjacency list
        // Key   -> Node
        // Value -> List of neighbours
        HashMap<Integer, List<Integer>> adj = new HashMap<>();

        // In a star graph:
        // Number of nodes = Number of edges + 1
        // Create an empty list for every node
        // Time Complexity : O(n)
        for (int i = 1; i <= edges.length + 1; i++) {
            adj.put(i, new ArrayList<>());
        }

        // Traverse all edges and build the graph
        // Since the graph is undirected,
        // add both u -> v and v -> u
        // Time Complexity : O(E)
        for (int i = 0; i < edges.length; i++) {

            // First node of the edge
            int u = edges[i][0];

            // Second node of the edge
            int v = edges[i][1];

            // Add v to u's neighbour list
            adj.get(u).add(v);

            // Add u to v's neighbour list
            adj.get(v).add(u);
        }

        // Stores the maximum degree seen so far
        int max = 0;

        // Stores the node having the maximum degree
        int maxDegree = 0;

        // Traverse all nodes and find the node with maximum degree
        // Time Complexity : O(n)
        for (int i = 1; i <= edges.length + 1; i++) {

            // Degree of current node
            int len = adj.get(i).size();

            // If current degree is greater than previous maximum
            if (len > max) {

                // Update maximum degree
                max = len;

                // Store current node as answer
                maxDegree = i;
            }
        }

        // Return the center node
        return maxDegree;
    }

    public int findCenterOptimal(int[][] edges) {

        int n = edges.length;

        int a = edges[0][0];
        int b = edges[0][1];
        int c = edges[1][0];
        int d = edges[1][1];

        if(a == c || a == d){
            return a;
        }
        return b;
    }
}

/*
----------------------------------------------------
Example

Input:

edges =
{
    {1,2},
    {2,3},
    {4,2}
}

Graph:

      1
      |
4 ----2----3

Adjacency List:

1 -> [2]
2 -> [1,3,4]
3 -> [2]
4 -> [2]

Degrees:

Node 1 -> 1
Node 2 -> 3
Node 3 -> 1
Node 4 -> 1

Maximum Degree = 3
Center Node = 2

Output:

Center of Star Graph : 2

----------------------------------------------------

Time Complexity

1. Initialize adjacency list : O(n)
2. Build graph               : O(E)
3. Find max degree node      : O(n)

Total:

O(n + E)

For a star graph:

E = n - 1

Therefore:

Overall Time Complexity = O(n)

----------------------------------------------------

Space Complexity

Adjacency List : O(n + E)

For a star graph:

E = n - 1

Overall Space Complexity = O(n)

----------------------------------------------------
*/