package Game;

import java.util.TimerTask;
import java.util.Random;

public class EnemyRefresh extends TimerTask {
    private int gun01 = 0;
    private int maxGun01 = 0;

    private int gun02 = 0;
    private int maxGun02 = 0;

    private int laser = 0;
    private int maxLaser = 0;

    private int art = 0;
    private int maxArt = 0;

    private int boss = 0;
    private int maxBoss = 0;

    private GameFrame gf;

    private Random rd;

    private int dieCode = 0;

    public EnemyRefresh(GameFrame gf) {

        gun01 = 0;
        gun02 = 0;
        laser = 0;
        art = 0;
        boss = 0;

        this.maxGun01 = Integer.parseInt((String) PropertyManager.get("gun01"));
        this.maxGun02 = Integer.parseInt((String) PropertyManager.get("gun02"));
        this.maxLaser = Integer.parseInt((String) PropertyManager.get("laser"));
        this.maxArt = Integer.parseInt((String) PropertyManager.get("art"));
        this.maxBoss = Integer.parseInt((String) PropertyManager.get("boss"));

        this.gf = gf;
        this.rd = new Random();
    }

    @Override
    public void run() {

        if (gun01 < maxGun01) {
            gf.enemyShips
                    .add(new ShipEnemyGun01(1400 + rd.nextInt(150), 50 + rd.nextInt(700), gf, dieCode++, true, false));
            gun01++;
        }

        if (gun02 < maxGun02) {
            gf.enemyShips
                    .add(new ShipEnemyGun02(1400 + rd.nextInt(150), 50 + rd.nextInt(700), gf, dieCode++, true, false));
            gun02++;
        }

        // 敌机超过八个休息一下
        if (gf.enemyShips.size() >= 8) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (gun01 >= maxGun01 / 2 && laser < maxLaser) {
            gf.enemyShips
                    .add(new ShipEnemyLaser(1400 + rd.nextInt(150), 50 + rd.nextInt(700), gf, dieCode++, true, false));
            laser++;
        }

        if (gun02 >= maxGun01 / 2 && art < maxArt) {
            gf.enemyShips
                    .add(new ShipEnemyArt(1400 + rd.nextInt(150), 50 + rd.nextInt(700), gf, dieCode++, true, false));
            art++;
        }

        if (gf.enemyShips.size() >= 8) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (laser > maxLaser / 2 && boss < maxBoss) {
            gf.enemyShips
                    .add(new ShipEnemyBoss(1400 + rd.nextInt(150), 50 + rd.nextInt(700), gf, dieCode++, true, false));
            boss++;
        }

        // 敌机超过12个直接暂停刷新
        while (gf.enemyShips.size() >= 12) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 击杀boss且宙域清空说明游戏胜利
        if(boss==maxBoss && gf.enemyShips.size() == 0) {
            gf.X=2;
        }

    }

}
