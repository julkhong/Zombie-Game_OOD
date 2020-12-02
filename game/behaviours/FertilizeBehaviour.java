package game.behaviours;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.actions.FertilizeAction;
import game.capability.DirtCapability;
/**
 * A class to figure out if current location's dirt has fertilize capability, and proceed with FertilizeAction to fertilize the crop.
 * Only available to Farmer.
 * @author Jun Ming Khong
 *
 */
public class FertilizeBehaviour implements Behaviour {
	/**
	 * Dirt's Capability (FERTILIZABLE)
	 */
	private DirtCapability Fertilizable;
	
	/**
	 * Constructor, takes an input of dirt's capability.
	 * @param FertilizableDirt DirtCapability.FERTILIZABLE
	 */
	public FertilizeBehaviour(DirtCapability FertilizableDirt) {
		this.Fertilizable = FertilizableDirt;
	}
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if(map.locationOf(actor).getGround().hasCapability(Fertilizable)){
			return new FertilizeAction(map.locationOf(actor));
		}
		return null;
		
	}

}

