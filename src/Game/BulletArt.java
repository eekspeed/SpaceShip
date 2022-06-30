package Game;

import java.awt.Rectangle;

public class BulletArt extends BulletBase {

    public BulletArt() {
        this.code = "12";
    }

    public BulletArt(int xc, int yc, Dir dir, Group group, GameFrame gf, boolean havesend) {
        this.SPEED = 15;

        this.image = ResourceManager.bullet_art;

        this.x = xc - image.getWidth() / 2;
        this.y = yc - image.getHeight() / 2;
        this.dir = dir;

        this.WIDTH = image.getWidth();
        this.HEIGHT = image.getHeight();

        this.rect = new Rectangle();
        rect.width = this.WIDTH;
        rect.height = this.HEIGHT;

        this.hurt = 100;
        this.pierce = 6;// 穿透同样决定子弹互相碰撞问题

        this.gf = gf;

        this.group = group;

        this.living = true;

        this.code = "12";
        this.havesend = havesend;

        this.maxAngle = 1;
        this.angle = 0;
        this.range = 1000;

        this.tx = 0;
        this.ty = 0;

        this.moving = false;
    }

}
