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
                    row[i] = Double.parseDouble(parts[i]);
                }
                rows.add(row);
            }
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
            return new double[0][0];
        }
        return rows.toArray(new double[rows.size()][]);
    }


    private static void floyd(double[][] mat) {
        int n = mat.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][k] + mat[k][j] < mat[i][j]) {
                        mat[i][j] = mat[i][k] + mat[k][j];
                    }
                }
            }
        }
    }

    private static void writeMatrix(double[][] mat, String fileName) {
        try (FileWriter fw = new FileWriter(fileName)) {
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[i].length; j++) {
                    fw.write(String.format("%.6f", mat[i][j]));
                    if (j < mat[i].length - 1) fw.write(" ");
                }
                fw.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Couldn't write output: " + e.getMessage());
        }
    }
}