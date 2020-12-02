package game.behaviours;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.actions.HarvestAction;
import game.capability.DirtCapability;
/**
 * A class to figure out if current location's dirt or available adjacent's dirt has harvest capability, 
 * and proceed with HarvestAction to harvest the ripen crop.
 * 
 * @author Jun Ming Khong
 *
 */
public class HarvestBehaviour implements Behaviour{
	/**
	 * Dirt's capability (HARVESTABLE)
	 */
	private DirtCapability Harvestable;
	
	/**
	 * Constructor, which takes an input of a dirt's capability.
	 * @param HarvestableDirt dirt's capability (HARVESTABLE)
	 */
	public HarvestBehaviour(DirtCapability HarvestableDirt) {
		this.Harvestable = HarvestableDirt;
	}
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// stands on
		if(map.locationOf(actor).getGround().hasCapability(Harvestable)){
			return new HarvestAction(map.locationOf(actor),"");
		}

		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		Collections.shuffle(exits);


		for (Exit e: exits) {
			if ((e.getDestination().containsAnActor()))
				continue;
			if (e.getDestination().getGround().hasCapability(Harvestable) ) {
				return new HarvestAction(e.getDestination(),"");
			}
		}
		return null;
		
	}

}
