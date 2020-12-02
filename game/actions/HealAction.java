package game.actions;
import edu.monash.fit2099.engine.*;
/**
 * Action for healing oneself.
 * 
 * @author Jun Ming Khong
 *
 */
public class HealAction extends Action {
	/**
	 * Item's that's able to eaten
	 */
    protected Item Consumable;

    /**
     * Constructor, which takes an input of Item.
     * @param consumable Item's that's able to eaten
     */
    public HealAction(Item consumable){
        this.Consumable = consumable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = actor + " has heal himself with 10 points by eating " + Consumable;
        if (actor.isConscious()){
            if(actor.getDisplayChar() == '@'){
                actor.heal(20);
                actor.removeItemFromInventory(Consumable);
            }
            else{
                actor.heal(20);
                map.locationOf(actor).removeItem(Consumable);
            }
        }
        return  result;
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats " + Consumable;
    }
}