package pokemon;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.AttackAction;
import game.Element;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;

public abstract class Pokemon extends Actor{
    private final Element pokemonType;
    private int affectionPoints;

    public Pokemon(String name, char displayChar, int hitPoints, Element type){
        super(name, displayChar, hitPoints);
        this.pokemonType = type;
        this.affectionPoints = 0;
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
    public abstract Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display);





}
