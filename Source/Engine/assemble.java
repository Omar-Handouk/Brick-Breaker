package Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

import Components.*;

public class assemble extends JPanel implements KeyListener, ActionListener {

    private boolean gameOver = false;
    private boolean play = false;

    private int mapRow = 3;
    private int mapCol = 7;

    private int score = 0;
    private int bricks = mapRow * mapCol;
    private int lives = 3;

    private Timer timer;
    private int delay = 5;

    private int playerX = 285;

    private double ballPosX = 325;
    private double ballPosY = 500;
    private double ballVelX = -0.6 * Math.PI;
    private double ballVelY = -0.34 * Math.PI;

    private paddle paddle;
    private ball ball;
    private mapGen mapGen;

    public assemble() {

        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);

        mapGen = new mapGen(mapRow, mapCol);

        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;

        RenderingHints render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        render.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        graphics2D.setRenderingHints(render);

        //BackGround
        new background(graphics2D,
                Color.BLACK, new Rectangle(0, 0, 700, 600),
                Color.WHITE, new Rectangle(0, 0, 5, 600),
                new Rectangle(679, 0, 5, 600),
                new Rectangle(0, 0, 700, 5),
                new Rectangle(0, 556, 700, 5)).draw();

        //Paddle
        this.paddle = new paddle(graphics2D,
                new GradientPaint(playerX, 500, new Color(17, 30, 108), playerX + 100, 500, new Color(249, 166, 2))
                , new Rectangle(playerX, 535, 100, 10));

        //Ball
        this.ball = new ball(graphics2D, Color.WHITE, new Ellipse2D.Double(ballPosX, ballPosY, 20, 20));

        //Score, Bricks, Lives
        new text(graphics2D, Color.WHITE, new Font("Monospaced", Font.BOLD, 25), score, "Score: ", new Point(520, 30), true).draw();
        new text(graphics2D, Color.WHITE, new Font("Monospaced", Font.BOLD, 25), lives, "Lives: ", new Point(285, 30), true).draw();
        new text(graphics2D, Color.WHITE, new Font("Monospaced", Font.BOLD, 25), bricks, "Bricks: ", new Point(45, 30), true).draw();

        //map
        mapGen.draw(graphics2D);

        this.checkStatus(graphics2D);

        this.paddle.draw();
        this.ball.draw();

        this.update();
    }

    public void update() {
        this.repaint();
        this.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (play && !gameOver) {
            timer.start();

            if (ball.getHitbox().intersects(paddle.getHitbox()))
                ballVelY = -ballVelY;

            outer:
            for (int i = 0; i < mapRow; ++i) {
                for (int j = 0; j < mapCol; ++j) {
                    brick temp = mapGen.getMap()[i][j];

                    if (!temp.isHit()) {
                        if (ball.getHitbox().intersects(temp.getHitbox())) {
                            mapGen.getMap()[i][j].setHit(true);

                            bricks -= 1;
                            score += 5;

                            if (ballPosX + 19 <= temp.getPoint().x || ballPosX + 1 >= temp.getPoint().x + temp.getWidth())
                                ballVelX = -ballVelX;
                            else
                                ballVelY = -ballVelY;

                            break outer;
                        }
                    }
                }
            }

            ballPosX += ballVelX;
            ballPosY += ballVelY;
            if (ballPosX <= 5 || 665 <= ballPosX)
                ballVelX = -ballVelX;
            if (ballPosY <= 5 || 580 <= ballPosY)
                ballVelY = -ballVelY;
        }

        this.update();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (26 < playerX)
                this.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX + 30 < 590)
                this.moverRight();

        }
        else if (e.getKeyCode() == KeyEvent.VK_F6)
        {
            bricks = 1;
        }
    }

    private void moveLeft() {

        if (!gameOver)
            this.play = true;

        playerX -= 20;
    }

    private void moverRight() {

        if (!gameOver)
            this.play = true;

        playerX += 20;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void checkStatus(Graphics2D graphics2D) {

        if (ballPosY > 570 && lives != 0) {

            play = false;

            playerX = 285;

            ballPosX = 325;
            ballPosY = 500;
            ballVelX = -0.6 * Math.PI;
            ballVelY = -0.34 * Math.PI;

            lives -= 1;
        } else if (ballPosY > 570 && lives == 0 || bricks == 0) {

            timer.stop();

            play = false;

            playerX = 285;

            ballPosX = 325;
            ballPosY = 500;
            ballVelX = -0.6 * Math.PI;
            ballVelY = -0.34 * Math.PI;

            int result = JOptionPane.showOptionDialog(null, "Game over     Score: " + score, "Game Over", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null,new String[]{"CONTINUE", "EXIT"}, "EXIT");
            //JOptionPane.showMessageDialog(null, "Game over     Score: " + score + "\nTo continue press OK to exit Press Cancel", "Game Over", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            gameOver = true;

            score = 0;
            bricks = mapRow * mapCol;
            lives = 3;

            if (result == JOptionPane.OK_OPTION) {
                gameOver = false;
                mapGen = new mapGen(mapRow, mapCol);
                timer.start();
            } else
                System.exit(0);
        }
    }
}
