package game.environment;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * A class that represents incubator.
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 * @version 1.0
 *
 * @see Ground
 * @see Status
 */


public class Incubator extends Ground {

    private Location location;

    /**
     * Constructor.
     * Incubator shows symbol 'X' in game map, and it is not expandable. It is a ground to hatch a Pokemon Egg.
     */
    public Incubator(){
        super('X');
        this.addCapability(Status.HATCHING_GROUND);

    }
}
