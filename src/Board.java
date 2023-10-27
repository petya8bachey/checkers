import java.util.Arrays;

public class Board {
    public int queenCoefficient = 3;
    public Field[] board = new Field[32];

    public Board() {
        int i = 0;
        for (int j = 0; j < 32; j++) {
            board[j] = new Field();
        }
        for (; i < 12; i++) {
            board[i].condition = FieldCondition.BLACK;
        }
        for (; i < 20; i++) {
            board[i].condition = FieldCondition.EMPTY;
        }
        for (; i < 32; i++) {
            board[i].condition = FieldCondition.WHITE;
        }
    }

    public int checkPos(FieldCondition color) {
        int black = 0;
        int white = 0;
        for (int i = 0; i < 32; i++) {
            if (board[i].condition != FieldCondition.EMPTY) {
                if (board[i].condition == FieldCondition.WHITE) {
                    if (board[i].idQueen) {
                        white += queenCoefficient;
                    } else {
                        white++;
                    }
                }
                if (board[i].condition == FieldCondition.BLACK) {
                    if (board[i].idQueen) {
                        black += queenCoefficient;
                    } else {
                        black++;
                    }
                }
            }
        }
        return color == FieldCondition.WHITE? white - black: black - white;
    }
    public String toString() {
        return Arrays.deepToString(board);
    }
}
