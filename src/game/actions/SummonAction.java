package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.Utils;
import game.items.Pokeball;
import game.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * An Action to feed a Pokemon.
 * Created by:
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 *
 * Modified by:
 *
 */
public class SummonAction extends Action {

    /**
     * The Pokemon that is to be summoned (release)
     */
    protected Pokemon target;

    /**
     * The Pokeball to be removed after summoning pokemon
     */
    protected Pokeball pokeball;


    /**
     * Constructor.
     *
     * @param target the Pokemon to summon (release)
     *
     */
    public SummonAction(Pokemon target, Pokeball pokeball) {
        this.target = target;
        this.pokeball = pokeball;

    }

    /**
     * Execute summon action for player to release a pokemon next to him/her on the map
     * @param actor the actor to release the pokemon (player)
     * @param map the game map
     * @return a string display after action is completed
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        Pokemon capturedPokemon = target;
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
            actor.removeItemFromInventory(pokeball);
            System.out.println(actor.getInventory());
            return String.format("I choose you... %s.", this.target);
            }

        return String.format("%s has not captured any %s.", actor, this.target);
    }

    /**
     * Method to return appropriate menu description for the action
     * @param actor the actor who performed the action
     * @return a string for menu description
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s summons %s." , actor, this.target);
    }
}
