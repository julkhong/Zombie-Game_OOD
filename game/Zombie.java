package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.HuntBehaviour;
import game.behaviours.PickUpBehaviour;
import game.behaviours.SayBehaviour;
import game.behaviours.WanderBehaviour;
import game.capability.ItemCapability;
import game.capability.ZombieCapability;

/**
 * A Zombie.
 * 
 * 
 * @author ram
 *
 */
public class Zombie extends ZombieActor {
	private int countLegs = 2;
	private int countArms = 2;
	private ZombieLimb[] leg = {
			new ZombieLeg(),
			new ZombieLeg()
	};
	private ZombieLimb[] arm = {
			new ZombieArm(),
			new ZombieArm()
	};
	
	private GameMap map;
	private Random rand = new Random();
	
	private Behaviour[] behaviours = {
			new SayBehaviour(),
			new PickUpBehaviour(ItemCapability.WEAPONZOMBIE),
			new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()
	};
	/**
	 * Constructor
	 * 
	 * @param name the name of this Zombie
	 */
	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
	}
	
	/**
	 * Do some damage to the Zombie.
	 * A 25% probability that the Zombie will loose either its Arm or Leg
	 * Lost Arm or Leg will be placed on the adjacent location of the Zombie
	 * If the Zombie does not have arms left, it will drop any weapon it is holding
	 */
	@Override
	public void hurt(int points) {
		
		hitPoints -= points;
		boolean haveLegs = countLegs > 0;
		boolean haveArms = countArms > 0;
		if (rand.nextFloat() < 0.25) {
			if(rand.nextBoolean()) {
				// if true means loose an arm
				if(!haveArms) {
					// if there is no arms left, then loose leg
					dropItemAdjacent(leg[countLegs-1]);
					countLegs -= 1;
					
				} else {
					dropItemAdjacent(arm[countArms-1]);
					countArms -= 1;
				}
			}else {
				if(!haveLegs) {
					// if there is no legs left, then loose arm
					dropItemAdjacent(arm[countArms-1]);
					countArms -= 1;
					
				}else {
					// loose leg
					dropItemAdjacent(leg[countLegs-1]);
					countLegs -= 1;
				}
			}
			if(countArms == 0) {
				// drop the weapon the zombie is holding
				removeHoldingItem();
			}
		}
	}
	
	/**
	 * A private method that checks whether this Zombie is holding anything,
	 * and drop those items.
	 */
	private void removeHoldingItem() {
		while(!this.getInventory().isEmpty()) {
			Item item = getInventory().get(0);
			dropItemAdjacent(item);
			removeItemFromInventory(item);
		}
	}
	
	/**
	 * Drops an Item on a random adjacent location of this Zombie
	 * 
	 * @param item the Item to be dropped
	 */
	private void dropItemAdjacent(Item item) {
		ArrayList<Location> locations = new ArrayList<Location>();
		for (Exit exit : map.locationOf(this).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(this)) {
            	locations.add(destination);
            }
        }
		if (!locations.isEmpty()) {
			Location placeAt = locations.get(rand.nextInt(locations.size()));
			placeAt.addItem(item);
		}
		else {
			map.locationOf(this).addItem(item);
		}
	}
	
	/**
	 * Checks whether this Zombie is conscious
	 * 
	 * returns true if it sill have at least 1 limb and still have
	 * positive hitpoints
	 * 
	 * @return true if and only if it has at least 1 limb and positive hitpoints
	 */
	@Override
	public boolean isConscious() {
		int sumLimbs = countLegs + countArms;
		if(sumLimbs > 0 && hitPoints > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * return an IntrinsicWeapon of this Zombie either a bite or a punch
	 * If this Zombie has only one arm left, then it has only 25% probability of punching the target
	 * If this Zombie has no arms left, it only can bite the target
	 * 
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		Random rand = new Random();
		if (rand.nextFloat() < 0.5) {
			return new Bite();
		}else if (countArms == 1){
			if(rand.nextFloat() < 0.25) {
				return new Punch();
			}else {
				return new Bite();
			}
		}else if(countArms  == 0){
			return new Bite();
		}else {
			return new Bite();
		}
	}

	/**
	 * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.  
	 * If no humans are close enough it will wander randomly.
	 * If the Zombie is standing on a Weapon, it can pick that up and use it
	 * The Zombie can also say the word "Braaaaains"
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		this.map = map;
		// 50% chance of dropping a weapon if arms are left only 1
		if(countArms == 1) {
			if(rand.nextFloat() < 0.5) {
				removeHoldingItem();
			}
		}
		for (Behaviour behaviour : behaviours) {
			boolean huntBehaviour = HuntBehaviour.class.isInstance(behaviour);
			boolean wanderBehaviour = WanderBehaviour.class.isInstance(behaviour);
			if(huntBehaviour || wanderBehaviour) {
				if(countLegs == 0) {
					continue;
				}else if(countLegs == 1 && MoveActorAction.class.isInstance(lastAction)) {
					continue;
				}
			}
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();	
	}
}
