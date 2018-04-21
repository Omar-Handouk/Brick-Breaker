package Prototype;

import java.awt.*;
import java.util.Arrays;

public class MapGenerator {

    public boolean[][] map;
    public int brickWidth;
    public int brickHeight;

    public int row;
    public int col;

    public MapGenerator(int row, int col) {
        this.row = row;
        this.col = col;

        map = new boolean[row][col];

        for (boolean[] r : map)
            Arrays.fill(r, true);

        for(boolean[] r : map)
            System.err.println(Arrays.toString(r));

        brickWidth = 540 / col;
        brickHeight = 150 / row;
    }
    
    public void draw(Graphics2D g)
    {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j])
                {
                    g.setColor(Color.WHITE);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);

                    g.setColor(Color.BLACK);
                    g.setStroke(new BasicStroke(2));
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int row, int col)
    {
        map[row][col] = false;
    }
}
