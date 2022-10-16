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
    boolean canPokemonEvolve();

    /**
     * Pokemon evolves (exp: Charmander -> Charmeleon).
     *
     * @return an evolved pokemon
     */
    Pokemon evolve();
}
