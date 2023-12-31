package Visualizacao;

import Algoritimos.SortAlgorithm;
import Utilitarios.ArgumentParser;
import Utilitarios.RandomList;
import Utilitarios.SortAlgorithmFactory;
import Utilitarios.SortStepCallback;

import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class SortingAlgorithmVisualizer {
    static int steps = 1;
    private static JFrame frame;

    public static void main(String[] args) {
        String algorithm = "";
        String listType = "";
        String sortOrder = "";
        String listValueType = "";
        int delay = 0;
        int randomCount = 0;
        int[] customList = null;

        ArgumentParser argParser = new ArgumentParser(args);

        if (!argParser.validateArguments()) {
            List<String> errors = argParser.getErrors();
            for (String error : errors) {
                System.out.println("\u001B[31m" + error + "\u001B[0m");
            }
            System.out.println("Usage: java Visualizacao.SortingAlgorithmVisualizer a=ALGORITHM t=LISTTYPE o=SORTORDER in=LISTVALUETYPE s=DELAY r=RANDOMCOUNT v=CUSTOMLIST");
            System.out.println("Example: after compile use:");
            System.out.println("\u001B[32mjava SAV a=B t=N o=AZ in=R s=200 r=20 \u001B[0mfor random values");
            System.out.println("\u001B[32mjava SAV a=B t=N o=AZ in=m s=200 v='2,7,8,9,10' \u001B[0mfor manual values");

            return;
        }
        algorithm = argParser.getString("a", "algorithm", "").toUpperCase();
        listType = argParser.getString("t", "listtype", "").toUpperCase();
        sortOrder = argParser.getString("o", "sortorder", "").toUpperCase();
        listValueType = argParser.getString("in", "listvaluetype", "").toUpperCase();
        delay = argParser.getInt("s", "delay", 0);

        if (listValueType.equals("M")) {
            customList = argParser.getIntArray("v", "customlist", customList);
            if (customList == null) {
                System.out.println("Custom list not provided.");
                return;
            }
        }

        int[] list;
        if (listValueType.equals("R")) {
            randomCount = argParser.getInt("r", "randomcount", 0);
            list = RandomList.getRandomList(randomCount);
        } else {
            list = customList;
        }

        if (listType.equals("C")) {
            System.out.println("Character list type not implemented. Use t=N to run the program with a numeric list.");
            return;
        }

        sortAndVisualize(algorithm, listType, sortOrder, list, delay);
    }
    private static void sortAndVisualize(final String algorithm, String listType, String sortOrder, final int[] list, int delay) {
        SortAlgorithm sorter = SortAlgorithmFactory.createSorter(algorithm);
        String SortName = sorter.getName();

        if (sorter == null) {
            System.out.println("Algoritmo de ordenação inválido.");

        } else {
            frame = new JFrame("Sorting Algorithm Visualizer - " + SortName);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            SavGUI panel = new SavGUI(list, algorithm, sorter, sortOrder, delay);
            frame.add(panel);
            frame.setVisible(true);
            int[] originalList = Arrays.copyOf(list, list.length);

            System.out.println("Array inicial: " + Arrays.toString(list));

            if (sortOrder.equalsIgnoreCase("AZ")) {
                System.out.println("Sorting in ascending order using " + SortName + "...");
                visualizeSorting(sorter, list, delay, true);

            } else if (sortOrder.equalsIgnoreCase("ZA")) {
                System.out.println("Sorting in descending order using " + SortName + "...");
                visualizeSorting(sorter, list, delay, false);
            } else {
                return;
            }
            long time = sorter.getSortingTime();
            System.out.println("Time taken by " + SortName + " in " + sortOrder + " order: " + time + " miliseconds");
            System.out.println("Lista ordenada: "+ Arrays.toString(list));

        }
    }

    private static void visualizeSorting(SortAlgorithm sorter, int[] list, int delay, boolean ascending) {
        int passo = 0;

        while (true) {
            sorter.sort(list, delay, ascending, new StepCallback(list, delay));
            frame.repaint();

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