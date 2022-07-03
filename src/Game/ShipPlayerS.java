package Game;

import java.awt.*;

public class ShipPlayerS extends ShipPlayer {

    public ShipPlayerS() {
        this.code = "201";
    }

    public ShipPlayerS(int x, int y, GameFrame gf, int dieCode, boolean moving, boolean havesend) {

        image = ResourceManager.playerShip_s;
        imageBomb = ResourceManager.bomb_n;

        this.x = x;
        this.y = y;

        speed = 5;

        shieldValue = 200;
        maxShieldValue = 200;
        Armorthick = 2;
        hp = 400;
        maxHp = 400;

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

        this.code = "201";
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

    }

    @Override
    public void fire() {
        if(framesSec<=0){
            if(shieldValue>30){
                shieldValue = ArmSec.fire(this.x + this.WIDTH / 2, this.y + this.HEIGHT / 2, dir, gf, shieldValue);
                framesSec += 6;
            }
        }
       
    }

}
