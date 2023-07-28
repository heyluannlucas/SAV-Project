public class SortAlgorithmFactory {
    public static SortAlgorithm createSorter(String algorithm) {
        return switch (algorithm.toUpperCase()) {
            case "I" -> new InsertionSort();
            case "S" -> new SelectionSort();
            case "B" -> new BubbleSort();
            default -> null;
        };
    }
}
