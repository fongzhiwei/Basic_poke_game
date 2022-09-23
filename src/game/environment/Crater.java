package game.environment;

import edu.monash.fit2099.engine.positions.Location;
import game.*;
import pokemon.Charmander;

public class Crater extends SpawningGround {
    //chance of spawning a Charmander
    final private int chanceSpawn = 10;

    //chance of drop a pokefruit
    final private int chancePokefruit = 25;

    /**
     * Constructor.
     *
     */
    public Crater() {
        super('O');
        this.addCapability(Element.FIRE);
    }

    @Override
    public void tick(Location location) {
        //chance of spawning a Charmander is 10%
        //no limitation of element ground surrounding to create Charmander
        super.tick(location, new Charmander(), Element.FIRE, 10, 0);
    }

    public void createFruit(Location location) {
        //chance of dropping fruit is 25%
        dropFruit(location, Element.FIRE, 25);
    }
}
