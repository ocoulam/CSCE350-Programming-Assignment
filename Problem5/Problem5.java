package Problem5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem5 {

    public static void main(String[] args) {
        double[][] D = readMatrix("Problem5/input.txt");
        floyd(D);
        writeMatrix(D, "Problem5/output.txt");
    }

    private static double[][] readMatrix(String fileName) {
        List<double[]> rows = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(" ");
                double[] row = new double[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    if (parts[i].equalsIgnoreCase("inf")) {
                        row[i] = Double.POSITIVE_INFINITY;
                    } else {
                        row[i] = Double.parseDouble(parts[i]);
                    }
                }
                rows.add(row);
            }
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
            return new double[0][0];
        }
        return rows.toArray(new double[rows.size()][]);
    }

    private static void floyd(double[][] D) {
        int n = D.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (D[i][k] == Double.POSITIVE_INFINITY) continue;
                for (int j = 0; j < n; j++) {
                    if (D[k][j] == Double.POSITIVE_INFINITY) continue;
                    double viaK = D[i][k] + D[k][j];
                    if (viaK < D[i][j]) {
                        D[i][j] = viaK;
                    }
                }
            }
        }
    }

    private static void writeMatrix(double[][] D, String fileName) {
        try (FileWriter fw = new FileWriter(fileName)) {
            for (int i = 0; i < D.length; i++) {
                for (int j = 0; j < D[i].length; j++) {
                    if (Double.isInfinite(D[i][j])) {
                        fw.write("inf");
                    } else {
                        fw.write(String.format("%.6f", D[i][j]));
                    }
                    if (j < D[i].length - 1) fw.write(" ");
                }
                if (i < D.length - 1) fw.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Couldn't write output: " + e.getMessage());
        }
    }
}
