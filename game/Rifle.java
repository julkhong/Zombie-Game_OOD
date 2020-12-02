package game;

import game.capability.ItemCapability;

/**
 * A Sniper Rifle gun
 * 
 * @author JulianYH
 *
 */
public class Rifle extends WeaponGun{

	/**
	 * Constructor
	 */
	public Rifle() {
		super("Sniper Rifle", 'R', 15, "hits");
		addCapability(ItemCapability.RIFLE);
		
	}

}
