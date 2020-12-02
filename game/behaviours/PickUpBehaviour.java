package game.behaviours;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.Behaviour;
import game.capability.ItemCapability;

/**
 * Allows the actor to pickup a targeted item on the ground when standing on it
 * 
 * @author JulianYH
 *
 */
public class PickUpBehaviour implements Behaviour {
	
	/**
	 * Random number generator
	 */
	private Random random = new Random();
	
	/**
	 * The targeted Item Capability
	 */
	private ItemCapability targetItemType;
	
	/**
	 * Constructor
	 * 
	 * @param targetItemType the targeted Item Capability
	 */
	public PickUpBehaviour(ItemCapability targetItemType) {
		this.targetItemType = targetItemType;
	}

	/**
	 * Returns a PickUpItemAction to pickup an item, if possible
	 * If there is no item possible to pickup, then returns null
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Item> weapons = new ArrayList<Item>();
		Location here = map.locationOf(actor);
		
		for(Item item : here.getItems()) {
			// checks which items on the ground are the targeted item type
			if(item.hasCapability(targetItemType)) {
				weapons.add(item);
			}
		}
		if(!weapons.isEmpty()) {
			return new PickUpItemAction(weapons.get(random.nextInt(weapons.size())));
		}
		return null;
	}

}
