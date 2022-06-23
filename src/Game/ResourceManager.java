package Game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//可尝试使用单例
public class ResourceManager {
    public static BufferedImage playerShip_L,playerShip_n,playerShip_s;
    public static BufferedImage enemy_gun01,enemy_gun02,enemy_laser,enemy_art,enemy_boss;
    public static BufferedImage bullet_gun,bullet_laser01,bullet_laser02,bullet_art;
    public static BufferedImage bomb_LL,bomb_L,bomb_n,bomb_s;


    static{
        try {
            //三种玩家飞船
            playerShip_L=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("Image/player_L.png"));
            playerShip_n=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("Image/player_n.png"));
            playerShip_s=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("Image/player_s.png"));

            //五种敌方飞船
            enemy_boss = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("Image/enemy_boss.png"));
            enemy_gun01=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("Image/enemy_gun01.png"));
            enemy_gun02=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("Image/enemy_gun02.png"));
            enemy_laser=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("Image/enemy_laser.png"));
            enemy_art=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("Image/enemy_artillery.png"));
            
            //四种子弹
            bullet_gun=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("Image/bullet_gun.png"));
            bullet_laser01=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("Image/bullet_laser01.png"));
            bullet_laser02=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("Image/bullet_laser02.png"));
            bullet_art=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("Image/bullet_artillery.png"));


            //四种爆炸
            bomb_LL=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("Image/bomb_LL.png"));
            bomb_L=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("Image/bomb_L.png"));
            bomb_n=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("Image/bomb_n.png"));
            bomb_s=ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("Image/bomb_s.png"));
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
