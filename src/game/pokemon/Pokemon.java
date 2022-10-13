package game.pokemon;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.*;
import game.Character;
import game.actions.CaptureAction;
import game.actions.FeedAction;
import game.actions.SummonAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * An entity representing a Pokemon that is alive.
 *
 * Created by:
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 *
 * Modified by:
 *
 */
public abstract class Pokemon extends Actor{
    /**
     * A set of behaviours
     */
    private final SortedMap<Integer, Behaviour> behaviours;

    /**
     * Location of a Pokemon
     */
    protected Location pokemonLocation;

    /**
     * Constructor.
     *
     * @param name          name of the Pokemon
     * @param displayChar   character to represent the Pokemon on game map
     * @param hitPoints     the Pokemon's hp level
     */
    public Pokemon(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours = new TreeMap<>();
        this.getBehaviours().put(2, new AttackBehaviour());
        this.getBehaviours().put(3, new WanderBehaviour());
        this.addCapability(AffectionLevel.NEUTRAL);
        this.addCapability(Character.NPC);
        this.setStatus(0);
        AffectionManager.getInstance().registerPokemon(this);
    }

    /**
     * Get the Pokemon's set of behaviours
     *
     * @return a sorted map of behaviours, ordered according to their respective priority
     */
    public SortedMap<Integer, Behaviour> getBehaviours() {
        return this.behaviours;
    }

    /**
     * Creates and returns an intrinsic weapon. By default, the Pokemon 'tackle' for 10 damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "tackle");
    }

//    /**
//     * Set the affection level of a Pokemon
//     *
//     * @param newAffectionLevel the affection level to be set to a Pokemon
//     */
//    public void setAffectionLevel(AffectionLevel newAffectionLevel) {
//        if (this.findCapabilitiesByType(AffectionLevel.class).size() > 0) {
//            this.findCapabilitiesByType(AffectionLevel.class).clear();
//        }
//        this.addCapability(newAffectionLevel);
//    }

    /**
     * Set the status of a Pokemon according to its current affection points
     *
     * @param affectionPoints the Pokemon's affection points towards the player or trainer
     */
    public void setStatus(int affectionPoints) {

        if(this.hasCapability(Status.CATCHABLE)){
            this.removeCapability(Status.CATCHABLE);

        }

        if (affectionPoints<=-50) {
            this.addCapability(Status.NOT_CATCHABLE);
            this.addCapability(AffectionLevel.DISLIKE);
        }
        else if (affectionPoints>=50) {
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
        this.pokemonLocation = map.locationOf(this);
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            boolean isEquipping = false;
            int index = 0;
            while (index < this.getInventory().size()) {
                if (this.getInventory().get(index).hasCapability(Status.WEAPON)) {
                    isEquipping = true;
                }
                index++;
            }
            if (action != null) {
                this.toggleWeapon(isEquipping);
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.isConscious() && this.isConscious()) {
            List<Exit> exits = map.locationOf(otherActor).getExits();
            boolean isActorReachable = false;
            int i = 0;

            while (!isActorReachable && i < exits.size()) {
                if (exits.get(i).getDestination().getActor() != null && exits.get(i).getDestination().getActor().equals(this)) {
                    isActorReachable = true;
                }
                i++;
            }

            if (isActorReachable) {
                if (!otherActor.hasCapability(Status.IMMUNE)) {
                    actions.add(new AttackAction(this, direction));
                }

                if (otherActor.hasCapability(Character.PLAYER)) {
                    if (!this.hasCapability(Status.NOT_CATCHABLE)){
                        actions.add(new CaptureAction(this, direction));
                    }

                    for (Item elem: otherActor.getInventory()) {
                        if (elem.hasCapability(Status.FRUIT) && !this.hasCapability(AffectionLevel.DISLIKE)) {
                            actions.add(new FeedAction(this, direction, elem));
                        }

                    }
                }
            }
        }
        return actions;
    }

    /**
     * Switch the pokemon's weapon in the game
     *
     * @param isEquipping boolean value representing if the pokemon is equipping any weapon at the moment
     */
    public abstract void toggleWeapon(boolean isEquipping);

}
