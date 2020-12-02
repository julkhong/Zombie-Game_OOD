package game.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Menu;
import game.capability.ZombieCapability;

/**
 * An action that allows the Player to select a target in a sub menu when using rifle
 * 
 * @author JulianYH
 *
 */
public class RifleSelectTargetAction extends Action{
	private Menu menu = new Menu();
	private Display display;
	private Item rifle;
	boolean hasTargetInRange = false;
	private Action playerSelectedAction;
	
	private int maxRange = 50;
	private HashSet<Location> visitedLocations = new HashSet<Location>();
	private Actions actions = new Actions();
	
	/**
	 * Constructor
	 * 
	 * @param rifle The rifle object
	 * @param display The display object
	 */
	public RifleSelectTargetAction(Item rifle, Display display) {
		this.rifle = rifle;
		this.display = display;
	}
	
	/**
	 * Triggers the breadth first search algorithm
	 * @param actor
	 * @param here
	 */
	private void targetActor(Actor actor, Location here) {
		visitedLocations.clear();
		ArrayList<Location> now = new ArrayList<Location>();
		
		now.add(here);
		
		ArrayList<ArrayList<Location>> layer = new ArrayList<ArrayList<Location>>();
		layer.add(now);
		
		for (int i = 0; i<maxRange; i++) {
			layer = getNextLayer(actor, layer);
			ArrayList<Actor> targeted = search(layer);
			if (targeted != null) {
				hasTargetInRange = true;
				for (Actor eachActor: targeted) {
					actions.add(new TargetedAction(eachActor, display, rifle));
				}
			}		
		}
		
	}
	
	private ArrayList<ArrayList<Location>> getNextLayer(Actor actor, ArrayList<ArrayList<Location>> layer) {
		ArrayList<ArrayList<Location>> nextLayer = new ArrayList<ArrayList<Location>>();

		for (ArrayList<Location> path : layer) {
			List<Exit> exits = new ArrayList<Exit>(path.get(path.size() - 1).getExits());
			Collections.shuffle(exits);
			for (Exit exit : path.get(path.size() - 1).getExits()) {
				Location destination = exit.getDestination();
				if (!destination.getGround().canActorEnter(actor) || visitedLocations.contains(destination))
					continue;
				visitedLocations.add(destination);
				ArrayList<Location> newPath = new ArrayList<Location>(path);
				newPath.add(destination);
				nextLayer.add(newPath);
			}
		}
		return nextLayer;
	}
	
	private ArrayList<Actor> search(ArrayList<ArrayList<Location>> layer) {
		ArrayList<Actor> inRangeActors = new ArrayList<Actor>();
		for (ArrayList<Location> path : layer) {
			Location targetLocation = path.get(path.size() - 1);
			if (containsTarget(targetLocation)) {
				inRangeActors.add(targetLocation.getActor());
			}
		}
		if(inRangeActors.size() != 0) {
			return inRangeActors;
		}
		return null;
	}
	
	private boolean containsTarget(Location here) {
		return (here.getActor() != null &&
				here.getActor().hasCapability(ZombieCapability.UNDEAD));
	}
	
	/**
	 * Executes the action
	 * 
	 * Conducts a breadth first search algorithm
	 * Put all targets in range to a sub menu for the Player to choose
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		targetActor(actor, map.locationOf(actor));
		if(hasTargetInRange) {
			playerSelectedAction = menu.showMenu(actor, actions, display);
			String result = playerSelectedAction.execute(actor, map);
			return result;
		}else {
			return "No target in range to shoot";
		}
		
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " shoots using " + rifle.toString();
	}
	
	/**
	 * return the selected target action
	 */
	@Override
	public Action getNextAction() {
		return playerSelectedAction.getNextAction();
	}
	
}
