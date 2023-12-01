import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(new Color(30, 28, 28));
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(1000,800));
        frame.setLocationRelativeTo(null);

        Game game = new Game();
        frame.add(game);
        frame.setVisible(true);
        for (int i = 0; i < 14; i++) {
            game.makeMove(game.clone().bestMove(game.clone()));
            sleep(2000);
        }
    }
}