public class InsertionSort implements SortAlgorithm {
    @Override
    public void sortAscending(int[] list, int delay) {
        // Algoritmo de ordenação por inserção em ordem crescente
        // Implementação do Insertion Sort

        int n = list.length;
        for (int i = 1; i < n; i++) {
            int key = list[i];
            int j = i - 1;

            while (j >= 0 && list[j] > key) {
                list[j + 1] = list[j];
                j--;
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            list[j + 1] = key;
        }
    }

    @Override
    public void sortDescending(int[] list, int delay) {
        // Algoritmo de ordenação por inserção em ordem decrescente
        // Implementação do Insertion Sort em ordem decrescente

        int n = list.length;
        for (int i = 1; i < n; i++) {
            int key = list[i];
            int j = i - 1;

            while (j >= 0 && list[j] < key) {
                list[j + 1] = list[j];
                j--;
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            list[j + 1] = key;
        }
    }
}