package game.environment;


import edu.monash.fit2099.engine.positions.Location;
import game.*;
import game.pokemon.Squirtle;

/**
 * A class that represents waterfall.
 * @author Soh Meng Jienq <msoh0007@stundet.monash.edu>
 * @version 1.0
 *
 * @see SpawningGround
 * @see Utils
 * @see Squirtle
 * @see Element
 */

public class Waterfall extends SpawningGround {
    /**
     * Constructor.
     *
     */
    public Waterfall() {
        super('W');
        this.addCapability(Element.WATER);
    }

    @Override
    public void tick(Location location) {
        //chance of spawning a Squirtle is 15%
        //at least 2 WATER element ground surrounding to create Squirtle
        super.tick(location, new Squirtle(), Element.WATER, 15, 2);
        createFruit(location);
    }

    public void createFruit(Location location) {
        //chance of dropping fruit is 15
        dropFruit(location, Element.WATER, 15);
    }
}
