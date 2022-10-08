package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

/**
 *  A class that represents door.
 *  @author Soh Meng Jienq <msoh0007@student.monash.edu>
 *  @version 1.0
 *
 * @see Item
 */

public class Door extends Item {
    /**
     * Constructor.
     * @param name the name of this Item: door
     * @param displayChar the character to use to represent this item if it is on the ground: '='
     * @param portable true if and only if the door can be picked up
     */
    public Door(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * Allow Door to add action.
     * @param newAction a new action to be added to the game.actions list.
     */
    public void addMapAction(Action newAction){
        this.addAction(newAction);
    }

    /**
     * Create an Action that will move the Actor to a Location in a given Direction. A currently-unused menu hotkey
     * will be assigned.
     * @param moveToLocation Location to move to
     * @param direction String describing the direction to move in, e.g. "to Pokemon Center!"
     */
    public void addMapAction(Location moveToLocation, String direction){
        addMapAction(new MoveActorAction(moveToLocation, direction));
    }

}
