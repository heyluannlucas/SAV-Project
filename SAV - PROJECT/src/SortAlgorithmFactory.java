public class SortAlgorithmFactory {
    public SortAlgorithmFactory() {
    }

    public static SortAlgorithm createSorter(String algorithm) {
        switch (algorithm.toUpperCase()) {
            case "I":
                return new InsertionSort();
            case "S":
                return new SelectionSort();
            case "B":
                return new BubbleSort();
            default:
                return null;
        }
    }

    public static String getAlgorithmName(String algorithm) {
        if (algorithm.equalsIgnoreCase("B")) {
            return "Bubble Sort";
        } else if (algorithm.equalsIgnoreCase("I")) {
            return "Insertion Sort";
        } else {
            return algorithm.equalsIgnoreCase("S") ? "Selection Sort" : "Algoritimo invalido";
        }
    }
}
