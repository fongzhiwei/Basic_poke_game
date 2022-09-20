package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Element;
import game.Player;

public class Pokefruit extends Item implements Tradable{

    private Element element;

    public Pokefruit(Element element){
        super(element.toString()+ " Pokefruit", 'f',true);
        setElement(element);
        this.addCapability(element);
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public void tradedWith(Player player){
        int price = 1;
        int money = 0;
        int count = 0;

        for(Item item : player.getInventory()){
            if (item instanceof Candy){
                money += 1;
            }
        }

        if (price > money){
            System.out.println("Trade cancelled. The player does not have enough candy");
            return;
        }
        else {
            while (count < price){
                for (Item item : player.getInventory()){
                    if (item instanceof Candy){
                        player.removeItemFromInventory(item);
                        count += 1;
                    }
                }
            }
        }
    }
}
