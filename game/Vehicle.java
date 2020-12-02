package game;
import edu.monash.fit2099.engine.*;
/**
 * A vehicle that allows player to move across maps.
 * @author Jun Ming Khong
 */
public class Vehicle extends Item {
	/**
	 * Default constructor, which creates Vehicle that's not portable
	 */
	public Vehicle() {
		super("Vehicle", 'W', false);
	}
	/**
	 * Adds an action to player.
	 * @param action moveActorAction 
	 */
	public void addAction(Action action) {
		this.allowableActions.add(action);
	}

}
