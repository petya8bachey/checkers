import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Game game = new Game();
        System.out.println(game);
        for (int i = 0; i < 14; i++) {
            game.makeMove(game.clone().bestMove(game.clone()));
            System.out.println(game);
        }
    }
}