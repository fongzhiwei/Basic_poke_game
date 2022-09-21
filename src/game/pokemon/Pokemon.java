package game.pokemon;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.AffectionLevel;
import game.Element;
import game.behaviours.Behaviour;

import java.util.SortedMap;
import java.util.TreeMap;

public abstract class Pokemon extends Actor{
//    private final Element pokemonType;
    private int affectionPoints;
    private boolean isCaptured, isCatchable;
    private SortedMap<Integer, Behaviour> behaviours;

    public Pokemon(String name, char displayChar, int hitPoints, Element type) {
        super(name, displayChar, hitPoints);
        this.addCapability(type);
        setBehaviours();
        setAffectionPoints(0);
        this.addCapability(AffectionLevel.NEUTRAL);
        setIsCaptured(false);
        setIsCatchable(false);
    }

    public SortedMap<Integer, Behaviour> getBehaviours() {
        return this.behaviours;
    }

    public int getAffectionPoints() {
        return this.affectionPoints;
    }

    public AffectionLevel getAffectionLevel() {
        int index = 0;
        AffectionLevel result = null;

        while (index < this.capabilitiesList().size()) {
            if (this.capabilitiesList().get(index) instanceof AffectionLevel) {
                result = (AffectionLevel) this.capabilitiesList().get(index);
            }
            index += 1;
        }
        return result;
    }

    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "tackle");
    }

    public void setBehaviours() {
        this.behaviours = new TreeMap<>();
    }

    public void setAffectionPoints(int newAffectionPoints) {
        this.affectionPoints = newAffectionPoints;
    }

    public void setAffectionLevel(AffectionLevel newAffectionLevel) {
        this.addCapability(newAffectionLevel);
    }

    public void setIsCaptured(boolean newIsCaptured) {
        this.isCaptured = newIsCaptured;
    }

    public void setIsCatchable(boolean newIsCatchable) {
        this.isCatchable = newIsCatchable;
    }

    public boolean isCatchable() {
        if (isCaptured()) {
            setIsCatchable(false);
            return false;
        }
        return getAffectionLevel() != AffectionLevel.DISLIKE && getAffectionLevel() != AffectionLevel.NEUTRAL;
    }

    public boolean isCaptured() {
        return this.isCaptured;
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
    // from Actor class??
//    public abstract Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display);

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
    @Override // from Charmander class
    public abstract ActionList allowableActions(Actor otherActor, String direction, GameMap map);






}
