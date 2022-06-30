package Game;

public class ArmSec {

    public static void fire(int x, int y, Dir dir, GameFrame gf) {
        if (ArmLaser.enable == true) {
            ArmLaser.fire(x,y,dir,gf);
        }

    }
}
