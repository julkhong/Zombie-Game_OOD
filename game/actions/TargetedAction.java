package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Menu;

/**
 * An action that allows the Player to choose whether to shoot the target or
 * to aim the target for one round
 * This action will also be responsible of keeping track of the number of aims
 * @author JulianYH
 *
 */
public class TargetedAction extends Action{
	private Menu menu = new Menu();
	private Display display;
	private Actor targetedActor;
	private Actions actions = new Actions();
	private String menuDesc = "Target ";
	private String menuDescBack = "";
	private boolean menuControl = true;
	private Action playerSelectedAction;
	private Item rifle;
	
	private int aimingCount = 0;
	
	/**
	 * Constructor
	 * 
	 * @param targetedActor the targeted actor
	 * @param display The display object
	 * @param rifle The rifle object that is used
	 */
	public TargetedAction(Actor targetedActor, Display display, Item rifle) {
		this.targetedActor = targetedActor;
		this.display = display;
		this.rifle = rifle;
	}
	
	/**
	 * Executes the action
	 * 
	 * based on the number of aims, shows a sub menu to the Player
	 * if the number of aims is lesser than 2, this action instance
	 * will be added to the list of available actions so that we can keep
	 * track of the numbe rof counts
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actions.clear();
		playerSelectedAction = null;
		actions.add(new RifleShootAction(targetedActor, aimingCount, rifle));
		if(aimingCount < 2) {
			actions.add(this);
			menuControl = false;
			menuDesc = "Aim ";
			menuDescBack = " for one round";
		}
		playerSelectedAction = menu.showMenu(actor, actions, display);
		menuControl = true;
		if(playerSelectedAction instanceof RifleShootAction) {
			return playerSelectedAction.execute(actor, map);
		}else if (playerSelectedAction instanceof TargetedAction){
			aimingCount += 1;
			return actor + " aimed " + targetedActor + " for one round";
		}else {
			return null;
		}
		
	}

	@Override
	public String menuDescription(Actor actor) {
		if(menuControl) {
			if(aimingCount > 0) {
				menuDesc = "Continue rifle target ";
				menuDescBack = "";
			}
		}
		return menuDesc + targetedActor.toString() + menuDescBack;
	}
	
	/**
	 * if the player selects to aim the target return this instance
	 */
	@Override
	public Action getNextAction() {
		if(playerSelectedAction instanceof TargetedAction) {
			return this;
		}
		return null;
	}

}
