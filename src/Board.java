public class Board {
    public Field[] board = new Field[32];
    public Board() {
        int i = 0;
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

    public void move(int[] move) throws Exception {
        for (int i = 0; i < move.length; i++) {
            if (
                    (move[i] == move[i + 1]) ||
                    (board[i + 1].condition != FieldCondition.EMPTY) ||
                    (board[i].condition == FieldCondition.EMPTY)){
                throw new Exception("Bad move");
            }
            if (board[i].idQueen) {

            }
        }
    }
}
