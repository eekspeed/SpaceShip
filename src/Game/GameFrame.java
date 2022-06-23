package Game;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends Frame {

    public static final int GAME_WIDTH = 1600;
    public static final int GAME_HEIGHT = 800;

    //֡����ʱ
    private int frames =0;

    // �ɴ�λ��
    Ship playership = new Ship(200, 200, Dir.R, Group.Good, this);

    // �з��ɴ��б�
    List<Ship> enemyShips = new ArrayList<>();

    // �ӵ��б�
    List<Bullet> bullets = new ArrayList<>();

    // ��ը�б�
    List<Bomb> bombs =new ArrayList<>();

    // ����
    public GameFrame() {
        setTitle("����ս������");
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        addKeyListener(new keyListener());
        // ���ڹر��¼�
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // ˫����
    Image offscreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offscreenImage == null) {
            offscreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOff = offscreenImage.getGraphics();
        Color c = gOff.getColor();
        gOff.setColor(Color.BLACK);
        gOff.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOff.setColor(c);
        paint(gOff);
        g.drawImage(offscreenImage, 0, 0, null);
    }

    public void paint(java.awt.Graphics g) {
        playership.paint(g);

        // ���ӵ�
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        // ��̹��
        for (int i = 0; i < enemyShips.size(); i++) {
            enemyShips.get(i).paint(g);
        }

        //����ը
        for(int i=0;i<bombs.size();i++){
            bombs.get(i).paint(g);
            frames++;
            if(frames>=30){
                bombs.remove(i);
                frames=0;
            }
        }


        //��ײ���
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemyShips.size(); j++) {
                bullets.get(i).collideWith(enemyShips.get(j));
            }
        }

    }

    // ���̼�����
    class keyListener extends KeyAdapter {

        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;

                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;

                case KeyEvent.VK_UP:
                    bU = true;
                    break;

                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;

                default:
                    break;
            }

            setShipDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;

                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;

                case KeyEvent.VK_UP:
                    bU = false;
                    break;

                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;

                case KeyEvent.VK_SPACE:
                    playership.fire();
                    break;

                default:
                    break;
            }

            setShipDir();

        }

        private void setShipDir() {
            if (!bL && !bR && !bU && !bD)
                playership.setMoving(false);
            else {
                playership.setMoving(true);

                if (bL)
                    playership.setDir(Dir.L);
                if (bR)
                    playership.setDir(Dir.R);
                if (bU)
                    playership.setDir(Dir.U);
                if (bD)
                    playership.setDir(Dir.D);
            }
        }

    }

    /*
     * //��ʼ����
     * 
     * @Override
     * public void paint(Graphics g) {
     * 
     * g.drawImage(GameUtils.bgImg, 0, 0, null);
     * g.drawImage(GameUtils.earth, 720, 120, null);
     * g.setColor(Color.white);
     * g.setFont(new Font("����", Font.PLAIN, 40));
     * 
     * String title = "����ս������R.P.G.";
     * int wordWidth = g.getFontMetrics().stringWidth(title);
     * g.drawString("����ս������R.P.G.", (width - wordWidth) / 2+20, 400);
     * }
     */

}
