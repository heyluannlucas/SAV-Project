public class BubbleSort implements SortAlgorithm {
    @Override
    public void sortAscending(int[] list, int delay) {
        // Algoritmo de ordenação por bolha em ordem crescente
        // Implementação do Bubble Sort

        int n = list.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list[j] > list[j + 1]) {
                    int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void sortDescending(int[] list, int delay) {
        // Algoritmo de ordenação por bolha em ordem decrescente
        // Implementação do Bubble Sort em ordem decrescente

        int n = list.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list[j] < list[j + 1]) {
                    int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}