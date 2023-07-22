public class SelectionSort implements SortAlgorithm {
    @Override
    public void sortAscending(int[] list, int delay) {
        // Implementação do Selection Sort em ordem crescente
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
