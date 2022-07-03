package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

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

    // ���ڴ������ݵ����ʹ���
    public String code;

    // �жϸ��ӵ�������Ϣ�Ƿ��Ѿ�����
    public boolean havesend;

    // �ӵ����ƫת�Ƕ�
    public double maxAngle;

    // �ӵ���ǰƫת�Ƕ�
    public double angle;

    // ���
    public int range;

    // �������
    public int FX, FY;

    // �ڷ��ٶȲ���1ʱ�����ۻ�������
    public double tx, ty;

    // �Ƿ�ʼ�ƶ�
    public boolean moving;

    // �����
    public Random random = new Random();

    // �����
    public int r;

    public BulletBase() {
        this.havesend = false;
    }

    public void paint(Graphics g) {

        if (this.moving == false) {
            this.moving = true;

            this.angle = Math.toRadians(this.random.nextDouble() * this.maxAngle);

            changeImage();
            point();

        }

        move();

        if (!living) {
            remove();
        }

        g.drawImage(this.image, this.x, this.y, null);

        rectmove();
    }

    public void rectmove() {
        if (this.rect.width == this.WIDTH) {
            this.rect.x = this.x;
        } else {
            this.rect.x = this.x + this.WIDTH / 2 - this.rect.width / 2;
        }

        if (this.rect.height == this.HEIGHT) {
            this.rect.y = this.y;
        } else {
            this.rect.y = this.y + this.HEIGHT / 2 - this.rect.height / 2;
        }
    }

    public void changeImage() {
        if (dir == Dir.R) {
            if (FY > y) {
                image = ImageUtil.rotateImage(image, angle);
            } else if (FY < y) {
                image = ImageUtil.rotateImage(image, -angle);
            }
        }

        if (dir == Dir.L) {
            if (FY > y) {
                image = ImageUtil.rotateImage(image, 180 + angle);
            } else if (FY < y) {
                image = ImageUtil.rotateImage(image, 180 - angle);
            }
        }

        if (dir == Dir.U) {
            if (FX > x) {
                image = ImageUtil.rotateImage(image, -90 + angle);
            } else if (FY < x) {
                image = ImageUtil.rotateImage(image, -90 - angle);
            }
        }

        if (dir == Dir.D) {
            if (FX > x) {
                image = ImageUtil.rotateImage(image, 90 - angle);
            } else if (FY < x) {
                image = ImageUtil.rotateImage(image, 90 + angle);
            }
        }
    }

    public void move() {
        if (this.dir == Dir.R) {

            this.x += this.SPEED * Math.cos(this.angle);

            if (this.FY > this.y) {
                this.ty += SPEED * Math.sin(this.angle);

            } else if (this.FY < this.y) {
                this.ty -= this.SPEED * Math.sin(this.angle);
            }

            if (this.ty > 1 || this.ty < -1) {
                this.y  += this.ty;
                this.ty  = 0;
            }

            boundsCheck();
            if (this.x >= this.FX) {
                die();
            }

        }

        if (this.dir == Dir.L) {

            this.x -= this.SPEED * Math.cos(this.angle);

            if (this.FY > this.y) {
                this.ty += this.SPEED * Math.sin(this.angle);
            } else if (this.FY < this.y) {
                this.ty -= this.SPEED * Math.sin(this.angle);
            }

            if (this.ty > 1 || this.ty < -1) {
                this.y  += this.ty;
                this.ty  = 0;
            }

            boundsCheck();
            if (this.x <= this.FX) {
                die();
            }

        }

        if (this.dir == Dir.U) {

            this.y -= this.SPEED * Math.cos(this.angle);

            if (this.FX > this.x) {
                this.tx += this.SPEED * Math.sin(this.angle);
            } else if (this.FY < this.x) {
                this.tx -= this.SPEED * Math.sin(this.angle);
            }

            if (this.tx > 1 || this.tx < -1) {
                this.x  += this.tx;
                this.tx  = 0;
            }

            boundsCheck();
            if (this.y <= this.FY) {
                die();
            }

        }

        if (this.dir == Dir.D) {

            this.y += this.SPEED * Math.cos(this.angle);

            if (this.FX > this.x) {
                this.tx += this.SPEED * Math.sin(this.angle);
            } else if (this.FX < this.x) {
                this.tx -= this.SPEED * Math.sin(this.angle);
            }

            if (this.tx > 1 || this.tx < -1) {
                this.x  += tx;
                this.tx  = 0;
            }

            boundsCheck();
            if (this.y >= this.FY) {
                die();
            }

        }
    }

    // ��ɴ���ײ���
    public void collideWith(ShipPlayer ship) {

        if (this.rect.intersects(ship.rect)) {
            if (ship.shieldValue > 0) {

                if(ship.Armorthick < this.hurt) {
                    ship.shieldValue -= this.hurt - ship.Armorthick;

                    if (ship.shieldValue < 0) {
                        ship.hp += ship.shieldValue;
                    }
                }

            } else {
                ship.hp -= this.hurt;
            }

            if (ship.hp <= 0) {
                ship.die();
                this.pierce -= ship.Armorthick;
            } else {
                this.die();
            }

        }
    }

    public void collideWith(ShipEnemy ship) {

        if (this.rect.intersects(ship.rect)) {
            if (ship.shieldValue > 0) {
                ship.shieldValue -= this.hurt;

                if (ship.shieldValue < 0) {
                    ship.hp += ship.shieldValue;
                }

            } else {
                ship.hp -= this.hurt;
            }

            if (ship.hp <= 0) {
                ship.die();
                this.pierce -= ship.Armorthick;
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

    // ���ݷ���ȷ����Ҫ�ƶ�����xyֵ
    public void point() {
        if (this.dir == Dir.R) {

            this.FX = (int) (this.x + this.range * Math.cos(this.angle));
            this.r  = random.nextInt(3);

            if (this.r == 0) {
                this.FY = this.y;
            } else if (this.r == 1) {
                this.FY = (int) (this.y + this.range * Math.sin(this.angle));
            } else {
                this.FY = (int) (this.y - this.range * Math.sin(this.angle));
            }

        }

        if (this.dir == Dir.L) {
            this.FX = (int) (this.x - this.range * Math.cos(this.angle));
            this.r  = this.random.nextInt(3);

            if (this.r == 0) {
                this.FY = this.y;
            } else if (this.r == 1) {
                this.FY = (int) (this.y + this.range * Math.sin(this.angle));
            } else {
                this.FY = (int) (this.y - this.range * Math.sin(this.angle));
            }

        }

        if (this.dir == Dir.U) {
            this.FY = (int) (this.y - this.range * Math.cos(this.angle));
            this.r  = this.random.nextInt(3);

            if (this.r == 0) {
                this.FX = this.x;
            } else if (this.r == 1) {
                this.FX = (int) (this.x + this.range * Math.sin(this.angle));
            } else {
                this.FX = (int) (this.x - this.range * Math.sin(this.angle));
            }
        }

        if (this.dir == Dir.D) {
            this.FY = (int) (this.y + this.range * Math.cos(this.angle));
            this.r  = this.random.nextInt(3);

            if (r == 0) {
                this.FX = this.x;
            } else if (r == 1) {
                this.FX = (int) (this.x + this.range * Math.sin(this.angle));
            } else {
                this.FX = (int) (this.x - this.range * Math.sin(this.angle));
            }
        }

    }

    // �߽���
    public void boundsCheck() {
        if (this.x < 0 || this.y < 0 || this.x > GameFrame.GAME_WIDTH || this.y > GameFrame.GAME_HEIGHT) {
            this.living = false;
        }
    }

}
