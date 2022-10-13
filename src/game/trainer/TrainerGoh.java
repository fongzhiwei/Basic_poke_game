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

    public TrainerGoh(){
        super("TrainerGoh", 'G', 1);
        this.getBehaviours().put(1, new TrainerCatchBehaviour());
        this.getBehaviours().put(2, new TrainerFeedBehaviour());
        this.getBehaviours().put(3, new TrainerPickupBehaviour());
        this.getBehaviours().put(4, new WanderBehaviour());
    }

    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        for (Behaviour behaviour : getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);

            if (action != null){
                return action;
            }

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
