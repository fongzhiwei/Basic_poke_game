package game.trainer;

import edu.monash.fit2099.engine.actors.Actor;
import game.AffectionManager;
import game.Character;
import game.Status;
import game.behaviours.Behaviour;
import game.pokemon.Pokemon;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class Trainer extends Actor {

    /**
     * A set of behaviours
     */
    private final SortedMap<Integer, Behaviour> behaviours;


    public Trainer(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
        this.behaviours = new TreeMap<>();
        this.addCapability(Status.IMMUNE);
        this.addCapability(Character.TRAINER);
        AffectionManager.getInstance().registerTrainer(this);


    }

    /**
     * Get the Trainer's set of behaviours
     *
     * @return a sorted map of behaviours, ordered according to their respective priority
     */
    public SortedMap<Integer, Behaviour> getBehaviours() {
        return this.behaviours;
    }

}
