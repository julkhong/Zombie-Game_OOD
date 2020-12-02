package game;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
/**
 * A car that uses to decorate. (a static object)
 * 
 * @author Jun Ming Khong
 *
 */
public class Car extends Ground{
	public Car() {
		super('C');
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
