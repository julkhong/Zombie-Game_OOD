package game.actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * Provides the rifle the ability to shoot a target
 * @author JulianYH
 *
 */
public class RifleShootAction extends AttackAction{
	/**
	 * Represents the number of aims the player have spend
	 */
	private int aimCount;
	/**
	 * The rifle object being used to shoot the target
	 */
	private Item rifle;
	
	/**
	 * Constructor
	 * 
	 * @param target The target actor
	 * @param aimCount The number of aims the Player spend
	 * @param rifle The rifle used to shoot the target
	 */
	public RifleShootAction(Actor target, int aimCount, Item rifle) {
		super(target);
		this.aimCount = aimCount;
		this.rifle = rifle;
	}
	
	/**
	 * Executes this action
	 * 
	 * checks how many aims did the Player spend
	 * Attack is based on the number of aims where:
	 * no aims: 75% of hitting the target
	 * 1 aim: 90% of hitting the target
	 * 2 aims: 100% chance to hit, instakill
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = rifle.asWeapon();
		int damage = weapon.damage() + 5;
		if(aimCount == 0) {
			if(rand.nextFloat() < 0.25) {
				return actor + " misses " + target;
			}
		}else if (aimCount == 1) {
			if (rand.nextFloat() < 0.1) {
				return actor + " misses " + target;
			}else {
				System.out.println("test");
				damage *= 2;
			}
		}
		String result = actor + " shot " + target + " for " + damage + " damage with " + weapon;
		if (aimCount == 2) {
			damage = 200;
			result = actor + " instakill " + target + " with " + weapon;
		}
		target.hurt(damage);
		result += isTargetConscious(actor, map);
		updateGunBullet(rifle);
		return result;
		
	}
	

	@Override
	public String menuDescription(Actor actor) {
		return "Shoot " + target.toString();
	}

}
