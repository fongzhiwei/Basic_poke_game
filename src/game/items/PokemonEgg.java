package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.pokemon.BasePokemon;
import game.pokemon.Pokemon;

import java.util.List;

/**
 * A class that represents Pokemon Egg.
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 * @version 1.0
 *
 * @see Item
 * @see Status
 * @see BasePokemon
 * @see Pokemon
 */
public class PokemonEgg extends Item {

    private int hatchTime;

    private int hatchTimer;

    private Pokemon pokemon;

    /**
     * Constructor.
     * Pokemon Egg shows symbol 'g' in game map. Each pokemon egg has a specific time to hatch and only
     * base Pokemon (exp: Charmander, Bulbasaur, Squirtle) can be born from an egg.
     */
    public PokemonEgg(BasePokemon basePokemon){
        super(basePokemon.toString() + "Egg",'g',true);
        setHatchTimer(0);
        setHatchTime(basePokemon.getHatchTime());
        setPokemon(basePokemon.getPokemon());
        this.addCapability(basePokemon);
        this.addCapability(Status.EGG);
    }

    /**
     * This method will check whether the pokemon egg is able to hatch for each turn.
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);

        // calculate the hatch time of the pokemon egg, increase one for each turn
        if (hatchTimer<hatchTime && currentLocation.getGround().hasCapability(Status.HATCHING_GROUND)){
            hatchTimer++;
        }

        // check whether the pokemon egg reaches its specific time to hatch
        if(hatchTimer == hatchTime){
            // if there is no actor on top of the incubator, hatch the pokemon on it
            if(!currentLocation.containsAnActor()){
                currentLocation.addActor(getPokemon());
                currentLocation.removeItem(this);
            }
            else {
                // if there is an actor on top of the incubator, hatch the pokemon on the surrounding empty space
                List<Exit> exits = currentLocation.getExits();

                for (Exit exit: exits){
                    // check if the surrounding ground is empty, pokemon egg can only hatch when there is an empty space
                    if (exit.getDestination().getGround().canActorEnter(getPokemon()) && !exit.getDestination().containsAnActor()){
                        exit.getDestination().addActor(getPokemon());
                        exit.getDestination().removeItem(this);
                        System.out.println(getPokemon() + " is hatched");
                        return;
                    }
                }
            }
        }
    }

    /**
     * Get hatch timer of the pokemon egg
     * @return hatch timer
     */
    public int getHatchTimer() {
        return hatchTimer;
    }

    /**
     * Get hatch time of the pokemon egg
     * @return hatch time
     */
    public int getHatchTime() {
        return hatchTime;
    }

    /**
     * Get base pokemon (Charmander, Bulbasaur, Squirtle)
     * @return a pokemon
     */
    public Pokemon getPokemon() {
        return pokemon;
    }

    /**
     * Set hatch time of the pokemon egg
     * @param hatchTime new hatch time
     */
    public void setHatchTime(int hatchTime) {
        this.hatchTime = hatchTime;
    }

    /**
     * Set hatch timer of the pokemon egg
     * @param hatchTimer new hatch timer
     */
    public void setHatchTimer(int hatchTimer) {
        this.hatchTimer = hatchTimer;
    }

    /**
     * Set pokemon of the pokemon egg
     * @param pokemon new pokemon
     */
    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
