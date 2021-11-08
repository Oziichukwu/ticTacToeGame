package ticTacToeGame;

import java.util.Scanner;

public class TicTacToeApp {
    public static void main(String[] args) {

        Board ticTakToeBoard = new Board(3);
        TicTacToe myTikky = new TicTacToe(ticTakToeBoard);

        Scanner input = new Scanner(System.in);

        boolean gameOver = false;

        while (!gameOver){

            try {
                myTikky.displayBoard();
                System.out.println("Enter a position to play fron 1-9");
                int position = input.nextInt();
                myTikky.markBoard(position);

            } catch (GameOverException e) {
                if ( myTikky.checkIfGameIsWon() && myTikky.lastPlayerToPlayWasX){
                    System.out.println("Player X WINS " + myTikky.checkIfGameIsWon());
                    myTikky.displayBoard();
                    System.exit(0);
                }else{
                    System.out.println("Player O WINS " + myTikky.checkIfGameIsWon());
                    System.exit(0);
                }
                    gameOver = true;
            }

        }


    }
}
