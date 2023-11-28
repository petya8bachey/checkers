import java.util.ArrayList;
import java.util.Arrays;

public class Move {
    Piece oldPiece;
    Piece newPiece;
    ArrayList<Piece> pieces = new ArrayList<>();
    public String toString() {
        return oldPiece.toString() + " " +
                newPiece.toString() +  " " +
                Arrays.deepToString(new ArrayList[]{pieces});
    }

    public int checkMove() {
        return pieces.toArray().length;
    }
}
