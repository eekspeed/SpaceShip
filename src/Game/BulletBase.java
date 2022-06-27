package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BulletBase {
    public int SPEED;

    // 图片
    public BufferedImage image;

    public int WIDTH;
    public int HEIGHT;

    // 碰撞矩形
    Rectangle rect;

    // 位置
    public int x, y;

    // 方向
    public Dir dir;

    // 伤害
    protected int hurt;

    // 穿透能力
    protected int pierce;

    // 引用窗口
    public GameFrame gf;

    // 是敌是友
    public Group group;

    // 判断生死
    public boolean living;

    // 存活时间
    protected int frames;

    // 最大存活时间
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

        // 改变碰撞矩形
        rect.x = this.x;
        rect.y = this.y;

        // 边界检测
        if (x < 0 || y < 0 || x > GameFrame.GAME_WIDTH || y > GameFrame.GAME_HEIGHT) {
            living = false;
        }
    }

    // 与飞船碰撞检测
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

    // 存活时间判断
    public void framesCheck() {
        if (this.frames > this.maxFrames) {
            this.living = false;
        }
    }

}
