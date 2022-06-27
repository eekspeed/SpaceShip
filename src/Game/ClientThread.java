package Game;

import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable{
    private Socket socket;

    private GameFrame gf;

    public ClientThread(Socket socket,GameFrame gf){
        this.socket=socket;
        this.gf=gf;
    }

    @Override
    public void run() {
        try {

            System.out.println("�߳�����");
            InputStream is=socket.getInputStream();
            OutputStream os =socket.getOutputStream();
                
            InputStreamReader isR = new InputStreamReader(is);
            OutputStreamWriter osR = new OutputStreamWriter(os);

            BufferedReader bR = new BufferedReader(isR);
            BufferedWriter bW = new BufferedWriter(osR);

            String play ="";
            String[] playsplit;

            String enemy = "";
            String[] enemysplit;

            String bullet = "";
            String[] bulletsplit;
            

            while(true){
                while(true){
                    play=bR.readLine();
                    playsplit=play.split(" ");
                    gf.secPlayerShips.get(gf.secPlayerStyle).x=(Integer.parseInt(playsplit[0]));
                    gf.secPlayerShips.get(gf.secPlayerStyle).y=(Integer.parseInt(playsplit[1]));
                    
                    //enemy=bR.readLine();
                    //enemysplit=enemy.split(" ");

                    // //�ɴ���Ҫʵʱ����λ�õ���Ϣ���ӵ�������ֱ���������㣬���ҪΪ�ӵ������жϿ���û�д�����Ϣ�������Ĳ���add�¶���
                    // //ͻȻ���ּ�ʱ�½��ɴ������ˢ�»��Ƶ��»��ഫ��ɴ���Ϣ�ǳ����ѣ��ڴ��޸�Ϊ���ֽ����зɴ�����ȫ������
                    // for(int i=0;i<enemysplit.length;i+=3){
                    //     if((Integer.parseInt(enemysplit[i]))==ShipEnemyGun01.code){
                    //         gf.enemyShips.add(new ShipEnemyGun01(Integer.parseInt(enemysplit[i+1]),Integer.parseInt(enemysplit[i+2]), gf));
                    //     }

                    //     if((Integer.parseInt(enemysplit[i]))==ShipEnemyGun02.code){
                    //         gf.enemyShips.add(new ShipEnemyGun02(Integer.parseInt(enemysplit[i+1]),Integer.parseInt(enemysplit[i+2]), gf));
                    //     }
                        
                    //     if((Integer.parseInt(enemysplit[i]))==ShipEnemyLaser.code){
                    //         gf.enemyShips.add(new ShipEnemyLaser(Integer.parseInt(enemysplit[i+1]),Integer.parseInt(enemysplit[i+2]), gf));
                    //     }

                        
                    // }
                    
                    play=(gf.playerShips.get(gf.playerShipStyle).x+" "+gf.playerShips.get(gf.playerShipStyle).y+"\n");
                    bW.write(play);
                    bW.flush();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
