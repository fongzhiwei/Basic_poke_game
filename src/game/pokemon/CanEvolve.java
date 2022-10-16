package game.pokemon;

/**
 * An interface for evolving purpose.
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 * @version 1.0
 */
public interface CanEvolve {
    /**
     * Get the starting turn number for when the Pokemon is spawned to the game world
     *
     * @return turn number when the Pokemon is spawned
     */
    public boolean canPokemonEvolve();
}
