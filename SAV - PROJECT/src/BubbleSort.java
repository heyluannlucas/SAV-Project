public class BubbleSort implements SortAlgorithm {
    @Override
    public void sortAscending(int[] list, int delay) {
        // Implementação do Bubble Sort em ordem crescente
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
        // Primeiro ordena em ordem crescente
        sortAscending(list, delay);

        // Inverte a lista para obter a ordem decrescente
        int n = list.length;
        for (int i = 0; i < n / 2; i++) {
            int temp = list[i];
            list[i] = list[n - i - 1];
            list[n - i - 1] = temp;
        }
    }
}
