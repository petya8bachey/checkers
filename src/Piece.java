public class Piece implements Cloneable{
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
    public String toStringFull() {
        String result = "";
        if (empty) {
            result += "0";
        } else if (color) {
            result += "w";
        } else {
            result +=  "b";
        }
        return result + " " + line + " " + column;
    }
    Piece(int line, int column) {
        this.line = line;
        this.column = column;
    }

    @Override
    public Piece clone() {
        Piece piece = new Piece(line, column);
        piece.queen = queen;
        piece.color = color;
        piece.empty = empty;
        return piece;
    }
}
