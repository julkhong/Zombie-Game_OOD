package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Weapon;

/**
 * A special action used only by Zombie to attack a target
 * 
 * @author JulianYH
 *
 */
public class ZombieAttackAction extends AttackAction{

	/**
	 * Constructor
	 * 
	 * @param target the target to be attacked
	 */
	public ZombieAttackAction(Actor target) {
		super(target);
	}
	
	/**
	 * Execute this action
	 * 
	 * Let the zombie attack a attackable actor
	 * The zombie will attack the target either using the weapon it is holding or
	 * its intrinsic Weapon
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();
		
		if(weapon.verb() == "bites") {
			// 75% of missing the target
			if(rand.nextFloat() < 0.75) {
				return actor + " misses " + target + ".";
			}
		}else if (rand.nextFloat() < 0.5) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		if(weapon.verb() == "bites") {
			actor.heal(5);
		}
		target.hurt(damage);
		result += isTargetConscious(actor, map);

		return result;
	}

}
