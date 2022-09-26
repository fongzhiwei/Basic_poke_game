package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Element;
import game.Status;
import game.trade.Tradable;

import java.util.ArrayList;

/**
 * A class that represents Pokefruit
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 * @version 1.0
 *
 * @see Item
 * @see Element
 * @see Tradable
 */

public class Pokefruit extends Item implements Tradable {

    private Element element;

    /**
     * Constructor.
     * Pokefruit shows symbol 'f' in game map, and it has a status of fruit.
     * @param element Each pokefruit has a unique element (Fire/Water/Grass)
     */
    public Pokefruit(Element element){
        super(element.toString()+ " Pokefruit", 'f',true);
        setElement(element);
        this.addCapability(Status.FRUIT);

    }

    /**
     * This is a method to get element.
     * @return a unique element (Fire/Water/Grass)
     */
    public Element getElement() {
        return element;
    }

    /**
     * This is a method to set element.
     * @param element a unique element (Fire/Water/Grass)
     */
    public void setElement(Element element) {
        this.element = element;
    }

    /**
     * This is a method to trade with player using candy to get Charmander and Pokefruit
     * @param player the person that want to trade
     * @return the result of whether the trade can successfully done (True/False)
     */
    public boolean tradedWith(Actor player){
        int price = 1;
        int money = 0;
        int count = 0;
        boolean flag = false;
        ArrayList <Integer> candyIndex = new ArrayList<>();


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
                player.removeItemFromInventory(player.getInventory().get(candyIndex.get(count)));
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
