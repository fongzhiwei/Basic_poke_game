package game.trade;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Element;
import game.Status;
import game.items.Pokefruit;
import game.pokemon.Bulbasaur;
import game.pokemon.Charmander;
import game.pokemon.Squirtle;

/**
 * A class that represents NurseJoy.
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 * @version 1.0
 *
 * @see TradeAction
 * @see Actor
 * @see Element
 * @see Status
 * @see Pokefruit
 * @see Bulbasaur
 * @see Charmander
 * @see Squirtle
 */

public class NurseJoy extends Actor {

    private static final int MAX_NJ_HP = 100;

    /**
     * Constructor.
     * Nurse Joy shows symbol '%' in game map.
     */
    public NurseJoy(){
        super("Nurse Joy",'%',MAX_NJ_HP);
        this.addCapability(Status.IMMUNE);
    }

    /**
     * This is a method to let nurse joy to stay in a fixed position.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an action that just stay in a fixed position
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * This is a method to get an action list.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A list of allowable actions.
     */

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList list = super.allowableActions(otherActor, direction, map);
        list.add(new TradeAction(otherActor, new Pokefruit(Element.FIRE),1));
        list.add(new TradeAction(otherActor, new Pokefruit(Element.WATER),1));
        list.add(new TradeAction(otherActor, new Pokefruit(Element.GRASS),1));
        list.add(new TradeAction(otherActor, new Charmander(),5));
        list.add(new TradeAction(otherActor, new Bulbasaur(),5));
        list.add(new TradeAction(otherActor, new Squirtle(),5));

        return list;
    }


}
