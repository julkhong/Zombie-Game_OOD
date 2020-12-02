package game.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.PortableItem;
import game.capability.HealCapability;
import game.capability.ItemCapability;
import game.capability.MamboMarieCapability;
import game.capability.ZombieCapability;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {
	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}
	
	/**
	 * Checks whether the target is Conscious
	 * If the target is not conscious, create a new corpse PortableItem and
	 * remove the target from the map
	 * @param actor this actor
	 * @param map the map of the current gameplay
	 * @return a string which represents the result
	 */
	protected String isTargetConscious(Actor actor, GameMap map) {
		String result = "";
		if (!target.isConscious()) {
			if(target.hasCapability(ZombieCapability.ALIVE)) {
				Item corpse = new PortableItem("Dead " + target, '%');
				map.locationOf(target).addItem(corpse);
			}
			else if(target.hasCapability(ZombieCapability.MAMBOMARIE)) {
				List<Exit> exits = new ArrayList<Exit>(map.locationOf(target).getExits());
				Collections.shuffle(exits);
				
				if(actor.hasCapability(ZombieCapability.PLAYER)) {
					actor.addCapability(MamboMarieCapability.DEAD);
				}
				
				map.removeActor(target);	
				
				result += System.lineSeparator() + target + " is killed.";
				return result;
			}
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory()) {
				dropActions.add(item.getDropAction());
			}
			for (Action drop : dropActions) {		
				drop.execute(target, map);
			}
			
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
		}
		return result;
	}
	
	/**
	 * updates the gun ammunition after each shot is used
	 * 
	 * @param gun The gun that is used to shot
	 */
	protected void updateGunBullet(Item gun) {
		// updates the gun ammunition status
		if(gun.hasCapability(ItemCapability.BULLET1) && gun.hasCapability(ItemCapability.BULLET2)) {
			gun.removeCapability(ItemCapability.BULLET1);
		}else if (gun.hasCapability(ItemCapability.BULLET2)) {
			gun.removeCapability(ItemCapability.BULLET2);
			gun.removeCapability(ItemCapability.LOADED);
			gun.addCapability(ItemCapability.UNLOADED);
		}	
		
			
	}
	
	/**
	 * Execute this action
	 * 
	 * Attacks the target
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();
		
		if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
		}
		
		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);
		target.addCapability(HealCapability.HEAL);
		result += isTargetConscious(actor, map);

		return result;
	}
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
