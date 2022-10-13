package game.environment;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * A class that represents incubator.
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 * @version 1.0
 *
 */


public class Incubator extends Ground {

    private Location location;

    /**
     * Constructor.
     * Incubator shows symbol 'X' in game map, and it is not expandable.
     */
    public Incubator(){
        super('X');
        this.addCapability(CapabilityOfExpand.NOT_EXPANDABLE);
        this.addCapability(Status.HATCHING_GROUND);

    }

//    public void tick(Location location){
//        this.location = location;
//
//        for(Item item:location.getItems()){
//            if(item.hasCapability(Status.EGG)){
//
//            }
//        }
//    }
}