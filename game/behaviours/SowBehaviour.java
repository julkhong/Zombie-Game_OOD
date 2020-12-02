package game.behaviours;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.actions.SowAction;
import game.capability.DirtCapability;

/**
 * A class to figure out if available adjacent location's dirt has sow capability, and proceed with SowAction to sow a crop.
 * Only available to Farmer.
 * @author Jun Ming Khong
 *
 */
public class SowBehaviour implements Behaviour {
	/**
	 * Dirt's capability (SOWABLE)
	 */
	private DirtCapability sowable;
	
	/**
	 * Constructor, which takes an input of DirtCapability
	 * @param SowableDirt Dirt's capability (SOWABLE)
	 */
	public SowBehaviour(DirtCapability SowableDirt) {
		this.sowable = SowableDirt;
	}
	@Override
	public Action getAction(Actor actor, GameMap map) {
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		Collections.shuffle(exits);
		
		for (Exit e: exits) {
			if ((e.getDestination().containsAnActor()))
				continue;
			if (e.getDestination().getGround().hasCapability(sowable)) {
				return new SowAction(e.getDestination(),sowable);
			}
		}
		return null;
		
	}

}

