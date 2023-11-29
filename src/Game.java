import java.util.ArrayList;

public class Game implements Cloneable{
    Player playerWhite = new Player();
    Player playerBlack = new Player();
    Player current = playerWhite;
    Board board = new Board();
    public static int deeper = 4;

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
            int local = move.checkMove();
            if (max < local) {
                max = local;
                moves.clear();
                moves.add(move);
            } else if (max == local) {
                moves.add(move);
            }
        }
        return moves;
    }
    public void makeMove(Move move) {
        board.setMove(move);
        current = nextPlayer();
    }
    public Player nextPlayer() {
        return current == playerWhite? playerBlack : playerWhite;
    }

    public Move bestMove(Game game) {
        Move bestMove = null;
        if (game.current.color) {
            int best = Integer.MIN_VALUE;
            for (Move move: game.canMove()) {
                game.makeMove(move);
                int mm = minMax(game.clone(), deeper);
                if (mm > best) {
                    best = mm;
                    bestMove = move;
                }
            }
        } else {
            int best = Integer.MAX_VALUE;
            for (Move move: game.canMove()) {
                game.makeMove(move);
                int mm = minMax(game.clone(), deeper);
                if (mm < best) {
                    best = mm;
                    bestMove = move;
                }
            }
        }
        return bestMove;
    }

    public int minMax(Game game, int depth) {
        if(game.board.win(true)) {
            return Integer.MAX_VALUE;
        } else if (game.board.win(false)) {
            return Integer.MIN_VALUE;
        } if (depth == 0) {
            return game.board.checkPos();
        }
        int result = Integer.MIN_VALUE;
        for (Move move : game.canMove()) {
            game.makeMove(move);
            if (game.current.color) {
                result = Math.max(minMax(game.clone(), depth - 1), result);
            } else {
                result = Math.min(minMax(game.clone(), depth - 1), result);
            }
        }
        return result;
    }
    public String toString() {
        return board.toString() + current.toString() + " " + board.checkPos();
    }

    @Override
    public Game clone() {
        Game game = new Game();
        game.playerWhite = playerWhite;
        game.playerBlack = playerBlack;
        game.current = current;
        game.board = board.clone();
        return game;
    }
}
