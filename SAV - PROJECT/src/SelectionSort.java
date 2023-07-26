public class SelectionSort implements SortAlgorithm {
    private long sortingTime;

    public long sort(int[] arr, int delay, boolean ascending, SortStepCallback callback) {
        long startTime = System.nanoTime(); // Record the start time
        int n = arr.length;

        for (int i = 0; i < n - 1; ++i) {
            int minIndex = i;

            for (int j = i + 1; j < n; ++j) {
                if (ascending && arr[j] < arr[minIndex] || !ascending && arr[j] > arr[minIndex]) {
                    minIndex = j;
                }

                if (callback != null) {
                    callback.onStep(arr);
                }

                try {
                    Thread.sleep((long) delay);
                } catch (InterruptedException var10) {
                    var10.printStackTrace();
                }

                logStep(arr, j); // Log using the current index 'j'.
            }

            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;

            if (callback != null) {
                callback.onStep(arr);
            }

            try {
                Thread.sleep((long) delay);
            } catch (InterruptedException var11) {
                var11.printStackTrace();
            }

            logStep(arr, i); // Log using the current index 'i'.
        }
        long endTime = System.nanoTime(); // Record the end time
        sortingTime = (endTime - startTime) / 1_000_000; // Store the sorting time in milliseconds
        return sortingTime; // Return the sorting time in milliseconds
    }

    @Override
    public long getSortingTime() {
        return sortingTime;
    }

    public void sort(int[] arr, int delay, boolean ascending) {
        this.sort(arr, delay, ascending, null);
    }
}