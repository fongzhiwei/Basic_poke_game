package game.trade;

import edu.monash.fit2099.demo.mars.actions.KickAction;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Player;

import java.util.List;

public class NurseJoy extends Actor {

    private static final int MAX_NJ_HP = 100;

    public NurseJoy(){
        super("Nurse Joy",'%',MAX_NJ_HP);

    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList list = super.allowableActions(otherActor, direction, map);
        list.add(new TradeFirePokefruitAction(otherActor));
        list.add(new TradeWaterPokefruitAction(otherActor));
        list.add(new TradeGrassPokefruitAction(otherActor));
        list.add(new TradeCharmanderAction(otherActor));

        return list;
    }


}
