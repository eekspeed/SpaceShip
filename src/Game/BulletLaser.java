package Game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class BulletLaser extends BulletBase {

    public BulletLaser(){
        this.code = "11";
    }

    public BulletLaser(int xc,int yc,Dir dir,Group group,GameFrame gf,boolean havesend) {
        this.SPEED = 10;

        this.image = ResourceManager.bullet_laser01;

        this.x=xc-image.getWidth()/2;
        this.y=yc-image.getHeight()/2;

        this.dir=dir;

        this.WIDTH = image.getWidth();
        this.HEIGHT = image.getHeight();

        this.rect = new Rectangle();
        rect.width=this.WIDTH;
        rect.height=this.HEIGHT;

        this.hurt=50;
        this.pierce=5;//穿透同样决定子弹互相碰撞问题

        this.gf=gf;

        this.group=group;

        this.living=true;

        this.frames = 0;
        this.maxFrames = 100;

        this.code = "11";
        this.havesend = havesend;
    }

    @Override
    public void paint(Graphics g){
        frames++;
        framesCheck();

        if (!living) {
            remove();
        }

        g.drawImage(image, x, y, null);

        move();

        rect.x = this.x;
        rect.y = this.y;
    }




}
