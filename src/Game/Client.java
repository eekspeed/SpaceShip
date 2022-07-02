package Game;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    // �����Լ��ǿͻ��˵Ĵ���
    private static int sign             = 1;
    private static List<Socket> sockets = new ArrayList<>();

    public static void main(String[] args) {

        try {
            System.out.println("�ͻ���׼������");
            Socket socket = new Socket("192.168.0.103", 19624);
            System.out.println("�ͻ������ӳɹ�");
            sockets.add(socket);
            Socket socket2 = new Socket("192.168.0.103", 19624);
            System.out.println("�ڶ��׽������ӳɹ�");
            sockets.add(socket2);
            Socket socket3 = new Socket("192.168.0.103", 19624);
            System.out.println("�����׽������ӳɹ�");
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
                        Thread.sleep(30);
                        frame.repaint();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

        }.start();

    }

}
