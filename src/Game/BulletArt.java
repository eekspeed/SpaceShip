package Game;

import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;
import java.awt.Graphics;

public class BulletArt extends BulletBase {

    private List<Trajectory> tra = new LinkedList<>();

    public BulletArt() {
        this.code = "12";
    }

    public BulletArt(int xc, int yc, Dir dir, Group group, GameFrame gf, boolean havesend) {
        this.SPEED = 18;

        this.image = ResourceManager.bullet_art;

        this.x   = xc - image.getWidth() / 2;
        this.y   = yc - image.getHeight() / 2;
        this.dir = dir;

        this.WIDTH  = image.getWidth();
        this.HEIGHT = image.getHeight();

        this.rect   = new Rectangle();
        rect.width  = this.WIDTH + 2;
        rect.height = this.HEIGHT + 2;

        this.hurt   = 300;
        this.pierce = 6;    // 穿透同样决定子弹互相碰撞问题

        this.gf = gf;

        this.group = group;

        this.living = true;

        this.code     = "12";
        this.havesend = havesend;

        this.maxAngle = 0;
        this.angle    = 0;
        this.range    = 1000;

        this.tx = 0;
        this.ty = 0;

        this.moving = false;
    }

    public BulletArt(int xc, int yc, Group group, GameFrame gf, boolean havesend, int fx, int fy) {
        this.SPEED = 18;

        this.image = ResourceManager.bullet_art;

        this.x = xc - image.getWidth() / 2;
        this.y = yc - image.getHeight() / 2;

        this.WIDTH  = image.getWidth();
        this.HEIGHT = image.getHeight();

        this.rect   = new Rectangle();
        rect.width  = this.WIDTH + 2;
        rect.height = this.HEIGHT + 2;

        this.hurt   = 300;
        this.pierce = 6;

        this.gf = gf;

        this.group = group;

        this.living = true;

        this.code     = "12";
        this.havesend = havesend;

        this.maxAngle = 0;
        this.angle    = 0;
        this.range    = 1000;

        this.tx = 0;
        this.ty = 0;

        this.moving = true;

        this.FX = fx;
        this.FY = fy;

        if (FX > x) {
            dir = Dir.R;
        } else {
            dir = Dir.L;

        }

        if (Math.abs(FX - x) > Math.abs(FY - y)) {
            this.angle = Math
                    .acos(Math.abs(FX - x) * 1.0 / Math.sqrt(Math.pow(FX - x, 2) * 1.0 + Math.pow(FY - y, 2) * 1.0));
        } else {
            this.angle = Math
                    .asin(Math.abs(FY - y) * 1.0 / Math.sqrt(Math.pow(FX - x, 2) * 1.0 + Math.pow(FY - y, 2) * 1.0));
        }

        FX += (FX - x) * 3;
        FY += (FY - y) * 3;

        changeImage();

    }

    @Override
    public void paint(Graphics g) {

        if (this.moving == false) {
            this.moving = true;

            this.angle = Math.toRadians(this.random.nextDouble() * this.maxAngle);

            changeImage();
            point();

        }

        tra.add(new Trajectory(x + WIDTH / 2, y + HEIGHT / 2));

        move();

        for (int i = 0; i < tra.size(); i++) {
            if (i == tra.size() - 1) {
                tra.get(i).x2 = x + WIDTH / 2;
                tra.get(i).y2 = y + HEIGHT / 2;
            }

            tra.get(i).paintTra(g);
        }

        if (!living) {
            remove();
        }

        g.drawImage(this.image, this.x, this.y, null);

        rectmove();
    }
}
