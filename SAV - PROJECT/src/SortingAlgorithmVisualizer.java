import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

public class SortingAlgorithmVisualizer {
    private static int steps = 1;
    private static JFrame frame;
    private static int[] originalList;

    public static void main(String[] args) {
        // Default parameter values
        String algorithm = "b";
        String listType = "n";
        String sortOrder = "za";
        String listValueType = "r";
        int delay = 100;
        int randomCount = 15;
        int[] customList = null;

        // Interpreta os argumentos da linha de comando
        ArgumentParser argParser = new ArgumentParser(args);

        // Validate the arguments
        if (!argParser.validateArguments()) {
            // Display errors and exit if arguments are invalid
            List<String> errors = argParser.getErrors();
            for (String error : errors) {
                System.out.println(error);
            }
            System.out.println("Uso: java SortingAlgorithmVisualizer -a=ALGORITHM -t=LISTTYPE -o=SORTORDER -in=LISTVALUETYPE -s=DELAY -r=RANDOMCOUNT -v=CUSTOMLIST");
            System.out.println("Exemplo: java SortingAlgorithmVisualizer -a=B -t=N -o=AZ -in=R -s=200 -r=20");
            return;
        }

        // Get the validated values of the arguments
        algorithm = argParser.getString("a", "algorithm", algorithm).toUpperCase();
        listType = argParser.getString("t", "listtype", listType).toUpperCase();
        sortOrder = argParser.getString("o", "sortorder", sortOrder).toUpperCase();
        listValueType = argParser.getString("in", "listvaluetype", listValueType).toUpperCase();
        delay = argParser.getInt("s", "delay", delay);

        // Get the custom list if listValueType is "M"
        if (listValueType.equals("M")) {
            customList = argParser.getIntArray("v", "customlist", customList);
            if (customList == null) {
                System.out.println("Lista personalizada não fornecida.");
                return;
            }
        }

        // Generate or get the random list if listValueType is "R"
        int[] list;
        if (listValueType.equals("R")) {
            randomCount = argParser.getInt("r", "randomcount", randomCount);
            list = RandomList.getRandomList(randomCount);
        } else {
            list = customList;
        }

        // Sort and visualize the list
        sortAndVisualize(algorithm, listType, sortOrder, list, delay);
    }
    private static void sortAndVisualize(final String algorithm, String listType, String sortOrder, final int[] list, int delay) {
        SortAlgorithm sorter = SortAlgorithmFactory.createSorter(algorithm);
        if (sorter == null) {
            System.out.println("Algoritmo de ordenação inválido.");
        } else {
            frame = new JFrame("Sorting Algorithm Visualizer - " + SortAlgorithmFactory.getAlgorithmName(algorithm));
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            JPanel panel = new JPanel() {
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    SortingAlgorithmVisualizer.drawListNumbers(g, list);
                    SortingAlgorithmVisualizer.drawBars(g, list);
                    SortingAlgorithmVisualizer.drawStats(g, list.length, SortingAlgorithmVisualizer.steps, algorithm);
                }
            };
            frame.add(panel);
            frame.setVisible(true);
            originalList = Arrays.copyOf(list, list.length);

            System.out.println("Array inicial: " + Arrays.toString(list));

            if (sortOrder.equalsIgnoreCase("AZ")) {
                System.out.println("Sorting in ascending order using " + SortAlgorithmFactory.getAlgorithmName(algorithm) + "...");
                visualizeSorting(sorter, list, delay, true);
            } else if (sortOrder.equalsIgnoreCase("ZA")) {
                System.out.println("Sorting in descending order using " + SortAlgorithmFactory.getAlgorithmName(algorithm) + "...");
                visualizeSorting(sorter, list, delay, false);
            } else {
                System.out.println("Tipo de ordenamento inválido.");
            }

            System.out.println("Time taken by " + SortAlgorithmFactory.getAlgorithmName(algorithm) + " in " + sortOrder + " order: " + sorter.getSortingTime() + " miliseconds");
            System.out.println("Lista ordenada: "+ Arrays.toString(list));
        }
    }

    private static void visualizeSorting(SortAlgorithm sorter, int[] list, int delay, boolean ascending) {
        int passo = 1;

        while (passo <= list.length) {
            sorter.sort(list, delay, ascending, new StepCallback(list, delay));
            frame.repaint();

            // Verifica se a lista está ordenada após cada iteração
            boolean sorted = true;
            for (int i = 0; i < list.length - 1; ++i) {
                if ((ascending && list[i] > list[i + 1]) || (!ascending && list[i] < list[i + 1])) {
                    sorted = false;
                    break;
                }
            }

            if (sorted) {
                break;
            }

            ++steps;

            try {
                Thread.sleep(delay);
            } catch (InterruptedException var8) {
                var8.printStackTrace();
            }
        }
    }

    private static void drawBars(Graphics g, int[] list) {
        int width = 20;
        int heightMultiplier = 2;
        int startX = 50;
        int startY = 420;
        int gap = 5;
        int padding = 20;

        for (int i = 0; i < list.length; ++i) {
            int barHeight = list[i] * heightMultiplier;
            int barX = startX + padding + (width + gap) * i;
            int barY = startY - barHeight;

            Color barColor;
            if (originalList[i] == list[i]) {
                barColor = new Color(135, 206, 250);
            } else {
                barColor = new Color(255, 192, 203);
            }

            g.setColor(barColor);
            g.fillRect(barX, barY, width, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(barX, barY, width, barHeight);

            int numberX = barX + width / 2 - 6;
            int numberY = startY + 20;
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString(Integer.toString(list[i]), numberX, numberY);
        }
    }


    private static void drawListNumbers(Graphics g, int[] list) {
        int width = 20;
        int heightMultiplier = 2;
        int startX = 50;
        int startY = 420;
        int gap = 5;
        int padding = 20;
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));

        for(int i = 0; i < list.length; ++i) {
            int barHeight = list[i] * heightMultiplier;
            int barX = startX + padding + (width + gap) * i;
            int barY = startY - barHeight;
            Color barColor = originalList[i] == list[i] ? new Color(135, 206, 250) : new Color(255, 192, 203);
            g.setColor(barColor);
            g.fillRect(barX, barY, width, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(barX, barY, width, barHeight);
            int numberX = barX + width / 2 - 6;
            int numberY = startY + 20;
            g.drawString(Integer.toString(list[i]), numberX, numberY);
        }

    }

    private static void drawStats(Graphics g, int listSize, int steps, String algorithm) {

        int startX = 400;
        int startY = 100;
        int gap = 20;
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.MAGENTA);
        g.drawString("Algorithm: " + SortAlgorithmFactory.getAlgorithmName(algorithm), startX, startY);
        g.setColor(Color.BLACK);
        g.drawString("Number of Elements: " + listSize, startX, startY + gap);
        g.drawString("Number of Steps: " + steps, startX, startY + 2 * gap);
    }

    private static class StepCallback implements SortStepCallback {
        private int[] arr;
        private int delay;

        public StepCallback(int[] arr, int delay) {
            this.arr = arr;
            this.delay = delay;
        }

        public void onStep(int[] arr) {
            SortingAlgorithmVisualizer.frame.repaint();

            try {
                Thread.sleep(this.delay);
            } catch (InterruptedException var3) {
                var3.printStackTrace();
            }

        }
    }
}
