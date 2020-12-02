package game;

import edu.monash.fit2099.engine.*;
import game.capability.DirtCapability;
/**
 * A class represents a Crop. 
 * 
 * @author Jun Ming Khong
 *
 */

public class Crop extends Item{
	/**
	 * Crop's age (to keep track of turns)
	 */
	private int age = 0;
	/**
	 * Dirt's capability
	 */
	private DirtCapability DirtCap;
	
	/**
	 * Default Constructor, which creates Crop that's not portable.
	 * 
	 */
	public Crop() {
		super("Crop",'v', false);
	}
	@Override
	public void tick(Location aLocation) {
		super.tick(aLocation);
		age ++;
		if (age == 20) {
			displayChar = 'Y';
			aLocation.getGround().removeCapability(DirtCap.FERTILIZABLE);
			aLocation.getGround().addCapability(DirtCap.HARVESTABLE);

		}


	}
}
