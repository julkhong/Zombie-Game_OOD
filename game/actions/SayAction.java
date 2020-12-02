package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DoNothingAction;

/**
 * An action for saying "Braaaaains"
 * 
 * @author JulianYH
 *
 */
public class SayAction extends DoNothingAction{

	public SayAction() {
	}
	
	/**
	 * Returns a string to the menu that
	 * represnets the Zombie saying "Braaaaains"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " said 'Braaaaains'";
	}

}
