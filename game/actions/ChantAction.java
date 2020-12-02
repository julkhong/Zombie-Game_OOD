package game.actions;
import edu.monash.fit2099.engine.*;
import game.Zombie;
import game.capability.MamboMarieCapability;

/**
 * Action for chanting.
 * (only used by MamboMarie)
 * @author Jun Ming Khong
 *
 */
public class ChantAction extends Action{
	/**
	 * Represents the PortableItem of MamboMarie
	 */
	protected Item Curse;
	
	/**
	 * Constructor, which takes an input of Item
	 * @param curse the PortableItem of MamboMarie
	 */
	public ChantAction(Item curse) {
		this.Curse = curse;
	}
	@Override
	public String execute(Actor actor, GameMap map) {
		Curse.removeCapability(MamboMarieCapability.CHANT);
		int x, y;
		String[] zombies = {"Summoned Zombie","Summoned Zombie","Summoned Zombie","Summoned Zombie","Summoned Zombie"};
		for(String name3: zombies) {
			do {
				x = (int) Math.floor(Math.random() * 50.0 + 10.0);
				y = (int) Math.floor(Math.random() * 15.0 + 5.0);
			}
			while (map.at(x, y).containsAnActor());
			map.at(x,  y).addActor(new Zombie(name3));
		}
		String result = actor + " chants and summons 5 Zombies in random places ";
		return result;
		
	}
	@Override
	public String menuDescription(Actor actor) {
		return actor + " chants and summons 5 Zombies in random places " ;
	}

}
