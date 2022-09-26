package game.environment;

import edu.monash.fit2099.engine.positions.Location;
import game.*;
import game.pokemon.Charmander;

/**
 * A class that represents crater
 * @author Soh Meng Jienq <msoh0007@stundet.monash.edu>
 * @version 1.0
 *
 * @see Utils
 * @see SpawningGround
 * @see Element
 * @see Charmander
 */

public class Crater extends SpawningGround {

    /**
     * Constructor.
     * Crater use symbol 'O' and it has Fire Element.
     */
    public Crater() {
        super('O');
        this.addCapability(Element.FIRE);
    }

    /**
     * This method will let Crater spawn Charmander.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        //chance of spawning a Charmander is 10%
        //no limitation of element ground surrounding to create Charmander
        super.tick(location, new Charmander(), Element.FIRE, 10, 0);
        createFruit(location);
    }

    /**
     * This method will let Crater drop 'Fire Pokefruit'
     * @param location the location of the Ground
     */
    public void createFruit(Location location) {
        //chance of dropping fruit is 25%
        dropFruit(location, Element.FIRE, 25);
    }
}
