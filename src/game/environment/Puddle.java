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
    }

    @Override
    public void tick(Location location) {
        TimePerceptionManager.getInstance().run();
    }

    public void createPuddle(){
        //Puddle has 10% chance to expand
        boolean chance = Utils.chance(10);

        if (chance && !location.containsAnActor()) {
            location.setGround(new Puddle());
        }
    }

    @Override
    public void dayEffect() {
        //Puddle has 10% chance of being destroyed
        boolean chance = Utils.chance(10);

        if (chance && !location.containsAnActor()){
            location.setGround(new Dirt());
        }
    }

    @Override
    public void nightEffect() {
        //expand puddle
        List<Exit> exits = location.getExits();
        for (Exit exit : exits) {
            location = exit.getDestination();

            Ground ground = exit.getDestination().getGround();
            boolean checkExpand = ground instanceof Floor || ground instanceof Wall || ground.hasCapability(Element.GRASS);
            if (!checkExpand) {
                createPuddle();
            }
        }
    }
}
