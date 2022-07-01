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
import java.util.Random;

public class GameFrame extends Frame {

    public static final int GAME_WIDTH = 1600;
    public static final int GAME_HEIGHT = 800;

    int x1 = 0, x2 = GAME_WIDTH;

    int temp;

    // 杀敌积分
    public int score = 0;

    // 窗口当前状态
    public int X = 0;

    // 敌方飞船死亡信息（自身代号），频繁写入清空使用链表
    List<Integer> enemyDie = new LinkedList<>();

    // 玩家飞船列表
    List<ShipPlayer> playerShips = new ArrayList<>();

    int playerShipStyle = 0;

    // 第二玩家飞船列表
    List<ShipPlayer> secPlayerShips = new ArrayList<>();

    int secPlayerStyle = 0;

    // 友方飞船列表
    List<ShipPlayer> friendShips = new ArrayList<>();

    // 敌方飞船列表
    List<ShipEnemy> enemyShips = new ArrayList<>();

    // 我方子弹列表
    List<BulletBase> playerBullets = new ArrayList<>();

    // 敌方子弹列表
    List<BulletBase> enemyBullets = new ArrayList<>();

    // 爆炸列表
    List<Bomb> bombs = new ArrayList<>();

    // 由主机启用还是由客户端启用
    int whoEnable;

    // 窗口
    public GameFrame(int whoEnable) {
        this.whoEnable = whoEnable;

        setTitle("宇宙战舰物语");
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        addKeyListener(new keyListener());
        addMouseListener(new MouseListener());
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

    public void paint(Graphics g) {
        if (X == 0) {
            // 开始界面
            g.drawImage(ResourceManager.background, 0, 0, null);
            g.drawImage(ResourceManager.earth, 720, 120, null);
            g.setColor(Color.white);
            g.setFont(new Font("黑体", Font.PLAIN, 40));

            String title = "宇宙战舰物语R.P.G.";
            String help = "点击开始游戏";
            int wordWidth1 = g.getFontMetrics().stringWidth(title);
            int wordWidth2 = g.getFontMetrics().stringWidth(help);

            g.drawString(title, (GAME_WIDTH - wordWidth1) / 2, 400);
            g.drawString(help, (GAME_WIDTH - wordWidth2) / 2, 600);

        } else {

            // 碰撞检测
            if (whoEnable == 0) {
                collidetest();
            } else {
                pseudoCollidetest();
            }

            // !画背景(背景必须最先！！！)
            paintBackground(g);

            // 积分升级
            if (score >= 40 && playerShipStyle == 0) {
                g.setColor(Color.red);
                g.setFont(new Font("黑体", Font.PLAIN, 20));
                g.drawString("按Alt键将主舰升级为“中型战舰”", 400, 60);
            } else if (score >= 120 && playerShipStyle == 1) {
                g.setColor(Color.red);
                g.setFont(new Font("黑体", Font.PLAIN, 20));
                g.drawString("按Alt键将主舰升级为“重型战舰”", 400, 60);
            }

            // 升级武器
            armsLevelUp(g);

            // 画分数
            paintScore(g);

            /* 曾经这里尝试过线程池多线程绘图，但在运行时频发空指针错误死机，遂放弃 */

            // 画玩家飞船
            if (playerShips.size() > 0) {
                if (playerShips.get(playerShipStyle).x == -200) {
                    playerShips.get(playerShipStyle).x = 200;
                }
                playerShips.get(playerShipStyle).paint(g);
            }

            // 画第二玩家飞船
            if (secPlayerShips.size() > 0) {
                if (secPlayerShips.get(secPlayerStyle).x == -200) {
                    secPlayerShips.get(secPlayerStyle).x = 200;
                }
                secPlayerShips.get(secPlayerStyle).paint(g);
            }

            // 画友方飞船
            if (friendShips.size() > 0) {
                for (int i = 0; i < friendShips.size(); i++) {
                    friendShips.get(i).paint(g);
                }
            }

            // 画敌方飞船
            for (int i = 0; i < enemyShips.size(); i++) {
                enemyShips.get(i).paint(g);
            }

            // 画我方子弹
            for (int i = 0; i < playerBullets.size(); i++) {
                playerBullets.get(i).paint(g);
            }

            // 画敌方子弹
            for (int i = 0; i < enemyBullets.size(); i++) {
                enemyBullets.get(i).paint(g);
            }

            // 画爆炸
            for (int i = 0; i < bombs.size(); i++) {
                if (bombs.get(i).frames >= 30) {
                    bombs.remove(i);
                }
                bombs.get(i).paint(g);
            }

            if (X == 2) {
                win(g);
            }

            if (X == 3) {
                gameover(g);
            }

        }

    }

    private void gameover(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑", Font.PLAIN, 60));
        g.drawString("主舰损毁！您失败了！", 550, 400);
    }

    private void win(Graphics g) {
        g.setColor(Color.yellow);
        g.setFont(new Font("微软雅黑", Font.PLAIN, 60));
        g.drawString("宙域清空！您胜利了！", 550, 400);
    }

    private void armsLevelUp(Graphics g) {
        g.setColor(Color.green);
        g.setFont(new Font("黑体", Font.PLAIN, 20));

        if (score >= 20 && ArmGun.level == 0) {
            g.drawString("按1键将弹幕“双联装机铳”升级为“三联装机铳”", 750, 60);
        }

        if (score >= 40 && ArmGun.level == 1) {
            g.drawString("按1键将弹幕“三联装机铳”升级为“四联装机铳”", 750, 60);
        }

        if (score >= 50 && ArmLaser.level == 0) {
            g.drawString("按2键将副炮“激光三门炮”升级为“激光五门炮”", 750, 90);
        }

        if (score >= 80 && ArmLaser.level == 1) {
            g.drawString("按2键将副炮“激光五门炮”升级为“激光七门炮”", 750, 90);
        }

        if (score >= 150 && ArmArt.level == 0) {
            g.drawString("按3键将主炮“电磁加农炮”升级为“电磁加农炮阵列”", 750, 120);
        }

        if (score >= 80 && ArmEngine.level == 0) {
            g.drawString("按4键将引擎“核裂变引擎”升级为“核聚变引擎”", 750, 150);
        }

        if (score >= 120 && ArmEngine.level == 1) {
            g.drawString("按4键将引擎“核聚变引擎”升级为“冷核聚变引擎”", 750, 150);
        }

        if (score >= 160 && ArmEngine.level == 2) {
            g.drawString("按4键将引擎“冷核聚变引擎”升级为“反物质引擎”", 750, 150);
        }

        if (score >= 200 && ArmEngine.level == 3) {
            g.drawString("按4键将引擎“反物质引擎”升级为“零点引擎”", 750, 150);
        }
    }

    // 碰撞检测方法
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

        // 子弹碰撞检测
        for (int i = 0; i < playerBullets.size(); i++) {
            for (int j = 0; j < enemyBullets.size(); j++) {
                if (playerBullets.get(i).rect.intersects(enemyBullets.get(j).rect)) {
                    playerBullets.get(i).pierce -= enemyBullets.get(j).pierce;
                    enemyBullets.get(j).pierce -= playerBullets.get(i).pierce;

                    if (playerBullets.get(i).pierce <= 0) {
                        playerBullets.get(i).die();
                    }

                    if (enemyBullets.get(j).pierce <= 0) {
                        enemyBullets.get(j).die();
                    }

                }
            }
        }

    }

    // 用于客户端的伪碰撞，无伤害，撞上就消失
    private void pseudoCollidetest() {
        for (int i = 0; i < playerBullets.size(); i++) {
            for (int j = 0; j < enemyShips.size(); j++) {

                if (playerBullets.get(i).rect.intersects(enemyShips.get(j).rect)) {
                    playerBullets.get(i).die();
                }

            }
        }

        for (int i = 0; i < enemyBullets.size(); i++) {
            for (int j = 0; j < playerShips.size(); j++) {

                if (enemyBullets.get(i).rect.intersects(playerShips.get(j).rect)) {
                    enemyBullets.get(i).die();
                }
            }
            for (int j = 0; j < secPlayerShips.size(); j++) {

                if (enemyBullets.get(i).rect.intersects(secPlayerShips.get(j).rect)) {
                    enemyBullets.get(i).die();
                }

            }
        }

        // 子弹碰撞检测
        for (int i = 0; i < playerBullets.size(); i++) {
            for (int j = 0; j < enemyBullets.size(); j++) {
                if (playerBullets.get(i).rect.intersects(enemyBullets.get(j).rect)) {
                    temp =playerBullets.get(i).pierce;
                    playerBullets.get(i).pierce -= enemyBullets.get(j).pierce;
                    enemyBullets.get(j).pierce -= temp;

                    if (playerBullets.get(i).pierce <= 0) {
                        playerBullets.get(i).die();
                    }

                    if (enemyBullets.get(j).pierce <= 0) {
                        enemyBullets.get(j).die();
                    }

                }
            }
        }
    }

    // 画背景方法
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
        g.setFont(new Font("黑体", Font.PLAIN, 20));

        g.drawString("当前分数为", 20, 60);
        g.drawString(String.valueOf(score), 140, 60);
    }

    // 键盘监听类 变向功能
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

                case KeyEvent.VK_1:
                    if (score >= 10 && ArmGun.level == 0) {
                        ArmGun.level = 1;
                        score -= 10;
                    } else if (score >= 20 && ArmGun.level == 1) {
                        ArmGun.level = 2;
                        score -= 20;
                    }
                    break;

                case KeyEvent.VK_2:
                    if (score >= 30 && ArmLaser.level == 0) {
                        ArmLaser.level = 1;
                        score -= 30;
                    } else if (score >= 60 && ArmLaser.level == 1) {
                        ArmLaser.level = 2;
                        score -= 60;
                    }
                    break;

                case KeyEvent.VK_3:
                    if (score >= 100 && ArmArt.level == 0) {
                        ArmArt.level = 1;
                        score -= 100;
                    }
                    break;

                case KeyEvent.VK_4:
                    if (score >= 80 && ArmEngine.level == 0) {
                        ArmEngine.level = 1;
                        score -= 80;
                    }

                    if (score >= 120 && ArmEngine.level == 1) {
                        ArmEngine.level = 2;
                        score -= 120;
                    }

                    if (score >= 160 && ArmEngine.level == 2) {
                        ArmEngine.level = 3;
                        score -= 160;
                    }

                    if (score >= 200 && ArmEngine.level == 3) {
                        ArmEngine.level = 4;
                        score -= 200;
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

    // 鼠标监听类
    class MouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == 1 && X == 0) {
                X = 1;
                Sound.stopSound();
                Random rand = new Random();

                if (rand.nextInt(3) == 0) {
                    Sound.playSound("Sound/FightBGM_1.wav");
                } else if (rand.nextInt(3) == 1) {
                    Sound.playSound("Sound/FightBGM_2.wav");
                } else {
                    Sound.playSound("Sound/FightBGM_3.wav");
                }

                repaint();
            } else if (e.getButton() == 1) {
                playerShips.get(playerShipStyle).fire(e.getX(), e.getY());
            }

        }
    }

}
