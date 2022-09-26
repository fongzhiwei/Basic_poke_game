package game.environment;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.*;
import game.pokemon.Charmander;

public class Crater extends SpawningGround {

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
        super.tick(location, new Charmander(), Element.FIRE, -1, 0);
    }

    public void createFruit(Location location) {
        //chance of dropping fruit is 25%
        dropFruit(location, Element.FIRE, 25);
    }
}
