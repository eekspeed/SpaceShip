package Game;

public class ArmLaser {
    // 是否启用
    public static boolean enable = true;

    // 武器等级
    public static int level = 0;

    public static int fire(int x, int y, Dir dir, GameFrame gf, int sV) {
        if (level == 0) {
            gf.playerBullets.add(new BulletLaser(x, y, dir, Group.Good, gf, false));
            gf.playerBullets.add(new BulletLaser(x - 8, y + 4, dir, Group.Good, gf, false));
            gf.playerBullets.add(new BulletLaser(x - 8, y - 4, dir, Group.Good, gf, false));
            sV -= 20;
        } else if (level == 1) {
            gf.playerBullets.add(new BulletLaser(x, y, dir, Group.Good, gf, false));
            gf.playerBullets.add(new BulletLaser(x - 8, y + 4, dir, Group.Good, gf, false));
            gf.playerBullets.add(new BulletLaser(x - 8, y - 4, dir, Group.Good, gf, false));
            gf.playerBullets.add(new BulletLaser(x - 16, y + 8, dir, Group.Good, gf, false));
            gf.playerBullets.add(new BulletLaser(x - 16, y - 8, dir, Group.Good, gf, false));
            sV -= 30;
        } else if (level == 2) {
            gf.playerBullets.add(new BulletLaser(x, y, dir, Group.Good, gf, false));
            gf.playerBullets.add(new BulletLaser(x - 8, y + 4, dir, Group.Good, gf, false));
            gf.playerBullets.add(new BulletLaser(x - 8, y - 4, dir, Group.Good, gf, false));
            gf.playerBullets.add(new BulletLaser(x - 16, y + 8, dir, Group.Good, gf, false));
            gf.playerBullets.add(new BulletLaser(x - 16, y - 8, dir, Group.Good, gf, false));
            gf.playerBullets.add(new BulletLaser(x - 24, y - 12, dir, Group.Good, gf, false));
            gf.playerBullets.add(new BulletLaser(x - 24, y - 12, dir, Group.Good, gf, false));
            sV -= 40;
        }
        return sV;
    }
}
