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

	public Wall() {
		super('#');
		this.addCapability(CapabilityOfExpand.NOTEXPANDABLE);
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
