package game.pokemon;


import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.*;
import game.Character;
import game.actions.CaptureAction;
import game.actions.FeedAction;
import game.actions.SummonAction;
import game.weapons.SpecialWeapon;

import java.util.ArrayList;
import java.util.List;


public class Squirtle extends Pokemon {
    /**
     * Constructor.
     */
    public Squirtle() {
        super("Squirtle", 's', 100);
        // HINT: add more relevant behaviours here
        this.addCapability(Element.WATER);
    }

    @Override
    public void toggleWeapon(boolean isEquipping) {
        List<Exit> exits = this.pokemonLocation.getExits();

        for(Exit elem: exits) {
            if (!elem.getDestination().containsAnActor()) {
                exits.remove(elem);
            }
            else {
                if (!elem.getDestination().getActor().hasCapability(Element.FIRE)) {
                    exits.remove(elem);
                }
            }
        }

        if (this.pokemonLocation.getGround().hasCapability(Element.WATER) || exits.size() > 0) {
            if (!isEquipping) {
                SpecialWeapon bubble = new SpecialWeapon("Bubble", 'B', 25, "burbles", 80, Element.WATER);
                this.addItemToInventory(bubble);
            }
        }
        else {
            if (isEquipping) {
                this.removeItemFromInventory((Item) this.getWeapon());
            }
        }
    }
}
