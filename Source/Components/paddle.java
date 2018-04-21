package Components;

import java.awt.*;

public class paddle extends hitObject {

    private Paint paint;
    private Rectangle paddle;

    public paddle(Graphics2D draw, Paint paint, Rectangle paddle) {
        super(draw, paddle);
        this.paint = paint;
        this.paddle = paddle;
    }

    @Override
    public void draw() {
        draw.setPaint(paint);
        draw.fill(paddle);
    }

    public Rectangle getPaddle() {
        return paddle;
    }
}
