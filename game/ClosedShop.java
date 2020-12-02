package game;
import edu.monash.fit2099.engine.Actor;

import edu.monash.fit2099.engine.Ground;
/**
 * A closed shop that uses to decorate. (a static object)
 * 
 * @author Jun Ming Khong
 *
 */
public class ClosedShop extends Ground{
	public ClosedShop() {
		super('S');
	}
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

}
