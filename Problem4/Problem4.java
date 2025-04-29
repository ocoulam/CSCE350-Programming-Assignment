package Problem4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Problem4 {

    public static void main(String[] args) {
        String pattern = "", text = "";

        // read from file
        try (Scanner sc = new Scanner(new File("Problem4/input.txt"))) {
            if (sc.hasNextLine()) {
                pattern = sc.nextLine();
            }
            if (sc.hasNextLine()) {
                text = sc.nextLine();
            }
        } catch (IOException e) {
            System.out.println("Couldn't open input file: " + e.getMessage());
            return;
        }

        // time the search
        long start = System.nanoTime();
        int index = horspool(text, pattern);
        long end = System.nanoTime();

        System.out.println("Horspool search took " + (end - start) + " ns");

        // write result
        try (FileWriter fw = new FileWriter("Problem4/output.txt")) {
            fw.write(Integer.toString(index));
        } catch (IOException e) {
            System.out.println("Failed writing output: " + e.getMessage());
        }
    }

    // Horspool string match: find first match index or -1
    public static int horspool(String text, String pat) {
        int n = text.length(), m = pat.length();
        if (m == 0 || n < m) return -1;

        // setup shift table
        int[] shift = new int[27];
        for (int i = 0; i < 27; i++) shift[i] = m;

        for (int i = 0; i < m - 1; i++) {
            shift[charToInt(pat.charAt(i))] = m - 1 - i;
        }

        int i = 0;
        while (i <= n - m) {
            int j = m - 1;
            while (j >= 0 && pat.charAt(j) == text.charAt(i + j)) {
                j--;
            }
            if (j < 0) return i;

            int move = shift[charToInt(text.charAt(i + m - 1))];
            i += move;
        }

        return -1;
    }

    // map a-z and space to 0-26
    private static int charToInt(char c) {
        return (c == ' ') ? 26 : (c - 'a');
    }
}
