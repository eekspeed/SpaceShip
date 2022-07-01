package Game;

public class ArmMain {

    public static void fire(int x, int y, Dir dir, GameFrame gf, int fx, int fy) {
        if (ArmArt.enable == true) {
            ArmArt.fire(x, y, dir, gf, fx, fy);
            Sound.playSound("Sound/Art.wav");
        }

    }
}
