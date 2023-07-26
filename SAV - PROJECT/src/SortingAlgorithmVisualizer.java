import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SortingAlgorithmVisualizer {
    private static int steps;
    private static JFrame frame;
    private static int[] originalList;
    private static double sortingTimeInMillis = 0;

    public SortingAlgorithmVisualizer() {
    }

    public static void main(String[] args) {
        // Argumentos padrão
        String algorithm = "b";
        String listType = "N";
        String sortOrder = "Az";
        String listValueType = "R";
        int delay = 100;
        int randomCount = 10;
        int[] customList = null;

        // Interpretar os argumentos da linha de comando
        ArgumentParser argParser = new ArgumentParser(args);
        algorithm = argParser.getString("a", "algorithm", algorithm).toUpperCase();
        listType = argParser.getString("t", "listtype", listType).toUpperCase();
        sortOrder = argParser.getString("o", "sortorder", sortOrder).toUpperCase();
        listValueType = argParser.getString("in", "listvaluetype", listValueType).toUpperCase();
        delay = argParser.getInt("s", "delay", delay);
        randomCount = argParser.getInt("r", "randomcount", randomCount);
        customList = argParser.getIntArray("v", "customlist", customList);

        // Verificar o tipo de valor da lista
        int[] list;
        if (listValueType.equalsIgnoreCase("R")) {
            list = RandomList.getRandomList(randomCount); // Chamando o método e obtendo a lista aleatória
        } else if (listValueType.equalsIgnoreCase("M")) {
            if (customList == null) {
                System.out.println("Lista personalizada não fornecida.");
                return;
            }
            list = customList;
        } else {
            System.out.println("Tipo de valor da lista inválido.");
            return;
        }

        // Chamar o método de ordenação e visualização
        sortAndVisualize(algorithm, listType, sortOrder, list, delay);
    }


    private static void sortAndVisualize(final String algorithm, String listType, String sortOrder, final int[] list, int delay) {
        SortAlgorithm sorter = SortAlgorithmFactory.createSorter(algorithm);
        if (sorter == null) {
            System.out.println("Algoritmo de ordenação inválido.");
        } else {
            frame = new JFrame("Sorting Algorithm Visualizer - " + algorithm);
            frame.setDefaultCloseOperation(3);
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
            if (sortOrder.equalsIgnoreCase("AZ")) {
                System.out.println("Sorting in ascending order using " + SortAlgorithmFactory.getAlgorithmName(algorithm) + "...");
                visualizeSorting(sorter, list, delay, true, "Passo ");
            } else if (sortOrder.equalsIgnoreCase("ZA")) {
                System.out.println("Sorting in descending order using " + SortAlgorithmFactory.getAlgorithmName(algorithm) + "...");
                visualizeSorting(sorter, list, delay, false, "Passo ");
            } else {
                System.out.println("Tipo de ordenamento inválido.");
            }

            sortingTimeInMillis = SortingTimeMeasurement.measureSortTime(sorter, list, sortOrder.equalsIgnoreCase("AZ"));
            PrintStream var10000 = System.out;
            String var10001 = SortAlgorithmFactory.getAlgorithmName(algorithm);
            var10000.println("Time taken by " + var10001 + " in " + sortOrder + " order: " + sortingTimeInMillis + " seconds");
            System.out.println("Sorted List: ");
            printList(list);
        }
    }

    private static void visualizeSorting(SortAlgorithm sorter, int[] list, int delay, boolean ascending, String passLabel) {
        int passo = 1;

        while(passo <= list.length) {
            System.out.print(passLabel + passo + ": ");
            sorter.sort(list, delay, ascending, new StepCallback(list, delay));
            printList(list);
            frame.repaint();
            boolean sorted = true;

            for(int i = 0; i < list.length - 1; ++i) {
                if (ascending && list[i] > list[i + 1] || !ascending && list[i] < list[i + 1]) {
                    sorted = false;
                    break;
                }
            }

            if (sorted) {
                break;
            }

            ++steps;
            ++passo;

            try {
                Thread.sleep((long)delay);
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
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", 1, 16));
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
        g.setFont(new Font("Arial", 1, 16));

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
        g.setFont(new Font("Arial", 1, 16));
        g.setColor(Color.BLUE);
        g.drawString("Algorithm: " + SortAlgorithmFactory.getAlgorithmName(algorithm), startX, startY);
        g.setColor(Color.BLACK);
        g.drawString("Number of Elements: " + listSize, startX, startY + gap);
        g.drawString("Number of Steps: " + steps, startX, startY + 2 * gap);
        new DecimalFormat("#.###");
        g.drawString("Sorting Time: " + sortingTimeInMillis + " seconds", startX, startY + 3 * gap);
    }

    private static void printList(int[] list) {
        int[] var1 = list;
        int var2 = list.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            int num = var1[var3];
            System.out.print("" + num + " ");
        }

        System.out.println();
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
                Thread.sleep((long)this.delay);
            } catch (InterruptedException var3) {
                var3.printStackTrace();
            }

        }
    }
}
