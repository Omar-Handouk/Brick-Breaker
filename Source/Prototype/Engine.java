package Prototype;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

public class Engine extends JPanel implements KeyListener, ActionListener {

    private boolean initiate = false;

    private int score = 0;
    private int bricks = 21;

    private Timer timer;
    private int timerDelay = 5;

    private int playerX = 285;

    private int ballPosX = 325;
    private int ballPosY = 500;
    private int ballVelX = -1;
    private int ballVelY = -2;

    private MapGenerator mapGenerator;

    public Engine()
    {
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);

        mapGenerator = new MapGenerator(3, 7);

        //Initialize Timer
        timer = new Timer(timerDelay, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;

        RenderingHints render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        render.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        graphics2D.setRenderingHints(render);

        //Background-Done
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 0, 700, 600);

        //Borders-Done
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, 5, 600); //Left
        graphics2D.fillRect(0, 0, 700, 5); //Up
        graphics2D.fillRect(679, 0, 5, 600); //Right
        graphics2D.fillRect(0, 556, 700, 5); //Down

        //Score-Done
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(new Font("SERIF", Font.BOLD, 25));
        graphics2D.drawString("Score: " + score, 590, 30);

        graphics2D.drawString("Test", 400, 30);

        mapGenerator.draw(graphics2D); //Contains bricks-Done

        //graphics2D.drawString("Game Over, Score: " + score, 190, 300);
        if (ballPosY > 570) //Checks
        {
            initiate = false;
            ballPosX = 0;
            ballPosY = 0;

            graphics2D.setColor(Color.RED);
            graphics2D.setFont(new Font("SERIF", Font.BOLD, 30));
            graphics2D.drawString("Game Over, Score: " + score, 190, 300);

            graphics2D.setFont(new Font("Monospaced", Font.BOLD, 20));
            graphics2D.drawString("Press enter to restart", 230, 350);

        }

        //Paddle
        graphics2D.setPaint(new GradientPaint(playerX, 500, new Color(17, 30, 108),playerX + 100, 500, new Color(249,166,2)));
        graphics2D.fillRect(playerX, 535, 100, 10);

        //Ball
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillOval(ballPosX, ballPosY, 20, 20);



        this.repaint();
        //this.revalidate();

        graphics2D.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (initiate)
        {

            timer.start();

            Rectangle2D ballHitbox = new Rectangle2D.Double(ballPosX, ballPosY, 20, 20);
            Rectangle2D paddleHitbox = new Rectangle2D.Double(playerX, 535, 100, 10);

            if (ballHitbox.intersects(paddleHitbox))
                ballVelY = - ballVelY;

            outer : for (int i = 0; i < mapGenerator.row; i++) {
                for (int j = 0; j < mapGenerator.col; j++) {
                    if (mapGenerator.map[i][j])
                    {
                        int x = j * mapGenerator.brickWidth + 80;
                        int y = i * mapGenerator.brickHeight + 50;

                        Rectangle2D brickRect = new Rectangle2D.Double(x, y, mapGenerator.brickWidth, mapGenerator.brickHeight);

                        if (ballHitbox.intersects(brickRect))
                        {
                            mapGenerator.setBrickValue(i, j);

                            bricks--;
                            score += 5;

                            if (ballPosX + 19 <= brickRect.getX() || ballPosX + 1 >= brickRect.getX() + brickRect.getWidth())
                                ballVelX = - ballVelX;
                            else
                                ballVelY = - ballVelY;

                            break outer;
                        }
                    }
                }
            }

            ballPosX += ballVelX;
            ballPosY += ballVelY;
            if (ballPosX <= 5 || 665 <= ballPosX)
                ballVelX = - ballVelX;
            if (ballPosY <= 5 || 580 <= ballPosY)
                ballVelY = - ballVelY;
        }

        this.repaint();
        this.revalidate();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            if (26 < playerX)
                this.moveLeft();
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if (playerX + 30 < 590)
                this.moverRight();

        }
        else if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if (!initiate)
            {
                initiate = true;

                playerX = 285;

                ballPosX = 325;
                ballPosY = 500;
                ballVelX = -1;
                ballVelY = -2;

                score = 0;
                bricks = 21;

                mapGenerator = new MapGenerator(3, 7);

                this.repaint();
                this.revalidate();
            }
        }

        this.repaint();
        this.revalidate();
    }

    private void moveLeft() {

        this.initiate = true;

        playerX -= 20;
    }

    private void moverRight() {

        this.initiate = true;

        playerX += 20;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
