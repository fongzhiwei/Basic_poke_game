package game.environment;

import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents the floor inside a building.
 * This terrain is not expandable
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Soh Meng Jienq
 * @version 2.0
 *
 * @see Ground
 * @see CapabilityOfExpand
 */
public class Floor extends Ground {
	/**
	 * Constructor.
	 * Floor  shows symbol '_' in game map, and it is not expandable.
	 */
	public Floor() {
		super('_');
		this.addCapability(CapabilityOfExpand.NOT_EXPANDABLE);
	}

}
