package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 *  A class that represents door.
 *  @author Soh Meng Jienq <msoh0007@student.monash.edu>
 *  @version 1.0
 *
 * @see Ground
 */

public class Door extends Ground {

    private Location moveToLocation;
    private String direction;
    /**
     * Constructor.
     */
    public Door() {
        super('=');
    }

    /**
     * Overloading Constructor.
     *
     * @param moveToLocation Location to move to
     * @param direction String describing the direction to move in, e.g. "to Pokemon Center!"
     */
    public Door(Location moveToLocation, String direction){
        super('=');
        this.moveToLocation = moveToLocation;
        this.direction = direction;
    }

    /**
     * Returns an Action list for door.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return an action list
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList tmpActionList = super.allowableActions(actor, location, direction);
        tmpActionList.add(new MoveActorAction(this.moveToLocation, this.direction));
        return tmpActionList;
    }

}
