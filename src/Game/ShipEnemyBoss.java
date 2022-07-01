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

        speed = 1;

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
        this.framesSec = 0;
        this.framesMain = 0;
        this.score = 100;

        this.code = "105";
        this.dieCode = dieCode;

        this.moving = moving;
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

        // 自动开火
        if (random.nextInt(100) > 90) {
            for(int i = 0; i <5;i++){
                gf.enemyBullets.add(new BulletGun(this.x + this.WIDTH / 2, this.y + this.HEIGHT / 2, dir, group, gf,false));
            }
        }

        if (random.nextInt(100) > 95) {
            for(int i = 0; i <4;i++){
                gf.enemyBullets.add(new BulletLaser(this.x + this.WIDTH / 2, this.y + this.HEIGHT / 2, dir, group, gf,false));
            }
        }

        // 随机开火
        if (framesSec >= 10) {
            if (random.nextInt(100) > 90) {
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
        gf.enemyBullets.add(new BulletLaser(this.x + this.WIDTH / 2, this.y + this.HEIGHT / 2, dir, group, gf,false));
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
