import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Piece implements Cloneable{
    static BufferedImage b;
    static BufferedImage bQ = null;
    static BufferedImage w = null;
    static BufferedImage wQ = null;
    static {
        try {
            b = ImageIO.read(new File("src/img/piece_b.png"));
            bQ = ImageIO.read(new File("src/img/piece_bQ.png"));
            w = ImageIO.read(new File("src/img/piece_w.png"));
            wQ = ImageIO.read(new File("src/img/piece_wQ.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
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

    public void paint(Graphics2D g){
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage image;
        if (queen) {
            image = color? wQ: bQ;
            g2d.drawImage(image, column * size, line * size, null);
        } else {
            image = color? w: b;
            g2d.drawImage(image, column * size, line * size, null);
        }
    }
    @Override
    public boolean equals(Object object) {
        Piece piece = (Piece) object;
        return queen == piece.queen && color == piece.color && empty == piece.empty && column == piece.column && line == piece.line;
    }
}
