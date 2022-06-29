package Game;

import java.awt.*;
import java.util.Random;

public class ShipEnemyArt extends ShipEnemy {

    private Random random = new Random();

    public ShipEnemyArt() {
        this.code = "104";
    }

    public ShipEnemyArt(int x, int y, GameFrame gf,int dieCode,boolean moving,boolean havesend) {

        this.image = ResourceManager.enemy_art;
        this.imageBomb = ResourceManager.bomb_n;

        this.x = x;
        this.y = y;

        this.speed = 5;

        this.shieldValue = 300;
        this.maxShieldValue = 300;
        this.Armorthick = 5;
        this.hp = 300;
        this.maxHp = 300;

        this.WIDTH = image.getWidth();
        this.HEIGHT = image.getHeight();

        this.rect = new Rectangle();
        this.rect.x = this.x;
        this.rect.y = this.y;
        this.rect.width = WIDTH;
        this.rect.height = HEIGHT;

        this.gf = gf;
        this.frames = 0;
        this.score = 10;

        this.code = "104";
        this.dieCode = dieCode;

        this.moving = moving;
        this.havesend = havesend;
    }

    @Override
    public void paint(Graphics g) {
        if (!living) {
            gf.enemyShips.remove(this);
        }

        g.drawImage(this.image, this.x, this.y, null);

        paintShield(g);
        paintHp(g);

        move();

        rect.x = this.x;
        rect.y = this.y;

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



        // 随机开火
        if (frames >= 150) {
            if (random.nextInt(100) > 90) {
                this.fire();
                frames = 0;
            }
        }

        // 随机变向
        if (random.nextInt(100) > 95) {
            randomDir();
        }

    }

    @Override
    public void fire() {

        // 传入子弹生成中心位置
        gf.enemyBullets.add(new BulletArt(this.x + this.WIDTH / 2, this.y + this.HEIGHT / 2, dir, group, gf,false));

    }

    // 二+4/4成几率往左
    private void randomDir() {
        if (random.nextInt(5) > 3) {
            this.dir = Dir.L;
            return;
        }
        this.dir = Dir.values()[random.nextInt(4)];
    }

}
