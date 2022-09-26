package game.trade;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Element;
import game.items.Pokefruit;

/**
 * A class that represents an action of trading Pokefruit that has water element.
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 * @version 1.0
 *
 * @see Action
 * @see NurseJoy
 */

public class TradeWaterPokefruitAction extends Action {

    protected Actor target;

    protected Pokefruit merchandise;

    /**
     * An action of trading Pokefruit that has water element.
     * @param target the player who wants to trade.
     */
    public TradeWaterPokefruitAction(Actor target){
        this.target = target;
        this.merchandise = new Pokefruit(Element.WATER);
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
            target.addItemToInventory(merchandise);
            return "Trade success. Water pokefruit is added";
        }
    }

    /**
     * This is a method that will show trading information in the console.
     * @param actor The actor performing the action.
     * @return a string of trading information.
     */
    @Override
    public String menuDescription(Actor actor) {
        return target + " trades Water Pokefruit for 1 Candy with Nurse Joy" ;
    }
}
