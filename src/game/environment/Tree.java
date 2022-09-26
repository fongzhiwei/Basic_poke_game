package game.environment;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.*;
import game.items.Candy;
import game.pokemon.Bulbasaur;
import game.time.TimePerception;

import java.util.List;

/**
 * A class that represents tree.
 * @author Soh Meng Jienq <msoh0007@stundet.monash.edu>
 * @version 1.0
 *
 * @see SpawningGround
 * @see Bulbasaur
 * @see Hay
 * @see Candy
 * @see TimePerception
 */

public class Tree extends SpawningGround  implements TimePerception {
    private Location location;

    /**
     * Constructor.
     * Tree use symbol 'T' and it has grass element.
     */
    public Tree() {
        super('T');
        this.addCapability(Element.GRASS);
        this.registerInstance();
    }

    /**
     * This method will let Tree spawn Bulbasaur.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        //chance of spawning a Bulbasaur is 15%
        //at least 1 GRASS element ground surrounding to create Bulbasaur
        super.tick(location, new Bulbasaur(), Element.GRASS, 15, 1);
        super.tick(location);
        this.location = location;
        createFruit(location);
    }

    /**
     * This method will let Tree drop 'Grass Pokefruit'
     * @param location the location of the Ground
     */
    public void createFruit(Location location) {
        //chance of dropping fruit is 15%
        dropFruit(location, Element.GRASS, 15);
    }

    /**
     * This method will drop candy when the time period is day.
     */
    @Override
    public void dayEffect() {
        if (location!=null) {
            //Trees have 5% of chance of dropping a Candy
            boolean chance = Utils.chance(5);

            if(chance && !location.containsAnActor()) {
                location.addItem(new Candy());
            }
        }
    }

    /**
     * This method will expand the tree and convert its surrounding to either all trees or all hays when the time period is night.
     */
    @Override
    public void nightEffect() {
        if (location!=null) {
            //Trees has 10% chance to expand
            boolean chance = Utils.chance(10);

            //Convert its surrounding to either all Trees or all Hays randomly
            //50% of chance to all Trees, 50% of chance to all Hays
            boolean chanceRandom = Utils.chance(50);

            if (chance && !location.containsAnActor()) {
                List<Exit> exits = location.getExits();
                for (Exit exit : exits) {
                    location = exit.getDestination();

                    Ground ground = exit.getDestination().getGround();
                    boolean checkExpand = ground.hasCapability(CapabilityOfExpand.NOTEXPANDABLE) || ground.hasCapability(Element.GRASS);
                    if (!checkExpand && chanceRandom) {
                        location.setGround(new Tree());
                    } else {
                        location.setGround(new Hay());
                    }
                }
            }
        }
    }
}
