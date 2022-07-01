package Game;

import java.awt.*;
import java.util.Random;

public class ShipEnemyGun01 extends ShipEnemy{
    private Random random = new Random();

    public ShipEnemyGun01() {
        this.code = "101";
    }

    public ShipEnemyGun01(int x, int y, GameFrame gf,int dieCode,boolean moving,boolean havesend) {

        this.image = ResourceManager.enemy_gun01;
        this.imageBomb =ResourceManager.bomb_s;

        this.x = x;
        this.y = y;

        this.speed = 3;

        this.shieldValue = 150;
        this.maxShieldValue = 150;
        this.Armorthick  = 2;
        this.hp          = 150;
        this.maxHp = 150;

        this.WIDTH  = image.getWidth();
        this.HEIGHT = image.getHeight();

        this.rect        = new Rectangle();
        this.rect.x      = this.x;
        this.rect.y      = this.y;
        this.rect.width  = WIDTH;
        this.rect.height = HEIGHT;

        this.gf = gf;
        this.framesSec=0;
        this.framesMain = 0;
        this.score=3;

        this.code = "101";
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

        //随机开火
        if(framesSec >=6) {
            if (random.nextInt(100) > 90) {
                this.fire();
                framesSec=0;
            }
        }

        //随机变向
        if (random.nextInt(100) > 95) {
            randomDir();
        }
        
    }

    @Override
    public void fire() {
        gf.enemyBullets.add(new BulletGun(this.x + this.WIDTH / 2, this.y + this.HEIGHT / 2, dir, group, gf,false));
        gf.enemyBullets.add(new BulletGun(this.x + this.WIDTH / 2, this.y + this.HEIGHT / 2, dir, group, gf,false));
    }

    //二+4/4成几率往左
    private void randomDir() {
        if(random.nextInt(5)>3){
            this.dir=Dir.L;
            return;
        }
        this.dir=Dir.values()[random.nextInt(4)];
    }




}


