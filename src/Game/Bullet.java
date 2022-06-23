package Game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
    private static final int SPEED = 10;

    public static int WIDTH = ResourceManager.bullet_laser01.getWidth();
    public static int HEIGHT = ResourceManager.bullet_laser01.getHeight();

    //碰撞矩形
    Rectangle rect = new Rectangle();

    // 位置
    private int x, y;
    // 方向
    private Dir dir;

    // 引用窗口
    private GameFrame gf;

    // 是敌是友
    private Group group = Group.Bad;

    // 判断生死
    private boolean living = true;

    public Bullet(int x, int y, Dir dir, Group group, GameFrame gf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gf = gf;

        rect.x=this.x;
        rect.y=this.y;
        rect.width=WIDTH;
        rect.height=HEIGHT;

    }

    public void paint(Graphics g) {
        if (!living) {
            gf.bullets.remove(this);
        }

        g.drawImage(ResourceManager.bullet_laser01, x, y, null);

        move();

    }

    private void move() {
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

        //改变碰撞矩形
        rect.x=this.x;
        rect.y=this.y;

        if (x < 0 || y < 0 || x > GameFrame.GAME_WIDTH || y > GameFrame.GAME_HEIGHT) {
            living = false;
        }
    }

    //碰撞检测
    public void collideWith(Ship ship) {
        if(this.group==ship.getGroup()){
            return;
        }

        if (rect.intersects(ship.rect)) {
            ship.die();
            this.die();
            int bx =ship.getX() + Ship.WIDTH/2 -Bomb.WIDTH_LL/2;
            int by =ship.getY() + Ship.HEIGHT/2 -Bomb.HEIGHT_LL/2;
            gf.bombs.add(new Bomb(bx, by, gf));
        }
    }

    private void die() {
        this.living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
