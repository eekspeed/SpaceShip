package Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.ArrayList;
import java.util.List;

public class Server {
    // 表明自己是主机的代号
    private static int sign = 0;
    private static List<Socket> sockets = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("服务器准备就绪");
        try {
            ServerSocket serverSocket = new ServerSocket(19624);
            GameFrame frame = new GameFrame(0);

            startGame(frame);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("客户端连接完成");
                sockets.add(socket);
                Socket socket2 = serverSocket.accept();
                System.out.println("第二套接字连接完成");
                sockets.add(socket2);
                Socket socket3 = serverSocket.accept();
                System.out.println("第三套接字连接完成");
                sockets.add(socket3);

                frame.secPlayerShips.add(new ShipPlayerS(-200, 200, frame,0,false,false));
                frame.secPlayerShips.add(new ShipPlayerN(-200, 200, frame,1,false,false));
                frame.secPlayerShips.add(new ShipPlayerL(-200, 200, frame,2,false,false));
                new Thread(new ServerClientThread(sockets, frame,sign)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void startGame(GameFrame frame) {
        new Thread() {
            public void run() {

                Sound.playSound("Sound/MenuBGM.wav");

                Timer enemyRefreshTimer =new Timer();
                enemyRefreshTimer.schedule(new EnemyRefresh(frame), 0, 3000);

                frame.playerShips.add(new ShipPlayerS(-200, 200, frame,0,false,false));
                frame.playerShips.add(new ShipPlayerN(-200, 200, frame,1,false,false));
                frame.playerShips.add(new ShipPlayerL(-200, 200, frame,2,false,false));

                new Thread(){
                    public void run(){
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

        }.start();

    }

}