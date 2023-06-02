package Game;

public class ArmEngine {
    // 武器等级
    public static int level = 0;

    public static int getEnergy() {
        if (level == 0) {
            return 1;
        } else if (level == 1) {
            return 2;
        } else if (level == 2) {
            return 4;
        } else if (level == 3) {
            return 6;
        } else if (level == 4) {
            return 8;
        }
        return 0;
    }
}
