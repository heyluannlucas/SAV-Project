public class BubbleSort implements SortAlgorithm {
    public BubbleSort() {
    }

    public void sort(int[] arr, int delay, boolean ascending, SortStepCallback callback) {
        int n = arr.length;

        for(int i = 0; i < n - 1; ++i) {
            boolean swapped = false;

            for(int j = 0; j < n - i - 1; ++j) {
                if (ascending && arr[j] > arr[j + 1] || !ascending && arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
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

            if (!swapped) {
                break;
            }
        }

    }

    public void sort(int[] arr, int delay, boolean ascending) {
        this.sort(arr, delay, ascending, (SortStepCallback)null);
    }
}
