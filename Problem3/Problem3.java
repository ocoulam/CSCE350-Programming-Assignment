package Problem3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem3 {

    private static int[] keys;

    public static void main(String[] args) {
        readInput("Problem3/input.txt");

        Scanner sc = new Scanner(System.in);
        System.out.println("1 for MaxHeap, 2 for MinHeap:");
        int option = sc.nextInt();
        sc.close();

        if (option == 1) {
            long t1 = System.nanoTime();
            int[] maxH = buildMaxHeap(keys);
            long t2 = System.nanoTime();
            System.out.println("Built Max-Heap in " + (t2 - t1) + " ns");
            writeHeap(maxH, "Problem3/output.txt");
        } else if (option == 2) {
            long t1 = System.nanoTime();
            int[] minH = buildMinHeap(keys);
            long t2 = System.nanoTime();
            System.out.println("Built Min-Heap in " + (t2 - t1) + " ns");
            writeHeap(minH, "Problem3/output.txt");
        } else {
            System.out.println("Not a valid choice.");
        }
    }


    private static void readInput(String path) {
        try (Scanner sc = new Scanner(new File(path))) {
                List<Integer> list = new ArrayList<>();
                if (sc.hasNextLine()) {
                    String line = sc.nextLine().trim();
                if (!line.isEmpty()) {
                    Scanner lineSc = new Scanner(line);
                    while (lineSc.hasNextInt()) {
                        list.add(lineSc.nextInt());
                    }
                    lineSc.close();
                }
            }
            keys = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                keys[i] = list.get(i);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            keys = new int[0];
        }
    }


    // Save heap to file
    private static void writeHeap(int[] heap, String path) {
        try (FileWriter fw = new FileWriter(path)) {
            for (int i = 0; i < heap.length; i++) {
                fw.write(heap[i] + (i != heap.length - 1 ? " " : ""));
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public static int[] buildMaxHeap(int[] arr) {
        int n = arr.length;
        int[] heap = arr.clone();
        for (int i = n/2 - 1; i >= 0; i--) {
            maxHeapify(heap, n, i);
        }
        return heap;
    }

    private static void maxHeapify(int[] heap, int n, int i) {
        int biggest = i;
        int l = 2*i + 1, r = 2*i + 2;

        if (l < n && heap[l] > heap[biggest]) biggest = l;
        if (r < n && heap[r] > heap[biggest]) biggest = r;

        if (biggest != i) {
            int tmp = heap[i];
            heap[i] = heap[biggest];
            heap[biggest] = tmp;
            maxHeapify(heap, n, biggest);
        }
    }

    public static int[] buildMinHeap(int[] arr) {
        int n = arr.length;
        int[] heap = arr.clone();
        for (int i = n/2 - 1; i >= 0; i--) {
            minHeapify(heap, n, i);
        }
        return heap;
    }

    private static void minHeapify(int[] heap, int n, int i) {
        int small = i;
        int l = 2*i + 1, r = 2*i + 2;

        if (l < n && heap[l] < heap[small]) small = l;
        if (r < n && heap[r] < heap[small]) small = r;

        if (small != i) {
            int tmp = heap[i];
            heap[i] = heap[small];
            heap[small] = tmp;
            minHeapify(heap, n, small);
        }
    }
}
