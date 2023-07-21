public class SelectionSort implements SortAlgorithm {
    @Override
    public void sortAscending(int[] list, int delay) {
        // Algoritmo de ordenação por seleção em ordem crescente
        // Implementação do Selection Sort

        int n = list.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (list[j] < list[minIndex]) {
                    minIndex = j;
                }
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int temp = list[minIndex];
            list[minIndex] = list[i];
            list[i] = temp;
        }
    }

    @Override
    public void sortDescending(int[] list, int delay) {
        // Algoritmo de ordenação por seleção em ordem decrescente
        // Implementação do Selection Sort em ordem decrescente

        int n = list.length;
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (list[j] > list[maxIndex]) {
                    maxIndex = j;
                }
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int temp = list[maxIndex];
            list[maxIndex] = list[i];
            list[i] = temp;
        }
    }
}