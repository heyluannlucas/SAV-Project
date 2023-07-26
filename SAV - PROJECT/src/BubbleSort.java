public class BubbleSort implements SortAlgorithm {
    private long sortingTime; // Declare sortingTime as an instance variable

    public BubbleSort() {
    }

    public long sort(int[] arr, int delay, boolean ascending, SortStepCallback callback) {
        long startTime = System.nanoTime(); // Record the start time
        int n = arr.length;
        int stepCounter = 1;

        for (int i = 0; i < n - 1; ++i) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; ++j) {
                if (ascending && arr[j] > arr[j + 1] || !ascending && arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    stepCounter++; // Increment the step counter on each swap
                }

                if (callback != null) {
                    callback.onStep(arr);
                }

                try {
                    Thread.sleep((long) delay);
                } catch (InterruptedException var10) {
                    var10.printStackTrace();
                }
                logStep(arr, stepCounter); // Log using the stepCounter
            }

            if (!swapped) {
                break;
            }
        }

        long endTime = System.nanoTime(); // Record the end time
        sortingTime = (endTime - startTime) / 1_000_000; // Store the sorting time in milliseconds
        return sortingTime; // Return the sorting time in milliseconds
    }

    public long getSortingTime() {
        return sortingTime; // Getter to access the sorting time from outside
    }


    public void sort(int[] arr, int delay, boolean ascending) {
        this.sort(arr, delay, ascending, (SortStepCallback) null);
    }
}
