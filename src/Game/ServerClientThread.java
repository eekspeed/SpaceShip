package Game;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerClientThread implements Runnable {
    private List<Socket> sockets;

    private GameFrame gf;

    private List<InputStream> inputStreams = new ArrayList<>();
    private List<OutputStream> outputStreams = new ArrayList<>();

    private List<InputStreamReader> inputStreamReaders = new ArrayList<>();
    private List<OutputStreamWriter> outputStreamWriters = new ArrayList<>();

    private List<BufferedReader> bufferedReaders = new ArrayList<>();
    private List<BufferedWriter> bufferedWriters = new ArrayList<>();

    int sign;

    public ServerClientThread(List<Socket> sockets, GameFrame gf,int sign) {
        this.sockets = sockets;
        this.gf = gf;
        this.sign = sign;
    }

    @Override
    public void run() {

        System.out.println("�߳�����");

        for(int i=0; i<sockets.size();i++){
            setIOStream(sockets.get(i));
        }

        // ����˫������λ����Ϣ
        new Thread(new PlayerShipThread(gf, bufferedReaders.get(0), bufferedWriters.get(0),sign)).start();

        // ����з�������Ϣ
        new Thread(new EnemyShipDataThread(gf, bufferedReaders.get(1), bufferedWriters.get(1),sign)).start();
        
        // �����ӵ��뱬ը��Ϣ
        new Thread(new BulletBombDataThread(gf, bufferedReaders.get(2), bufferedWriters.get(2),sign)).start();
    }

    private void setIOStream(Socket socket){
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            InputStreamReader isR = new InputStreamReader(is);
            OutputStreamWriter osW = new OutputStreamWriter(os);

            BufferedReader bR = new BufferedReader(isR);
            BufferedWriter bW = new BufferedWriter(osW);

            inputStreams.add(is);
            outputStreams.add(os);

            inputStreamReaders.add(isR);
            outputStreamWriters.add(osW);

            bufferedReaders.add(bR);
            bufferedWriters.add(bW);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
