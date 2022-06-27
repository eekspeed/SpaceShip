package Game;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable{
    private Socket socket;

    private GameFrame gf;

    public ServerThread(Socket socket,GameFrame gf){
        this.socket=socket;
        this.gf=gf;
    }

    @Override
    public void run() {

        try {
            System.out.println("Ïß³ÌÆô¶¯");
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


                play=(gf.playerShips.get(gf.playerShipStyle).x+" "+gf.playerShips.get(gf.playerShipStyle).y+"\n");
                bW.write(play);
                bW.flush();
                

                // for(int i=0; i<gf.enemyShips.size();i++){
                //     enemy+=(gf.enemyShips.get(i).code+" "+gf.enemyShips.get(i).x+" "+gf.enemyShips.get(i).y);

                //     if(i!=gf.enemyShips.size()-1){
                //         enemy+=" ";
                //     } else {
                //         enemy+="\n";
                //     }
                // }
                // bW.write(enemy);
                // bW.flush();
                
                play=bR.readLine();
                playsplit=play.split(" ");
                gf.secPlayerShips.get(gf.secPlayerStyle).x=(Integer.parseInt(playsplit[0]));
                gf.secPlayerShips.get(gf.secPlayerStyle).y=(Integer.parseInt(playsplit[1]));

                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    
}
