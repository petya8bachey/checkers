import java.util.ArrayList;

public class Game {
    Player playerWhite = new Player();
    Player playerBlack = new Player();
    Player current = playerWhite;
    Board board = new Board();

    Game() {
        playerWhite.color = true;
        playerBlack.color = false;
        playerWhite.type = false;
        playerBlack.type = false;
    }

    public ArrayList<Move> allMove() {
        ArrayList<Piece> pieces = board.piecesList(current.color);
        ArrayList<Move> moves = new ArrayList<>();
        for (Piece piece : pieces) {
            moves.addAll(board.getMove(piece));
        }
        return moves;
    }
    public ArrayList<Move> canMove() {
        ArrayList<Move> moves = new ArrayList<>();
        int max = 0;
        for (Move move: allMove()) {
            if (max < move.checkMove()) {
                max = move.checkMove();
                moves.clear();
            } else {
                moves.add(move);
            }
        }
        return moves;
    }
    public Move bestMove() {
        for (Move move: canMove()) {

        }
        return null;
    }

    public int minMax(Move move, int depth) {
        return 0;
    }
    public void makeMove(Move move) {
        board.setMove(move);
        current = nextPlayer();
    }

    public Player nextPlayer() {
        return current == playerWhite? playerWhite : playerBlack;
    }
}
