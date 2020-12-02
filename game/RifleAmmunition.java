package game;

import game.capability.ItemCapability;

/**
 * Represents the sniper ammunition which is needed to load a sniper rifle gun
 * @author JulianYH
 *
 */
public class RifleAmmunition extends PortableItem{

	/**
	 * Constructor
	 */
	public RifleAmmunition() {
		super("Rifle Ammunition", 'r');
		addCapability(ItemCapability.RIFLEAM);
	}
	
}
