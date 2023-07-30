package Algoritimos;

import Utilitarios.SortStepCallback;

public class InsertionSort implements SortAlgorithm {
    private long sortingTime;
    private int stepCounter;
    public InsertionSort() {
    }

    public long sort(int[] arr, int delay, boolean ascending, SortStepCallback callback) {
        long startTime = System.nanoTime();
        int n = arr.length;
        stepCounter = 0;

        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && (ascending && arr[j] > key || !ascending && arr[j] < key)) {
                arr[j + 1] = arr[j];
                --j;

                if (callback != null) {
                    callback.onStep(arr);
                }

                try {
                    Thread.sleep((long) delay);
                } catch (InterruptedException var10) {
                    var10.printStackTrace();
                }

                logStep(arr, stepCounter);

            }

            arr[j + 1] = key;

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
        long time = (endTime - startTime);
        sortingTime = time / 1_000_000;
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
        return "Insertion Sort";
    }
}
