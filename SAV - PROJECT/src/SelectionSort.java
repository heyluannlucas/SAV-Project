public class SelectionSort implements SortAlgorithm {
    public SelectionSort() {
    }

    public void sort(int[] arr, int delay, boolean ascending, SortStepCallback callback) {
        int n = arr.length;

        for(int i = 0; i < n - 1; ++i) {
            int minIndex = i;

            int j;
            for(j = i + 1; j < n; ++j) {
                if (ascending && arr[j] < arr[minIndex] || !ascending && arr[j] > arr[minIndex]) {
                    minIndex = j;
                }

                if (callback != null) {
                    callback.onStep(arr);
                }

                try {
                    Thread.sleep((long)delay);
                } catch (InterruptedException var10) {
                    var10.printStackTrace();
                }
            }

            j = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = j;
            if (callback != null) {
                callback.onStep(arr);
            }

            try {
                Thread.sleep((long)delay);
            } catch (InterruptedException var11) {
                var11.printStackTrace();
            }
        }

    }

    public void sort(int[] arr, int delay, boolean ascending) {
        this.sort(arr, delay, ascending, (SortStepCallback)null);
    }
}
