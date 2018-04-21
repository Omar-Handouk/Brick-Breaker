package Frames;

import javax.swing.*;
import java.awt.*;

import Engine.*;

public class Game extends JFrame {

    public Game() {
        this.setBounds(new Rectangle(0, 0, 700, 600));
        this.setTitle("Breakout");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        this.getContentPane().add(new assemble());

        this.repaint();
        this.revalidate();
    }
}
