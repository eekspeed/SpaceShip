package Game;

public class ShipPlayer extends ShipBase{
    public ShipPlayer(){
        dir   = Dir.R;

        moving = false;
        living = true;
        group  = Group.Good;
    }
}
