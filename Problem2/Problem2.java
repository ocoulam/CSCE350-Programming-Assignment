package Problem2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem2 {

    private static float[] array;
    
    public static void main(String[] args) {
        readInput("Problem2/input.txt");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 1 for Merge Sort or 2 for Quick Sort:");
        int choice = sc.nextInt();
        if (choice == 1) {
            mergeSort(array, 0, array.length - 1);
            System.out.println("Sorted using Merge Sort! Check output.txt for the result.");
            long start = System.nanoTime();
            long duration = System.nanoTime() - start;
            System.out.println("Array sorted in " + duration + " ns");
        } else if (choice == 2) {
            quickSort(array, 0, array.length - 1);
            System.out.println("Sorted using Quick Sort! Check output.txt for the result.");
            long start = System.nanoTime();
            long duration = System.nanoTime() - start;
            System.out.println("Array sorted in " + duration + " ns");
        } else {
            System.out.println("Invalid choice.");
            return;
        }
        saveResult();
    }

    public static void readInput(String file) {
        try {
            Scanner sc = new Scanner(new File("Problem2/input.txt"));
            List<Float> list = new ArrayList<>();
            while (sc.hasNextFloat()) {
                list.add(sc.nextFloat());
            }
            array = new float[list.size()];
            for (int i = 0; i < array.length; i++) {
                array[i] = list.get(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveResult() {
        try {
            FileWriter writer = new FileWriter("Problem2/output.txt");
            for (float num : array) {
                writer.write(num + " ");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mergeSort(float[] array, int l, int r) {
        if (l >= r) return;
        int m = (l + r) / 2;
        mergeSort(array, l, m);
        mergeSort(array, m + 1, r);
        merge(array, l, m, r);
    }

    public static void merge(float[] a, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        float[] left = new float[n1];
        float[] right = new float[n2];

        for (int i = 0; i < n1; i++) {
            left[i] = a[l + i];
        }
        for (int j = 0; j < n2; j++) {
            right[j] = a[m + 1 + j];
        }

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                a[k] = left[i];
                i++;
            } else {
                a[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            a[k++] = left[i++];
        }
        while (j < n2) {
            a[k++] = right[j++];
        }
    }

    public static void quickSort(float[] array, int lo, int hi) {
        if (lo < hi) {
            int pi = partition(array, lo, hi);
            quickSort(array, lo, pi - 1);
            quickSort(array, pi + 1, hi);
        }
    }

    public static int partition(float[] arr, int lo, int hi) {
        float piv = arr[hi];
        int i = lo - 1;

        for (int j = lo; j < hi; j++) {
            if (arr[j] <= piv) {
                i++;
                // swapping
                float t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        float t = arr[i + 1];
        arr[i + 1] = arr[hi];
        arr[hi] = t;
        return i + 1;
    }

    // Helper method for my own purposes
    public void printArray(float[] arr) {
        for (float v : arr) System.out.print(v + " ");
        System.out.println();
    }
}
