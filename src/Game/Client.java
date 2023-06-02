/*
 * @Author: eekspeed 103409346+eekspeed@users.noreply.github.com
 * @Date: 2022-06-28 13:25:51
 * @LastEditors: eekspeed 103409346+eekspeed@users.noreply.github.com
 * @LastEditTime: 2023-06-02 13:22:44
 * @FilePath: \SpaceShip\src\Game\Client.java
 * @Description: 
 * ( ﾟ∀。)
 * Copyright (c) 2023 by ${git_name_email}, All Rights Reserved. 
 */
package Game;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    // 表明自己是客户端的代号
    private static int sign             = 1;
    private static List<Socket> sockets = new ArrayList<>();

    public static void main(String[] args) {

        try {
            System.out.println("客户端准备就绪");
            Socket socket = new Socket("在这里填入主机的ip地址", 19624);
            System.out.println("客户端连接成功");
            sockets.add(socket);
            Socket socket2 = new Socket("在这里填入主机的ip地址", 19624);
            System.out.println("第二套接字连接成功");
            sockets.add(socket2);
            Socket socket3 = new Socket("在这里填入主机的ip地址", 19624);
            System.out.println("第三套接字连接成功");
            sockets.add(socket3);

            GameFrame frame = new GameFrame(1);

            startGame(frame);

            frame.playerShips.add(new ShipPlayerS(-200, 200, frame, 0, false, false));
            frame.playerShips.add(new ShipPlayerN(-200, 200, frame, 1, false, false));
            frame.playerShips.add(new ShipPlayerL(-200, 200, frame, 2, false, false));

            frame.secPlayerShips.add(new ShipPlayerS(-200, 200, frame, 0, false, false));
            frame.secPlayerShips.add(new ShipPlayerN(-200, 200, frame, 1, false, false));
            frame.secPlayerShips.add(new ShipPlayerL(-200, 200, frame, 2, false, false));

            new Thread(new ServerClientThread(sockets, frame, sign)).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void startGame(GameFrame frame) {
        new Thread() {
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(25);
                        frame.repaint();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

        }.start();

    }

}
