package game.trade;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface for trading purpose.
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 * @version 1.0
 */

public interface Tradable {
    /**
     * This is an abstract method that trade with player.
     * @param player the person who want to trade
     * @return The result of trading whether it is successful (True/False)
     */
    public boolean tradedWith(Actor player);
}
