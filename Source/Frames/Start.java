package Frames;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Start extends JFrame {

    private Container container;

    private drawBackground background;

    public Start() {
        container = this.getContentPane();
        background = new drawBackground(this);

        this.initUI();
    }

    public void initUI() {
        this.setTitle("Breakout");
        this.setBounds(0, 0, 800, 490);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        background.setBounds(0, 0, 785, 455);

        container.add(background);

        this.repaint();
        this.revalidate();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Start game = new Start();
            game.setVisible(true);
        });
    }
}

