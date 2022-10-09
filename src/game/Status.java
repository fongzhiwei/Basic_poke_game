package game;

/**
 * An enumeration to store the status of the actor and item instances.
 * Collection of status is used to give a `state` to abilities or
 * game.actions that can be attached-detached.
 *
 * Created by:
 * @author Riordan D. Alfredo
 *
 * Modified by:
 * @author Leong Xin Yun <xleo0002@student.monash.edu>
 * @author Fong Zhiwei <zfon0005@student.monash.edu>
 *
 * @version 2.0
 *
 */
public enum Status {
    IMMUNE,         // an enum to identify that an object is immune to any attack.
    HOSTILE,        // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    NOT_CATCHABLE,  // to identify that an actor is uncatchable
    CATCHABLE,      // to identify that an actor is catchable
    CURRENCY,       // to identify that an item is tradable
    FRUIT,          // to identify that an item is feedable
    BALL,           // to identify that an item can catch pokemon
    EGG,            // to identify that an item is a pokemon egg
    HATCHING_GROUND,// to identify that a ground is an incubator
    WEAPON          // to identify that an item is a weapon
}
