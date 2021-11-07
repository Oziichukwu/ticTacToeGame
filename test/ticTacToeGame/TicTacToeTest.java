package ticTacToeGame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TicTacToeTest {
    TicTacToe myTikky;
    Board ticTacToeBoard;

    @BeforeEach
    void setUp(){
        ticTacToeBoard = new Board(3);
        myTikky = new TicTacToe(ticTacToeBoard);
    }
    @AfterEach
    void tearDown(){
        ticTacToeBoard = null;
        myTikky = null;
    }
    @Test
    public void testThatTicTacToeHasABoard(){

        assertNotNull(myTikky.getBoard());
    }

    @Test
    public void testThatTicTacToeBoardHasGridWhenInitialized(){
        Board ticTacToeBoard = new Board(3);
        assertNotNull(ticTacToeBoard.getGrid());
    }

    @Test
    public void testThatTicTacToeBoardGridDoesNotExceedTheValuePassedIntoConstructor(){

        assertEquals(3, ticTacToeBoard.getGrid().length);
        assertEquals(3, ticTacToeBoard.getGrid()[0].length);
    }

    @Test
    public void ticTacToeBoardIsEmptyByDefault(){

        Board ticTacToeBoard = myTikky.getBoard();
        TicTacToeValue[][]ticTacToeGrid = ticTacToeBoard.getGrid();

        for (TicTacToeValue[] ticTacToeValues : ticTacToeGrid)
            for(TicTacToeValue ticTacToeValue : ticTacToeValues){
                assertEquals(TicTacToeValue.Empty, ticTacToeValue);
            }
    }

    @Test
    public void ticTacToePlayerCanMarkAParticularGridOnTheBoard() throws GameOverException {

        myTikky.markBoard(3);
        assertEquals(TicTacToeValue.X, myTikky.getBoard().getGrid()[0][2]);

        myTikky.markBoard(5);
        assertEquals(TicTacToeValue.O, myTikky.getBoard().getGrid()[1][1]);
    }

    @Test
    public void playerCanOnlyPlayOnAnEmptyGridTest() throws GameOverException{
        myTikky.markBoard(3);
        assertEquals(TicTacToeValue.X, myTikky.getBoard().getGrid()[0][2]);
        assertEquals(-1, myTikky.markBoard(3));

        myTikky.markBoard(5);
        assertEquals(TicTacToeValue.O, myTikky.getBoard().getGrid()[1][1]);
        assertEquals(-1, myTikky.markBoard(5));
    }

    @Test
    public void playerCannotPlayTwoConsecutiveXCharacters() throws GameOverException{
        myTikky.markBoard(3);
        assertEquals(TicTacToeValue.X, myTikky.getBoard().getGrid()[0][2]);

        myTikky.markBoard(5);
        assertNotEquals(TicTacToeValue.X, myTikky.getBoard().getGrid()[1][1]);

        myTikky.markBoard(1);
        assertEquals(TicTacToeValue.X, myTikky.getBoard().getGrid()[0][0]);

        myTikky.markBoard(2);
        assertEquals(TicTacToeValue.O,myTikky.getBoard().getGrid()[0][1]);
    }

    @Test
    public void gamePlayerCanOnlyPlayOnAnEmptySquare() throws GameOverException{
        assertEquals(1, myTikky.markBoard(3));
        assertEquals(-1, myTikky.markBoard(3));

        assertEquals(1, myTikky.markBoard(6));
        assertEquals(-1, myTikky.markBoard(6));
    }

    @Test
    void gameThrowsIllegalArgumentExceptionWhenUserPassesInOutOfRangeGridPosition(){

        assertThrows(IllegalArgumentException.class, ()-> myTikky.markBoard(17));
        assertThrows(IllegalArgumentException.class, () -> myTikky.markBoard(11));
    }
    @Test
    void gameThrowsGameOverExceptionWhenAllPositionsOnTheGridAreFullTest() throws GameOverException{
        for (int i = 1; i <= 9; i++) {
            myTikky.markBoard(i);
        }
        assertThrows(GameOverException.class, ()->myTikky.markBoard(9));
    }

    @Test

    void gameResetsBoardWhenGameIsWonTest() throws GameOverException {

        Board oldTicTacToeBoard = myTikky.getBoard();
        myTikky.markBoard(1);
        myTikky.markBoard(4);
        myTikky.markBoard(3);
        myTikky.markBoard(5);
        myTikky.markBoard(8);
        myTikky.markBoard(6);

        assertTrue(myTikky.checkIfGameIsWon());

        myTikky.resetGame();

        assertFalse(myTikky.checkIfGameIsWon());

        assertNotEquals(oldTicTacToeBoard, myTikky.getBoard());

        assertEquals(oldTicTacToeBoard.getGrid().length, myTikky.getBoard().getGrid().length);

    }

    @Test
    void gameWonIfPlayerMakesHorizontalLineXMovesOnRow1() throws GameOverException{

        myTikky.markBoard(1);
        myTikky.markBoard(4);
        myTikky.markBoard(2);
        myTikky.markBoard(5);
        myTikky.markBoard(3);

        assertTrue(myTikky.checkIfGameIsWon());

        myTikky.resetGame();

        assertFalse(myTikky.checkIfGameIsWon());

    }

    @Test
    void gameWonIfPlayerMakesHorizontalLineXMovesOnRow2() throws GameOverException{

        myTikky.resetGame();
        assertFalse(myTikky.checkIfGameIsWon());
        myTikky.markBoard(4);
        myTikky.markBoard(2);
        myTikky.markBoard(5);
        myTikky.markBoard(3);
        myTikky.markBoard(6);

        assertTrue(myTikky.checkIfGameIsWon());
    }

    @Test
    void gameWonIfPlayerMakesHorizontalLineXMovesOnRow3() throws GameOverException{
        myTikky.resetGame();
        assertFalse(myTikky.checkIfGameIsWon());

        myTikky.markBoard(7);
        myTikky.markBoard(4);
        myTikky.markBoard(8);
        myTikky.markBoard(5);
        myTikky.markBoard(9);

        assertTrue(myTikky.checkIfGameIsWon());
    }

    @Test
    void gameWonIfPlayerMakesVerticalLineXMovesOnColumn1() throws GameOverException{

        myTikky.markBoard(1);
        myTikky.markBoard(5);
        myTikky.markBoard(4);
        myTikky.markBoard(9);
        myTikky.markBoard(7);

        assertTrue(myTikky.checkIfGameIsWon());
    }

    @Test
    void gameWonIfPlayerMakesVerticalLineXMovesOnColumn2() throws GameOverException{

        myTikky.resetGame();
        assertFalse(myTikky.checkIfGameIsWon());
        myTikky.markBoard(2);
        myTikky.markBoard(3);
        myTikky.markBoard(5);
        myTikky.markBoard(4);
        myTikky.markBoard(8);

        assertTrue(myTikky.checkIfGameIsWon());
    }
    @Test
    void gameWonIfPlayerMakesVerticalLineXMovesOnColumn3() throws GameOverException{

        myTikky.resetGame();
        assertFalse(myTikky.checkIfGameIsWon());
        myTikky.markBoard(3);
        myTikky.markBoard(1);
        myTikky.markBoard(6);
        myTikky.markBoard(2);
        myTikky.markBoard(9);

        assertTrue(myTikky.checkIfGameIsWon());
    }
    @Test
    void gameWonIfPlayerRightMakesDiagonalLineXMoves() throws GameOverException{

        myTikky.markBoard(7);
        myTikky.markBoard(1);
        myTikky.markBoard(5);
        myTikky.markBoard(2);
        myTikky.markBoard(3);

        assertTrue(myTikky.checkIfGameIsWon());
    }

    @Test
    void gameWonIfPlayerMakesLeftDiagonalLineXMoves() throws GameOverException{
        myTikky.resetGame();
        assertFalse(myTikky.checkIfGameIsWon());
        myTikky.markBoard(9);
        myTikky.markBoard(4);
        myTikky.markBoard(5);
        myTikky.markBoard(2);
        myTikky.markBoard(1);

        assertTrue(myTikky.checkIfGameIsWon());
    }

    @Test
    void gameCanDisplayBoardTest() throws GameOverException {
//        myTikky.markBoard(1);
//        myTikky.markBoard(2);
//        myTikky.markBoard(3);
//        myTikky.markBoard(4);
//        myTikky.markBoard(5);
//        myTikky.markBoard(6);
//        myTikky.markBoard(7);
//        myTikky.markBoard(8);
//        myTikky.markBoard(9);
//        myTikky.displayBoard();
        TicTacToeValue [][] ticTacToeGrid = ticTacToeBoard.getGrid();
        for (int i = 1; i <= ticTacToeGrid.length; i++) {
            for (int j = 1; j <= ticTacToeGrid.length; j++) {
            myTikky.markBoard(j);
            }
            myTikky.displayBoard();
            System.out.println();
        }
    }

}


