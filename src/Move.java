import java.util.ArrayList;
import java.util.Arrays;

public class Move implements Cloneable{
    Piece oldPiece;
    Piece newPiece;
    ArrayList<Piece> pieces = new ArrayList<>();
    public String toString() {
        return oldPiece.toStringFull() + " " +
                newPiece.toStringFull() +  " " +
                Arrays.deepToString(new ArrayList[]{pieces});
    }

    public int checkMove() {
        return pieces.size();
    }

    @Override
    public boolean equals(Object obj) {
        Move move = (Move) obj;
        return newPiece.equals(move.newPiece) && oldPiece.equals(move.oldPiece);
    }

    @Override
    public Move clone() {
        Move move = new Move();
        move.oldPiece = oldPiece.clone();
        move.newPiece = newPiece.clone();
        move.pieces = (ArrayList<Piece>) pieces.clone();
        return move;
    }
}
