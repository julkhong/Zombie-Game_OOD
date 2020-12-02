package game;

import edu.monash.fit2099.engine.IntrinsicWeapon;

/**
 * An Intrinsic Weapon thant punches the target
 *
 *@author JulianYH
 */
public class Punch extends IntrinsicWeapon{
	
	/**
	 * Constructor
	 * 
	 * Creates a Punch object that can be used to punch the target
	 */
	public Punch() {
		super(10, "punch");
	}

}
