package Game;

public class ShipEnemy extends ShipBase {
    // 击破分数
    public int score;

    public ShipEnemy() {
        this.dir = Dir.L;

        this.moving = true;
        this.living = true;
        this.group  = Group.Bad;
    }

    @Override
    public void die() {
        this.living    = false;
        this.gf.score += this.score;
        this.gf.enemyDie.add(dieCode);
    }

}
