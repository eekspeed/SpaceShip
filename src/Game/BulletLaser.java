package Game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class BulletLaser extends BulletBase {
    public BulletLaser(int xc,int yc,Dir dir,Group group,GameFrame gf) {
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

        this.hurt=20;
        this.pierce=5;//��͸ͬ�������ӵ�������ײ����

        this.gf=gf;

        this.group=group;

        this.living=true;

        this.frames = 0;
        this.maxFrames = 100;
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
