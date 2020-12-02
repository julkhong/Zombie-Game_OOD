package game;

import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.WeaponItem;
import game.capability.ItemCapability;
/**
 * An abstract class
 * Represents a Zombie limb which could be crafted to a Weapon
 * 
 * @author JulianYH
 *
 */
public abstract class ZombieLimb extends WeaponItem{
	
	private String craftTo;
	
	/**
	 * Constructor
	 * 
	 * @param name the name of the Limb
	 * @param displayChar the display char of the limb
	 * @param damage the amount of damage this limb does after it is crafted to a weapon
	 * @param verb the verb used for this weapon (after it is crafted)
	 * @param craftTo the name of the weapon after it is crafted
	 */
	public ZombieLimb(String name, char displayChar, int damage, String verb, String craftTo) {
		super(name, displayChar, damage, verb);
		assert name != null : "ZombieLimb: name cannot be null";
		assert craftTo != null : "ZombieLimb: craftTo cannot be null";
		assert damage >= 0 : "ZombieLimb: damage must be larger than 0";
		this.craftTo = craftTo;
		addCapability(ItemCapability.ZOMBIELIMB);
	}
	
	/**
	 * Checks whether this limb  has a capability of "WEAPON"
	 * If there is none, then it cant be used as a weapon (Player need to craft first)
	 * If there is, then it can be used as a weapon
	 */
	@Override
	public Weapon asWeapon() {
		if (this.hasCapability(ItemCapability.WEAPON)){
			return this;
		}
		return null;
	}
	
	/**
	 * Removes the capability given as input
	 * and change the name of the limb to the intended weapon name
	 * This method is called specifically by CraftWeaponAction
	 */
	@Override
	public void removeCapability(Enum<?> capability) {
		super.removeCapability(capability);
		this.name = craftTo;
	}

}
