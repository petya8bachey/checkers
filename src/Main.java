import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        try {
            Game game = new Game();
            System.out.println(game.board);
            System.out.println(game.canMove());
        } catch (Exception e) {}
    }
}