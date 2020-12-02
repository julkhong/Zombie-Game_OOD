package game.actions;

import java.util.List;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Menu;

/**
 * An action that shows the available direction to shoot based on the position of the player
 * 
 * @author JulianYH
 *
 */
public class ShotgunDirectionAction extends Action{
	private List<Exit> exits;
	private Item shotgun;
	private Display display;
	private Menu menu = new Menu();
	private Actions actions = new Actions();

	/**
	 * Constructor
	 * 
	 * @param exits The exits that is available
	 * @param shotgun The shotgun object that is used
	 * @param display The display object
	 */
	public ShotgunDirectionAction(List<Exit> exits, Item shotgun, Display display) {
		this.exits = exits;
		this.shotgun = shotgun;
		this.display = display;
	}

	/**
	 * Executes this action
	 * 
	 * Shows the list of available directions to shoot in a sub menu
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		for(Exit exit : exits) {
			actions.add(new ShotgunShootAction(exit, shotgun));
		}
		String result = menu.showMenu(actor, actions, display).execute(actor, map);
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " shoots using " + shotgun;
	}

}
