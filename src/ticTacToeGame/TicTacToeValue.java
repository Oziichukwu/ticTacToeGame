package ticTacToeGame;

public enum TicTacToeValue {
    Empty,
    X,
    O;

@Override
public String toString(){
    String value = "";
    switch(this){
        case O -> {
            value = "O";
        }
        case X -> {
            value = "X";
        }
        case Empty -> {
            value = " ";
        }
    }
    return value;
}

}