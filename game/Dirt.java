package game;

import edu.monash.fit2099.engine.*;
import game.capability.DirtCapability;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {
	/**
	 * Dirt's Capability
	 */
	private DirtCapability sow;

	/**
	 * Default constructor, upon creation, dirt set to be sowable.  
	 */
	public Dirt() {
		super('.');
		addCapability(sow.SOWABLE);
	}
	
	/**
	 * Constructor, creates Dirt with other char. 
	 * @param myChar a char
	 */
	public Dirt(char myChar){
		super(myChar);

	}

}


