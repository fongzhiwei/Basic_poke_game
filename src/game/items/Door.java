package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

/**
 *  A class that represents candy.
 *  @author Soh Meng Jienq <msoh0007@student.monash.edu>
 *  @version 1.0
 *
 * @see Item
 */

public class Door extends Item {

    public Door(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    public void addMapAction(Action newAction){
        this.addAction(newAction);
    }

    public void addMapAction(Location moveToLocation, String direction){
        addMapAction(new MoveActorAction(moveToLocation, direction));
    }

}
