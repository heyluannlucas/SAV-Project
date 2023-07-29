public class SelectionSort implements SortAlgorithm {
    public SelectionSort() {
    }
    private long sortingTime;
    private int stepCounter = 1;

    public long sort(int[] arr, int delay, boolean ascending, SortStepCallback callback) {
        long startTime = System.nanoTime();
        int n = arr.length;
        stepCounter = 1;

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

                logStep(arr, j);
                stepCounter++;
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

            logStep(arr, i);
            stepCounter++;
        }

        long endTime = System.nanoTime();
        sortingTime = (endTime - startTime) / 1_000_000;
        return sortingTime;
    }

    @Override
    public long getSortingTime() {
        return sortingTime;
    }

    public int getStepCounter() {
        return stepCounter;
    }

    @Override
    public String getName() {
        return "Selection Sort";
    }
}
