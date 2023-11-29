import java.util.ArrayList;
import java.util.Arrays;

public class Move {
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
}
