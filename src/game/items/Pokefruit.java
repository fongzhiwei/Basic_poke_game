package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Element;
import game.Status;
import game.trade.Tradable;

import java.util.ArrayList;

public class Pokefruit extends Item implements Tradable {

    private Element element;

    public Pokefruit(Element element){
        super(element.toString()+ " Pokefruit", 'f',true);
        setElement(element);
        this.addCapability(Status.FOOD);

    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

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
