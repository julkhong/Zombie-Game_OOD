package game;
import edu.monash.fit2099.engine.Actor;

import edu.monash.fit2099.engine.Ground;
/**
 * A house that uses to decorate. (a static object)
 * 
 * @author Jun Ming Khong
 *
 */
public class House extends Ground{
	public House() {
		super('N');
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
