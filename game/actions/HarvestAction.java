package game.actions;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.*;
import game.PortableItem;
import game.capability.DirtCapability;
/**
 * Action for harvesting a ripen crop(food).
 * 
 * @author Jun Ming Khong
 *
 */
public class HarvestAction extends Action {
	/**
	 * Actor's current location or available adjacent location
	 */
	protected Location TargetLocation;
	/**
	 * Direction from the actor to the ripen crop.
	 * Specially made for Player.
	 */
	protected String Direction;

	/**
	 * Constructor, which takes inputs of a Location and a String direction.
	 * @param targetLocation Actor's current location or available adjacent location
	 * @param direction  Direction from the actor to the ripen crop.
	 */
	public HarvestAction(Location targetLocation, String direction){
		this.TargetLocation = targetLocation;
		this.Direction = direction;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		Item foodInDirt = new PortableItem("Food",'O');
		List<Item> dirtItems = new ArrayList<Item>();
		dirtItems = TargetLocation.getItems();
		for(int i = 0; i < dirtItems.size();i ++) {
			char tempChar = dirtItems.get(i).getDisplayChar();
			if (tempChar == 'Y') {
				TargetLocation.removeItem(dirtItems.get(i));
			}
		}
		TargetLocation.addItem(foodInDirt);
		TargetLocation.getGround().removeCapability(DirtCapability.HARVESTABLE);
		char actorChar = actor.getDisplayChar();
		if (actorChar == '@') {
			actor.addItemToInventory(foodInDirt);
			TargetLocation.removeItem(foodInDirt);
			
		}
	
		
		
		String result = actor + " harvests a Crop (Food)";
			
		
		return result;
	}
	
	
	
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " harvests the crop in " + Direction;
	}
}
