package game;

import edu.monash.fit2099.engine.WeaponItem;
import game.capability.ItemCapability;
/**
 * A primitive weapon.
 * 
 * @author JulianYH
 * 
 */

public class Steel extends WeaponItem{
	/**
	 * Constructor
	 * 
	 * A steel rod which can be used as weapon to attack other actors
	 */
	public Steel() {
		super("steel rod", '|', 15, "whack");
		addCapability(ItemCapability.WEAPON);
		addCapability(ItemCapability.WEAPONZOMBIE);
	}

}
