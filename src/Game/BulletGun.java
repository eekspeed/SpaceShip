package Game;

import java.awt.Rectangle;

public class BulletGun extends BulletBase {

    public BulletGun() {
        this.code = "10";
    }

    public BulletGun(int xc, int yc, Dir dir, Group group, GameFrame gf, boolean havesend) {
        this.SPEED = 6;

        this.image = ResourceManager.bullet_gun;

        this.x   = xc - image.getWidth() / 2;
        this.y   = yc - image.getHeight() / 2;
        this.dir = dir;

        this.WIDTH  = image.getWidth();
        this.HEIGHT = image.getHeight();

        this.rect   = new Rectangle();
        rect.width  = this.WIDTH;
        rect.height = this.HEIGHT;

        this.hurt   = 10;
        this.pierce = 1;   // 穿透同样决定子弹互相碰撞问题

        this.gf = gf;

        this.group = group;

        this.living = true;

        this.code     = "10";
        this.havesend = havesend;

        this.maxAngle = 10;
        this.angle    = 0;
        this.range    = 150;

        this.tx = 0;
        this.ty = 0;

        this.moving = false;
    }

}
