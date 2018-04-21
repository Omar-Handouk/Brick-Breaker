package Engine;

import Components.brick;

import java.awt.*;

public class mapGen {

    private brick[][] map;

    private int row;
    private int col;

    private int brickWidth;
    private int brickHeight;

    public mapGen(int row, int col) {

        this.row = row;
        this.col = col;

        map = new brick[row][col];

        brickWidth = 540 / col;
        brickHeight = 150 / row;
    }

    public void draw(Graphics2D draw) {
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (map[i][j] == null || !map[i][j].isHit()) {
                    int x = j * brickWidth + 80;
                    int y = i * brickHeight + 50;

                    map[i][j] = new brick(draw, new Point(x, y), new Rectangle(x, y, brickWidth, brickHeight), brickWidth, brickHeight);

                    map[i][j].draw();
                }
            }
        }
    }

    public brick[][] getMap() {
        return map;
    }

    public int getBrickWidth() {
        return brickWidth;
    }

    public int getBrickHeight() {
        return brickHeight;
    }
}
