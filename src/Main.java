import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        try {
            Board board = new Board();
            System.out.println(board);
            ArrayList<Move> arrayList = board.getMove(board.board[2][2]);
            System.out.println(arrayList);
        } catch (Exception e) {}
    }
}