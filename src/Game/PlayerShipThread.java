package Game;

import java.io.*;

public class PlayerShipThread implements Runnable {
    private GameFrame gf;

    private BufferedReader bR;

    private BufferedWriter bW;

    private int sign;

    private String data = "";
    private String[] dataSplit = null;

    public PlayerShipThread(GameFrame gf, BufferedReader bR, BufferedWriter bW, int sign) {
        this.gf = gf;
        this.bR = bR;
        this.bW = bW;
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

    private void read() {
        try {
            data = bR.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dataSplit = data.split(" ");
        if (sign == 1) {
            gf.secPlayerStyle = Integer.parseInt(dataSplit[0]);
            gf.secPlayerShips.get(gf.secPlayerStyle).x = Integer.parseInt(dataSplit[1]);
            gf.secPlayerShips.get(gf.secPlayerStyle).y = Integer.parseInt(dataSplit[2]);
            gf.secPlayerShips.get(gf.secPlayerStyle).shieldValue = Integer.parseInt(dataSplit[3]);
            gf.secPlayerShips.get(gf.secPlayerStyle).hp = Integer.parseInt(dataSplit[4]);
            gf.playerShips.get(gf.playerShipStyle).shieldValue = Integer.parseInt(dataSplit[5]);
            gf.playerShips.get(gf.playerShipStyle).hp = Integer.parseInt(dataSplit[6]);
        } else {
            gf.secPlayerStyle = Integer.parseInt(dataSplit[0]);
            gf.secPlayerShips.get(gf.secPlayerStyle).x = Integer.parseInt(dataSplit[1]);
            gf.secPlayerShips.get(gf.secPlayerStyle).y = Integer.parseInt(dataSplit[2]);
        }

    }

    private void write() {
        if (sign == 0) {
            data = (gf.playerShipStyle + " "
                    + gf.playerShips.get(gf.playerShipStyle).x + " "
                    + gf.playerShips.get(gf.playerShipStyle).y + " "
                    + gf.playerShips.get(gf.playerShipStyle).shieldValue + " "
                    + gf.playerShips.get(gf.playerShipStyle).hp + " "
                    + gf.secPlayerShips.get(gf.secPlayerStyle).shieldValue + " "
                    + gf.secPlayerShips.get(gf.secPlayerStyle).hp + "\n");
        } else {
            data = (gf.playerShipStyle + " " 
                    + gf.playerShips.get(gf.playerShipStyle).x + " "
                    + gf.playerShips.get(gf.playerShipStyle).y + "\n");
        }

        try {
            bW.write(data);
            bW.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}