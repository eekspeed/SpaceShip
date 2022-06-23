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

    //帧数定时
    private int frames =0;

    // 飞船位置
    Ship playership = new Ship(200, 200, Dir.R, Group.Good, this);

    // 敌方飞船列表
    List<Ship> enemyShips = new ArrayList<>();

    // 子弹列表
    List<Bullet> bullets = new ArrayList<>();

    // 爆炸列表
    List<Bomb> bombs =new ArrayList<>();

    // 窗口
    public GameFrame() {
        setTitle("宇宙战舰物语");
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        addKeyListener(new keyListener());
        // 窗口关闭事件
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // 双缓冲
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

        // 画子弹
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        // 画坦克
        for (int i = 0; i < enemyShips.size(); i++) {
            enemyShips.get(i).paint(g);
        }

        //画爆炸
        for(int i=0;i<bombs.size();i++){
            bombs.get(i).paint(g);
            frames++;
            if(frames>=30){
                bombs.remove(i);
                frames=0;
            }
        }


        //碰撞检测
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemyShips.size(); j++) {
                bullets.get(i).collideWith(enemyShips.get(j));
            }
        }

    }

    // 键盘监听类
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
     * //开始界面
     * 
     * @Override
     * public void paint(Graphics g) {
     * 
     * g.drawImage(GameUtils.bgImg, 0, 0, null);
     * g.drawImage(GameUtils.earth, 720, 120, null);
     * g.setColor(Color.white);
     * g.setFont(new Font("黑体", Font.PLAIN, 40));
     * 
     * String title = "宇宙战舰物语R.P.G.";
     * int wordWidth = g.getFontMetrics().stringWidth(title);
     * g.drawString("宇宙战舰物语R.P.G.", (width - wordWidth) / 2+20, 400);
     * }
     */

}
