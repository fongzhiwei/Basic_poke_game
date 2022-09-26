package game.environment;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.*;
import game.items.Pokefruit;
import game.pokemon.Pokemon;

import java.util.List;

public abstract class SpawningGround extends Ground{

    public SpawningGround(char displayChar){
        super(displayChar);
    }

    public void tick(Location location, Pokemon pokemon , Element element, int chanceSpawn, int minGround){
        boolean chance = Utils.chance(chanceSpawn);

        int numOfGround = 0;
        List<Exit> exits = location.getExits();
        for (Exit exit : exits) {
            if (exit.getDestination().getGround().hasCapability(element)) {
                numOfGround += 1;
            }
        }

        if (chance && !location.containsAnActor() && numOfGround>=minGround){
            location.addActor(pokemon);
        }
    }

    public void dropFruit(Location location, Element element, int chancePokefruit){
        boolean chance = Utils.chance(chancePokefruit);
        Pokefruit pokefruit = new Pokefruit(element);

        if (chance && !location.containsAnActor()){
            location.addItem(pokefruit);
        }

    }

}
