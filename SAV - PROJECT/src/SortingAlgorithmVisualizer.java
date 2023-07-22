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
            long startTime = System.nanoTime(); // Marca o tempo inicial
            sorter.sortAscending(list, delay);
            long endTime = System.nanoTime(); // Marca o tempo final
            long elapsedTime = endTime - startTime; // Calcula o tempo decorrido em nanossegundos
            double elapsedTimeInSeconds = elapsedTime / 1_000_000_000.0; // Converte para segundos

            System.out.println("Tempo gasto: " + elapsedTimeInSeconds + " segundos");
        } else if (sortOrder.equalsIgnoreCase("ZA")) {
            long startTime = System.nanoTime(); // Marca o tempo inicial
            sorter.sortDescending(list, delay);
            long endTime = System.nanoTime(); // Marca o tempo final
            long elapsedTime = endTime - startTime; // Calcula o tempo decorrido em nanossegundos
            double elapsedTimeInSeconds = elapsedTime / 1_000_000_000.0; // Converte para segundos

            System.out.println("Tempo gasto: " + elapsedTimeInSeconds + " segundos");
        } else {
            System.out.println("Tipo de ordenamento inválido.");
        }

        System.out.println("Lista ordenada: ");
        printList(list);
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
