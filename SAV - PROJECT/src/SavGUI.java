import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Arrays;
import javax.swing.*;

public class SavGUI extends JPanel {
    private int[] list;
    private int[] originalList;
    private int steps;
    private String algorithm;
    private SortAlgorithm sorter;
    private String sortOrder;
    private int delay;

    public SavGUI(int[] list, String algorithm, SortAlgorithm sorter, String sortOrder, int delay) {
        this.list = list;
        this.originalList = Arrays.copyOf(list, list.length);
        this.steps = 1;
        this.algorithm = algorithm;
        this.sorter = sorter;
        this.sortOrder = sortOrder;
        this.delay = delay;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(230, 230, 230)); // Define a cor de fundo como cinza claro
        drawListNumbers(g, list);
        drawBars(g, list, originalList);
        drawStats(g, list.length, steps, algorithm, sorter, sortOrder, delay);
    }

    private void drawBars(Graphics g, int[] list, int[] originalList) {
        int width = 20;
        int heightMultiplier = 2;
        int startX = 50;
        int startY = 420;
        int gap = 5;
        int padding = 20;

        // Define an array of pastel colors
        Color[] pastelColors = {
                new Color(255, 204, 204), // Light pink
                new Color(204, 255, 204), // Light green
                new Color(204, 204, 255), // Light blue
                new Color(255, 255, 204), // Light yellow
                new Color(255, 204, 255), // Light purple
                new Color(204, 255, 255)  // Light cyan
        };

        for (int i = 0; i < list.length; ++i) {
            int barHeight = list[i] * heightMultiplier;
            int barX = startX + padding + (width + gap) * i;
            int barY = startY - barHeight;

            // Get the index of the pastel color based on the current bar index
            int colorIndex = i % pastelColors.length;
            Color barColor = pastelColors[colorIndex];

            // Set the color based on the comparison with the original list
            if (list[i] == originalList[i]) {
                // Sorted bar color
                barColor = new Color(135, 206, 250); // Light blue
            } else if (isSorting(list, originalList, i)) {
                // Sorting bar color
                barColor = new Color(255, 192, 203); // Light pink
            } else {
                // Unsorted bar color
                barColor = new Color(230, 230, 230); // Light gray
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

    private boolean isSorting(int[] list, int[] originalList, int index) {
        for (int i = 0; i < index; i++) {
            if (list[i] != originalList[i]) {
                return true;
            }
        }
        return false;
    }

    private void drawListNumbers(Graphics g, int[] list) {
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

    private void drawStats(Graphics g, int listSize, int steps, String algorithm, SortAlgorithm sorter, String sortOrder, int delay) {
        double time = (double) sorter.getSortingTime(); // Cast the sorting time to an integer
        int etapa = sorter.getStepCounter();

        int startX = 400;
        int startY = 100;
        int gap = 20;
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.MAGENTA);
        g.drawString("Algorithm: " + SortAlgorithmFactory.getAlgorithmName(algorithm), startX, startY);
        g.setColor(Color.BLACK);
        g.drawString("Number of Elements: " + listSize, startX, startY + gap);
        g.drawString("Number of Steps: " + etapa, startX, startY + 2 * gap);
        g.drawString("Time Taken: " + time + " milliseconds", startX, startY + 3 * gap);
        g.drawString("Sorting Order: " + (sortOrder.equalsIgnoreCase("AZ") ? "Ascending (AC)" : "Descending (ZA)"), startX, startY + 4 * gap);
        g.drawString("Delay: " + delay + " milliseconds", startX, startY + 5 * gap);
    }
}