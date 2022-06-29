package Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ShipBase {
    // ͼƬ
    public BufferedImage image;
    public BufferedImage imageBomb;

    // λ��
    public int x, y;

    // �˶�����
    public Dir dir;

    // �ٶ�
    public int speed;

    // ����ֵ
    protected int shieldValue;

    // ��󻤶�ֵ
    protected int maxShieldValue;

    // װ�׺�ȣ����ˣ��谭��͸��
    protected int Armorthick;

    // ����ֵ��Ѫ����
    protected int hp;

    // �����ֵ
    protected int maxHp;

    // ���ȸ߶�
    public int WIDTH;
    public int HEIGHT;

    // ��ײ����
    Rectangle rect;

    // ��ʼĬ���ƶ�
    public boolean moving;

    // �ж�����
    public boolean living;

    // �ǵ�����
    public Group group;

    // ���ô���
    protected GameFrame gf;

    // ֡��
    protected int frames;

    // ���ڴ������ݵ����ʹ���
    public String code;

    // ������ţ�����������Ϣɾ����
    public int dieCode;

    // �жϸô�������Ϣ�Ƿ��Ѿ�����
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
