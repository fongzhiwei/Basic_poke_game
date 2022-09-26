package game.environment;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.*;
import game.items.Pokefruit;
import game.pokemon.Pokemon;
import java.util.List;

/**
 * A class that represents spawning ground which is an interactive ground.
 * @author Soh Meng Jienq <msoh0007@stundet.monash.edu>
 * @version 1.0
 */

public abstract class SpawningGround extends Ground{

    /**
     * Constructor.
     * @param displayChar the symbol that will show in game map
     */
    public SpawningGround(char displayChar){
        super(displayChar);
    }

    /**
     * This method will let the spawning ground to create pokemon
     * @param location the location in game map
     * @param pokemon the pokemon to be created
     * @param element the element that the surrounding terrain has
     * @param chanceSpawn the chance to spawn pokemon
     * @param minGround minimum amount of the surrounding ground
     */
    public void tick(Location location, Pokemon pokemon , Element element, int chanceSpawn, int minGround){
        boolean chance = Utils.chance(chanceSpawn);
        int numOfGround = 0;
        List<Exit> exits = location.getExits();
        for (Exit exit : exits) {
            if (exit.getDestination().getGround().hasCapability(element)) {
                numOfGround += 1;
            }
        }

        if (chance && !location.containsAnActor() && numOfGround>=minGround){
            location.addActor(pokemon);
        }
    }

    /**
     * This method will let the spawning ground to drop fruit
     * @param location the location in game map
     * @param element the element that the pokefruit has
     * @param chancePokefruit the chance of the pokefruit drop
     */
    public void dropFruit(Location location, Element element, int chancePokefruit){
        boolean chance = Utils.chance(chancePokefruit);
        Pokefruit pokefruit = new Pokefruit(element);

        if (chance && !location.containsAnActor()){
            location.addItem(pokefruit);
        }

    }

}
