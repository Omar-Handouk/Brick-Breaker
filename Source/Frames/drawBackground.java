package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class drawBackground extends JPanel implements ActionListener {

    private Image background =
            Toolkit.getDefaultToolkit().createImage("Resources/giphy.gif");

    private Image title =
            Toolkit.getDefaultToolkit().createImage("Resources/EDbwq81524297477.png");

    private JButton newGame;
    private JButton closeGame;
    private JButton credits;

    private JPanel buttons;

    private Start main;

    public drawBackground(Start main) {

        this.main = main;

        newGame = new JButton("New Game");
        newGame.addActionListener(this);
        closeGame = new JButton("Close");
        closeGame.addActionListener(this);
        credits = new JButton("Credits");
        credits.addActionListener(this);

        buttons = new JPanel(new GridLayout(1, 3));

        this.setLayout(null);

        buttons.add(newGame);
        buttons.add(closeGame);
        buttons.add(credits);

        buttons.setOpaque(false);
        buttons.setBounds(240, 300, 300, 30);

        this.add(buttons);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (background != null)
            g.drawImage(background, 0, 0, this);

        if (title != null)
            g.drawImage(title, 180, 225, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.closeGame)
            System.exit(0);
        else if (e.getSource() == this.credits)
            JOptionPane.showMessageDialog(null, "Code and Design by Omar Handouk", "Credits", JOptionPane.INFORMATION_MESSAGE);
        else if (e.getSource() == this.newGame) {
            new Game();
            main.setVisible(false);
            main.dispose();
        }
    }
}
