package Game;

public class ArmAnto {
    // 帧率计时，不使用Timer是因为新线程难以调控
    private static int frames = 0;
    
    public static void fire(int x, int y, Dir dir, GameFrame gf) {
        if (ArmGun.enable == true) {
            if(frames>=6){
                ArmGun.fire(x,y,dir,gf);
                frames=0;
            } else {
                frames++;
            }
        }

        if (ArmVulcan.enable == true) {
            if(frames>=6){
                ArmVulcan.fire(x,y,dir,gf);
                frames=0;
            } else {
                frames++;
            }
        }

    }
}
