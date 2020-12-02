package game; 
import java.util.Random;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.capability.MamboMarieCapability;
import game.capability.ZombieCapability;
/**
 * 
 * Inherited from the World class. Class representing the game world, including the locations of all Actors, the
 * player, and the playing grid.
 */
public class NewWorld extends World {
	/**
	 * A random generator
	 */
	protected Random rand = new Random();
	/**
	 * Represents a message
	 */
	protected String Message;
	
	/**
	 * Constructor
	 * 
	 * @param display The display object
	 */
	public NewWorld(Display display) {
		super(display);
	}
	
	/**
	 * runs the whole game with new added functions
	 */
	@Override
	public void run() {
		if (player == null)
			throw new IllegalStateException();

		// initialize the last action map to nothing actions;
		for (Actor actor : actorLocations) {
			lastActionMap.put(actor, new DoNothingAction());
		}
		MamboMarie boss = new MamboMarie("Mambo Marie");
		// This loop is basically the whole game
		while (stillRunning()) {
			GameMap playersMap = actorLocations.locationOf(player).map();
			if (!(player.hasCapability(MamboMarieCapability.DEAD))) {
				boolean bossIsAround = false;
				for(int i = 0; i< gameMaps.size(); i++) {
					if(gameMaps.get(i).contains(boss)) {
						bossIsAround = true;
					}
				}
				if(!bossIsAround) {
					if (rand.nextFloat()<=0.05) {
						playersMap.addActor(boss, playersMap.at(69, 24));
					}
				}
			}
			playersMap.draw(display);

			// Process all the actors.
			for (Actor actor : actorLocations) {
				if (stillRunning())
					processActorTurn(actor);
			}

			// Tick over all the maps. For the map stuff.
			for (GameMap gameMap : gameMaps) {
				gameMap.tick();
			}

		}
		display.println(endGameMessage());
	}
	
	/**
	 * Checks whether the game is still running, or whether the player
	 * loose or win the game
	 * Checks whether humans and player are alive
	 */
	@Override
	protected boolean stillRunning() {
		boolean humanExist = false;
		boolean zombieExist = false;
		for(Actor actor : actorLocations) {
			if(humanExist && zombieExist) {
				break;
			}
			// checks whether any human still present 
			if(!actor.hasCapability(ZombieCapability.PLAYER)) {
				if(actor.hasCapability(ZombieCapability.ALIVE)) {
					humanExist = true;
				}
			}
			if(actor.hasCapability(ZombieCapability.UNDEAD)) {
				zombieExist = true;
			}
			
		}
		if(player.hasCapability(MamboMarieCapability.DEAD) && !zombieExist) {
			Message = "Congratulation! you save the World! ";
			return false;
		}
		else if (actorLocations.contains(player)) {
			if(humanExist) {
				return true;
			}
			Message = "Game Over, all human being is dead. ";
			return false;
		}
		else {
			Message = "Game Over, player is dead. ";
			return false;
		}
	}
	@Override
	protected String endGameMessage() {
		return Message;
		
	}
}
