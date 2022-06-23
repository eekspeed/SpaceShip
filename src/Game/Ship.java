package Game;

import java.awt.*;
import java.util.Random;

public class Ship {
    private int x, y;
    private Dir dir = Dir.R;
    private static final int SPEED = 5;

    public static int WIDTH = ResourceManager.playerShip_L.getWidth();
    public static int HEIGHT = ResourceManager.playerShip_L.getHeight();

    //碰撞矩形
    Rectangle rect = new Rectangle();

    // 随机数
    private Random random = new Random();

    // 初始默认移动
    private boolean moving = true;

    // 判断生死
    private boolean living = true;

    // 是敌是友
    private Group group = Group.Bad;

    // 引用窗口
    private GameFrame gf = null;

    public Ship(int x, int y, Dir dir, Group group, GameFrame gf) {
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


    //画飞船
    public void paint(Graphics g) {
        if (!living) {
            gf.enemyShips.remove(this);
        }

        if(group==Group.Good){
            g.drawImage(ResourceManager.playerShip_L, x, y, null);
        } else{
            g.drawImage(ResourceManager.enemy_boss, x, y, null);
        }
        

        move();
    }

    public void move() {
        if (!moving) {
            return;
        }

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

        //边界检测
        boundsCheck();

        //改变碰撞矩形
        rect.x=this.x;
        rect.y=this.y;

        //随机开火
        if (this.group == Group.Bad && random.nextInt(100) > 95) {
            this.fire();
        }

        //随机变向
        if (this.group == Group.Bad && random.nextInt(100) > 95) {
            randomDir();
        }
        

    }

    //开火
    public void fire() {
        int bx = x + Ship.WIDTH / 2 - Bullet.WIDTH;
        int by = y + Ship.HEIGHT / 2 - Bullet.HEIGHT;
        gf.bullets.add(new Bullet(bx, by, dir, group, gf));

    }

    //六+4/4成几率往左
    private void randomDir() {
        if(random.nextInt(5)>2){
            this.dir=Dir.L;
            return;
        }
        this.dir=Dir.values()[random.nextInt(4)];
    }

    private void boundsCheck(){
        if(this.x<0){x=0;}
        if(this.y<30){y=30;}
        if(this.x>GameFrame.GAME_WIDTH-Ship.WIDTH){x=GameFrame.GAME_WIDTH-Ship.WIDTH;}
        if(this.y>GameFrame.GAME_HEIGHT-Ship.HEIGHT){y=GameFrame.GAME_HEIGHT-Ship.HEIGHT;}

    }



    public void die() {
        this.living = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
