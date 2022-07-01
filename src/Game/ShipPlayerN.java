package Game;

import java.awt.*;

public class ShipPlayerN extends ShipPlayer {

    public ShipPlayerN() {
        this.code = "202";
    }

    public ShipPlayerN(int x, int y, GameFrame gf, int dieCode, boolean moving, boolean havesend) {

        image = ResourceManager.playerShip_n;
        imageBomb = ResourceManager.bomb_L;

        this.x = x;
        this.y = y;

        speed = 5;

        shieldValue = 400;
        maxShieldValue = 400;
        Armorthick = 2;
        hp = 800;
        maxHp = 800;

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

        this.code = "202";
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
        if (framesSec <= 0) {
            if(shieldValue>30){
                shieldValue = ArmSec.fire(this.x + this.WIDTH / 2, this.y + this.HEIGHT / 2, dir, gf, shieldValue);
                framesSec += 5;
            }
        }
    }

}
