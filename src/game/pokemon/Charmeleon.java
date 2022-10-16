package game.pokemon;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.AffectionLevel;
import game.AffectionManager;
import game.Character;
import game.Element;
import game.Utils;
import game.actions.EvolveAction;
import game.behaviours.EvolveBehaviour;
import game.time.TimePerceptionManager;
import game.weapons.SpecialWeapon;

/**
 * Class representing the Charmeleon
 *
 * Created by:
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 *
 * Modified by:
 *
 * @see Pokemon
 * @see CanEvolve
 * @see AffectionLevel
 * @see AffectionManager
 * @see Character
 * @see Element
 * @see Utils
 * @see EvolveAction
 * @see EvolveBehaviour
 * @see TimePerceptionManager
 * @see SpecialWeapon
 */
public class Charmeleon extends Pokemon implements CanEvolve {
    /**
     * The starting turn number when a Pokemon is spawned
     */
    private final int birthCount;

    /**
     * Constructor.
     */
    public Charmeleon() {
        super("Charmeleon", 'C', 150);
        this.addCapability(Element.FIRE);
        this.getBehaviours().put(0, new EvolveBehaviour());
        this.birthCount = TimePerceptionManager.getInstance().getTurn();
    }

    /**
     * Creates and returns an intrinsic weapon. By default, the Charmeleon 'scratch' for 10 damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(10, "scratch");
    }

    /**
     * Switch the pokemon's weapon in the game
     *
     * @param isEquipping boolean value representing if the pokemon is equipping any weapon at the moment
     */
    public void toggleWeapon(boolean isEquipping) {
        if (this.pokemonLocation.getGround().hasCapability(Element.FIRE)) {
            if (!isEquipping) {
                SpecialWeapon weapon;
                int randomNum = Utils.nextNum(0, 2);

                if (randomNum == 0) {
                    weapon = new SpecialWeapon("Ember", 'E', 20, "sparks", 90, Element.FIRE);
                }
                else {
                    weapon = new SpecialWeapon("Blaze", 'B', 60, "burns", 90, Element.FIRE);
                }

                this.addItemToInventory(weapon);
            }
        }
        else {
            if (isEquipping) {
                this.removeItemFromInventory((Item) this.getWeapon());
            }
        }
    }

    /**
     * Check if pokemon meets the criteria to evolve
     *
     * @return boolean value representing the result of the validation
     */
    @Override
    public boolean canPokemonEvolve() {
        return TimePerceptionManager.getInstance().getTurn() - this.birthCount >= 20;
    }

    @Override
    public Pokemon evolve() {
        return new Charizard();
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
        if (this.canPokemonEvolve()) {
            this.getBehaviours().put(0, new EvolveBehaviour());
        }
        return super.playTurn(actions, lastAction, map, display);
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
        ActionList actions = super.allowableActions(otherActor, direction, map);

        if (otherActor.hasCapability(Character.PLAYER)) {
            if (AffectionManager.getInstance().getAffectionPoint(otherActor, this) == AffectionLevel.MAX.getPoints()) {
                actions.add(new EvolveAction(this));
            }
        }

        return actions;
    }
}
