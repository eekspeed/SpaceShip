package Game;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameFrame extends Frame {

    public static final int GAME_WIDTH = 1600;
    public static final int GAME_HEIGHT = 800;

    int x1 = 0, x2 = GAME_WIDTH;

    // ɱ�л���
    public int score = 0;

    // ���ڵ�ǰ״̬
    public int X = 0;

    //�з��ɴ�������Ϣ��������ţ���Ƶ��д�����ʹ������
    List<Integer> enemyDie = new LinkedList<>();

    // ��ҷɴ��б�
    List<ShipPlayer> playerShips = new ArrayList<>();

    int playerShipStyle = 0;

    // �ڶ���ҷɴ��б�
    List<ShipPlayer> secPlayerShips = new ArrayList<>();

    int secPlayerStyle = 0;

    // �ѷ��ɴ��б�
    List<ShipPlayer> friendShips = new ArrayList<>();

    // �з��ɴ��б�
    List<ShipEnemy> enemyShips = new ArrayList<>();

    // �ҷ��ӵ��б�
    List<BulletBase> playerBullets = new ArrayList<>();

    // �з��ӵ��б�
    List<BulletBase> enemyBullets = new ArrayList<>();

    // ��ը�б�
    List<Bomb> bombs = new ArrayList<>();

    // ���������û����ɿͻ�������
    int whoEnable;

    // ����
    public GameFrame(int whoEnable) {
        this.whoEnable = whoEnable;

        setTitle("����ս������");
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        addKeyListener(new keyListener());
        addMouseListener(new MouseListener());
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

    public void paint(Graphics g) {
        if (X == 0) {
            // ��ʼ����
            g.drawImage(ResourceManager.background, 0, 0, null);
            g.drawImage(ResourceManager.earth, 720, 120, null);
            g.setColor(Color.white);
            g.setFont(new Font("����", Font.PLAIN, 40));

            String title = "����ս������R.P.G.";
            String help = "�����ʼ��Ϸ";
            int wordWidth1 = g.getFontMetrics().stringWidth(title);
            int wordWidth2 = g.getFontMetrics().stringWidth(help);

            g.drawString(title, (GAME_WIDTH - wordWidth1) / 2, 400);
            g.drawString(help, (GAME_WIDTH - wordWidth2) / 2, 600);

        } else {

            // ��ײ���
            if(whoEnable==0){
                collidetest();
            } else {
                pseudoCollidetest();
            }

            // !������(�����������ȣ�����)
            paintBackground(g);

            // �����������򽢶�
            if (score >= 40 && playerShipStyle == 0) {
                g.setColor(Color.red);
                g.setFont(new Font("����", Font.PLAIN, 20));
                g.drawString("��Alt������������Ϊ������ս����", 500, 60);
            } else if (score >= 120 && playerShipStyle == 1){
                g.setColor(Color.red);
                g.setFont(new Font("����", Font.PLAIN, 20));
                g.drawString("��Alt������������Ϊ������ս����", 500, 60);
            }

            // ������
            paintScore(g);

            /* �������ﳢ�Թ��̳߳ض��̻߳�ͼ����������ʱƵ����ָ���������������� */

            // ����ҷɴ�
            if (playerShips.size() > 0) {
                if (playerShips.get(playerShipStyle).x == -200) {
                    playerShips.get(playerShipStyle).x = 200;
                }
                playerShips.get(playerShipStyle).paint(g);
            }

            // ���ڶ���ҷɴ�
            if (secPlayerShips.size() > 0) {
                if (secPlayerShips.get(secPlayerStyle).x == -200) {
                    secPlayerShips.get(secPlayerStyle).x = 200;
                }
                secPlayerShips.get(secPlayerStyle).paint(g);
            }

            // ���ѷ��ɴ�
            if (friendShips.size() > 0) {
                for (int i = 0; i < friendShips.size(); i++) {
                    friendShips.get(i).paint(g);
                }
            }

            // ���з��ɴ�
            for (int i = 0; i < enemyShips.size(); i++) {
                enemyShips.get(i).paint(g);
            }

            // ���ҷ��ӵ�
            for (int i = 0; i < playerBullets.size(); i++) {
                playerBullets.get(i).paint(g);
            }

            // ���з��ӵ�
            for (int i = 0; i < enemyBullets.size(); i++) {
                enemyBullets.get(i).paint(g);
            }

            // ����ը
            for (int i = 0; i < bombs.size(); i++) {
                if (bombs.get(i).frames >= 30) {
                    bombs.remove(i);
                }
                bombs.get(i).paint(g);
            }

            // if(X==2){
            //     win();
            // }

            // if(X==3){
            //     gameover();
            // }
            
        }

    }

    // ��ײ��ⷽ��
    private void collidetest() {

        for (int i = 0; i < playerBullets.size(); i++) {
            for (int j = 0; j < enemyShips.size(); j++) {
                playerBullets.get(i).collideWith(enemyShips.get(j));
            }
        }

        for (int i = 0; i < enemyBullets.size(); i++) {
            for (int j = 0; j < playerShips.size(); j++) {
                enemyBullets.get(i).collideWith(playerShips.get(j));
            }
            for (int j = 0; j < secPlayerShips.size(); j++) {
                enemyBullets.get(i).collideWith(secPlayerShips.get(j));
            }
        }

        // �ӵ���ײ���
        for (int i = 0; i < playerBullets.size(); i++) {
            for (int j = 0; j < enemyBullets.size(); j++) {
                if (playerBullets.get(i).rect.intersects(enemyBullets.get(j).rect)) {
                    playerBullets.get(i).pierce -= enemyBullets.get(j).pierce;
                    enemyBullets.get(j).pierce -= playerBullets.get(i).pierce;

                    if(playerBullets.get(i).pierce<=0){
                        playerBullets.get(i).die();
                    }

                    if(enemyBullets.get(j).pierce<=0){
                        enemyBullets.get(j).die();
                    }

                }
            }
        }

    }

    private void pseudoCollidetest() {
        for (int i = 0; i < playerBullets.size(); i++) {
            for (int j = 0; j < enemyShips.size(); j++) {

                if(playerBullets.get(i).rect.intersects(enemyShips.get(j).rect)){
                    playerBullets.get(i).die();
                }

            }
        }

        for (int i = 0; i < enemyBullets.size(); i++) {
            for (int j = 0; j < playerShips.size(); j++) {

                if(enemyBullets.get(i).rect.intersects(playerShips.get(j).rect)){
                    enemyBullets.get(i).die();
                }
            }
            for (int j = 0; j < secPlayerShips.size(); j++) {

                if(enemyBullets.get(i).rect.intersects(secPlayerShips.get(j).rect)){
                    enemyBullets.get(i).die();
                }

            }
        }

        // �ӵ���ײ���
        for (int i = 0; i < playerBullets.size(); i++) {
            for (int j = 0; j < enemyBullets.size(); j++) {
                if (playerBullets.get(i).rect.intersects(enemyBullets.get(j).rect)) {
                    playerBullets.get(i).pierce -= enemyBullets.get(j).pierce;
                    enemyBullets.get(j).pierce -= playerBullets.get(i).pierce;

                    if(playerBullets.get(i).pierce<=0){
                        playerBullets.get(i).die();
                    }

                    if(enemyBullets.get(j).pierce<=0){
                        enemyBullets.get(j).die();
                    }

                }
            }
        }
    }

    // ����������
    private void paintBackground(Graphics g) {
        g.drawImage(ResourceManager.background, x1, 0, null);
        g.drawImage(ResourceManager.background, x2, 0, null);

        x1 -= 2;
        x2 -= 2;
        if (x1 <= -GameFrame.GAME_WIDTH) {
            x1 = GameFrame.GAME_WIDTH;
        }
        if (x2 <= -GameFrame.GAME_WIDTH) {
            x2 = GameFrame.GAME_WIDTH;
        }
    }

    private void paintScore(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("����", Font.PLAIN, 20));

        g.drawString("��ǰ����Ϊ", 20, 60);
        g.drawString(String.valueOf(score), 140, 60);
    }

    // ���̼����� ������
    class keyListener extends KeyAdapter {

        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_A:
                    bL = true;
                    break;

                case KeyEvent.VK_D:
                    bR = true;
                    break;

                case KeyEvent.VK_W:
                    bU = true;
                    break;

                case KeyEvent.VK_S:
                    bD = true;
                    break;

                case KeyEvent.VK_ALT:
                    if (score >= 40 && playerShipStyle == 0) {
                        playerShipStyle = 1;
                        score -= 40;
                    } else if (score >= 120 && playerShipStyle == 1) {
                        playerShipStyle = 2;
                        score -= 120;
                    }
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
                case KeyEvent.VK_A:
                    bL = false;
                    break;

                case KeyEvent.VK_D:
                    bR = false;
                    break;

                case KeyEvent.VK_W:
                    bU = false;
                    break;

                case KeyEvent.VK_S:
                    bD = false;
                    break;

                case KeyEvent.VK_SPACE:
                    playerShips.get(playerShipStyle).fire();
                    break;

                default:
                    break;
            }

            setShipDir();

        }

        private void setShipDir() {
            if (!bL && !bR && !bU && !bD)
                playerShips.get(playerShipStyle).moving = false;
            else {
                playerShips.get(playerShipStyle).moving = true;

                if (bL)
                    playerShips.get(playerShipStyle).dir = Dir.L;
                if (bR)
                    playerShips.get(playerShipStyle).dir = Dir.R;
                if (bU)
                    playerShips.get(playerShipStyle).dir = Dir.U;
                if (bD)
                    playerShips.get(playerShipStyle).dir = Dir.D;
            }
        }

    }

    // ��������
    class MouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == 1 && X == 0) {
                X = 1;
                repaint();
            }
        }
    }

}
