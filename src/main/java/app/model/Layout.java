package model;

public class Layout {
    private final char[][] grid;

    public Layout() {
        grid = new char[][] {
            {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'}, // Rangée 1
            {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'},     // Rangée 2
            {'z', 'x', 'c', 'v', 'b', 'n', 'm'}               // Rangée 3
        };
    }


    public int[] getPosition(char c) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == c) {
                    return new int[] {row, col};
                }
            }
        }
        return null; 
    }

    public char getCharAt(int row, int col) {
        if (row >= 0 && row < grid.length && col >= 0 && col < grid[row].length) {
            return grid[row][col];
        }
        return '\0';
    }
}
