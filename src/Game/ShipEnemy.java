package Game;

public class ShipEnemy extends ShipBase{
    //击破分数
    public int score;

    //用于传输数据的类型代号
    public static int code;

    public ShipEnemy(){
        dir   = Dir.L;

        moving = true;
        living = true;
        group  = Group.Bad;
    }

    @Override
    public void die() {
        this.living = false;
        this.gf.score+=this.score;
    }
    
}
