package Game;

import java.awt.*;
import java.util.Random;

public class ShipEnemyGun02 extends ShipEnemy{
    private Random random = new Random();

    public ShipEnemyGun02(int x, int y, GameFrame gf) {

        image = ResourceManager.enemy_gun02;
        imageBomb =ResourceManager.bomb_s;

        this.x = x;
        this.y = y;

        speed = 4;

        shieldValue = 100;
        maxShieldValue = 100;
        Armorthick  = 2;
        hp          = 100;
        maxHp = 100;

        WIDTH  = image.getWidth();
        HEIGHT = image.getHeight();

        rect        = new Rectangle();
        rect.x      = this.x;
        rect.y      = this.y;
        rect.width  = WIDTH;
        rect.height = HEIGHT;

        this.gf = gf;
        this.frames=0;
        this.score=2;

        this.code = 02;
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

        rect.x=this.x;
        rect.y=this.y;

        //随机开火
        if(frames >=6) {
            if (random.nextInt(100) > 90) {
                this.fire();
                frames=0;
            }
        }

        //随机变向
        if (random.nextInt(100) > 95) {
            randomDir();
        }
        
    }

    @Override
    public void fire() {

        gf.enemyBullets.add(new BulletGun(this.x + this.WIDTH / 2, this.y + this.HEIGHT / 2, dir, group, gf));
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


