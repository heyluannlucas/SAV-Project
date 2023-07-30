package Visualizacao;

import Algoritimos.SortAlgorithm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Arrays;
import javax.swing.*;

public class SavGUI extends JPanel {
    private final int[] list;
    private final int[] originalList;
    private final int steps;
    private final String algorithm;
    private final SortAlgorithm sorter;
    private final String sortOrder;
    private final int delay;
    private int traversalIndex;

    public SavGUI(int[] list, String algorithm, SortAlgorithm sorter, String sortOrder, int delay) {
        this.list = list;
        this.originalList = Arrays.copyOf(list, list.length);
        this.steps = 1;
        this.algorithm = algorithm;
        this.sorter = sorter;
        this.sortOrder = sortOrder;
        this.delay = delay;
        this.traversalIndex = -1;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(230, 230, 230));
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

        for (int i = 0; i < list.length; ++i) {
            int barHeight = list[i] * heightMultiplier;
            int barX = startX + padding + (width + gap) * i;
            int barY = startY - barHeight;

            Color barColor;

            if (i == traversalIndex) {
                // Traversal bar color
                barColor = new Color(135, 206, 250);
            } else if (list[i] == originalList[i]) {
                // Sorted bar color
                barColor = new Color(135, 206, 250);
            } else {
                // Unsorted bar color
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

    private boolean isSorting(int[] list, int[] originalList, int index) {
        return index <= traversalIndex;
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
        long time = sorter.getSortingTime();
        int etapas = sorter.getStepCounter();
        String SortName = sorter.getName();

        int startX = 400;
        int startY = 100;
        int gap = 20;
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.MAGENTA);
        g.drawString("Algorithm: " + SortName , startX, startY);
        g.setColor(Color.BLACK);
        g.drawString("Number of Elements: " + listSize, startX, startY + gap);
        g.drawString("Number of Steps: " + etapas, startX, startY + 2 * gap);
        g.drawString("Time Taken: " + time + " ms", startX, startY + 3 * gap);
        g.drawString("Sorting Order: " + (sortOrder.equalsIgnoreCase("AZ") ? "Ascending (AZ)" : "Descending (ZA)"), startX, startY + 4 * gap);
        g.drawString("Delay: " + delay + " milliseconds", startX, startY + 5 * gap);
    }


    public void setTraversalIndex(int index) {
        this.traversalIndex = index;
        repaint();
    }
}