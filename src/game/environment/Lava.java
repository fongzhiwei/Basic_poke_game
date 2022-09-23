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
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Lava extends Ground implements TimePerception {
    private Location groundLocation;
    private int turn;

    /**
     * Constructor.
     */
    public Lava() {
        super('^');
        this.addCapability(Element.FIRE);
        this.addCapability(MoreCapabilityType.EXPANDFIRE);
        this.registerInstance();
    }

    public void createLava(){
        //Lava ground has 10% chance to expand
        boolean chance = Utils.chance(10);

        if (chance && !groundLocation.containsAnActor()) {
            groundLocation.setGround(new Lava());
        }
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        this.groundLocation = location;
    }

    @Override
    public void dayEffect() {
        if (groundLocation!=null) {
            //expand lava
            List<Exit> exits = groundLocation.getExits();
            for (Exit exit : exits) {
                groundLocation = exit.getDestination();

                Ground ground = exit.getDestination().getGround();
                boolean checkExpand = ground.hasCapability(MoreCapabilityType.SOLID) || ground.hasCapability(Element.GRASS) || ground.hasCapability(MoreCapabilityType.EXPANDFIRE);
                if (!checkExpand) {
                    createLava();
                }
            }
        }
    }

    @Override
    public void nightEffect() {
        if (groundLocation != null) {
            //Lava ground has 10% chance of being destroyed
            boolean chance = Utils.chance(10);

            if (chance && !groundLocation.containsAnActor()) {
                TimePerceptionManager.getInstance().cleanUp(this);
                groundLocation.setGround(new Dirt());
            }
        }
    }
}
