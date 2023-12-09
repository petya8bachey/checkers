import java.awt.*;

public class Piece implements Cloneable{
    boolean queen = false;
    boolean color;
    boolean empty;
    int line;
    int column;
    int size = 85;
    int xPos;
    int yPos;
    int r = 70;
    int s = 20;
    public String toString() {
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

    public void paint(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(!color ? new Color(134, 20, 20):new Color(50, 85, 192));
        g2d.fillRect(column * size,line * size,r,r);
        if (queen) {
            g2d.setColor(color ? new Color(134, 20, 20):new Color(50, 85, 192));
            g2d.fillRect(column * size,line * size,s,s);
        }
    }
    @Override
    public boolean equals(Object object) {
        Piece piece = (Piece) object;
        return queen == piece.queen && color == piece.color && empty == piece.empty && column == piece.column && line == piece.line;
    }
}
