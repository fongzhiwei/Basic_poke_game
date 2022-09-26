package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or game.actions that can be attached-detached.
 *
 * @author Riordan D. Alfredo
 */
public enum Status {
    IMMUNE,     // an enum to identify that an object is immune to any attack.
    HOSTILE,    // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    CATCHABLE,  // for released pokemons and pokemons with affection points of 50 and more
    CURRENCY,   // for candy
    FRUIT,      // for all pokefruit item
    BALL,       // for all pokeball item
    WEAPON      // for all weapon item
}
