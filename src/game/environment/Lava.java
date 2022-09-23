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
    private int turn;

    /**
     * Constructor.
     */
    public Lava() {
        super('^');
        this.addCapability(Element.FIRE);
    }

    @Override
    public void tick(Location location) {
        TimePerceptionManager.getInstance().run();
    }

    public void createLave(){
        //Lava ground has 10% chance to expand
        boolean chance = Utils.chance(10);

        if (chance && !location.containsAnActor()) {
            location.setGround(new Lava());
        }
    }

    @Override
    public void dayEffect() {
        //expand lava
        List<Exit> exits = location.getExits();
        for (Exit exit : exits) {
            location = exit.getDestination();

            Ground ground = exit.getDestination().getGround();
            boolean checkExpand = ground instanceof Floor || ground instanceof Wall || ground.hasCapability(Element.GRASS);
            if (!checkExpand) {
                createLave();
            }
        }
    }

    @Override
    public void nightEffect() {
        //Lava ground has 10% chance of being destroyed
        boolean chance = Utils.chance(10);

        tick(location);

        if (chance && !location.containsAnActor()){
            location.setGround(new Dirt());
        }
    }
}
