package Game;

import java.awt.*;

public class ShipPlayerL extends ShipPlayer {

    public ShipPlayerL(int x, int y, GameFrame gf) {

        image = ResourceManager.playerShip_L;
        imageBomb = ResourceManager.bomb_LL;

        this.x = x;
        this.y = y;

        speed = 5;

        shieldValue = 1000;
        maxShieldValue = 1000;
        Armorthick = 10;
        hp = 2000;
        maxHp = 2000;

        WIDTH = image.getWidth();
        HEIGHT = image.getHeight();

        rect = new Rectangle();
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        this.gf = gf;
        this.frames = 0;
    }

    @Override
    public void paint(Graphics g) {
        if (!living) {
            gf.playerShips.remove(this);
        }

        g.drawImage(this.image, this.x, this.y, null);
        paintShield(g);
        paintHp(g);

        move();

        frames++;
    }

    @Override
    public void move() {
        if (!moving) {
            return;
        }

        switch (dir) {
            case L:
                x -= speed;
                break;
            case R:
                x += speed;
                break;
            case U:
                y -= speed;
                break;
            case D:
                y += speed;
                break;
        }

        boundsCheck();

        rect.x = this.x;
        rect.y = this.y;
    }

    @Override
    public void fire() {
        gf.playerBullets.add(new BulletLaser(this.x + this.WIDTH / 2, this.y + this.HEIGHT / 2, dir, group, gf));
    }

}
