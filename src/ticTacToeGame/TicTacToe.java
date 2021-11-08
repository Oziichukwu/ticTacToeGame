package ticTacToeGame;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TicTacToe {

    private Board ticTacToeBoard;
    public boolean lastPlayerToPlayWasX;
    private boolean gameWon;

    public TicTacToe(Board ticTacToeBoard) {

        this.ticTacToeBoard = ticTacToeBoard;
        lastPlayerToPlayWasX = false;
        gameWon = false;
    }

    public Board getBoard() {
        
        return ticTacToeBoard;
    }

    public int  markBoard(int gridPosition )throws GameOverException {

        if (isBoardFull()){
            throw new GameOverException();
        }
        if (gridPosition > 9 || gridPosition < 1){
            throw new IllegalArgumentException("Grid position must be between 1 and 9");
        }
        gridPosition -=1;
        int row = gridPosition / 3;
        int column = gridPosition % 3;
        return  placeMark(row, column);
    }

    private boolean isBoardFull() {
        TicTacToeValue[][] ticTacToeGrid = ticTacToeBoard.getGrid();
        for (int row = 0; row < ticTacToeGrid.length; row++) {
            for (int column = 0; column < ticTacToeGrid[row].length; column++) {
                if (ticTacToeGrid[row][column] == TicTacToeValue.Empty){
                    return false;
                }
            }
        }
        return true;
    }

    private int placeMark(int rowPosition, int columnPosition){
        TicTacToeValue [][] ticTacToeGrid = ticTacToeBoard.getGrid();
        if (ticTacToeGrid[rowPosition][columnPosition].equals(TicTacToeValue.Empty)){

            if (lastPlayerToPlayWasX){
                ticTacToeGrid[rowPosition][columnPosition] = TicTacToeValue.O;
                lastPlayerToPlayWasX = false;
            }else
            {
                ticTacToeGrid[rowPosition][columnPosition] = TicTacToeValue.X;
                lastPlayerToPlayWasX = true;
            }
            return 1;
        }
        return -1;
    }

    public boolean checkIfGameIsWon() {

        checkHorizontalValuesForWinningMove();
        checkVerticalValuesForWinningMove();
        checkLeftDiagonalValuesForWinningMove();
        checkRightDiagonalValuesForWinningMove();
       //System.out.println(gameWon);
        return gameWon;
    }

    private void checkRightDiagonalValuesForWinningMove() {
        TicTacToeValue [][]ticTacToeBoardGrid = ticTacToeBoard.getGrid();
        boolean flag = true;
        int rowNumber = 2;
        int columnNumber = 0;
        TicTacToeValue firstValue = ticTacToeBoardGrid[rowNumber][columnNumber];
        for (int row = 0; row < ticTacToeBoardGrid.length; row++) {
            if (!ticTacToeBoardGrid[rowNumber][columnNumber].equals(firstValue)|| ticTacToeBoardGrid[rowNumber][columnNumber] == TicTacToeValue.Empty){
                flag = false;
                break;
            }
            rowNumber -=1;
            columnNumber +=1;
        }
        if (flag){
            gameWon = true;
        }
    }

    private void checkLeftDiagonalValuesForWinningMove() {
        TicTacToeValue [][] ticTakToeGrid = ticTacToeBoard.getGrid();
        boolean flag = true;
        int columnNumber = 0;
        int rowNumber = 0;
        TicTacToeValue firstValue = ticTakToeGrid[rowNumber][columnNumber];
        for (int row = 0; row < ticTakToeGrid.length; row++) {
            if(!ticTakToeGrid[rowNumber][columnNumber].equals(firstValue) || ticTakToeGrid[rowNumber][columnNumber] == TicTacToeValue.Empty){
                flag = false;
                break;
            }
            columnNumber += 1;
            rowNumber += 1;
        }
        if (flag){
            gameWon = true;
        }
    }

    private void checkVerticalValuesForWinningMove() {
        TicTacToeValue [][] ticTakToeGrid = ticTacToeBoard.getGrid();
        int columnNumber = 0;
        for (int column = 0; column < ticTakToeGrid.length; column++) {
            Set<TicTacToeValue>values = new HashSet<>();
            for (int row = 0; row < ticTakToeGrid.length; row++) {
                values.add(ticTakToeGrid[row][columnNumber]);
            }
            if (columnNumber < 2){
                columnNumber++;
            }
            if (!values.contains(TicTacToeValue.Empty)){
                if (values.size() == 1){
                    gameWon = true;
                }
            }
        }
    }

    private void checkHorizontalValuesForWinningMove() {

        TicTacToeValue [][] ticTacToeGrid = ticTacToeBoard.getGrid();
        for(TicTacToeValue [] ticTacToeValues : ticTacToeGrid){
            Set<TicTacToeValue> values = new HashSet<>(Arrays.asList(ticTacToeValues));
            if (!values.contains(TicTacToeValue.Empty)){
                if(values.size() == 1){
                    gameWon = true;
                    return;
                }
            }
        }
    }

    public void resetGame() {

        int gameBoardSize = ticTacToeBoard.getGrid().length;
        ticTacToeBoard = new Board(gameBoardSize);
        lastPlayerToPlayWasX = false;
        gameWon = false;

    }

    public void displayBoard() {
        checkIfGameIsWon();
        ticTacToeBoard.displayBoard();
    }
}