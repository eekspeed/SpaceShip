## java 横版射击游戏 宇宙战舰物语 pc复刻 添加联机

本项目为完成java期末大作业而生。内容设计来自于手游《宇宙战舰物语》，使用java代码对其进行了简单复刻，并添加了联机功能。

开发环境为vscode，jdk11。

游戏主要代码参考马士兵的23种设计模式精讲教程，可在b站搜索关键词“java”，“坦克大战”，“设计模式”找到相关视频

音频相关代码参考书籍《Java游戏编程原理与实践教程》，陈锐 夏敏捷 葛丽萍 主编，ISBN：9787115301345

联机相关代码以java SE教程为基础自行实现

## 项目结构
### 代码：Game文件夹
#### 以Arm开头的文件：
武器类。以ArmMain为主武器，以ArmSec为副武器，ArmEngine为引擎作为战舰使用的类，其余类实现具体的功能并被ArmMain，ArmSec持有其对象

#### 以Bullet开头的文件（除BulletBombDataThread外）
子弹类。以BulletBase为父类，实现了子弹飞行，碰撞等，其余为子类，在构造参数中赋予子弹各属性值，并重写paint以使用自身图片

#### 以Ship开头的文件
飞船类。以ShipBase为父类，ShipPlayer与ShipEnemy继承ShipBase，而我方与敌方具体的飞船各自继承ShipPlayer与ShipEnemy，各具体飞船在构造参数中赋予飞船各属性值

#### 以Thread结尾的文件
实现联机的信息交换线程，其中ServerClientThread实现具体功能，BulletBombDataThread，EnemyShipDataThread，PlayerShipThread实现相关信息的封装

#### Server与Client
Server为主机的游戏线程，Client为其他玩家的游戏线程，如需运行游戏，运行Server.java即可。如需联机，请保证玩家处于局域网中，主机运行Server.java后其他玩家运行Client.java(目前仅适配双人联机游戏，且bug众多）

#### GameFrame 
游戏主体框架，实现游戏运行的主要功能，包括界面绘制，游戏状态控制，鼠标键盘控制等。

#### 其余文件
##### Bomb：绘制爆炸
##### Dir：方向的枚举类
##### EnemyRefresh：控制敌方飞船的刷新
##### Group：区分敌我的枚举类
##### ImageUtil：图片工具类，主要用于旋转子弹图片
##### PropertyManager：从config文件中读取配置（目前只有EnemyRefresh中的参数从config中读取）
##### ResourceManager：用于载入图片资源
##### Sound：声音类 
##### Trajectory：用于绘制主炮的弹道尾迹

### 图片：Image文件夹
### 声音：Sound文件夹
