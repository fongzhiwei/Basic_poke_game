package game.pokemon;


import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.AttackAction;
import game.Element;
import game.behaviours.WanderBehaviour;


public class Squirtle extends Pokemon {

    /**
     * Constructor.
     */
    public Squirtle() {
        super("Squirtle", 's', 100);
        // HINT: add more relevant behaviours here
        this.addCapability(Element.WATER);
    }

    /**
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of game.actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new AttackAction(this, direction));
        //FIXME: allow other actor to attack this Squirtle (incl. Player). Please check requirement! :)
        return actions;
    }
}
