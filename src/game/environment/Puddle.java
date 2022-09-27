package game.environment;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Element;
import game.Utils;
import game.time.TimePerception;
import game.time.TimePerceptionManager;

import java.util.List;

/**
 * A class that represents puddle.
 * @author Soh Meng Jienq <msoh0007@stundet.monash.edu>
 * @version 1.0
 *
 * @see Utils
 * @see Ground
 * @see Dirt
 * @see CapabilityOfExpand
 * @see TimePerception
 */
public class Puddle extends Ground  implements TimePerception {
    private Location location;

    /**
     * Constructor.
     * Puddle  shows symbol '~' in game map, and it has water element.
     */
    public Puddle() {
        super('~');
        this.addCapability(Element.WATER);
        this.registerInstance();
    }

    /**
     * This method will expand puddle.
     */
    public void createPuddle(){
        //Puddle has 10% chance to expand
        boolean chance = Utils.chance(10);

        if (chance && !location.containsAnActor()) {
            location.setGround(new Puddle());
        }
    }

    /**
     * This method will get the location from the game map.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        this.location = location;
    }

    /**
     * This method will destroy puddle to dirt when the time period is night.
     */
    @Override
    public void dayEffect() {
        if (location != null) {
            //Puddle has 10% chance of being destroyed
            boolean chance = Utils.chance(10);

            if (chance && !location.containsAnActor()) {
                TimePerceptionManager.getInstance().cleanUp(this);
                location.setGround(new Dirt());
            }
        }
    }

    /**
     * This method will expand the puddle when the time period is day.
     */
    @Override
    public void nightEffect() {
        if (location != null) {
            //expand puddle
            List<Exit> exits = location.getExits();
            for (Exit exit : exits) {
                location = exit.getDestination();

                Ground ground = exit.getDestination().getGround();
                boolean checkExpand = ground.hasCapability(CapabilityOfExpand.NOT_EXPANDABLE) || ground.hasCapability(Element.WATER);
                if (!checkExpand) {
                    createPuddle();
                }
            }
        }
    }
}
