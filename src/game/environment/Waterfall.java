package game.environment;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.*;
import game.environment.SpawningGround;

import java.util.List;

public class Waterfall extends SpawningGround {
    //chance of spawning a Squirtle
    final private int chanceSpawn = 15;

    //chance of drop a pokefruit
    final private int chancePokefruit = 15;

    //minimum WATER element ground to spawn Squirtle
    final private int minGround = 2;

    /**
     * Constructor.
     *
     */
    public Waterfall() {
        super('W');
        this.addCapability(Element.WATER);
    }

    @Override
    public void tick(Location location) {
        //chance of spawning a Squirtle is 15%
        //at least 2 WATER element ground surrounding to create Squirtle
        super.tick(location, new Squirtle(), Element.WATER, 15, 2);
    }

    public void createFruit(Location location) {
        //chance of dropping fruit is 15
        dropFruit(location, Element.WATER, 15);
    }
}
