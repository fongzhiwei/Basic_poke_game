package game.pokemon;

/**
 * An enumeration that store the BasePokemon.
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 * @version 1.0
 *
 */

public enum BasePokemon {
    CHARMANDER("Charmander", new Charmander(),4),       // Base Charmander Pokemon
    BULBASAUR("Bulbasaur", new Bulbasaur(),3),     // Base Bulbasaur Pokemon
    SQUIRTLE("Squirtle", new Squirtle(),2);     // Base Squirtle Pokemon

    /**
     * The label text for the Pokemon
     */
    private final String label;

    /**
     * The Pokemon instance stored within the Base Pokemon
     */
    private final Pokemon pokemon;
    /**
     * The fixed hatch time for that Base Pokemon
     */
    private final int hatchTime;

    /**
     * Constructor.
     *
     * @param label a String that represents the name of the pokemon
     * @param pokemon the pokemon (Charmander, Bulbasaur, Squirtle)
     * @param hatchTime the specific time for pokemon edd to hatch
     */
    BasePokemon(String label,Pokemon pokemon, int hatchTime){
        this.label = label;
        this.pokemon = pokemon;
        this.hatchTime = hatchTime;
    }

    /**
     * Get the specific time for pokemon egg to hatch.
     *
     * @return the hatch time of pokemon egg
     */
    public int getHatchTime() {
        return hatchTime;
    }

    /**
     * Get the pokemon from pokemon egg.
     *
     * @return a pokemon (Charmander/Bulbasaur/Squirtle)
     */
    public Pokemon getPokemon() {
        return pokemon;
    }

    /**
     * Return a string that indicates the name of pokemon (label).
     *
     * @return the label text
     */
    @Override
    public String toString() {
        return label;
    }
}
