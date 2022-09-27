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
import game.behaviours.WanderBehaviour;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class Pokemon extends Actor{
    private final SortedMap<Integer, Behaviour> behaviours;
    protected Location pokemonLocation;

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

    public SortedMap<Integer, Behaviour> getBehaviours() {
        return this.behaviours;
    }

    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "tackle");
    }

    public void setAffectionLevel(AffectionLevel newAffectionLevel) {
        if (this.findCapabilitiesByType(AffectionLevel.class).size() > 0) {
            this.findCapabilitiesByType(AffectionLevel.class).clear();
        }
        this.addCapability(newAffectionLevel);
    }

    public void setStatus(int affectionPoints) {
        if (this.findCapabilitiesByType(Status.class).size() > 0) {
            for (Status status : this.findCapabilitiesByType(Status.class)) {
                this.removeCapability(status);
            }
        }
        System.out.println(findCapabilitiesByType(AffectionLevel.class));
        if (this.hasCapability(AffectionLevel.DISLIKE)) {
            this.addCapability(Status.HOSTILE);
        }
        else if (this.hasCapability(AffectionLevel.NEUTRAL)) {
            this.addCapability(Status.HOSTILE);
        }
        else if (this.hasCapability(AffectionLevel.LIKE)) {
            this.addCapability(Status.CATCHABLE);
        }
        else if (this.hasCapability(AffectionLevel.FOLLOW)) {
            this.addCapability(Status.CATCHABLE);
        }
        else if (this.hasCapability(AffectionLevel.MAX)) {
            this.addCapability(Status.CATCHABLE);
        }
        System.out.println(findCapabilitiesByType(Status.class));
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
            }
            if (action != null) {
                this.toggleWeapon(isEquipping);
                return action;
            }
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
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new AttackAction(this, direction));
        //FIXME: allow other actor to attack this Squirtle (incl. Player). Please check requirement! :)
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
                if (!ElementsHelper.hasAnySimilarElements(this, otherActor.findCapabilitiesByType(Element.class))) {
                    actions.add(new AttackAction(this, direction));
                }

                if (otherActor.hasCapability(Character.PLAYER)) {
                    actions.add(new CaptureAction(this, direction));

                    for (Item elem: otherActor.getInventory()) {
                        if (elem.hasCapability(Status.FRUIT) && !this.hasCapability(AffectionLevel.DISLIKE)) {
                            actions.add(new FeedAction(this, direction, elem));
                        }

//                        if (elem.hasCapability(Status.BALL)) {
//                            actions.add(new SummonAction(this, direction));
//                        }
                    }
                }
            }
        }
        return actions;
    }

    /**
     * @param isEquipping FIXME: develop a logic to toggle weapon (put a selected weapon to the inventory - used!);
     */
    public abstract void toggleWeapon(boolean isEquipping);

}
