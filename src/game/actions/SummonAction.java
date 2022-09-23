package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.AffectionManager;
import game.Element;
import game.items.Pokeball;
import game.items.Pokefruit;
import game.pokemon.Pokemon;

import java.lang.annotation.ElementType;

public class SummonAction extends Action {
    protected Pokemon target;
    protected String direction;

    public SummonAction(Pokemon target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    public String execute(Actor actor, GameMap map) {
        if (actor.getInventory().size() == 0) {
            return String.format("%s's inventory is empty", actor);
        }
        else {
            int index = 0;

            while (index < actor.getInventory().size()) {
                if (actor.getInventory().get(index) instanceof Pokeball) {
                    Pokeball pokeball = (Pokeball) actor.getInventory().get(index);

                    if (pokeball.getPokemon().toString().equals(this.target.toString())) {
                        // retrieve pokemon beside actor
                        //// check if surrounding is valid to place pokemon
                        //// if valid --> place pokemon randomly at valid positions
                        //// if invalid --> cannot retrieve pokemon

                        actor.removeItemFromInventory(pokeball);
                        return String.format("%s summoned a %s.", actor, this.target);
                    }
                }
                index += 1;
            }
            return String.format("%s has not captured any %s.", actor, this.target);
        }
    }

    public String menuDescription(Actor actor) {
        return String.format("%s summons %s." , actor, this.target);
    }
}
