package Game;

import java.awt.*;
import java.util.Random;

public class ShipEnemyLaser extends ShipEnemy{
    private Random random = new Random();

    public ShipEnemyLaser() {
        this.code = "103";
    }

    public ShipEnemyLaser(int x, int y, GameFrame gf,int dieCode,boolean moving,boolean havesend) {

        this.image = ResourceManager.enemy_laser;
        this.imageBomb =ResourceManager.bomb_n;

        this.x = x;
        this.y = y;

        this.speed = 5;

        this.shieldValue = 100;
        this.maxShieldValue = 100;
        this.Armorthick  = 5;
        this.hp          = 100;
        this.maxHp = 100;

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
        this.score=10;

        this.code = "103";
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



        //�������
        if(framesSec >=30) {
            if (random.nextInt(100) > 90) {
                this.fire();
                framesSec=0;
            }
        }

        //�������
        if (random.nextInt(100) > 95) {
            randomDir();
        }
        
    }

    @Override
    public void fire() {

        gf.enemyBullets.add(new BulletLaser(this.x + this.WIDTH / 2, this.y + this.HEIGHT / 2, dir, group, gf,false));
    }

    //��+4/4�ɼ�������
    private void randomDir() {
        if(random.nextInt(5)>3){
            this.dir=Dir.L;
            return;
        }
        this.dir=Dir.values()[random.nextInt(4)];
    }




}


