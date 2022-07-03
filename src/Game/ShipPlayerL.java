package Game;

import java.awt.*;

public class ShipPlayerL extends ShipPlayer {

    public ShipPlayerL() {
        this.code = "203";
    }

    public ShipPlayerL(int x, int y, GameFrame gf, int dieCode, boolean moving, boolean havesend) {

        image     = ResourceManager.playerShip_L;
        imageBomb = ResourceManager.bomb_LL;

        this.x = x;
        this.y = y;

        speed = 3;

        shieldValue    = 1000;
        maxShieldValue = 1000;
        Armorthick     = 15;
        hp             = 2000;
        maxHp          = 2000;

        WIDTH  = image.getWidth();
        HEIGHT = image.getHeight();

        rect        = new Rectangle();
        rect.x      = this.x-20;
        rect.y      = this.y;
        rect.width  = WIDTH;
        rect.height = HEIGHT;

        this.gf         = gf;
        this.framesSec  = 0;
        this.framesMain = 0;

        this.code    = "203";
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

    }

    @Override
    public void fire() {
        if (framesSec <= 0) {
            if (shieldValue > 30) {
                shieldValue  = ArmSec.fire(this.x + this.WIDTH / 2, this.y + this.HEIGHT / 2, dir, gf, shieldValue);
                framesSec   += 6;
            }
        }
    }

}
