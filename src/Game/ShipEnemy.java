package Game;

public class ShipEnemy extends ShipBase{
    //���Ʒ���
    public int score;

    //���ڴ������ݵ����ʹ���
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
