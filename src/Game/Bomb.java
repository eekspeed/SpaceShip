package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bomb {
    private BufferedImage image;

    // 位置
    private int x, y;

    // 存活时间
    public int frames = 0;

    public Bomb(BufferedImage image, int x, int y) {
        this.image = image;
        this.x     = x;
        this.y     = y;
    }

    public void paint(Graphics g) {
        g.drawImage(image, x, y, null);
        frames++;
    }

}
