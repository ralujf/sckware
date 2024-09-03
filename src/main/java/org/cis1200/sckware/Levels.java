package org.cis1200.sckware;

import java.util.Random;

public class Levels {
    private int level = 1;
    private int[][] matrix;

    public Levels() {
        //the data is pulled from these to change the difficulty
        //each number represents weighted rotation value

        if (level == 1) {
            matrix = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 1, 2},
                    {3, 4, 5, 6},
                    {1, 2, 3, 4}
            };
        } else if (level == 2) {
            matrix = new int[][]{
                    {1, 2, 3, 1},
                    {3, 1, 2, 3},
                    {2, 3, 1, 2},
                    {1, 2, 3, 1}
            };

        } else if (level == 3) {
            matrix = new int[][]{
                    {1, 2, 3, 4},
                    {3, 4, 1, 2},
                    {2, 3, 4, 1},
                    {4, 1, 2, 3}
            };
        }

    }

    public int calcValue() {
        int n = 0;
        Random random = new Random();

        // Choose whether to select a diagonal, vertical, or horizontal line
        int choice = random.nextInt(3);

        int length = matrix.length;
        int[] line;

        if (choice == 0) {
            // diagonal line
            line = new int[length];
            for (int i = 0; i < length; i++) {
                line[i] = matrix[i][i];
            }
        } else if (choice == 1) {
            // vertical line
            int columnIndex = random.nextInt(length);
            line = new int[length];
            for (int i = 0; i < length; i++) {
                line[i] = matrix[i][columnIndex];
            }
        } else {
            // horizontal line
            int rowIndex = random.nextInt(length);
            line = matrix[rowIndex];
        }

        // calc average
        int sum = 0;
        for (int value : line) {
            sum += value;
        }

        n = (sum / length) / 10;
        return n;
    }

    public int[][] getMatrix() {
        return this.matrix;
    }

    ;

    public void setLevel(int l) {
        this.level = l;
    }

    ;
}
