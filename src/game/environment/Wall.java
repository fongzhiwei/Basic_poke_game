package game.environment;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents wall.
 * @author Soh Meng Jienq <msoh0007@stundet.monash.edu>
 * @version 1.0
 *
 * @see CapabilityOfExpand
 */

public class Wall extends Ground {

	/**
	 *  Wall shows symbol '#' in game map, and it is not expandable.
	 */
	public Wall() {
		super('#');
		this.addCapability(CapabilityOfExpand.NOT_EXPANDABLE);
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
