package game.actions;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;
import edu.monash.fit2099.engine.Weapon;

/**
 * Provides the shotgun the ability to shoot an area
 * @author JulianYH
 *
 */
public class ShotgunShootAction extends AttackAction{
	private Exit e;
	private Item shotgun;
	private ArrayList<Actor> actorsToShoot = new ArrayList<Actor>();

	/**
	 * Constructor
	 * 
	 * @param e The selected direction
	 * @param shotgun The shotgun used
	 */
	public ShotgunShootAction(Exit e, Item shotgun) {
		super(null);
		this.e = e;
		this.shotgun = shotgun;
	}
	
	/**
	 * Checks whether the selected exit contains an actor
	 */
	private void exitContainActor() {
		if(e.getDestination().containsAnActor()) {
			actorsToShoot.add(e.getDestination().getActor());
		}
	}
	
	/**
	 * Executes the action
	 * 
	 * searches the 3 squares area from the selected direction
	 * checks if any actor is in range, and shoot those actors
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = shotgun.asWeapon();
		int damage = weapon.damage() + 8;
		String result = "";
		int eX = e.getDestination().x();
		int eY = e.getDestination().y();
		ArrayList<int[]> coordinates = new ArrayList<>(); 
				
		if(e.getName() == "North") {
			exitContainActor();
			NumberRange xs_layer1 = new NumberRange(eX - 1, 3);
			NumberRange xs_layer2 = new NumberRange(eX - 2, 5);
			for(int x : xs_layer1) {
				int[] coor = {x,eY-1};
				coordinates.add(coor);
			}
			for(int x : xs_layer2) {
				int[] coor = {x,eY-2};
				coordinates.add(coor);
			}
		}else if(e.getName() == "East") {
			exitContainActor();
			NumberRange ys_layer1 = new NumberRange(eY - 1, 3);
			NumberRange ys_layer2 = new NumberRange(eY - 2, 5);
			for(int y : ys_layer1) {
				int[] coor = {eX + 1, y};
				coordinates.add(coor);
			}
			for(int y : ys_layer2) {
				int[] coor = {eX + 2, y};
				coordinates.add(coor);
			}
		}else if(e.getName() == "South") {
			exitContainActor();
			NumberRange xs_layer1 = new NumberRange(eX - 1, 3);
			NumberRange xs_layer2 = new NumberRange(eX - 2, 5);
			for(int x : xs_layer1) {
				int[] coor = {x, eY + 1};
				coordinates.add(coor);
			}
			for(int x : xs_layer2) {
				int[] coor = {x, eY + 2};
				coordinates.add(coor);
			}
		}else if (e.getName() == "West") {
			exitContainActor();
			NumberRange ys_layer1 = new NumberRange(eY - 1, 3);
			NumberRange ys_layer2 = new NumberRange(eY - 2, 5);
			for(int y : ys_layer1) {
				int[] coor = {eX - 1, y};
				coordinates.add(coor);
			}
			for(int y : ys_layer2) {
				int[] coor = {eX - 2, y};
				coordinates.add(coor);
			}
		}else if (e.getName() == "North-East") {
			NumberRange xs = new NumberRange(eX, 3);
			NumberRange ys = new NumberRange(eY - 2, 3);
			for(int x : xs) {
				for(int y : ys) {
					int[] coor = {x,y};
					coordinates.add(coor);
				}
			}
		}else if (e.getName() == "South-East") {
			NumberRange xs = new NumberRange(eX, 3);
			NumberRange ys = new NumberRange(eY, 3);
			for(int x : xs) {
				for(int y : ys) {
					int[] coor = {x,y};
					coordinates.add(coor);
				}
			}
		}else if (e.getName() == "South-West") {
			NumberRange xs = new NumberRange(eX - 2, 3);
			NumberRange ys = new NumberRange(eY, 3);
			for(int x : xs) {
				for(int y : ys) {
					int[] coor = {x,y};
					coordinates.add(coor);
				}
			}
		}else if (e.getName() == "North-West") {
			NumberRange xs = new NumberRange(eX - 2, 3);
			NumberRange ys = new NumberRange(eY - 2, 3);
			for(int x : xs) {
				for(int y : ys) {
					int[] coor = {x,y};
					coordinates.add(coor);
				}
			}
		}
		
		//go through the selected range whether there are actors
		for (int[] coor : coordinates) {
			// check if the coordinate is out of bound
			
			if(coor[0] >= 0 && coor[1] >= 0 && coor[1] < 25 && coor[0] < 80) {
				Location searchLocation = map.at(coor[0], coor[1]);
				if(searchLocation.containsAnActor()) {
					actorsToShoot.add(searchLocation.getActor());
				}
			}
		}
		
		// shoot target in range
		for(Actor tempTarget : actorsToShoot) {
			target = tempTarget;
			if(rand.nextFloat() < 0.75) {
				result += actor + " shot " + target + " for " + damage + " damage " + System.lineSeparator();
				target.hurt(damage);
				isTargetConscious(actor, map);
			}else {
				result += actor + " misses " + target + System.lineSeparator();
			}
		}
		if(actorsToShoot.size() == 0) {
			result += actor + " shot nothing";
		}
		updateGunBullet(shotgun);
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Shoot at " + e.getName();
	}
	
	/**
	 * returns the hotkeys based on the direction
	 */
	@Override
	public String hotkey() {
		String exitName = e.getName();
		if (exitName == "North") {
			return "8";
		}else if(exitName == "North-East") {
			return "9";	
		}else if (exitName == "East") {
			return "6";
		}else if(exitName == "South-East") {
			return "3";
		}else if(exitName == "South-West") {
			return "1";
		}else if (exitName == "South") {
			return "2";
		}else if (exitName == "West") {
			return "4";
		}else if (exitName == "North-West") {
			return "7";
		}
		return null;
	}

}
