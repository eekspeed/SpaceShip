package Game;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameFrame frame = new GameFrame();

        int initShipCount = Integer.parseInt((String)PropertyManager.get("initShipCount"));

        for(int i=0;i<initShipCount;i++){
            frame.enemyShips.add(new Ship(1000, 200+i*100, Dir.L,Group.Bad, frame));
        }

        while(true){
            Thread.sleep(30);
            frame.repaint();
        }
    }
    
}
