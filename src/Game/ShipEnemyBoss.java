package Game;

import java.awt.*;
import java.util.Random;

public class ShipEnemyBoss extends ShipEnemy {
    private Random random = new Random();

    public ShipEnemyBoss(){
        this.code = "105";
    }

    public ShipEnemyBoss(int x, int y, GameFrame gf,int dieCode,boolean moving,boolean havesend) {

        this.image = ResourceManager.enemy_boss;
        this.imageBomb = ResourceManager.bomb_LL;

        this.x = x;
        this.y = y;

        speed = 2;

        shieldValue = 2000;
        maxShieldValue = 2000;
        Armorthick = 10;
        hp = 3000;
        maxHp = 3000;

        WIDTH = image.getWidth();
        HEIGHT = image.getHeight();

        rect = new Rectangle();
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        this.gf = gf;
        this.frames = 0;
        this.score = 100;

        this.code = "105";
        this.dieCode = dieCode;

        this.moving = moving;
        this.havesend = havesend;
    }

    @Override
    public void paint(Graphics g) {
        if (!living) {
            gf.enemyShips.remove(this);
            Bomb();
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
        if (frames >= 10) {
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
        gf.enemyBullets.add(new BulletLaser(this.x + this.WIDTH / 2, this.y + this.HEIGHT / 2, dir, group, gf,false));
    }

    // 四+4/4成几率往左
    private void randomDir() {
        if (random.nextInt(5) > 2) {
            this.dir = Dir.L;
            return;
        }
        this.dir = Dir.values()[random.nextInt(4)];
    }

}
