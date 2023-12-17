import java.util.ArrayList;

public class Board implements Cloneable{
    Piece[][] field = new Piece[8][8];
    public int pieceCof = 20;
    public int godPieceCof = 10;
    public int queenCof = 50;

    Board() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = new Piece(i, j);
                if (i < 3) {
                    piece.color = false;
                    piece.empty = false;
                } else if (i < 5) {
                    piece.empty = true;
                } else {
                    piece.color = true;
                    piece.empty = false;
                }
                field[i][j] = piece;
            }
        }
    }

    public void setMove(Move move) {
        Piece oldPiece = field[move.oldPiece.line][move.oldPiece.column];
        Piece newPiece = field[move.newPiece.line][move.newPiece.column];
        ArrayList<Piece> pieces = move.pieces;
        if (oldPiece.color && newPiece.line == 0) {
            oldPiece.queen = true;
        }
        if (!oldPiece.color && newPiece.line == 7) {
            oldPiece.queen = true;
        }
        newPiece.color = oldPiece.color;
        newPiece.queen = oldPiece.queen;
        newPiece.empty = false;
        oldPiece.empty = true;
        for (Piece piece : pieces) {
            field[piece.line][piece.column].empty = true;
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    builder.append(field[i][j].toString());
                } else {
                    builder.append("-");
                }
                builder.append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public ArrayList<Move> getMove(Piece piece) {
        ArrayList<Move> moveArrayList = new ArrayList<>();
        if (piece == null) {
            return moveArrayList;
        }
        int line = piece.line;
        int column = piece.column;
        boolean need = false;
        if (!piece.empty) {
            if (!piece.queen) {
                if (line - 2 >= 0 && column - 2 >= 0 && field[line - 2][column - 2].empty && field[line - 1][column - 1].color != piece.color && !field[line - 1][column - 1].empty) {
                    Move move = new Move();
                    move.oldPiece = piece;
                    move.newPiece = field[line - 2][column - 2];
                    move.pieces.add(field[line - 1][column - 1]);
                    moveArrayList.add(move);
                    need = true;
                }
                if (line + 2 < 8 && column - 2 >= 0 && field[line + 2][column - 2].empty && field[line + 1][column - 1].color != piece.color && !field[line + 1][column - 1].empty) {
                    Move move = new Move();
                    move.oldPiece = piece;
                    move.newPiece = field[line + 2][column - 2];
                    move.pieces.add(field[line + 1][column - 1]);
                    moveArrayList.add(move);
                    need = true;
                }
                if (line + 2 < 8 && column + 2 < 8 && field[line + 2][column + 2].empty && field[line + 1][column + 1].color != piece.color && !field[line + 1][column + 1].empty) {
                    Move move = new Move();
                    move.oldPiece = piece;
                    move.newPiece = field[line + 2][column + 2];
                    move.pieces.add(field[line + 1][column + 1]);
                    moveArrayList.add(move);
                    need = true;
                }
                if (line - 2 >= 0 && column + 2 < 8 && field[line - 2][column + 2].empty && field[line - 1][column + 1].color != piece.color && !field[line - 1][column + 1].empty) {
                    Move move = new Move();
                    move.oldPiece = piece;
                    move.newPiece = field[line - 2][column + 2];
                    move.pieces.add(field[line - 1][column + 1]);
                    moveArrayList.add(move);
                    need = true;
                }
                if (!need) {
                    if (piece.color) {
                        if (line - 1 >= 0 && column - 1 >= 0 && field[line - 1][column - 1].empty) {
                            Move move = new Move();
                            move.oldPiece = piece;
                            move.newPiece = field[line - 1][column - 1];
                            moveArrayList.add(move);
                        }
                        if (line - 1 >= 0 && column + 1 < 8 && field[line - 1][column + 1].empty) {
                            Move move = new Move();
                            move.oldPiece = piece;
                            move.newPiece = field[line - 1][column + 1];
                            moveArrayList.add(move);
                        }
                    } else {
                        if (line + 1 < 8 && column + 1 < 8 && field[line + 1][column + 1].empty) {
                            Move move = new Move();
                            move.oldPiece = piece;
                            move.newPiece = field[line + 1][column + 1];
                            moveArrayList.add(move);
                        }
                        if (line + 1 < 8 && column - 1 >= 0 && field[line + 1][column - 1].empty) {
                            Move move = new Move();
                            move.oldPiece = piece;
                            move.newPiece = field[line + 1][column - 1];
                            moveArrayList.add(move);
                        }
                    }
                }
            } else {
                int lLine = line;
                int lColumn = column;
                while (lLine >= 1 && lColumn >= 1) {
                    if (field[lLine][lColumn].color != piece.color && field[lLine - 1][lColumn - 1].empty && !field[lLine][lColumn].empty) {
                        Piece eat = field[lLine][lColumn];
                        while (lLine >= 1 && lColumn >= 1 && field[lLine - 1][lColumn - 1].empty) {
                            Move move = new Move();
                            move.oldPiece = piece;
                            move.newPiece = field[lLine - 1][lColumn - 1];
                            move.pieces.add(eat);
                            moveArrayList.add(move);
                            lLine--;
                            lColumn--;
                        }
                        need = true;
                        break;
                    }
                    lLine--;
                    lColumn--;
                }
                lLine = line;
                lColumn = column;
                while (lLine <= 6 && lColumn >= 1) {
                    if (field[lLine][lColumn].color != piece.color && field[lLine + 1][lColumn - 1].empty && !field[lLine][lColumn].empty) {
                        Piece eat = field[lLine][lColumn];
                        while (lLine <= 6 && lColumn >= 1 && field[lLine + 1][lColumn - 1].empty) {
                            Move move = new Move();
                            move.oldPiece = piece;
                            move.newPiece = field[lLine + 1][lColumn - 1];
                            move.pieces.add(eat);
                            moveArrayList.add(move);
                            lLine++;
                            lColumn--;
                        }
                        need = true;
                        break;
                    }
                    lLine++;
                    lColumn--;
                }
                lLine = line;
                lColumn = column;
                while (lLine <= 6 && lColumn <= 6) {
                    if (field[lLine][lColumn].color != piece.color && field[lLine + 1][lColumn + 1].empty && !field[lLine][lColumn].empty) {
                        Piece eat = field[lLine][lColumn];
                        while (lLine <= 6 && lColumn <= 6 && field[lLine + 1][lColumn + 1].empty) {
                            Move move = new Move();
                            move.oldPiece = piece;
                            move.newPiece = field[lLine + 1][lColumn + 1];
                            move.pieces.add(eat);
                            moveArrayList.add(move);
                            lLine++;
                            lColumn++;
                        }
                        need = true;
                        break;
                    }
                    lLine++;
                    lColumn++;
                }
                lLine = line;
                lColumn = column;
                while (lLine >= 1 && lColumn <= 6) {
                    if (field[lLine][lColumn].color != piece.color && field[lLine - 1][lColumn + 1].empty && !field[lLine][lColumn].empty) {
                        Piece eat = field[lLine][lColumn];
                        while (lLine >= 1 && lColumn <= 6 && field[lLine - 1][lColumn + 1].empty) {
                            Move move = new Move();
                            move.oldPiece = piece;
                            move.newPiece = field[lLine - 1][lColumn + 1];
                            move.pieces.add(eat);
                            moveArrayList.add(move);
                            lLine--;
                            lColumn++;
                        }
                        need = true;
                        break;
                    }
                    lLine--;
                    lColumn++;
                }
                if (!need) {
                    lLine = line;
                    lColumn = column;
                    while (lLine >= 1 && lColumn >= 1 && field[lLine - 1][lColumn - 1].empty) {
                        Move move = new Move();
                        move.oldPiece = piece;
                        move.newPiece = field[lLine - 1][lColumn - 1];
                        moveArrayList.add(move);
                        lLine--;
                        lColumn--;
                    }
                    lLine = line;
                    lColumn = column;
                    while (lLine <= 6 && lColumn >= 1 && field[lLine + 1][lColumn - 1].empty) {
                        Move move = new Move();
                        move.oldPiece = piece;
                        move.newPiece = field[lLine + 1][lColumn - 1];
                        moveArrayList.add(move);
                        lLine++;
                        lColumn--;
                    }
                    lLine = line;
                    lColumn = column;
                    while (lLine <= 6 && lColumn <= 6 && field[lLine + 1][lColumn + 1].empty) {
                        Move move = new Move();
                        move.oldPiece = piece;
                        move.newPiece = field[lLine + 1][lColumn + 1];
                        moveArrayList.add(move);
                        lLine++;
                        lColumn++;
                    }
                    lLine = line;
                    lColumn = column;
                    while (lLine >= 1 && lColumn <= 6 && field[lLine - 1][lColumn + 1].empty) {
                        Move move = new Move();
                        move.oldPiece = piece;
                        move.newPiece = field[lLine - 1][lColumn + 1];
                        moveArrayList.add(move);
                        lLine--;
                        lColumn++;
                    }
                }
            }
        }
        if(need && !moveArrayList.isEmpty()) {
            ArrayList<Move> result = new ArrayList<>();
            ArrayList<Move> moveArrayList1 = new ArrayList<>();
            for (Move move: moveArrayList) {
                Board board = this.clone();
                board.setMove(move);
                moveArrayList1 = board.getMove(board.field[move.newPiece.line][move.newPiece.column]);
                if (!moveArrayList1.isEmpty() && !moveArrayList1.get(0).pieces.isEmpty()) {
                    for (Move move1: moveArrayList1) {
                        Move add = move.clone();
                        add.pieces.addAll(move1.pieces);
                        add.newPiece = board.field[move1.newPiece.line][move1.newPiece.column];
                        result.add(add);
                    }
                }
            }
            if (!result.isEmpty()) {
                return result;
            }
        }
        return moveArrayList;
    }

    public ArrayList<Piece> piecesList(boolean color) {
        ArrayList<Piece> pieceArrayList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0 && field[i][j].color == color && !field[i][j].empty) {
                    pieceArrayList.add(field[i][j]);
                }
            }
        }
        return pieceArrayList;
    }

    public int checkPos() {
        int white = 0;
        int black = 0;
        ArrayList<Piece> whiteList  = piecesList(true);
        ArrayList<Piece> blackList = piecesList(false);
        for (Piece piece: whiteList) {
            if (piece.queen) {
                white += queenCof;
            } else {
                white += pieceCof;
            }
            if (piece.column == 0 || piece.column == 7) {
                white += godPieceCof;
            }
        }
        for (Piece piece: blackList) {
            if (piece.queen) {
                black += queenCof;
            } else {
                black += pieceCof;
            }
            if (piece.column == 0 || piece.column == 7) {
                black += godPieceCof;
            }
        }
        return black - white;
    }

    public boolean win(boolean color) {
        ArrayList<Move> cmoves = new ArrayList<>();
        int max = 0;
        ArrayList<Piece> pieces = piecesList(color);
        ArrayList<Move> moves = new ArrayList<>();
        for (Piece piece : pieces) {
            moves.addAll(getMove(piece));
        }
        for (Move move : moves) {
            int local = move.checkMove();
            if (max < local) {
                max = local;
                cmoves.clear();
                cmoves.add(move);
            } else if (max == local) {
                cmoves.add(move);
            }
        }

        return cmoves.isEmpty();
    }

    @Override
    public Board clone() {
        Board result = new Board();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    result.field[i][j] = field[i][j].clone();
                }
            }
        }
        return result;
    }
}
