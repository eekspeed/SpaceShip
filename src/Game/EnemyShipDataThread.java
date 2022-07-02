package Game;

import java.io.*;

public class EnemyShipDataThread implements Runnable {
    private GameFrame gf;

    private BufferedReader bR;

    private BufferedWriter bW;

    private int sign;

    private ShipEnemyArt sEArt     = new ShipEnemyArt();
    private ShipEnemyBoss sEBoss   = new ShipEnemyBoss();
    private ShipEnemyGun01 sEGun01 = new ShipEnemyGun01();
    private ShipEnemyGun02 sEGun02 = new ShipEnemyGun02();
    private ShipEnemyLaser sELaser = new ShipEnemyLaser();

    private static String data             = "";
    private static String temp             = "";
    private static String[] dataSplit      = null;
    private static String[] dataSplitSplit = null;

    public EnemyShipDataThread(GameFrame gf, BufferedReader bR, BufferedWriter bW, int sign) {
        this.gf   = gf;
        this.bR   = bR;
        this.bW   = bW;
        this.sign = sign;
    }

    @Override
    public void run() {

        if (sign == 0) {
            while (true) {
                write();

                feedback();

                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if (sign == 1) {
            while (true) {
                read();

                feedback();

            }

        }
    }

    private void feedback() {
        if (sign == 0) {

            try {
                data = bR.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

            data = "yes\n";

            try {
                bW.write(data);
                bW.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void write() {

        data = "";

        addDieData();
        data += "|";

        addCreateData();
        data += "|";

        addPositionData();
        data += "\n";

        try {
            bW.write(data);
            bW.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 封装敌方舰船死亡信息
    private void addDieData() {
        temp = "";

        for (int i = 0; i < gf.enemyDie.size(); i++) {
            temp += gf.enemyDie.get(i);

            if (i != gf.enemyDie.size() - 1) {
                temp += " ";
            }
        }

        if (temp != "") {
            data += temp;
        } else {
            data += "n";
        }

    }

    // 封装敌方舰船生成信息与死亡代号
    private void addCreateData() {
        temp = "";

        for (int i = 0; i < gf.enemyShips.size(); i++) {

            if (gf.enemyShips.get(i).havesend == false) {
                                  temp        += (gf.enemyShips.get(i).code + " " + gf.enemyShips.get(i).dieCode);
                gf.enemyShips.get(i).havesend  = true;

                if (i != gf.enemyShips.size() - 1) {
                    temp += " ";
                }
            }
        }

        if (temp != "") {
            data += temp;
        } else {
            data += "n";
        }

    }

    // 封装敌方舰船死亡代号，位置、护盾、血量信息
    private void addPositionData() {
        temp = "";

        for (int i = 0; i < gf.enemyShips.size(); i++) {
            temp += (gf.enemyShips.get(i).dieCode + " "
                    + gf.enemyShips.get(i).x + " "
                    + gf.enemyShips.get(i).y + " "
                    + gf.enemyShips.get(i).shieldValue + " "
                    + gf.enemyShips.get(i).hp);

            if (i != gf.enemyShips.size() - 1) {
                temp += " ";
            }
        }

        if (temp != "") {
            data += temp;
        } else {
            data += "n";
        }

    }

    private void read() {
        try {
            data = bR.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dataSplit = data.split("\\|");

        setDieData();
        setCreateData();
        setPositionData();
    }

    // 设置敌方舰船死亡信息
    private void setDieData() {

        if (dataSplit[0].length() == 1) {
            return;
        }

        dataSplitSplit = dataSplit[0].split(" ");

        for (int i = 0; i < dataSplitSplit.length; i++) {
            for (int j = 0; j < gf.enemyShips.size(); j++) {
                if (gf.enemyShips.get(j).dieCode == Integer.parseInt(dataSplitSplit[i])) {
                    gf.enemyShips.get(j).die();
                    break;
                }
            }
        }
    }

    // 设置敌方舰船生成信息与死亡代号
    private void setCreateData() {
        if (dataSplit[1].length() == 1) {
            return;
        }

        dataSplitSplit = dataSplit[1].split(" ");

        for (int i = 0; i < dataSplitSplit.length; i += 2) {

            if (dataSplitSplit[i].equals(sEGun01.code)) {
                gf.enemyShips
                        .add(new ShipEnemyGun01(-200, -200, gf, Integer.parseInt(dataSplitSplit[i + 1]), false, true));
            }

            if (dataSplitSplit[i].equals(sEGun02.code)) {
                gf.enemyShips
                        .add(new ShipEnemyGun02(-200, -200, gf, Integer.parseInt(dataSplitSplit[i + 1]), false, true));
            }

            if (dataSplitSplit[i].equals(sELaser.code)) {
                gf.enemyShips
                        .add(new ShipEnemyLaser(-200, -200, gf, Integer.parseInt(dataSplitSplit[i + 1]), false, true));
            }

            if (dataSplitSplit[i].equals(sEArt.code)) {
                gf.enemyShips
                        .add(new ShipEnemyArt(-200, -200, gf, Integer.parseInt(dataSplitSplit[i + 1]), false, true));
            }

            if (dataSplitSplit[i].equals(sEBoss.code)) {
                gf.enemyShips
                        .add(new ShipEnemyBoss(-200, -200, gf, Integer.parseInt(dataSplitSplit[i + 1]), false, true));
            }
        }

    }

    // 根据死亡代号设置敌方舰船位置、护盾、血量信息
    private void setPositionData() {
        if (dataSplit[2].length() == 1) {
            return;
        }

        dataSplitSplit = dataSplit[2].split(" ");

        for (int i = 0; i < dataSplitSplit.length; i += 5) {
            for (int j = 0; j < gf.enemyShips.size(); j++) {

                if (gf.enemyShips.get(j).dieCode == Integer.parseInt(dataSplitSplit[i])) {

                    gf.enemyShips.get(j).x           = Integer.parseInt(dataSplitSplit[i + 1]);
                    gf.enemyShips.get(j).y           = Integer.parseInt(dataSplitSplit[i + 2]);
                    gf.enemyShips.get(j).shieldValue = Integer.parseInt(dataSplitSplit[i + 3]);
                    gf.enemyShips.get(j).hp          = Integer.parseInt(dataSplitSplit[i + 4]);
                    break;

                }

            }

        }

    }

}
