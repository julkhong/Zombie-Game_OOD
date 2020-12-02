package game.behaviours;
import edu.monash.fit2099.engine.*;
import game.Behaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class to figure out if there's Zombie nearby, and proceed with MoveActorAction to move away from Zombie.
 * 
 * @author Jun Ming Khong
 *
 */
public class RunAwayBehaviour implements Behaviour  {
	/**
	 * Generates random number, uses as choosing an available adjacent location to move
	 */
    private Random random = new Random();
    
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<Action>();
        List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
        boolean hasZombie = false;

        for (Exit e: exits) {
            if (!(e.getDestination().containsAnActor()))
                continue;
            if (e.getDestination().getActor().getDisplayChar()=='Z') {
               hasZombie = true;
            }
        }
        boolean checkPos = true;
        if (hasZombie) {
            for (Exit e : exits) {
                Location destination = e.getDestination();
                if (destination.canActorEnter(actor)) {
                    List<Exit> newExits = new ArrayList<Exit>(destination.getExits());
                    for (Exit exit : newExits) {
                        if (exit.getDestination().containsAnActor()) {
                            if (exit.getDestination().getActor().getDisplayChar() == 'Z') {
                                checkPos = false;
                            }
                        }
                    }
                    if(checkPos){
                    actions.add(e.getDestination().getMoveAction(actor, "away from zombie", e.getHotKey()));}
                }
            }
        }

        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        }
        else {
            return null;
        }

    }
}
