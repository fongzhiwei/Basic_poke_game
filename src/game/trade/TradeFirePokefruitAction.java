package game.trade;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Element;
import game.items.Pokefruit;

/**
 * A class that represents an action of trading Pokefruit that has fire element.
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 * @version 1.0
 *
 * @see Action
 * @see NurseJoy
 */

public class TradeFirePokefruitAction extends Action {
    /**
     * Trade target (player)
     */
    protected Actor target;

    /**
     * Trade target (player)
     */
    protected Pokefruit merchandise;

    /**
     * An action of trading Pokefruit that has fire element.
     * @param target the player who wants to trade.
     */
    public TradeFirePokefruitAction(Actor target){
        this.target = target;
        this.merchandise = new Pokefruit(Element.FIRE);
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
            return "Trade success. Fire pokefruit is added";
        }
    }

    /**
     * This is a method that will show trading information in the console.
     * @param actor The actor performing the action.
     * @return a string of trading information.
     */
    @Override
    public String menuDescription(Actor actor) {
        return target + " trades Fire Pokefruit with 1 Candy with Nurse Joy" ;
    }
}
