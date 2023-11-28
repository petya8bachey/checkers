import java.util.ArrayList;

public class Board {
    Piece[][] board = new Piece[8][8];

    Board() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
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
                    board[i][j] = piece;
                }
            }
        }
    }

    void setMove(Move move) {
        if (move.oldPiece.color && move.newPiece.line == 0) {
            move.oldPiece.queen = true;
        }
        if (!move.oldPiece.color && move.newPiece.line == 7) {
            move.oldPiece.queen = true;
        }
        move.newPiece.color = move.oldPiece.color;
        move.newPiece.queen = move.oldPiece.queen;
        move.newPiece.empty = false;
        move.oldPiece.empty = true;
        for (Piece piece : move.pieces) {
            piece.empty = true;
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    builder.append(board[i][j].toString());
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
        int line = piece.line;
        int column = piece.column;
        boolean need = false;
        if (!piece.empty) {
            if (!piece.queen) {
                if (line - 2 >= 0 && column - 2 >= 0 && board[line - 2][column - 2].empty && board[line - 1][column - 1].color != piece.color && !board[line - 1][column - 1].empty) {
                    Move move = new Move();
                    move.oldPiece = piece;
                    move.newPiece = board[line - 2][column - 2];
                    move.pieces.add(board[line - 1][column - 1]);
                    moveArrayList.add(move);
                    need = true;
                }
                if (line + 2 < 8 && column - 2 >= 0 && board[line + 2][column - 2].empty && board[line + 1][column - 1].color != piece.color && !board[line + 1][column - 1].empty) {
                    Move move = new Move();
                    move.oldPiece = piece;
                    move.newPiece = board[line + 2][column - 2];
                    move.pieces.add(board[line + 1][column - 1]);
                    moveArrayList.add(move);
                    need = true;
                }
                if (line + 2 < 8 && column + 2 < 8 && board[line + 2][column + 2].empty && board[line + 1][column + 1].color != piece.color && !board[line + 1][column + 1].empty) {
                    Move move = new Move();
                    move.oldPiece = piece;
                    move.newPiece = board[line + 2][column + 2];
                    move.pieces.add(board[line + 1][column + 1]);
                    moveArrayList.add(move);
                    need = true;
                }
                if (line - 2 >= 0 && column + 2 < 8 && board[line - 2][column + 2].empty && board[line - 1][column + 1].color != piece.color && !board[line - 1][column + 1].empty) {
                    Move move = new Move();
                    move.oldPiece = piece;
                    move.newPiece = board[line - 2][column + 2];
                    move.pieces.add(board[line - 1][column + 1]);
                    moveArrayList.add(move);
                    need = true;
                }
                if (!need) {
                    if (piece.color) {
                        if (line - 1 >= 0 && column - 1 >= 0 && board[line - 1][column - 1].empty) {
                            Move move = new Move();
                            move.oldPiece = piece;
                            move.newPiece = board[line - 1][column - 1];
                            moveArrayList.add(move);
                        }
                        if (line - 1 >= 0 && column + 1 < 8 && board[line - 1][column + 1].empty) {
                            Move move = new Move();
                            move.oldPiece = piece;
                            move.newPiece = board[line - 1][column + 1];
                            moveArrayList.add(move);
                        }
                    } else {
                        if (line + 1 >= 0 && column + 1 >= 0 && board[line + 1][column + 1].empty) {
                            Move move = new Move();
                            move.oldPiece = piece;
                            move.newPiece = board[line + 1][column + 1];
                            moveArrayList.add(move);
                        }
                        if (line + 1 >= 0 && column - 1 < 8 && board[line + 1][column - 1].empty) {
                            Move move = new Move();
                            move.oldPiece = piece;
                            move.newPiece = board[line + 1][column - 1];
                            moveArrayList.add(move);
                        }
                    }
                }
            } else {
                int lLine = line;
                int lColumn = column;
                while (lLine >= 1 && lColumn >= 1) {
                    lLine--;
                    lColumn--;
                    if (board[lLine][lColumn].color != piece.color && board[lLine - 1][lColumn - 1].empty) {
                        Piece eat = board[lLine][lColumn];
                        while (lLine >= 1 && lColumn >= 1 && board[lLine - 1][lColumn - 1].empty) {
                            Move move = new Move();
                            move.oldPiece = piece;
                            move.newPiece = board[lLine - 1][lColumn - 1];
                            move.pieces.add(eat);
                            moveArrayList.add(move);
                            lLine--;
                            lColumn--;
                        }
                        need = true;
                        break;
                    }
                }
                lLine = line;
                lColumn = column;
                while (lLine <= 6 && lColumn >= 1) {
                    lLine++;
                    lColumn--;
                    if (board[lLine][lColumn].color != piece.color && board[lLine + 1][lColumn - 1].empty) {
                        Piece eat = board[lLine][lColumn];
                        while (lLine <= 6 && lColumn >= 1 && board[lLine + 1][lColumn - 1].empty) {
                            Move move = new Move();
                            move.oldPiece = piece;
                            move.newPiece = board[lLine + 1][lColumn - 1];
                            move.pieces.add(eat);
                            moveArrayList.add(move);
                            lLine++;
                            lColumn--;
                        }
                        need = true;
                        break;
                    }
                }
                lLine = line;
                lColumn = column;
                while (lLine <= 6 && lColumn <= 6) {
                    lLine++;
                    lColumn++;
                    if (board[lLine][lColumn].color != piece.color && board[lLine + 1][lColumn + 1].empty) {
                        Piece eat = board[lLine][lColumn];
                        while (lLine <= 6 && lColumn <= 6 && board[lLine + 1][lColumn + 1].empty) {
                            Move move = new Move();
                            move.oldPiece = piece;
                            move.newPiece = board[lLine + 1][lColumn + 1];
                            move.pieces.add(eat);
                            moveArrayList.add(move);
                            lLine++;
                            lColumn++;
                        }
                        need = true;
                        break;
                    }
                }
                lLine = line;
                lColumn = column;
                while (lLine >= 1 && lColumn <= 6) {
                    lLine--;
                    lColumn++;
                    if (board[lLine][lColumn].color != piece.color && board[lLine - 1][lColumn + 1].empty) {
                        Piece eat = board[lLine][lColumn];
                        while (lLine >= 1 && lColumn <= 6 && board[lLine - 1][lColumn + 1].empty) {
                            Move move = new Move();
                            move.oldPiece = piece;
                            move.newPiece = board[lLine - 1][lColumn + 1];
                            move.pieces.add(eat);
                            moveArrayList.add(move);
                            lLine--;
                            lColumn++;
                        }
                        need = true;
                        break;
                    }
                }
                if (!need) {
                    lLine = line;
                    lColumn = column;
                    while (lLine >= 1 && lColumn >= 1 && board[lLine - 1][lColumn - 1].empty) {
                        Move move = new Move();
                        move.oldPiece = piece;
                        move.newPiece = board[lLine - 1][lColumn - 1];
                        moveArrayList.add(move);
                        lLine--;
                        lColumn--;
                    }
                    lLine = line;
                    lColumn = column;
                    while (lLine <= 6 && lColumn >= 1 && board[lLine + 1][lColumn - 1].empty) {
                        Move move = new Move();
                        move.oldPiece = piece;
                        move.newPiece = board[lLine + 1][lColumn - 1];
                        moveArrayList.add(move);
                        lLine++;
                        lColumn--;
                    }
                    lLine = line;
                    lColumn = column;
                    while (lLine <= 6 && lColumn <= 6 && board[lLine + 1][lColumn + 1].empty) {
                        Move move = new Move();
                        move.oldPiece = piece;
                        move.newPiece = board[lLine + 1][lColumn + 1];
                        moveArrayList.add(move);
                        lLine++;
                        lColumn++;
                    }
                    lLine = line;
                    lColumn = column;
                    while (lLine >= 1 && lColumn <= 6 && board[lLine - 1][lColumn + 1].empty) {
                        Move move = new Move();
                        move.oldPiece = piece;
                        move.newPiece = board[lLine - 1][lColumn + 1];
                        moveArrayList.add(move);
                        lLine--;
                        lColumn++;
                    }
                }
            }
        }
        return moveArrayList;
    }
}
