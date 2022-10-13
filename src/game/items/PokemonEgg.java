package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.AffectionManager;
import game.Status;
import game.pokemon.BasePokemon;
import game.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents Pokemon Egg.
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 * @version 1.0
 *
 */
public class PokemonEgg extends Item {

    private int hatchTime;

    private int hatchTimer;

    private Pokemon pokemon;

    /**
     * Constructor.
     * Pokemon Egg shows symbol 'g' in game map.
     */
    public PokemonEgg(BasePokemon basePokemon){
        super(basePokemon.toString() + " Egg",'g',true);
        setHatchTimer(0);
        setHatchTime(basePokemon.getHatchTime());
        setPokemon(basePokemon.getPokemon());
        this.addCapability(basePokemon);
        this.addCapability(Status.EGG);
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        if (hatchTimer<hatchTime && currentLocation.getGround().hasCapability(Status.HATCHING_GROUND)){
            hatchTimer++;
        }
        if(hatchTimer == hatchTime){
            if(!currentLocation.containsAnActor()){
                currentLocation.addActor(getPokemon());
                currentLocation.removeItem(this);
            }
            else {
                List<Exit> exits = currentLocation.getExits();

                for (Exit exit: exits){
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

    public int getHatchTimer() {
        return hatchTimer;
    }

    public int getHatchTime() {
        return hatchTime;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setHatchTime(int hatchTime) {
        this.hatchTime = hatchTime;
    }

    public void setHatchTimer(int hatchTimer) {
        this.hatchTimer = hatchTimer;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
