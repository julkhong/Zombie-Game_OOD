package game.actions;
import edu.monash.fit2099.engine.*;
import game.capability.MamboMarieCapability;

/**
 * Action for vanishing in air.
 * (only used by MamboMaire)
 * @author Jun Ming Khong
 *
 */
public class VanishAction extends Action {
	/**
	 * Represents the PortableItem of MamboMarie
	 */
	protected Item Curse;
	/**
	 * Constructor, which takes an input of Item
	 * @param curse the PortableItem of MamboMarie
	 */
	public VanishAction(Item curse) {
		this.Curse = curse;
	}
	@Override
	public String execute(Actor actor, GameMap map) {
		Curse.removeCapability(MamboMarieCapability.VANISH);
		map.removeActor(actor);
		String result = actor + " vanishes in air ";
		return result;
		
	}
	@Override
	public String menuDescription(Actor actor) {
		return  actor + " vanishes in air ";
	}

}
