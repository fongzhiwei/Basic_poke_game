package game.pokemon;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.AffectionLevel;
import game.AffectionManager;
import game.Element;
import game.Status;
import game.actions.EvolveAction;
import game.behaviours.EvolveBehaviour;
import game.time.TimePerception;
import game.weapons.SpecialWeapon;

import java.util.ArrayList;

/**
 * Class representing the Charmander
 *
 * Created by:
 * @author Riordan D. Alfredo
 *
 * Modified by:
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 */
public class Charmander extends Pokemon implements TimePerception{
    /**
     * Constructor.
     */
    public Charmander() {
        super("Charmander", 'c', 100);
        // HINT: add more relevant behaviours here
        this.getBehaviours().put(0, new EvolveBehaviour());
        this.addCapability(Element.FIRE);
        this.registerInstance();
    }

    /**
     * Creates and returns an intrinsic weapon. By default, the Charmander 'scratch' for 10 damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "scratch");
    }

    /**
     * Switch the pokemon's weapon in the game
     *
     * @param isEquipping boolean value representing if the pokemon is equipping any weapon at the moment
     */
    public void toggleWeapon(boolean isEquipping) {
        if (this.pokemonLocation.getGround().hasCapability(Element.FIRE)) {
            if (!isEquipping) {
                SpecialWeapon ember = new SpecialWeapon("Ember", 'E', 20, "sparks", 90, Element.FIRE);
                this.addItemToInventory(ember);
            }
        }
        else {
            if (isEquipping) {
                this.removeItemFromInventory((Item) this.getWeapon());
            }
        }
    }

    /**
     * Charmander will be healed during the period of day
     */
    @Override
    public void dayEffect() {
        // Charmander will be healed by 10 points
        super.heal(10);
    }

    /**
     * Charmander will be hurt during the period of night
     */
    @Override
    public void nightEffect() {
        // Charmander will be hurt by 10 points
        super.hurt(10);
    }

    /**
     * This is a method to trade with player using candy to get Charmander and Pokefruit
     *
     * @param player the person that want to trade
     * @return the result of whether the trade can successfully done (True/False)
     */
    public boolean tradedWith(Actor player){
        int price = 5;
        int money = 0;
        int count = 0;
        boolean flag = false;
        ArrayList<Integer> candyIndex = new ArrayList<>();


        for(Item item : player.getInventory()){
            if (item.hasCapability(Status.CURRENCY)){
                money += 1;
                candyIndex.add(player.getInventory().indexOf(item));
            }
        }
        System.out.println(money);

        if(price <= money) {
            flag = true;
            while (count < price){
                player.removeItemFromInventory(player.getInventory().get(candyIndex.get(count)-count));
                count += 1;
            }
        }
        money=0;
        for(Item item : player.getInventory()){
            if (item.hasCapability(Status.CURRENCY)){
                money += 1;
                candyIndex.add(player.getInventory().indexOf(item));
            }
        }
        System.out.println(money);
        return flag;
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        if (AffectionManager.getInstance().getAffectionPoint(otherActor, this) == AffectionLevel.MAX.getPoints()) {
            actions.add(new EvolveAction(this));
        }

        return actions;
    }
}
