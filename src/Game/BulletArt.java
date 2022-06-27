package Game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class BulletArt extends BulletBase {
    public BulletArt(int xc,int yc,Dir dir,Group group,GameFrame gf) {
        this.SPEED = 15;

        this.image = ResourceManager.bullet_art;

        this.x=xc-image.getWidth()/2;
        this.y=yc-image.getHeight()/2;
        this.dir=dir;

        this.WIDTH = image.getWidth();
        this.HEIGHT = image.getHeight();

        this.rect = new Rectangle();
        rect.width=this.WIDTH;
        rect.height=this.HEIGHT;

        this.hurt=20;
        this.pierce=6;//穿透同样决定子弹互相碰撞问题

        this.gf=gf;

        this.group=group;

        this.living=true;

        this.frames = 0;
        this.maxFrames = 200;
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
    }



}
