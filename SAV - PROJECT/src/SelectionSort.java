public class SelectionSort implements SortAlgorithm {
    public SelectionSort() {
    }
    private long sortingTime;
    private int stepCounter = 1; // Variável para contar os passos

    public long sort(int[] arr, int delay, boolean ascending, SortStepCallback callback) {
        long startTime = System.nanoTime(); // Record the start time
        int n = arr.length;
        stepCounter = 1; // Inicializa o contador de passos

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

                logStep(arr, j); // Log usando o índice atual 'j'.
                stepCounter++; // Incrementa o contador de passos a cada iteração
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
}
