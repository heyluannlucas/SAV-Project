import java.util.Arrays;

public interface SortAlgorithm {
    long sort(int[] arr, int delay, boolean ascending, SortStepCallback callback);

    void sort(int[] arr, int delay, boolean ascending);

    long getSortingTime(); // New method to retrieve the sorting time

    default void logStep(int[] arr, int stepNumber) {
        System.out.println("Step " + stepNumber + ": " + Arrays.toString(arr));
    }
}
