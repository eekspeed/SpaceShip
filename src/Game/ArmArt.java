package Game;

public class ArmArt {
    // �Ƿ�����
    public static boolean enable = true;

    // �����ȼ�
    public static int level = 0;

    public static void fire(int x, int y, Dir dir, GameFrame gf,int fx,int fy){
        if(level==0){
            gf.playerBullets.add(new BulletArt(x, y, Group.Good, gf,false,fx,fy));

        } else if(level==1){
            gf.playerBullets.add(new BulletArt(x+5, y+5, Group.Good, gf,false,fx+5,fy+5));
            gf.playerBullets.add(new BulletArt(x+5, y-5, Group.Good, gf,false,fx+5,fy-5));
            gf.playerBullets.add(new BulletArt(x-5, y+5, Group.Good, gf,false,fx-5,fy+5));
            gf.playerBullets.add(new BulletArt(x-5, y-5, Group.Good, gf,false,fx-5,fy-5));
        }
    }
}
