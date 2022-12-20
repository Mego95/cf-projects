package gr.aueb.cf.projects;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Reads a file with integers (from 1 to 49)
 * and makes all possible combinations of six
 * that fulfill these requirements:
 * 1) includes a max of 4 even numbers
 * 2) includes a max of 4 odd numbers
 * 3) includes a max of 2 consecutive same numbers
 * 4) includes a max of 3 same numbers at the end
 * 5) includes a max of 3 numbers of same ten
 */
public class CombinationsSixProject1App {
    final static Path randomNumbersPath = Paths.get("C:/tmp/random-numbers.txt");
    final static Path combinationsOut = Paths.get("C:/tmp/combinations-out.txt");
    final static int N = 6;


    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        int[] combination = new int[N];

        try {
            numbers = createNumbersList();
        } catch (FileNotFoundException e) {
            System.out.println("List of numbers not found");
            System.exit(1);
        }

        try {
            for (int i = 0; i <= numbers.size() - N; i++) {
                for (int j = i + 1; j <= numbers.size() - N + 1; j++) {
                    for (int k = j + 1; k <= numbers.size() - N + 2; k++) {
                        for (int m = k + 1; m <= numbers.size() - N + 3; m++) {
                            for (int q = m + 1; q <= numbers.size() - N + 4; q++) {
                                for (int r = q + 1; r <= numbers.size(); r++) {
                                    combination[0] = numbers.get(i);
                                    combination[1] = numbers.get(j);
                                    combination[2] = numbers.get(k);
                                    combination[3] = numbers.get(m);
                                    combination[4] = numbers.get(q);
                                    combination[5] = numbers.get(r);

                                    if (isValidCombination(combination)) {
                                        printCombination(combination);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Output file not found");
            System.exit(2);
        }


    }

    static public ArrayList<Integer> createNumbersList() throws FileNotFoundException {
        ArrayList<Integer> numbers = new ArrayList<>();

        try (Scanner in = new Scanner(randomNumbersPath.toFile())) {
            while (in.hasNext()) {
                numbers.add(in.nextInt());
            }
            Collections.sort(numbers);
            return numbers;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    static public void printCombination(int[] arr) throws FileNotFoundException {
        try (PrintStream ps = new PrintStream(combinationsOut.toFile())) {
            if (arr == null) throw new NullPointerException();
            ps.printf("%d\t%d\t%d\t%d\t%d\t%d\n", arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static boolean isValidCombination(int[] arr) {
        return hasFourOrLessEvenNumbers(arr) && hasFourOrLessOddNumbers(arr) && hasTwoOrLessSameConsecutive(arr)
                && hasThreeOrLessSameEnding(arr) && hasThreeOrLessSameTen(arr);
    }

    public static boolean hasFourOrLessEvenNumbers(int[] arr) {
        int count = 0;
        final int THRESHOLD = 5;

        if (arr == null) return false;

        // not sure if/how to validate
        if (arr.length < N) return false;

        for (int number : arr) {
            if (number % 2 == 0) {
                count++;
            }
        }

        return count < THRESHOLD;
    }

    public static boolean hasFourOrLessOddNumbers(int[] arr) {
        int count = 0;
        final int THRESHOLD = 5;

        if (arr == null) return false;

        // not sure if/how to validate
        if (arr.length < N) return false;

        for (int number : arr) {
            if (number % 2 != 0) {
                count++;
            }
        }

        return count < THRESHOLD;
    }

    public static boolean hasTwoOrLessSameConsecutive(int[] arr) {
        int cursor = -1;
        int count = 0;
        final int THRESHOLD = 2;

        if (arr == null) return false;

        // not sure if/how to validate
        if (arr.length < N) return false;

        for (int i = 0; i < arr.length - 1; i++) {
            if (cursor == arr[i]) {
                count++;
            } else {
                count = 0;
            }
            cursor = arr[i];

            if (count > THRESHOLD) {
                return false;
            }
        }

        return true;
    }

    public static boolean hasThreeOrLessSameEnding(int[] arr) {
        if (arr == null) return false;

        // not sure if/how to validate
        if (arr.length < N) return false;
        if (arr[N - 1] == arr[N - 2]) {
            if (arr[N - 2] == arr[N - 3]) {
                if (arr[N - 3] == arr[N - 4]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean hasThreeOrLessSameTen(int[] arr) {
        // digits array has length of 5 because our highest possible number is 49 that gives 4 tens
        int[] digits = new int[5];
        int num = 0;
        int[] tens = new int[N];
        final int THRESHOLD = 4;

        if (arr == null) return false;

        // not sure if/how to validate
        if (arr.length < N) return false;

        for (int i = 0; i < arr.length - 1; i++) {
            tens[i] = arr[i] / 10;
        }

        for (int i = 0; i < tens.length - 1; i++) {
            num = tens[i];

            if (++digits[num] >= THRESHOLD) {
                return false;
            }
        }
        return true;
    }
}
