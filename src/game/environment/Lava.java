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
 * A class that represents lava.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Soh Meng Jienq
 * @version 2.0
 *
 * @see Utils
 * @see Ground
 * @see CapabilityOfExpand
 * @see TimePerception
 */
public class Lava extends Ground implements TimePerception {
    private Location location;

    /**
     * Constructor.
     * Lava  shows symbol '^' in game map, and it has Fire Element.
     */
    public Lava() {
        super('^');
        this.addCapability(Element.FIRE);
        this.registerInstance();
    }

    /**
     * This method will expand lava.
     */
    public void createLava(){
        //Lava ground has 10% chance to expand
        boolean chance = Utils.chance(10);

        if (chance && !location.containsAnActor()) {
            location.setGround(new Lava());
        }
    }

    /**
     * This method will get the location from the game map
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        this.location = location;
    }

    /**
     * This method will expand the lava when the time period is day.
     */
    @Override
    public void dayEffect() {
        if (location !=null) {
            //expand lava
            List<Exit> exits = location.getExits();
            for (Exit exit : exits) {
                location = exit.getDestination();

                Ground ground = exit.getDestination().getGround();
                boolean checkExpand = ground.hasCapability(CapabilityOfExpand.NOT_EXPANDABLE) || ground.hasCapability(Element.FIRE);
                if (!checkExpand) {
                    createLava();
                }
            }
        }
    }

    /**
     * This method will destroy lava to dirt when the time period is night.
     */
    @Override
    public void nightEffect() {
        if (location != null) {
            //Lava ground has 10% chance of being destroyed
            boolean chance = Utils.chance(10);

            if (chance && !location.containsAnActor()) {
                TimePerceptionManager.getInstance().cleanUp(this);
                location.setGround(new Dirt());
            }
        }
    }
}
