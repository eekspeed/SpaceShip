package Game;

import java.awt.*;

public class ShipPlayer extends ShipBase{
    public ShipPlayer(){
        dir   = Dir.R;

        moving = false;
        living = true;
        group  = Group.Good;
    }

    @Override
    public void paint(Graphics g) {
        if (!living) {
            gf.playerShips.remove(this);
            Bomb();
        }

        g.drawImage(this.image, this.x, this.y, null);
        paintShield(g);
        paintHp(g);

        move();

        ArmAnto.fire(this.x + this.WIDTH / 2, this.y + this.HEIGHT / 2, dir, gf);

        rect.x = this.x;
        rect.y = this.y;

        if(framesSec>0){
            framesSec--;
        }

        if(framesMain>0){
            framesMain--;
        }
    }

    public void fire(int fx, int fy){
        if(framesMain<=0){
            ArmMain.fire(this.x + this.WIDTH / 2, this.y + this.HEIGHT / 2, dir, gf,fx,fy);
            framesMain+=80;
        }
    }
}
