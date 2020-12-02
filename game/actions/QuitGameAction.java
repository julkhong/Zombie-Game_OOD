package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An action that allows the Player to quit the game anytime
 * @author JulianYH
 *
 */
public class QuitGameAction extends Action {

	public QuitGameAction() {
	}
	
	/**
	 * Executes the action
	 * 
	 * Removes the player from the map which triggers the game to stop
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		map.removeActor(actor);
		return actor + " quit game";
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Quit Game";
	}
	
	/**
	 * returns the hot key of 0 to the Player
	 */
	@Override
	public String hotkey() {
		return "0";
	}

}
