import java.util.Arrays;

public interface SortAlgorithm {
    long sort(int[] arr, int delay, boolean ascending, SortStepCallback callback);

    long getSortingTime();
    int getStepCounter();
    String getName();
    default void logStep(int[] arr, int stepNumber) {
        System.out.println("Step " + stepNumber + ": " + Arrays.toString(arr));
    }
}
