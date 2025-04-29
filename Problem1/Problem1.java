package Problem1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem1 {

    private static int[][] matrix;

    public static void main(String[] args) {
        readMatrix("Problem1/input.txt");
        long start = System.nanoTime();
        String type = topologyType();
        long duration = System.nanoTime() - start;
        System.out.println("Array sorted in " + duration + " ns");
        saveResult(type);
    }

    public static String topologyType() {
        int n = matrix.length;

        // Checks for mesh topology
        boolean mesh = true;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (matrix[i][j] != 1) {
                    mesh = false;
                    break;
                }
            }
            if (!mesh) {
                break;
            }
        }
        if (mesh) {
            return "Fully Connected Mesh";
        }

        // Checks for star topology
        int[] deg = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) deg[i]++;
            }
        }

        int centers = 0, leaves = 0, centerIdx = -1;
        for (int i = 0; i < n; i++) {
            if (deg[i] == n - 1) {
                centers++;
                centerIdx = i;
            } else if (deg[i] == 1) {
                leaves++;
            }
        }
        if (centers == 1 && leaves == n - 1) {
            for (int i = 0; i < n; i++) {
                if (i == centerIdx) continue;
                if (matrix[i][centerIdx] != 1 || deg[i] != 1) {
                    return "None";
                }
            }
            return "Star";
        }

        // Checks for ring topology
        boolean ring = true;
        for (int d : deg) {
            if (d != 2) {
                ring = false;
                break;
            }
        }

        // Uses dfs to check if all vertices are connected
        if (ring) {
            boolean[] visited = new boolean[n];
            dfs(0, visited);
            for (boolean v : visited) {
                if (!v) {
                    return "None";
                }
            }
            return "Ring";
        }

        // If all other checks fail then topology is none
        return "None";
    }

    private static void dfs(int u, boolean[] visited) {
        visited[u] = true;
        for (int v = 0; v < matrix.length; v++) {
            if (matrix[u][v] == 1 && !visited[v]) {
                dfs(v, visited);
            }
        }
    }

    public static void readMatrix(String file) {
        try (Scanner sc = new Scanner(new File(file))) {
            List<int[]> rows = new ArrayList<>();

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");
                int[] row = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    row[i] = Integer.parseInt(parts[i]);
                }
                rows.add(row);
            }

            int size = rows.size();
            matrix = new int[size][size];
            for (int i = 0; i < size; i++) {
                matrix[i] = rows.get(i);
            }

        } catch (IOException e) {
            System.out.println("Problem reading file: " + e.getMessage());
        }
    }

    public static void saveResult(String result) {
        try (FileWriter out = new FileWriter("Problem1/output.txt")) {
            out.write(result);
        } catch (IOException e) {
            System.out.println("Couldn't write result: " + e.getMessage());
        }
    }

    public void testAdjMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
