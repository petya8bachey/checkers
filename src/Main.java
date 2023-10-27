public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Board board = new Board();
        System.out.println(board.checkPos(FieldCondition.WHITE));
        System.out.println(board);
    }
}