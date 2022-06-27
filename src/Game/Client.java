package Game;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args){
        
        try{
            System.out.println("客户端准备就绪");
            Socket socket =new Socket("192.168.0.103",19624);
            System.out.println("客户端连接成功");

            GameFrame frame = new GameFrame();

            startGame(frame);

            frame.playerShips.add(new ShipPlayerS(-200, 200, frame));
            frame.playerShips.add(new ShipPlayerN(-200, 200, frame));
            frame.playerShips.add(new ShipPlayerL(-200, 200, frame));

            frame.secPlayerShips.add(new ShipPlayerS(-200, 200, frame));
            frame.secPlayerShips.add(new ShipPlayerN(-200, 200, frame));
            frame.secPlayerShips.add(new ShipPlayerL(-200, 200, frame));

            new Thread(new ClientThread(socket,frame)).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public static void startGame(GameFrame frame) {
        new Thread(){
            public void run(){
                
                while(true){
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
