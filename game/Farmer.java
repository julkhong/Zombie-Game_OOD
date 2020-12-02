package game;

import edu.monash.fit2099.engine.*;
import game.behaviours.FertilizeBehaviour;
import game.behaviours.HarvestBehaviour;
import game.behaviours.HealBehaviour;
import game.behaviours.RunAwayBehaviour;
import game.behaviours.SowBehaviour;
import game.behaviours.WanderBehaviour;
import game.capability.DirtCapability;
import game.capability.HealCapability;

/**
 * A class represents farmer.
 * @author Jun Ming Khong
 *
 */
public class Farmer extends Human {

	/**
	 * List of behaviours that Farmer has.
	 */
	private Behaviour[] behaviours = {
			new RunAwayBehaviour(),
			new FertilizeBehaviour(DirtCapability.FERTILIZABLE),
			new HarvestBehaviour(DirtCapability.HARVESTABLE),
			new SowBehaviour(DirtCapability.SOWABLE),
			new HealBehaviour(HealCapability.HEAL),
			new WanderBehaviour()
	};
	/**
	 * The default constructor creates default Farmers
	 * 
	 * @param name the farmer's name
	 */
	public Farmer(String name) {
		
		super(name, 'F', 50);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();	
	}
}