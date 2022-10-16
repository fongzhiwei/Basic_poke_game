package game.pokemon;

/**
 * An interface for evolving purpose.
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 * @version 1.0
 */
public interface CanEvolve {
    /**
     * Check if pokemon meets the criteria to evolve
     *
     * @return boolean value representing the result of the validation
     */
    public boolean canPokemonEvolve();

    public Pokemon evolve();
}
