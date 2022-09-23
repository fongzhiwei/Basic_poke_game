package game.pokemon;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.AffectionLevel;
import game.AffectionManager;
import game.Element;
import game.Status;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;

import java.util.SortedMap;
import java.util.TreeMap;

public abstract class Pokemon extends Actor{
//    private int affectionPoints;
    private final SortedMap<Integer, Behaviour> behaviours;

    public Pokemon(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours = new TreeMap<>();
        this.getBehaviours().put(2, new AttackBehaviour());
        this.getBehaviours().put(3, new WanderBehaviour());
//        this.addCapability(type);
        this.addCapability(AffectionLevel.NEUTRAL);
//        this.setAffectionPoints(0);
        this.setStatus();
        AffectionManager.getInstance().registerPokemon(this);
    }

    public SortedMap<Integer, Behaviour> getBehaviours() {
        return this.behaviours;
    }

    public AffectionLevel getAffectionLevel() {
        return this.findCapabilitiesByType(AffectionLevel.class).get(0);
//        int index = 0;
//        AffectionLevel result = null;
//
//        while (index < this.capabilitiesList().size()) {
//            if (this.capabilitiesList().get(index) instanceof AffectionLevel) {
//                result = (AffectionLevel) this.capabilitiesList().get(index);
//            }
//            index += 1;
//        }
//        return result;
    }

    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "tackle");
    }

//    public void setAffectionPoints(int newAffectionPoints) {
//        this.affectionPoints = newAffectionPoints;
//    }

    public void setAffectionLevel(AffectionLevel newAffectionLevel) {
        if (this.findCapabilitiesByType(AffectionLevel.class).size() > 0) {
            this.findCapabilitiesByType(AffectionLevel.class).clear();
        }
        this.addCapability(newAffectionLevel);
    }

    public void setStatus() {
        if (this.findCapabilitiesByType(Status.class).size() > 0) {
            this.findCapabilitiesByType(AffectionLevel.class).clear();
        }

        if (this.getAffectionLevel() == AffectionLevel.DISLIKE || this.getAffectionLevel() == AffectionLevel.NEUTRAL || this instanceof Charmander) {
            this.addCapability(Status.HOSTILE);
        }
        else {
            this.addCapability(Status.CATCHABLE);
        }
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of game.actions
     */
    @Override
    public abstract ActionList allowableActions(Actor otherActor, String direction, GameMap map);

}
