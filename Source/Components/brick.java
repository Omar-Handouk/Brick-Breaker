package Components;

import java.awt.*;

import Engine.nac;

public class brick extends hitObject {

    private Point point;

    private Rectangle brickRectangle;

    private boolean isHit = false;
    private int width;
    private int height;

    private Paint color;

    public brick(Graphics2D draw, Point point, Rectangle brickRectangle, int width, int height) {
        super(draw, brickRectangle);
        this.point = point;
        this.brickRectangle = brickRectangle;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        draw.setPaint(Color.WHITE);
        draw.fill(brickRectangle);

        draw.setPaint(Color.BLACK);
        draw.setStroke(new BasicStroke(2));
        draw.draw(brickRectangle);
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public Point getPoint() {
        return point;
    }

    public Rectangle getBrickRectangle() {
        return brickRectangle;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
