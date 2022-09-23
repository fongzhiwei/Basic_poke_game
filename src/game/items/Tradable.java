package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.Player;

public interface Tradable {
    public boolean tradedWith(Actor player);
}
