package game.environment;

import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents bare dirt.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Soh Meng Jienq
 * @version 2.0
 *
 * @see Puddle
 * @see CapabilityOfExpand
 */
public class Dirt extends Ground {

    /**
     * Constructor.
     * Dirt shows symbol '.' in game map.
     */
    public Dirt() {
        super('.');
    }
}
