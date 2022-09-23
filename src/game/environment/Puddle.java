package game.environment;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Element;
import game.Utils;
import game.time.TimePerception;
import game.time.TimePerceptionManager;

import java.util.List;

public class Puddle extends Ground  implements TimePerception {
    private Location location;
    private int turn;
    /**
     * Constructor.
     *
     */
    public Puddle() {
        super('~');
        this.addCapability(Element.WATER);
        this.registerInstance();
    }

    public void createPuddle(){
        //Puddle has 10% chance to expand
        boolean chance = Utils.chance(10);

        if (chance && !location.containsAnActor()) {
            location.setGround(new Puddle());
        }
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        this.location = location;
    }

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

    @Override
    public void nightEffect() {
        if (location != null) {
            //expand puddle
            List<Exit> exits = location.getExits();
            for (Exit exit : exits) {
                location = exit.getDestination();

                Ground ground = exit.getDestination().getGround();
                boolean checkExpand = ground.hasCapability(CapabilityOfExpand.NOTEXPANDABLE) || ground.hasCapability(Element.WATER);
                if (!checkExpand) {
                    createPuddle();
                }
            }
        }
    }
}
