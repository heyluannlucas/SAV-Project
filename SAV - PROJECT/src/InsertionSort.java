public class InsertionSort implements SortAlgorithm {
    public InsertionSort() {
    }

    public void sort(int[] arr, int delay, boolean ascending, SortStepCallback callback) {
        int n = arr.length;

        for(int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while(j >= 0 && (ascending && arr[j] > key || !ascending && arr[j] < key)) {
                arr[j + 1] = arr[j];
                --j;
                if (callback != null) {
                    callback.onStep(arr);
                }

                try {
                    Thread.sleep((long)delay);
                } catch (InterruptedException var10) {
                    var10.printStackTrace();
                }
            }

            arr[j + 1] = key;
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
