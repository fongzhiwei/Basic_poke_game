package game.pokemon;

/**
 * An enumeration that store the BasePokemon.
 * @author Riordan D. Alfredo
 * Modified by: Soh Meng Jienq
 * @version 2.0
 */

public enum BasePokemon {
    CHARMANDER("Charmander", new Charmander(),4),       // fire element
    BULBASAUR("Bulbasaur", new Bulbasaur(),3),     // water element
    SQUIRTLE("Squirtle", new Squirtle(),2);     // grass element

    /**
     * The label text for element type
     */
    private final String label;

    private final Pokemon pokemon;
    private final int hatchTime;

    BasePokemon(String label,Pokemon pokemon, int hatchTime){
        this.label = label;
        this.pokemon = pokemon;
        this.hatchTime = hatchTime;
    }

    public int getHatchTime() {
        return hatchTime;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    /**
     *
     * @return the label text
     */
    @Override
    public String toString() {
        return label;
    }
}
