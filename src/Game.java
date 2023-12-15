import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game extends JPanel implements Cloneable {
    Player playerWhite = new Player();
    Player playerBlack = new Player();
    Piece selectedPiece = null;
    Player current = playerWhite;
    Board board = new Board();
    Input input = new Input(this);
    public int deeper = 3;
    public int titleSize = 85;
    Game() {
        playerWhite.color = true;
        playerBlack.color = false;
        playerWhite.type = true;
        playerBlack.type = false;
        this.setPreferredSize(new Dimension(8*titleSize, 8*titleSize));
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                g2d.setColor((i + j) % 2 != 0 ? new Color(231, 231, 231):new Color(59, 55, 55));
                g2d.fillRect(j * titleSize,i * titleSize,titleSize,titleSize);
            }
        }
        ArrayList<Move> moves = board.getMove(selectedPiece);
        for (Move move : moves) {
            g2d.setColor(new Color(192, 241, 59, 137));
            g2d.fillRect(move.newPiece.column * titleSize, move.newPiece.line * titleSize, titleSize, titleSize);
        }
        ArrayList<Piece> pieces = board.piecesList(true);
        pieces = board.piecesList(true);
        pieces.addAll(board.piecesList(false));
        for(Piece piece : pieces){
            piece.paint(g2d);
        }

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
        repaint();
    }
    public Player nextPlayer() {
        return current == playerWhite ? playerBlack : playerWhite;
    }

    public Move bestMove(Game game) {
        Move bestMove = game.canMove().get(0);
        int best = (game.current.color? Integer.MIN_VALUE: Integer.MAX_VALUE);
        if (game.current.color) {
            for (Move move: game.canMove()) {
                game.makeMove(move);
                int mm = minMax(game.clone(), deeper);
                if (mm > best) {
                    best = mm;
                    bestMove = move;
                }
            }
        } else {
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
        int result = (game.current.color? Integer.MIN_VALUE : Integer.MAX_VALUE);
        for (Move move : game.canMove()) {
            game.makeMove(move);
            if (game.current.color) {
                result = Math.max(game.minMax(game, depth - 1), result);
            } else {
                result = Math.min(game.minMax(game, depth - 1), result);
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

    public Move isValidMove(Move move, Game game) {
        if (current.color == move.oldPiece.color) {
            ArrayList<Move> moves = game.canMove();
            for (Move local: moves) {
                if (local.equals(move)) {
                    return local;
                }
            }
        }
        return null;
    }
}
