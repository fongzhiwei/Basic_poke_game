package game.trainer;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;

/**
 * A class that represents Trainer Goh.
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 * @version 1.0
 *
 */

public class TrainerGoh extends Trainer{

    /**
     * Constructor.
     * It shows symbol 'G' in the game map. Trainer Goh has behaviours to catch and feed pokemon, and pick up item.
     * And, he also has a behaviour to wander around.
     */
    public TrainerGoh(){
        super("TrainerGoh", 'G', 1);
        this.getBehaviours().put(1, new TrainerCatchBehaviour());
        this.getBehaviours().put(2, new TrainerFeedBehaviour());
        this.getBehaviours().put(3, new TrainerPickupBehaviour());
        this.getBehaviours().put(4, new WanderBehaviour());
    }

    /**
     * This method will check which action Trainer Goh will do in each turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an action
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // Loop through all the trainer's behaviours until it returns an action
        for (Behaviour behaviour : getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);

            if (action != null){
                return action;
            }
        }
        return new DoNothingAction();
    }
    /**
     * Get the display character at game map
     * @return  display character of an instance
     */
    @Override
    public char getDisplayChar() {
        return super.getDisplayChar();
    }

    /**
     * Return a string of trainer name
     * @return a string
     */
    @Override
    public String toString() {
        return "Trainer Goh";
    }
}
