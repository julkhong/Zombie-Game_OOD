package game;

import game.capability.ItemCapability;

/**
 * A Shotgun
 * 
 * @author JulianYH
 *
 */
public class Shotgun extends WeaponGun{

	/**
	 * Constructor
	 */
	public Shotgun() {
		super("Shotgun", 'G', 15, "hits");
		addCapability(ItemCapability.SHOTGUN);
	}

}
