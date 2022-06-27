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

            System.out.println("线程启动");
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

                    // //飞船才要实时更新位置等信息，子弹传过来直接自行演算，这就要为子弹加上判断看有没有传过信息，传过的不在add新对象
                    // //突然发现即时新建飞船对象的刷新机制导致互相传输飞船信息非常困难，在此修改为开局将所有飞船对象全部建立
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
