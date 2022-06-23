package Game;

import java.awt.Graphics;


public class Bomb {
    public static int WIDTH_LL =ResourceManager.bomb_LL.getWidth();
    public static int HEIGHT_LL=ResourceManager.bomb_LL.getHeight();
    public static int WIDTH_L =ResourceManager.bomb_L.getWidth();
    public static int HEIGHT_L=ResourceManager.bomb_L.getHeight();
    public static int WIDTH_n =ResourceManager.bomb_n.getWidth();
    public static int HEIGHT_n=ResourceManager.bomb_n.getHeight();
    public static int WIDTH_s =ResourceManager.bomb_s.getWidth();
    public static int HEIGHT_s=ResourceManager.bomb_s.getHeight();

    //位置
    private int x,y;

    //引用窗口
    private GameFrame gf;

    public Bomb(int x,int y,GameFrame gf){
        this.x=x;
        this.y=y;
        this.gf=gf;
    }

    public void paint(Graphics g){

        g.drawImage(ResourceManager.bomb_LL, x, y, null);

    }

    
}
