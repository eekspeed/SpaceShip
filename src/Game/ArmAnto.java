package Game;

public class ArmAnto {
    // 帧率计时，不使用Timer是因为新线程难以调控
    private static int frames = 0;

    public static void fire(int x, int y, Dir dir, GameFrame gf) {
        if (ArmGun.enable == true) {
            if (frames >= 8) {
                ArmGun.fire(x, y, dir, gf);
                frames = 0;
                Sound.playSound("Sound/Gun.wav");
            } else {
                frames++;
            }

        }

    }
}
