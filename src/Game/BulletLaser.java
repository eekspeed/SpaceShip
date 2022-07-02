package Game;

import java.awt.Rectangle;

public class BulletLaser extends BulletBase {

    public BulletLaser() {
        this.code = "11";
    }

    public BulletLaser(int xc, int yc, Dir dir, Group group, GameFrame gf, boolean havesend) {
        this.SPEED = 14;

        this.image = ResourceManager.bullet_laser01;

        this.x   = xc - image.getWidth() / 2;
        this.y   = yc - image.getHeight() / 2;
        this.dir = dir;

        this.WIDTH  = image.getWidth();
        this.HEIGHT = image.getHeight();

        this.rect = new Rectangle();

        if (this.dir == Dir.R || this.dir == Dir.L) {
            rect.width  = 8;
            rect.height = 2;
        } else {
            rect.width  = 2;
            rect.height = 8;
        }

        this.hurt   = 30;
        this.pierce = 5;   // 穿透同样决定子弹互相碰撞问题

        this.gf = gf;

        this.group = group;

        this.living = true;

        this.code     = "11";
        this.havesend = havesend;

        this.maxAngle = 2;
        this.angle    = 0;
        this.range    = 1000;

        this.tx = 0;
        this.ty = 0;

        this.moving = false;
    }

}
