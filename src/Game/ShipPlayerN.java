package Game;

import java.awt.*;

public class ShipPlayerN extends ShipPlayer {

    public ShipPlayerN(){
        this.code = "202";
    }

    public ShipPlayerN(int x, int y, GameFrame gf,int dieCode ,boolean moving,boolean havesend) {

        image = ResourceManager.playerShip_n;
        imageBomb =ResourceManager.bomb_L;

        this.x = x;
        this.y = y;

        speed = 5;

        shieldValue = 400;
        maxShieldValue = 400;
        Armorthick  = 2;
        hp          = 800;
        maxHp = 800;

        WIDTH  = image.getWidth();
        HEIGHT = image.getHeight();

        rect        = new Rectangle();
        rect.x      = this.x;
        rect.y      = this.y;
        rect.width  = WIDTH;
        rect.height = HEIGHT;

        this.gf = gf;
        this.frames=0;

        this.code = "202";
        this.dieCode = dieCode;

        this.moving = moving;
        this.havesend = havesend;
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

        rect.x=this.x;
        rect.y=this.y;

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


    }

    @Override
    public void fire() {
        gf.playerBullets.add(new BulletLaser(this.x + this.WIDTH / 2, this.y + this.HEIGHT / 2, dir, group, gf,false));
    }

    
}
