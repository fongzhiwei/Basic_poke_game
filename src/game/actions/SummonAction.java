package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.Utils;
import game.items.Pokeball;
import game.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class SummonAction extends Action {
    protected Pokemon target;
    protected String direction;

    public SummonAction(Pokemon target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    public String execute(Actor actor, GameMap map) {
        int index = 0;

        while (index < actor.getInventory().size()) {
            if (actor.getInventory().get(index).hasCapability(Status.BALL)) {
                Pokeball pokeball = (Pokeball) actor.getInventory().get(index);
                Pokemon capturedPokemon = pokeball.getPokemon();

                if (capturedPokemon.toString().equals(this.target.toString())) {
                    // retrieve pokemon next to actor
                    List<Exit> exits = map.locationOf(actor).getExits();
                    ArrayList<Integer> validPosIndex = new ArrayList<>();
                    for (int i = 0; i < exits.size(); i++) {
                        if (exits.get(i).getDestination().getGround().canActorEnter(capturedPokemon)) {
                            validPosIndex.add(i);
                        }
                    }

                    if (validPosIndex.size() > 0) {
                        int randomPosIndex = Utils.nextNum(0, validPosIndex.size());
                        map.addActor(capturedPokemon, exits.get(randomPosIndex).getDestination());
                        capturedPokemon.addCapability(Status.CATCHABLE);
                        actor.removeItemFromInventory(pokeball);
                        return String.format("I choose you... %s.", this.target);
                    }
                }
            }
            index += 1;
        }
        return String.format("%s has not captured any %s.", actor, this.target);
    }

    public String menuDescription(Actor actor) {
        return String.format("%s summons %s." , actor, this.target);
    }
}
