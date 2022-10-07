package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;

public class Door extends Item {

    public Door(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    public void addMapAction(Action newAction){
        this.addAction(newAction);
    }

}
