package Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("服务器准备就绪");
        ServerSocket serverSocket = new ServerSocket(19624);

        GameFrame frame = new GameFrame();

        startGame(frame);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("客户端连接完成");

            frame.secPlayerShips.add(new ShipPlayerS(-200, 200, frame));
            frame.secPlayerShips.add(new ShipPlayerN(-200, 200, frame));
            frame.secPlayerShips.add(new ShipPlayerL(-200, 200, frame));
            new Thread(new ServerThread(socket, frame)).start();
        }

    }

    public static void startGame(GameFrame frame) {
        new Thread() {
            public void run() {

                Sound.playSound("Sound/MenuBGM.wav");

                Timer enemyRefreshTimer =new Timer();
                enemyRefreshTimer.schedule(new EnemyRefresh(frame), 0, 3000);

                frame.playerShips.add(new ShipPlayerS(-200, 200, frame));
                frame.playerShips.add(new ShipPlayerN(-200, 200, frame));
                frame.playerShips.add(new ShipPlayerL(-200, 200, frame));

                new Thread(){
                    public void run(){
                        while (true) {
                            try {
                                Thread.sleep(30);
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