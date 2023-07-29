public class BubbleSort implements SortAlgorithm {
    private long sortingTime;
    private int stepCounter = 0;


    public BubbleSort() {
    }

    public long sort(int[] arr, int delay, boolean ascending, SortStepCallback callback) {
        long startTime = System.nanoTime();
        int n = arr.length;
        stepCounter = 1;

        for (int i = 0; i < n - 1; ++i) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; ++j) {
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
                    Thread.sleep(delay);
                } catch (InterruptedException var10) {
                    var10.printStackTrace();
                }
                logStep(arr, stepCounter);
                stepCounter++;
            }

            if (!swapped) {
                break;
            }
        }

        long endTime = System.nanoTime();
        sortingTime = (endTime - startTime) / 1_000_000;
        return sortingTime;
    }
    @Override
    public long getSortingTime() {return sortingTime;}
    @Override
    public int getStepCounter() {return stepCounter;}

    @Override
    public String getName() {return "Bubble Sort";}
}
