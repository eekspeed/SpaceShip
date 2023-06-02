/*
 * @Author: eekspeed 103409346+eekspeed@users.noreply.github.com
 * @Date: 2022-06-30 17:50:39
 * @LastEditors: eekspeed 103409346+eekspeed@users.noreply.github.com
 * @LastEditTime: 2023-06-02 11:46:19
 * @FilePath: \SpaceShip\src\Game\ArmArt.java
 * @Description: 
 * ( ﾟ∀。)
 * Copyright (c) 2023 by ${git_name_email}, All Rights Reserved. 
 */
package Game;

public class ArmArt {
    // 是否启用
    public static boolean enable = true;

    // 武器等级
    public static int level = 0;

    public static void fire(int x, int y, Dir dir, GameFrame gf, int fx, int fy) {
        if (level == 0) {
            gf.playerBullets.add(new BulletArt(x, y, Group.Good, gf, false, fx, fy));

        } else if (level == 1) {
            gf.playerBullets.add(new BulletArt(x + 5, y + 5, Group.Good, gf, false, fx + 5, fy + 5));
            gf.playerBullets.add(new BulletArt(x + 5, y - 5, Group.Good, gf, false, fx + 5, fy - 5));
            gf.playerBullets.add(new BulletArt(x - 5, y + 5, Group.Good, gf, false, fx - 5, fy + 5));
            gf.playerBullets.add(new BulletArt(x - 5, y - 5, Group.Good, gf, false, fx - 5, fy - 5));
        } else if (level == 2) {
            gf.playerBullets.add(new BulletArt(x + 8, y, Group.Good, gf, false, fx + 8, fy));
            gf.playerBullets.add(new BulletArt(x + 8, y + 8, Group.Good, gf, false, fx + 8, fy + 8));
            gf.playerBullets.add(new BulletArt(x + 8, y - 8, Group.Good, gf, false, fx + 8, fy - 8));
            gf.playerBullets.add(new BulletArt(x - 8, y, Group.Good, gf, false, fx - 8, fy));
            gf.playerBullets.add(new BulletArt(x - 8, y + 8, Group.Good, gf, false, fx - 8, fy + 8));
            gf.playerBullets.add(new BulletArt(x - 8, y - 8, Group.Good, gf, false, fx - 8, fy - 8));
            gf.playerBullets.add(new BulletArt(x, y, Group.Good, gf, false, fx, fy));
            gf.playerBullets.add(new BulletArt(x, y + 8, Group.Good, gf, false, fx, fy + 8));
            gf.playerBullets.add(new BulletArt(x, y - 8, Group.Good, gf, false, fx, fy - 8));
        }
    }
}
