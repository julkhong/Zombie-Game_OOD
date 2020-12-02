package game;

import edu.monash.fit2099.engine.*;
import game.actions.CraftWeaponAction;
import game.actions.HarvestAction;
import game.actions.HealAction;
import game.actions.LoadGunAction;
import game.actions.QuitGameAction;
import game.actions.RifleSelectTargetAction;
import game.actions.ShotgunDirectionAction;
import game.actions.TargetedAction;
import game.capability.DirtCapability;
import game.capability.ItemCapability;
import game.capability.ZombieCapability;

import java.util.List;
import java.util.ArrayList;

/**
 * Class representing the Player.
 */
public class Player extends Human {

	private Menu menu = new Menu();
	private boolean concentrationAffected = false;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, '@', hitPoints);
		addCapability(ZombieCapability.PLAYER);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		int currentX = map.locationOf(this).x();
		int currentY = map.locationOf(this).y();
		//if player stands on ripen crop
		if (map.locationOf(this).getGround().hasCapability(DirtCapability.HARVESTABLE)) {
			actions.add(new HarvestAction(map.locationOf(this),"Current Position"));
		}
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(this).getExits());
		//if player stands next to ripen crop
		for (Exit e: exits) {
			//harvest the crop next to player
			if (e.getDestination().getGround().hasCapability(DirtCapability.HARVESTABLE) ) {
				int nextX = e.getDestination().x();
				int nextY = e.getDestination().y();
				int newX = nextX - currentX;
				int newY = nextY - currentY;
				if (newX == 0 && newY == 1){
					//harvest in South
					actions.add(new HarvestAction(e.getDestination(),"South"));//

				}
				else if (newX == 0 && newY == -1){
					//harvest in North
					actions.add(new HarvestAction(e.getDestination(),"North"));//
				}
				else if (newX == -1 && newY == 0){
					//harvest in West
					actions.add(new HarvestAction(e.getDestination(),"West"));//
				}
				else if(newX == 1 && newY == 0){
					//harvest in East
					actions.add(new HarvestAction(e.getDestination(),"East"));//
				}
				else if (newX == -1 && newY == 1){
					//harvest in South West
					actions.add(new HarvestAction(e.getDestination(),"South West"));//
				}
				else if (newX == 1 && newY == 1){
					//harvest in South East
					actions.add(new HarvestAction(e.getDestination(),"South East"));
				}
				else if (newX == -1 && newY == -1){
					//harvest in North West
					actions.add(new HarvestAction(e.getDestination(),"North West"));
				}
				else if (newX == 1 && newY == -1){
					//harvest in North East
					actions.add(new HarvestAction(e.getDestination(),"North East"));//
				}
			}
		}
		List<Item> itemList = new ArrayList<Item>();
		Item rifle = null;
		Item rifleAm = null;
		Item shotgun = null;
		Item shotgunAm = null;
		itemList = this.getInventory();
		for(int i = 0 ; i<itemList.size(); i++){
			Item theItem = itemList.get(i);
			if(theItem.getDisplayChar() == 'O'){
				actions.add(new HealAction(theItem));
			}else if (theItem.hasCapability(ItemCapability.ZOMBIELIMB)) {
				actions.add(new CraftWeaponAction(theItem));
			}else if (theItem.hasCapability(ItemCapability.RIFLE)) {
				rifle = itemList.get(i);
				if(theItem.hasCapability(ItemCapability.LOADED)) {
					actions.add(new RifleSelectTargetAction(rifle, display));
				}
			}else if (theItem.hasCapability(ItemCapability.RIFLEAM)) {
				rifleAm = itemList.get(i);
			}else if(theItem.hasCapability(ItemCapability.SHOTGUN)) {
				shotgun = theItem;
				if(itemList.get(i).hasCapability(ItemCapability.LOADED)){
					actions.add(new ShotgunDirectionAction(exits, shotgun, display));
				}
			}else if (theItem.hasCapability(ItemCapability.SHOTGUNAM)) {
				shotgunAm = theItem;
			}
			
			// checks if both rifle and rifle ammunition is present in inventory in order
			// to load the weapon
			if(rifle != null && rifleAm != null) {
				if(rifle.hasCapability(ItemCapability.UNLOADED)) {
					actions.add(new LoadGunAction(rifle,rifleAm));
				}
			}
			// checks if both shotgun and shotgun ammunition is present in inventory in order
			// to load the weapon
			if(shotgun != null && shotgunAm != null) {
				if(shotgun.hasCapability(ItemCapability.UNLOADED)) {
					actions.add(new LoadGunAction(shotgun,shotgunAm));
				}
			}
		}
		
		// Handle multi-turn Actions by also allowing other actions
		if(lastAction.getNextAction() instanceof TargetedAction) {
			if(!concentrationAffected) {
				actions.add(lastAction.getNextAction());
			}
		}else {
			concentrationAffected = false; //reset back to normal
		}

		
//		if (lastAction.getNextAction() != null){
//			return lastAction.getNextAction();}
		actions.add(new QuitGameAction());
		return menu.showMenu(this, actions, display);
	}
	
	@Override
	public void hurt(int points) {
		super.hurt(points);
		concentrationAffected = true;
	}
}
