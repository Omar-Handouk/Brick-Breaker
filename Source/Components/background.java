package Components;

import java.awt.*;

public class background extends Component {

    private Color backgroundColor;
    private Rectangle backGroundRectangle;

    private Color borderColor;
    private Rectangle borderLeft;
    private Rectangle borderRight;
    private Rectangle borderUp;
    private Rectangle borderDown;

    public background(Graphics2D draw, Color backgroundColor, Rectangle backGroundRectangle, Color borderColor, Rectangle borderLeft, Rectangle borderRight, Rectangle borderUp, Rectangle borderDown) {
        super(draw);
        this.backgroundColor = backgroundColor;
        this.backGroundRectangle = backGroundRectangle;
        this.borderColor = borderColor;
        this.borderLeft = borderLeft;
        this.borderRight = borderRight;
        this.borderUp = borderUp;
        this.borderDown = borderDown;
    }

    @Override
    public void draw() {
        draw.setColor(backgroundColor);
        draw.fill(backGroundRectangle);

        draw.setColor(borderColor);
        draw.fill(borderLeft);
        draw.fill(borderRight);
        draw.fill(borderUp);
        draw.fill(borderDown);
    }
}
