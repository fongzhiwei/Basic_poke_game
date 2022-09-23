package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Status;

public class Candy extends Item {

    public Candy(){
        super("Candy", '*', true);
        this.addCapability(Status.CURRENCY);
    }
}
