package Components;

import java.awt.*;

public abstract class hitObject extends Component {

    protected Rectangle hitbox;

    public hitObject(Graphics2D draw, Rectangle hitbox) {
        super(draw);
        this.hitbox = hitbox;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }
}
