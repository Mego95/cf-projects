package gr.aueb.cf.projects;

import java.util.Arrays;

public class DeepAndShallowCopyApp {

    public static void main(String[] args) {
        // original 2d array
        int[][] original = { {1, 2}, {3, 4}, {5, 6} };

        int[][] deepCopy = deepCopy(original);
        int[][] shallowCopy = shallowCopy(original);
        
        // array to be swapped in copies
        int[] change = {10, 20};

        deepCopy[0] = change;

        System.out.println("deep copy after change: ");
        printArray(deepCopy[0]);
        System.out.println("original after deep copy change: ");
        printArray(original[0]);

        shallowCopy[0] = change;

        System.out.println("shallow copy after change: ");
        printArray(shallowCopy[0]);
        System.out.println("original after shallow copy change: ");
        printArray(original[0]);
    }

    public static int[][] deepCopy(int[][] original) {
        int[][] copy = new int[original.length][];

        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }

        return copy;
    }

    public static int[][] shallowCopy(int[][] original) {
        return original;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
