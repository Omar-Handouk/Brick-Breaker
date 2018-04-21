package Prototype;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main()
    {
        this.setBounds(new Rectangle(0, 0, 700, 600));
        this.setTitle("Breakout");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        Engine engine = new Engine();

        this.getContentPane().add(engine);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Main game = new Main();
            game.setVisible(true);
        });
    }
}
