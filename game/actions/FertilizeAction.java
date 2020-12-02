package game.actions;
import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.*;

/**
 * Action for fertilizing an unripe crop.
 * Only available to Farmer.
 * @author Jun Ming Khong
 *
 */
public class FertilizeAction extends Action{
	/**
	 * Actor's current location
	 */
	protected Location TargetLocation;
	
	/**
	 * Constructor, which takes inputs of a Location and a DirtCapability
	 * @param targetLocation actor's current location
	 */
	public FertilizeAction(Location targetLocation){
		this.TargetLocation = targetLocation;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		List<Item> dirtItems = new ArrayList<Item>();
		dirtItems = TargetLocation.getItems();
		for(int i = 0; i < dirtItems.size();i ++) {
			char tempChar = dirtItems.get(i).getDisplayChar();
			if (tempChar == 'v') {
				for(int j = 0; j<9; j++) { 
					//skips 10 turns in total 9 in here, another last turn is to be executed in gameMap
					dirtItems.get(i).tick(TargetLocation);
					}
			}
		}
		String result = actor + " fertilizes a Crop";
			
		
		return result;
	}
	
	
	
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " fertilizes the crop in " + TargetLocation;
	}
}
