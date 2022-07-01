package Game;

public class ArmSec {

    public static int fire(int x, int y, Dir dir, GameFrame gf, int sV) {
        if (ArmLaser.enable == true) {
            sV = ArmLaser.fire(x, y, dir, gf, sV);
            Sound.playSound("Sound/Laser.wav");
        }
        return sV;
    }
}
