package Game;

import java.io.*;

public class BulletBombDataThread implements Runnable {
    private GameFrame gf;

    private BufferedReader bR;

    private BufferedWriter bW;

    private int sign;

    private static String data = "";
    private static String temp = "";

    private static String[] dataSplit = null;
    private static String[] tempSplit = null;

    private BulletArt bArt     = new BulletArt();
    private BulletGun bGun     = new BulletGun();
    private BulletLaser bLaser = new BulletLaser();

    public BulletBombDataThread(GameFrame gf, BufferedReader bR, BufferedWriter bW, int sign) {
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

                read();

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

                write();

            }

        }
    }

    private void write() {
        data = "";
        temp = "";

        if (sign == 0) {

            writeEnemyBulletData();

            data += "|";
            temp  = "";

            writePlayerBulletData();

            data += "\n";

        } else {

            writePlayerBulletData();

            data += "\n";

        }

        try {
            bW.write(data);
            bW.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeEnemyBulletData() {

        for (int i = 0; i < gf.enemyBullets.size(); i++) {

            if (gf.enemyBullets.get(i).havesend == false) {
                temp += (gf.enemyBullets.get(i).code + " "
                        + gf.enemyBullets.get(i).x + " "
                        + gf.enemyBullets.get(i).y + " "
                        + gf.enemyBullets.get(i).dir + " ");
                gf.enemyBullets.get(i).havesend = true;
            }

        }

        if (temp != "") {
            data += temp;
        } else {
            data += "n";
        }

    }

    private void writePlayerBulletData() {

        for (int i = 0; i < gf.playerBullets.size(); i++) {

            if (gf.playerBullets.get(i).havesend == false) {

                if (gf.playerBullets.get(i).code.equals(bArt.code)) {
                    temp += (gf.playerBullets.get(i).x + " "
                            + gf.playerBullets.get(i).y + " "
                            + gf.playerBullets.get(i).FX + " "
                            + gf.playerBullets.get(i).FY + " ");
                } else {
                    temp += (gf.playerBullets.get(i).code + " "
                            + gf.playerBullets.get(i).x + " "
                            + gf.playerBullets.get(i).y + " "
                            + gf.playerBullets.get(i).dir + " ");
                }

                gf.playerBullets.get(i).havesend = true;
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

        if (sign == 0) {

            dataSplit = data.split(" ");

            if (dataSplit[0].length() != 1) {
                readPlayerBullets();
            }

        } else {
            tempSplit = data.split("\\|");

            if (tempSplit[0].length() != 1) {

                dataSplit = tempSplit[0].split(" ");

                readEnemyBullets();

            }

            if (tempSplit[1].length() != 1) {
                dataSplit = tempSplit[1].split(" ");

                readPlayerBullets();
            }

        }
    }

    private void readEnemyBullets() {
        for (int i = 0; i < dataSplit.length; i += 4) {

            if (dataSplit[i].equals(bArt.code)) {
                gf.enemyBullets.add(new BulletArt(Integer.parseInt(dataSplit[i + 1]),
                        Integer.parseInt(dataSplit[i + 2]), Dir.getDir(dataSplit[i + 3]), Group.Bad, gf, true));
            }

            if (dataSplit[i].equals(bGun.code)) {
                gf.enemyBullets.add(new BulletGun(Integer.parseInt(dataSplit[i + 1]),
                        Integer.parseInt(dataSplit[i + 2]), Dir.getDir(dataSplit[i + 3]), Group.Bad, gf, true));
            }

            if (dataSplit[i].equals(bLaser.code)) {
                gf.enemyBullets.add(new BulletLaser(Integer.parseInt(dataSplit[i + 1]),
                        Integer.parseInt(dataSplit[i + 2]), Dir.getDir(dataSplit[i + 3]), Group.Bad, gf, true));
            }

        }
    }

    // 在玩家子弹信息中先检测其他，未检测到的就是主炮子弹信息
    private void readPlayerBullets() {

        for (int i = 0; i < dataSplit.length; i += 4) {

            if (dataSplit[i].equals(bGun.code)) {

                gf.playerBullets.add(new BulletGun(Integer.parseInt(dataSplit[i + 1]),
                        Integer.parseInt(dataSplit[i + 2]), Dir.getDir(dataSplit[i + 3]), Group.Good, gf, true));

            } else if (dataSplit[i].equals(bLaser.code)) {

                gf.playerBullets.add(new BulletLaser(Integer.parseInt(dataSplit[i + 1]),
                        Integer.parseInt(dataSplit[i + 2]), Dir.getDir(dataSplit[i + 3]), Group.Good, gf, true));

            } else {

                gf.playerBullets.add(new BulletArt(Integer.parseInt(dataSplit[i]),
                        Integer.parseInt(dataSplit[i + 1]), Group.Good, gf, true, Integer.parseInt(dataSplit[i + 2]),
                        Integer.parseInt(dataSplit[i + 3])));

            }

        }

    }

}
