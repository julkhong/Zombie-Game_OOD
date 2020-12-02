package game;

import edu.monash.fit2099.engine.WeaponItem;
import game.capability.ItemCapability;

/**
 * A primitive weapon.
 * 
 * @author ram
 *
 */
public class Plank extends WeaponItem {
	/**
	 * A plank that can be used to attack other actors
	 */
	public Plank() {
		super("plank", ')', 15, "hit");
		addCapability(ItemCapability.WEAPON);
		addCapability(ItemCapability.WEAPONZOMBIE);
	}

}
