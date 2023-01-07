package gr.aueb.cf.projects;

public class MaxSumSubArrayApp {

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println("The max sum of continuous sub-array is: " + maxSumSubArray(arr));
    }

    public static int maxSumSubArray(int[] arr) {
        int localMax = 0;
        int globalMax = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            localMax += arr[i];
            if (localMax < arr[i]) {
                localMax = arr[i];
            }
            if (globalMax < localMax) {
                globalMax = localMax;
            }
        }

        return globalMax;
    }
}
