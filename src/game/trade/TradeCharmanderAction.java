package game.trade;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.AffectionLevel;
import game.AffectionManager;
import game.pokemon.Charmander;
import game.items.Pokeball;

/**
 * A class that represents an action of trading Charmander.
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 * @version 1.0
 *
 * @see Action
 * @see NurseJoy
 */

public class TradeCharmanderAction extends Action {

    protected Actor target;
    protected Charmander merchandise;

    protected Pokeball pokeball;

    /**
     * An action of trading Charmander.
     * @param target the player who wants to trade.
     */
    public TradeCharmanderAction(Actor target){
        this.target = target;
        this.merchandise = new Charmander();
        AffectionManager.getInstance().increaseAffection(merchandise, 50);
        this.pokeball = new Pokeball(merchandise);
    }

    /**
     * This is a method to execute trading.
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return a string result
     */
    @Override
    public String execute(Actor target, GameMap map) {
        boolean tradeStatus = merchandise.tradedWith(target);
        if (!tradeStatus){
            return "Trade cancelled. The player does not have enough candy";
        }
        else {
            target.addItemToInventory(pokeball);
            return "Trade success. Charmander inside a pokeball is added";
        }
    }

    /**
     * This is a method that will show trading information in the console.
     * @param actor The actor performing the action.
     * @return a string of trading information.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " trades Charmander with 5 candies with Nurse Joy" ;
    }
}
