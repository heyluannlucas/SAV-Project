public class InsertionSort implements SortAlgorithm {
    @Override
    public void sortAscending(int[] list, int delay) {
        // Implementação do Insertion Sort em ordem crescente
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
