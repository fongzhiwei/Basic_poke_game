package game.environment;

import edu.monash.fit2099.engine.positions.Ground;
import game.Element;

/**
 * A class that represents hay.
 * @author Soh Meng Jienq <msoh0007@stundet.monash.edu>
 * @version 1.0
 *
 * @see Ground
 * @see Tree
 */

public class Hay extends Ground {

    /**
     * Constructor.
     * Hey  shows symbol ',' in game map, and it has Grass Element.
     */
    public Hay() {
        super(',');
        this.addCapability(Element.GRASS);
    }
}
