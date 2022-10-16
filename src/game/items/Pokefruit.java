package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Element;
import game.Status;
import game.trade.Tradable;


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
        this.addCapability(Status.FRUIT);
        this.addCapability(element);

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

    public void tradedWith(Actor player){
        player.addItemToInventory(this);
    }
}
