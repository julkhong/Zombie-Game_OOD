package game;

import game.capability.ItemCapability;

/**
 * Represents a shotgun ammunition
 * @author JulianYH
 *
 */
public class ShotgunAmmunition extends PortableItem{

	/**
	 * Constructor
	 */
	public ShotgunAmmunition() {
		super("Shotgun Ammunition", 'g');
		addCapability(ItemCapability.SHOTGUNAM);
	}

}
