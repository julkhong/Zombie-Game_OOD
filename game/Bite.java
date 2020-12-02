package game;

import edu.monash.fit2099.engine.IntrinsicWeapon;

/**
 * An Intrinsic Weapon for the Zombie which bites other targets
 * 
 * @author JulianYH
 *
 */
public class Bite extends IntrinsicWeapon {
	
	/**
	 * Constructor
	 * 
	 * Let the Zombie Bite
	 */
	public Bite() {
		super(8, "bites");
	}

}
