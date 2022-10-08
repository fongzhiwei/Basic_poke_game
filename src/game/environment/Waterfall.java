package game.environment;

import edu.monash.fit2099.engine.positions.Location;
import game.*;
import game.pokemon.Pokemon;
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
    //chance of spawning a Squirtle
    final private int chanceSpawn = 15;

    //chance of drop a pokefruit
    final private int chancePokefruit = 15;

    //minimum WATER element ground to spawn Squirtle
    final private int minGround = 2;

    /**
     * Constructor.
     * Waterfall shows symbol 'W' in game map, and it has water element.
     */
    public Waterfall() {
        super('W');
        this.addCapability(Element.WATER);
    }

    /**
     * This method will let Waterfall spawn Squirtle.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location, Pokemon pokemon, Element element, int chanceSpawn, int minGround) {
        //chance of spawning a Squirtle is 15%
        //at least 2 WATER element ground surrounding to create Squirtle
        super.tick(location, new Squirtle(), Element.WATER, 15, 2);
        createFruit(location);
    }

    /**
     * This method will let Waterfall drop 'Water Pokefruit'
     * @param location The location of the Ground
     */
    public void createFruit(Location location) {
        //chance of dropping fruit is 15
        dropFruit(location, Element.WATER, 15);
    }
}
