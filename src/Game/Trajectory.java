package Game;

import java.awt.Color;
import java.awt.Graphics;

public class Trajectory {
    public int x1,x2;
    public int y1,y2;
    private int frames;

    public Trajectory(int x, int y) {
        this.x1=x;
        this.y1=y;
        frames=0;
    }

    public void paintTra(Graphics g) {
        frames++;
        if(frames<=5){
            g.setColor(Color.white);
            g.drawLine(x1, y1, x2, y2);
        }
    }
}
