package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BulletBase {
    public int SPEED;

    // ͼƬ
    public BufferedImage image;

    public int WIDTH;
    public int HEIGHT;

    // ��ײ����
    Rectangle rect;

    // λ��
    public int x, y;

    // ����
    public Dir dir;

    // �˺�
    protected int hurt;

    // ��͸����
    protected int pierce;

    // ���ô���
    public GameFrame gf;

    // �ǵ�����
    public Group group;

    // �ж�����
    public boolean living;

    // ���ʱ��
    protected int frames;

    // �����ʱ��
    protected int maxFrames;

    public BulletBase() {
    }

    public void paint(Graphics g) {
    }

    public void move() {
        switch (dir) {
            case L:
                x -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case U:
                y -= SPEED;
                break;
            case D:
                y += SPEED;
                break;
        }

        // �ı���ײ����
        rect.x = this.x;
        rect.y = this.y;

        // �߽���
        if (x < 0 || y < 0 || x > GameFrame.GAME_WIDTH || y > GameFrame.GAME_HEIGHT) {
            living = false;
        }
    }

    // ��ɴ���ײ���
    public void collideWith(ShipPlayer ship) {

        if (rect.intersects(ship.rect)) {
            if (ship.shieldValue > 0) {
                ship.shieldValue -= this.hurt;

                if (ship.shieldValue < 0) {
                    ship.hp += ship.shieldValue;
                }

            } else {
                ship.hp -= this.hurt;
            }

            if(ship.hp<=0) {
                ship.die();
                ship.Bomb();
                this.pierce-=ship.Armorthick;
            } else {
                this.die();
            }

        }
    }

    public void collideWith(ShipEnemy ship) {

        if (rect.intersects(ship.rect)) {
            if (ship.shieldValue > 0) {
                ship.shieldValue -= this.hurt;

                if (ship.shieldValue < 0) {
                    ship.hp += ship.shieldValue;
                }

            } else {
                ship.hp -= this.hurt;
            }

            if(ship.hp<=0) {
                ship.die();
                ship.Bomb();
                this.pierce-=ship.Armorthick;
            } else {
                this.die();
            }

        }
    }

    public void remove() {
        if (this.group == Group.Bad) {
            this.gf.enemyBullets.remove(this);
        }

        if (this.group == Group.Good) {
            this.gf.playerBullets.remove(this);
        }
    }

    public void die() {
        this.living = false;
    }

    // ���ʱ���ж�
    public void framesCheck() {
        if (this.frames > this.maxFrames) {
            this.living = false;
        }
    }

}
