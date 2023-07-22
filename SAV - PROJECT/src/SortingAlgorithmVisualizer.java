import java.util.Random;

public class SortingAlgorithmVisualizer {
    public static void main(String[] args) {
        if (args.length < 6) {
            System.out.println("Parâmetros insuficientes.");
            return;
        }

        String algorithm = args[0];
        String listType = args[1];
        String sortOrder = args[2];
        String listValueType = args[3];
        int delay = Integer.parseInt(args[4]);

        if (listValueType.equalsIgnoreCase("R")) {
            int randomCount = Integer.parseInt(args[5]);
            int[] randomList = generateRandomList(randomCount);
            sortAndVisualize(algorithm, listType, sortOrder, randomList, delay);

        } else if (listValueType.equalsIgnoreCase("M")) {
            String[] values = args[5].split(",");
            int[] customList = new int[values.length];

            for (int i = 0; i < values.length; i++) {
                customList[i] = Integer.parseInt(values[i].trim());
            }
            sortAndVisualize(algorithm, listType, sortOrder, customList, delay);
        } else {
            System.out.println("Tipo de valor da lista inválido.");
        }
    }

    private static void sortAndVisualize(String algorithm, String listType, String sortOrder, int[] list, int delay) {
        SortAlgorithm sorter;

        switch (algorithm.toUpperCase()) {
            case "I":
                sorter = new InsertionSort();
                break;
            case "S":
                sorter = new SelectionSort();
                break;
            case "B":
                sorter = new BubbleSort();
                break;
            default:
                System.out.println("Algoritmo de ordenação inválido.");
                return;
        }

        if (sortOrder.equalsIgnoreCase("AZ")) {
            System.out.println("Sorting in ascending order using " + algorithm + "...");
            visualizeSorting(sorter, list, delay, "Passo ");
        } else if (sortOrder.equalsIgnoreCase("ZA")) {
            System.out.println("Sorting in descending order using " + algorithm + "...");
            sorter.sortDescending(list, delay);
        } else {
            System.out.println("Tipo de ordenamento inválido.");
        }

        System.out.println("Sorted List: ");
        printList(list);
    }

    private static void visualizeSorting(SortAlgorithm sorter, int[] list, int delay, String passLabel) {
        int pass = 1;
        boolean sorted = false;

        long startTime = System.nanoTime(); // Start timing

        while (!sorted) {
            System.out.print(passLabel + pass + ": ");
            printList(list);

            sorted = true;
            for (int i = 0; i < list.length - 1; i++) {
                if (list[i] > list[i + 1]) {
                    int temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    sorted = false;
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            pass++;
        }

        long endTime = System.nanoTime(); // End timing
        double elapsedTimeInSeconds = calculateElapsedTimeInSeconds(startTime, endTime);

        System.out.println("Sorting completed in " + elapsedTimeInSeconds + " seconds");
    }

    private static double calculateElapsedTimeInSeconds(long startTime, long endTime) {
        long elapsedTime = endTime - startTime; // Calculate the elapsed time in nanoseconds
        return elapsedTime / 1_000_000_000.0; // Convert to seconds
    }

    private static int[] generateRandomList(int count) {
        int[] list = new int[count];
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            list[i] = random.nextInt(100); // Gera números aleatórios entre 0 e 99
        }

        return list;
    }

    private static void printList(int[] list) {
        for (int num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
