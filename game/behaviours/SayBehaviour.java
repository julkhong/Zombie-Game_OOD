package game.behaviours;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.actions.SayAction;

public class SayBehaviour implements Behaviour {

	private Random rand = new Random();

	public SayBehaviour() {
	}
	
	/**
	 * Let the Zombie have a chance to say "Braaaaains"
	 * 
	 * The Zombie have a 10% chance of saying "Braaaaains"
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if(rand.nextFloat() < 0.1) {
			return new SayAction();
		}
		return null;
	}

}
