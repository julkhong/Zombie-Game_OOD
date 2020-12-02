package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.capability.ItemCapability;

/**
 * An action that crafts a Zombie Limb to a usable weapon
 * 
 * @author JulianYH
 * 
 */
public class CraftWeaponAction extends Action{
	/**
	 * the Zombie Limb that is about to be crafted
	 */
	private Item zombieLimb;
	
	/**
	 * The name of the weapon after the Zombie Limb is crafted to
	 */
	private String craftTo;

	/**
	 * Constructor
	 * 
	 * Checks what type of the zombie limb is either a Zombie Arm or a Zombie Leg
	 * if it is a Zombie Arm then it will be crafted to a Zombie Club
	 * if it is a Zombie Leg then it will be crafted to a Zombie Mace
	 * 
	 * @param zombieLimb the zombie limb that is to be crafted
	 */
	public CraftWeaponAction(Item zombieLimb) {
		this.zombieLimb = zombieLimb;
		
		if(zombieLimb.getDisplayChar() == '!') {
			craftTo = "Zombie Club";
		}else if (zombieLimb.getDisplayChar() == '&') {
			craftTo = "Zombie Mace";
		}
	}
	
	/**
	 * Execute the action
	 * 
	 * Removes the Capability of the Zombie Limb and add in a Weapon capbability
	 * By doing this will allow this item to be used as a Weapon
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = actor + " crafted " + zombieLimb.toString() + " to a " + craftTo;
		zombieLimb.removeCapability(ItemCapability.ZOMBIELIMB);
		zombieLimb.addCapability(ItemCapability.WEAPON);
		zombieLimb.addCapability(ItemCapability.WEAPONZOMBIE);
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " crafts " + zombieLimb.toString() + " to " + craftTo;
	}

}
