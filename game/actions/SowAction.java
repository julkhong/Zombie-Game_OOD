package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.*;
import game.Crop;
import game.capability.DirtCapability;

/**
 * Action for sowing a crop.
 * Only available to Farmer.
 * @author Jun Ming Khong
 *
 */
public class SowAction extends Action{
	/**
	 * Generates random number, use as probabilty in sowing
	 */
	protected Random rand = new Random();
	/**
	 * an available adjacent location
	 */
	protected Location TargetLocation;
	/**
	 * Dirt's capability (SOWABLE)
	 */
	protected DirtCapability DirtCap;

	/**
	 * Constructor, which takes a Location and a DirtCapability as inputs.
	 * @param targetLocation an available adjacent location
	 * @param dirtCap Dirt's capability (SOWABLE)
	 */
	public SowAction(Location targetLocation, DirtCapability dirtCap){
		this.TargetLocation = targetLocation;
		this.DirtCap = dirtCap;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		String result = actor + " fails to sow a Crop";
		if (rand.nextFloat()<= 0.33) {
			Crop crop = new Crop();
			TargetLocation.addItem(crop);
			TargetLocation.getGround().removeCapability(DirtCap.SOWABLE);
			TargetLocation.getGround().addCapability(DirtCap.FERTILIZABLE);
			result = actor + " sows a crop ";
			
		}
		return result;
	}
	
	
	
	
	@Override
	public String menuDescription(Actor actor) {
		return actor + " sows in " + TargetLocation;
	}
}
