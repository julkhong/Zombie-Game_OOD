package game.behaviours;
import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.actions.HealAction;
import game.capability.HealCapability;

import java.util.ArrayList;
import java.util.List;
/**
 * A class to figure out if oneself is hurt, and proceed with HealAction.
 * 
 * @author Jun Ming Khong
 *
 */
public class HealBehaviour implements Behaviour{
	/**
	 * Heal's capability, once an actor is damaged, it will have heal's capability
	 */
    private HealCapability Healable;
    
    /**
     * Constructor, which takes an input of HealCapability.
     * @param healable Heal's capability
     */
    public HealBehaviour (HealCapability healable){
        this.Healable = healable;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor.hasCapability(Healable)) {
            actor.removeCapability(Healable);
            List<Item> itemList = new ArrayList<Item>();
            itemList = map.locationOf(actor).getItems();
            for (int i = 0; i < itemList.size(); i++) {
                if (itemList.get(i).getDisplayChar() == 'O') {
                    return new HealAction(itemList.get(i));
                }
            }
        }
        return null;

    }
}