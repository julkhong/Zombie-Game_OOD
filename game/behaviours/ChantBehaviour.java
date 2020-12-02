package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.actions.ChantAction;
import game.actions.VanishAction;
import game.capability.MamboMarieCapability;
/**
 * A behaviour that checks on the an item and decide which actions to implement (ChantAction and VanishAction).
 * (only used by MamboMarie)
 * @author Jun Ming Khong
 *
 */
public class ChantBehaviour implements Behaviour{
	/**
	 * MamboMarieCapability.CHANT
	 */
	private MamboMarieCapability Chant;
	/**
	 * MamboMarieCapability.VANISH
	 */
	private MamboMarieCapability Vanish;
	
	/**
	 * Default constructor which takes 2 inputs of MamboMarieCapability.
	 * @param chant  MamboMarieCapability.CHANT
	 * @param vanish MamboMarieCapability.VANISH
	 */
	public ChantBehaviour(MamboMarieCapability chant, MamboMarieCapability vanish) {
		this.Chant = chant;
		this.Vanish = vanish;
	}
	@Override
	public Action getAction(Actor actor, GameMap map) {
		for(int i = 0; i < (actor.getInventory().size()); i++ ) {
			if (actor.getInventory().get(i).hasCapability(Chant)) {
				return new ChantAction(actor.getInventory().get(i));
			}
			else if (actor.getInventory().get(i).hasCapability(Vanish)) {
				return new VanishAction(actor.getInventory().get(i));
			}
		}
		return null;
		
	}
}
