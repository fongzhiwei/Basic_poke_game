package pokemon;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.AffectionLevel;
import game.AttackAction;
import game.Element;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;

import java.util.SortedMap;
import java.util.TreeMap;

public abstract class Pokemon extends Actor{
    final Element pokemonType;
    final int intrinsicDamage, intrinsicChanceToHit;
    SortedMap<Integer, Behaviour> behaviours;
    private int affectionPoints;
    private AffectionLevel affectionLevel;

    public Pokemon(String name, char displayChar, int hitPoints, Element type) {
        super(name, displayChar, hitPoints);
        this.pokemonType = type;
        this.intrinsicDamage = 10;
        this.intrinsicChanceToHit = 50;
        this.behaviours = new TreeMap<>();
        setAffectionPoints(0);
        setAffectionLevel(AffectionLevel.NEUTRAL);
    }

    public int getAffectionPoints() {
        return this.affectionPoints;
    }

    public AffectionLevel getAffectionLevel() {
        return this.affectionLevel;
    }

    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "tackle");
    }

    public void setAffectionPoints(int newAffectionPoints) {
        this.affectionPoints = newAffectionPoints;
    }

    public void setAffectionLevel(AffectionLevel newAffectionLevel) {
        this.affectionLevel = newAffectionLevel;
    }

    public boolean isCatchable(Pokemon pokemon) {
        return pokemon.getAffectionLevel() != AffectionLevel.DISLIKE && pokemon.getAffectionLevel() != AffectionLevel.NEUTRAL;
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
