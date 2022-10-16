package game.trade;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

import java.util.ArrayList;

/**
 * A class that represents trade action between player and nurse joy.
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 * @version 1.0
 *
 * @see Action
 * @see Status
 * @see Actor
 * @see Item
 */

public class TradeAction extends Action {
    /**
     * Trade target (player)
     */
    protected Actor target;

    /**
     * Trade item (merchandise)
     */
    protected Tradable merchandise;

    protected int price;

    /**
     * Constructor.
     *
     * @param actor the player who wants to trade
     * @param merchandise the tradable item
     * @param price the price of the tradable item
     */
    public TradeAction(Actor actor, Tradable merchandise, int price){
        this.target = actor;
        this.merchandise = merchandise;
        this.price = price;
    }

    /**
     * This method will execute the tradable action and check whether the player has enough currencies to trade.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the trade action (a string to indicates whether it is success or failed)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int money = 0;
        int count = 0;
        ArrayList<Integer> candyIndex = new ArrayList<>();


        for(Item item : actor.getInventory()){
            if (item.hasCapability(Status.CURRENCY)){
                money += 1;
                candyIndex.add(actor.getInventory().indexOf(item));
            }
        }
        if (price > money){
            return "Trade cancelled. The player does not have enough candy";
        }
        else {
            while (count < price){
                actor.removeItemFromInventory(actor.getInventory().get(candyIndex.get(count)-count));
                count += 1;
            }
            merchandise.tradedWith(actor);
        }

        return "Trade success. "+ merchandise + " is added";
    }

    /**
     * This method will print an appropriate trade message to console.
     *
     * @param actor The actor performing the action.
     * @return A message to indicates the trade action progress
     */
    public String menuDescription(Actor actor) {
        return actor + " trades " +merchandise+ " with " + price + " candies with Nurse Joy" ;
    }
}


