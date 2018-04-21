package Components;

import java.awt.*;

public abstract class Component {

    protected Graphics2D draw;

    public Component(Graphics2D draw) {
        this.draw = draw;
    }

    public abstract void draw();

}
