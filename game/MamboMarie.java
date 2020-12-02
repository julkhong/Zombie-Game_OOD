package game;

import edu.monash.fit2099.engine.*;

import game.behaviours.ChantBehaviour;
import game.behaviours.WanderBehaviour;
import game.capability.MamboMarieCapability;
import game.capability.ZombieCapability;
/**
 * Represents MamboMarie.
 * @author Jun Ming Khong
 *
 */
public class MamboMarie extends ZombieActor {
	/**
	 * List of behaviours that MamboMarie has.
	 */
	private Behaviour[] behaviours = {
			new ChantBehaviour(MamboMarieCapability.CHANT, MamboMarieCapability.VANISH),
			new WanderBehaviour()
	};
	/**
	 * the map the Player is on.
	 */
	private GameMap map;
	
	/**
	 * Default constructor that creates MamboMarie object.
	 * when MamboMarie is created, a PortableItem will be also created.
	 * the Item won't be dropped, it's used to keep track of time.
	 * @param name name of the boss
	 */
	public MamboMarie(String name) {
		super(name, 'M', 150, ZombieCapability.UNDEAD);
		PortableItem VoodooCurse = new PortableItem("VoodooCurse",'q');
		this.addItemToInventory(VoodooCurse);
		addCapability(ZombieCapability.MAMBOMARIE);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		this.map = map;
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}
}
