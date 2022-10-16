package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Status;

/**
 *  A class that represents candy.
 *  @author Fong Zhiwei <zfon0005@student.monash.edu>
 *  @version 1.0
 *
 * @see Item
 * @see Status
 */

public class Candy extends Item {

    /**
     * Constructor.
     * Candy shows symbol '*' in game map, and it has a status of currency.
     */
    public Candy(){
        super("Candy", '*', true);
        this.addCapability(Status.CURRENCY);
    }
}
