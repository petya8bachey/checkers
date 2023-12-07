import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Input extends MouseAdapter {

    Game game;

    public Input(Game game) {
        this.game = game;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int col = e.getX() / game.titleSize;
        int row = e.getY() / game.titleSize;
        Piece pieceXY = game.board.field[row][col];
        if (pieceXY != null) {
            game.selectedPiece = pieceXY;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        int col = e.getX() / game.titleSize;
        int row = e.getY() / game.titleSize;

        if (game.selectedPiece != null) {
            Move move = new Move();
            move.oldPiece = game.selectedPiece;
            move.newPiece = game.board.field[row][col];
            move = game.isValidMove(move, game);
            if (move != null) {
                game.makeMove(move);
                game.repaint();
                System.out.println("comp");
                game.makeMove(game.bestMove(game.clone()));
                game.selectedPiece = null;
                game.repaint();
            } else {
                game.selectedPiece.xPos = game.selectedPiece.column * game.titleSize;
                game.selectedPiece.yPos = game.selectedPiece.line * game.titleSize;
            }
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (game.selectedPiece != null) {
            game.selectedPiece.xPos = e.getX() - game.titleSize / 2;
            game.selectedPiece.yPos = e.getY() - game.titleSize / 2;

            game.repaint();
        }

    }
}