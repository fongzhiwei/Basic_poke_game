package game.trade;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A class that represents NurseJoy.
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 * @version 1.0
 *
 * @see TradeCharmanderAction
 * @see TradeGrassPokefruitAction
 * @see TradeFirePokefruitAction
 * @see TradeWaterPokefruitAction
 * @see Actor
 */

public class NurseJoy extends Actor {

    private static final int MAX_NJ_HP = 100;

    /**
     * Constructor.
     * Nurse Joy shows symbol '%' in game map.
     */
    public NurseJoy(){
        super("Nurse Joy",'%',MAX_NJ_HP);

    }

    /**
     * This is a method to let nurse joy to stay in a fixed position.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
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
        list.add(new TradeFirePokefruitAction(otherActor));
        list.add(new TradeWaterPokefruitAction(otherActor));
        list.add(new TradeGrassPokefruitAction(otherActor));
        list.add(new TradeCharmanderAction(otherActor));

        return list;
    }


}
