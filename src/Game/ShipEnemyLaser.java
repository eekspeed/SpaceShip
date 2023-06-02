package Game;

import java.awt.*;
import java.util.Random;

public class ShipEnemyLaser extends ShipEnemy {
    private Random random = new Random();

    public ShipEnemyLaser() {
        this.code = "103";
    }

    public ShipEnemyLaser(int x, int y, GameFrame gf, int dieCode, boolean moving, boolean havesend) {

        this.image     = ResourceManager.enemy_laser;
        this.imageBomb = ResourceManager.bomb_n;

        this.x = x;
        this.y = y;

        this.speed = 3;

        this.shieldValue    = 200;
        this.maxShieldValue = 200;
        this.Armorthick     = 5;
        this.hp             = 200;
        this.maxHp          = 200;

        this.WIDTH  = image.getWidth();
        this.HEIGHT = image.getHeight();

        this.rect        = new Rectangle();
        this.rect.x      = this.x;
        this.rect.y      = this.y;
        this.rect.width  = WIDTH;
        this.rect.height = HEIGHT;

        this.gf         = gf;
        this.framesSec  = 0;
        this.framesMain = 0;
        this.score      = 20;

        this.code    = "103";
        this.dieCode = dieCode;

        this.moving   = moving;
        this.havesend = havesend;
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
        if (framesSec >= 15) {
            if (random.nextInt(100) > 0) {
                this.fire();
                framesSec -= 15;
            } else {
                this.fire();
                this.fire();
                this.fire();
                this.fire();
                framesSec = 0;
            }
        }

        // 随机变向
        if (random.nextInt(100) > 95) {
            randomDir();
        }

    }

    @Override
    public void fire() {
        for (int i = 0; i < 5; i++) {
            gf.enemyBullets
                    .add(new BulletLaser(this.x + this.WIDTH / 2, this.y + this.HEIGHT / 2, dir, group, gf, false));
        }
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
