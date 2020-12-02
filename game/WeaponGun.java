package game;
import edu.monash.fit2099.engine.WeaponItem;
import game.capability.ItemCapability;

/**
 * An abstract class
 * represents a gun weapon
 * 
 * @author JulianYH
 *
 */
public abstract class WeaponGun extends WeaponItem{
	

	/**
	 * Constructor
	 * 
	 * @param name The name of the gun
	 * @param displayChar The display char of the gun
	 * @param damage The damage that the gun can do
	 * @param verb The verb when using the gun
	 */
	public WeaponGun(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
		addCapability(ItemCapability.WEAPON);
		addCapability(ItemCapability.UNLOADED);
	}
	
}
