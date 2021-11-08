package ticTacToeGame;

import java.util.Arrays;

public class Board {
    private TicTacToeValue [][]ticTacToeGrid;

    public Board(int gridNumber) {
        ticTacToeGrid = new TicTacToeValue[gridNumber][gridNumber];
        for (int row = 0; row < ticTacToeGrid.length; row++) {
            Arrays.fill(ticTacToeGrid[row], TicTacToeValue.Empty);
        }
    }

    public TicTacToeValue[][] getGrid() {
        return ticTacToeGrid;
    }

    public void displayBoard() {
        for (int row = 0; row < ticTacToeGrid.length; row++) {
            for (int column = 0; column < ticTacToeGrid.length; column++) {
                System.out.print(ticTacToeGrid[row][column] + "| ");
            }
            System.out.println();
        }
    }
}
