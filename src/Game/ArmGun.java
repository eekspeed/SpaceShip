package Game;

public class ArmGun {
    // �Ƿ�����
    public static boolean enable = true;

    // �����ȼ�
    public static int level = 0;

    public static void fire(int x, int y, Dir dir, GameFrame gf){
        if(level==0){
            gf.playerBullets.add(new BulletGun(x, y, dir, Group.Good, gf,false));
            gf.playerBullets.add(new BulletGun(x, y, dir, Group.Good, gf,false));
        } else if(level==1){
            gf.playerBullets.add(new BulletGun(x, y, dir, Group.Good, gf,false));
            gf.playerBullets.add(new BulletGun(x, y, dir, Group.Good, gf,false));
            gf.playerBullets.add(new BulletGun(x, y, dir, Group.Good, gf,false));
        } else if(level==2){
            gf.playerBullets.add(new BulletGun(x, y, dir, Group.Good, gf,false));
            gf.playerBullets.add(new BulletGun(x, y, dir, Group.Good, gf,false));
            gf.playerBullets.add(new BulletGun(x, y, dir, Group.Good, gf,false));
            gf.playerBullets.add(new BulletGun(x, y, dir, Group.Good, gf,false));
        }
    }
}
