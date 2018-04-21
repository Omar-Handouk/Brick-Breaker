package Components;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class ball extends hitObject {

    private Paint paint;

    private Ellipse2D ball;

    public ball(Graphics2D draw, Paint paint, Ellipse2D ball) {
        super(draw, new Rectangle((int) ball.getX(), (int) ball.getY(), (int) ball.getWidth(), (int) ball.getHeight()));
        this.paint = paint;
        this.ball = ball;
    }

    @Override
    public void draw() {
        draw.setPaint(paint);
        draw.fill(ball);
    }

    public Ellipse2D getBall() {
        return ball;
    }
}
