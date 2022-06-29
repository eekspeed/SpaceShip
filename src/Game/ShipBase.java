package Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ShipBase {
    // 图片
    public BufferedImage image;
    public BufferedImage imageBomb;

    // 位置
    public int x, y;

    // 运动方向
    public Dir dir;

    // 速度
    public int speed;

    // 护盾值
    protected int shieldValue;

    // 最大护盾值
    protected int maxShieldValue;

    // 装甲厚度（减伤，阻碍穿透）
    protected int Armorthick;

    // 船体值（血量）
    protected int hp;

    // 最大船体值
    protected int maxHp;

    // 长度高度
    public int WIDTH;
    public int HEIGHT;

    // 碰撞矩形
    Rectangle rect;

    // 初始默认移动
    public boolean moving;

    // 判断生死
    public boolean living;

    // 是敌是友
    public Group group;

    // 引用窗口
    protected GameFrame gf;

    // 帧数
    protected int frames;

    // 用于传输数据的类型代号
    public String code;

    // 自身代号（用于死亡信息删除）
    public int dieCode;

    // 判断该船存在信息是否已经发送
    public boolean havesend;

    public ShipBase() {
        this.havesend = false;
    }

    public void paint(Graphics g) {
    }

    public void move() {
    }

    public void fire() {
    }

    public void boundsCheck() {
        if (this.x < 5 && this.x > -10) {
            x = 5;
        }
        if (this.y < 30 && this.y > -10) {
            y = 30;
        }
        if (this.x > GameFrame.GAME_WIDTH - this.WIDTH - 10 && this.x < GameFrame.GAME_WIDTH + 10) {
            x = GameFrame.GAME_WIDTH - this.WIDTH - 10;
        }
        if (this.y > GameFrame.GAME_HEIGHT - this.HEIGHT - 10 && this.y < GameFrame.GAME_HEIGHT + 10) {
            y = GameFrame.GAME_HEIGHT - this.HEIGHT - 10;
        }
    }

    public void die() {
        this.living = false;
    }

    public void Bomb() {
        int bx = this.x + this.WIDTH / 2 - imageBomb.getWidth() / 2;
        int by = this.y + this.HEIGHT / 2 - imageBomb.getHeight() / 2;
        this.gf.bombs.add(new Bomb(imageBomb, bx, by));
    }

    public void paintShield(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y - 24, (int) (1.0 * this.shieldValue / this.maxShieldValue * this.WIDTH), 3);
    }

    public void paintHp(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y - 18, (int) (1.0 * this.hp / this.maxHp * this.WIDTH), 3);
    }
}
