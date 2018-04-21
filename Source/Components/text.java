package Components;

import java.awt.*;

public class text extends Component {

    private Color textColor;
    private Font font;

    private int value;
    private String string;

    private Point point;

    boolean hasValue;

    public text(Graphics2D draw, Color textColor, Font font, int value, String string, Point point, boolean hasValue) {
        super(draw);
        this.textColor = textColor;
        this.font = font;
        this.value = value;
        this.string = string;
        this.point = point;
        this.hasValue = hasValue;
    }

    @Override
    public void draw() {
        draw.setColor(textColor);
        draw.setFont(font);

        if (hasValue)
            draw.drawString(string + value, point.x, point.y);
        else
            draw.drawString(string, point.x, point.y);
    }
}
