public class InsertionSort implements SortAlgorithm {
    private long sortingTime;
    private int stepCounter; // Variável para contar os passos

    public InsertionSort() {
    }

    public long sort(int[] arr, int delay, boolean ascending, SortStepCallback callback) {
        long startTime = System.nanoTime(); // Record the start time
        int n = arr.length;
        stepCounter = 0; // Inicializa o contador de passos

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

                logStep(arr, i); // Log usando o índice atual 'i'.
                stepCounter++; // Incrementa o contador de passos a cada iteração do while
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

            logStep(arr, i); // Log usando o índice atual 'i'.
            stepCounter++; // Incrementa o contador de passos após a troca
        }

        long endTime = System.nanoTime(); // Record the end time
        sortingTime = (endTime - startTime) / 1_000_000; // Store the sorting time in milliseconds
        return sortingTime; // Return the sorting time in milliseconds
    }

    @Override
    public long getSortingTime() {
        return sortingTime;
    }

    public int getStepCounter() {
        return stepCounter; // Retorna o total de passos ou iterações realizadas
    }

    public void sort(int[] arr, int delay, boolean ascending) {
        this.sort(arr, delay, ascending, null);
    }
}
