package Question4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MinimumNumberOfRoad {
    /**
     * The main entry point of the program. It processes an array indicating package locations 
     * and a list of connections between different points. It then determines the least number 
     * of roads required to gather all available packages for each test scenario.
     */
    public static void main(String[] args) {
        // Test Case 1: Packages' placement and their respective connecting paths
        int[] packages1 = {1, 0, 0, 0, 0, 1};
        int[][] roads1 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}};

        // Test Case 2: A different arrangement of package locations and their connecting roads
        int[] packages2 = {0, 0, 0, 1, 1, 0, 0, 1};
        int[][] roads2 = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {5, 6}, {5, 7}};

        // Compute the minimum number of roads to pass through for the first test case
        int minRoads1 = findMinRoads(packages1, roads1);
        System.out.println("Minimum number of roads to traverse for Input 1: " + minRoads1);

        // Compute the minimum number of roads to pass through for the second test case
        int minRoads2 = findMinRoads(packages2, roads2);
        System.out.println("Minimum number of roads to traverse for Input 2: " + minRoads2);
    }

    /**
     * Determines the smallest number of roads that need to be traveled to collect all packages.
     * It considers the paths necessary for collection as well as the return journey.
     *
     * @param packages An array representing whether a package is available at a given location (1 for present, 0 for absent).
     * @param roads A list of roads connecting different locations.
     * @return The fewest number of roads that must be used.
     */
    public static int findMinRoads(int[] packages, int[][] roads) {
        int n = packages.length; // Total locations (nodes) in the network
        List<List<Integer>> graph = buildGraph(n, roads); // Construct the network using the road data

        int minRoads = Integer.MAX_VALUE; // Placeholder for the minimum path count

        // Consider each location as a possible starting point
        for (int start = 0; start < n; start++) {
            boolean[] visited = new boolean[n]; // Keeps track of visited locations
            int roadsTraversed = 0; // Tracks the number of roads used

            // Execute BFS to gather all packages within a two-road radius
            roadsTraversed += bfs(start, graph, packages, visited);

            // Compute the return journey to the starting position
            roadsTraversed += backtrack(start, graph, visited);

            // Update the record of the least number of roads needed
            minRoads = Math.min(minRoads, roadsTraversed);
        }

        return minRoads; // Return the smallest number of roads needed
    }

    /**
     * Constructs a graph representation using a list-based structure, 
     * where each location maintains a list of directly connected points.
     *
     * @param n Total number of locations.
     * @param roads Connections between locations forming a pathway network.
     * @return A graph where each location is linked to its neighboring locations.
     */
    private static List<List<Integer>> buildGraph(int n, int[][] roads) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Populate the adjacency list with the given road connections
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            graph.get(u).add(v);
            graph.get(v).add(u); // Since the paths are bidirectional
        }

        return graph;
    }

    /**
     * Uses a breadth-first search to travel a maximum of two roads from the starting point 
     * while collecting available packages and counting the roads traveled.
     *
     * @param start The location from which the traversal begins.
     * @param graph A representation of the locations and connections between them.
     * @param packages An array specifying where packages are located.
     * @param visited An array to track which locations have already been checked.
     * @return The number of roads used during package collection.
     */
    private static int bfs(int start, List<List<Integer>> graph, int[] packages, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start); // Insert the starting point into the queue
        visited[start] = true;

        int roadsTraversed = 0;

        // Conduct BFS exploration within a two-road distance limit
        for (int level = 0; level < 2; level++) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll(); // Remove the first item in the queue

                // If there is a package at the current location, pick it up
                if (packages[current] == 1) {
                    packages[current] = 0; // Mark the package as retrieved
                }

                // Traverse all adjacent locations
                for (int neighbor : graph.get(current)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor); // Queue the neighboring location
                        roadsTraversed++; // Increase the count of roads used
                    }
                }
            }
        }

        return roadsTraversed;
    }

    /**
     * Implements a return mechanism to go back to the starting location, 
     * tracking the roads used in the process.
     *
     * @param start The original starting point.
     * @param graph The structure representing connections between locations.
     * @param visited Tracks which locations have been visited during traversal.
     * @return The total number of roads required for the return journey.
     */
    private static int backtrack(int start, List<List<Integer>> graph, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        int roadsTraversed = 0;

        // Continue the reverse traversal until the start point is reached
        while (!queue.isEmpty()) {
            int current = queue.poll();

            // Stop if we have reached the initial location
            if (current == start) {
                break;
            }

            // Process neighboring locations for return
            for (int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    roadsTraversed++; // Count the roads used in backtracking
                }
            }
        }

        return roadsTraversed;
    }
}
