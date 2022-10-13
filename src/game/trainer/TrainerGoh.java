package game.trainer;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;

public class TrainerGoh extends Trainer{

    public TrainerGoh(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
        this.getBehaviours().put(1, new TrainerCatchBehaviour());
        this.getBehaviours().put(2, new TrainerFeedBehaviour());
        this.getBehaviours().put(3, new TrainerPickupBehaviour());
        this.getBehaviours().put(4, new WanderBehaviour());
    }

    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        for (Behaviour behaviour : getBehaviours().values()) {
            return behaviour.getAction(this, map);

        }
        return new DoNothingAction();
    }
    /**
     * @return  display character of an instance
     */
    @Override
    public char getDisplayChar() {
        return super.getDisplayChar();
    }
}
