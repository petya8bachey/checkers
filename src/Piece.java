public class Piece {
    boolean queen = false;
    boolean color;
    boolean empty;
    int line;
    int column;
    public String toString() {
        if (empty) {
            return "0";
        } else if (color) {
            return  "w";
        } return "b";
    }
    Piece(int line, int column) {
        this.line = line;
        this.column = column;
    }
}
