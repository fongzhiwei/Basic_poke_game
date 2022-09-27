package game.pokemon;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Element;
import game.Status;
import game.time.TimePerception;
import game.weapons.SpecialWeapon;

import java.util.ArrayList;

/**
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
        this.addCapability(Element.FIRE);
        this.addCapability(Status.NOT_CATCHABLE);
        this.registerInstance();
    }


    @Override
    public void setStatus(int affectionPoints) {
        if (this.findCapabilitiesByType(Status.class).size() > 0) {
            this.findCapabilitiesByType(Status.class).clear();
        }

        this.addCapability(Status.HOSTILE);
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "scratch");
    }

    /**
     * @param isEquipping FIXME: develop a logic to toggle weapon (put a selected weapon to the inventory - used!);
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
}
