package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.capability.ItemCapability;

/**
 * An action that enables the Player to load a gun with ammunition
 * @author JulianYH
 *
 */
public class LoadGunAction extends Action{
	
	/**
	 * The gun object that the Player intend to load ammunition
	 */
	private Item gun;
	/**
	 * The ammunition object that the Player use to load a gun
	 */
	private Item ammunition;
	
	/**
	 * Constructor
	 * 
	 * @param gun The gun object that the Player intend to load ammunition
	 * @param ammunition The ammunition object
	 */
	public LoadGunAction(Item gun, Item ammunition) {
		this.gun = gun;
		this.ammunition = ammunition;
	}
	
	/**
	 * Execute the action
	 * 
	 * Loads two bullets into the gun per ammunition
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = actor + " loaded " + gun.toString();
		gun.removeCapability(ItemCapability.UNLOADED);
		gun.addCapability(ItemCapability.LOADED);
		gun.addCapability(ItemCapability.BULLET1);
		gun.addCapability(ItemCapability.BULLET2);
		actor.removeItemFromInventory(ammunition);
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " loads " + gun.toString() + " with " + ammunition.toString();
	}

}
