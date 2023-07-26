import java.util.Arrays;

public class SortingTimeMeasurement {
    public SortingTimeMeasurement() {
    }

    public static double measureSortTime(SortAlgorithm sorter, int[] list, boolean ascending) {
        int[] listCopy = Arrays.copyOf(list, list.length);
        long startTime = System.nanoTime();
        sorter.sort(listCopy, 0, ascending);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        return (double) elapsedTime / 1_000_000.0; // Convertendo de nanossegundos para milissegundos
    }
}