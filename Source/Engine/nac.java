package Engine;

//Class used to select a random number or a random color if needed

import java.awt.*;
import java.util.Random;

public class nac {

    private Random number = new Random();
    private Color[] color = new Color[]
            {Color.RED, Color.GREEN, Color.PINK, Color.MAGENTA, Color.BLUE,
                    Color.CYAN, Color.GRAY, Color.WHITE, Color.YELLOW, Color.ORANGE};

    public int getHits() {
        return number.nextInt(10) + 1;
    }

    public Color getColor() {
        return color[(new Random()).nextInt(color.length)];
    }

}
