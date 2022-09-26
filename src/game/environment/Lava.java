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
    private Location location;

    /**
     * Constructor.
     */
    public Lava() {
        super('^');
        this.addCapability(Element.FIRE);
        this.registerInstance();
    }

    public void createLava(){
        //Lava ground has 10% chance to expand
        boolean chance = Utils.chance(10);

        if (chance && !location.containsAnActor()) {
            location.setGround(new Lava());
        }
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        this.location = location;
    }

    @Override
    public void dayEffect() {
        if (location !=null) {
            //expand lava
            List<Exit> exits = location.getExits();
            for (Exit exit : exits) {
                location = exit.getDestination();

                Ground ground = exit.getDestination().getGround();
                boolean checkExpand = ground.hasCapability(CapabilityOfExpand.NOTEXPANDABLE) || ground.hasCapability(Element.FIRE);
                if (!checkExpand) {
                    createLava();
                }
            }
        }
    }

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
