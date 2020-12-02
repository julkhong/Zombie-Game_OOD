package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.monash.fit2099.engine.*;
import game.capability.MamboMarieCapability;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {
	private int age = 0;

	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}
	
	@Override 
	public void tick(Location currentLocation) {
		super.tick(currentLocation);
		age ++;
		if(displayChar == '%') {
			if(age == 8) {
				currentLocation.removeItem(this);
				if (!(currentLocation.containsAnActor())) {
					currentLocation.addActor(new Zombie(name));
				}
				else {
					List<Exit> exits = new ArrayList<Exit>(currentLocation.getExits());
					Collections.shuffle(exits);
					
					for (Exit e: exits) {
						if (e.getDestination().containsAnActor())
							continue;
						e.getDestination().addActor(new Zombie(name));;
						break;
					}
				}
			}
		}
		
	}
	@Override
	public void tick(Location actorLocation, Actor actor) {
		super.tick(actorLocation, actor);
		age ++;
		if(displayChar == '%') {
			if(age == 8) {
				actor.removeItemFromInventory(this);
				List<Exit> exits = new ArrayList<Exit>(actorLocation.getExits());
				Collections.shuffle(exits);
				
				for (Exit e: exits) {
					if (e.getDestination().containsAnActor())
						continue;
					e.getDestination().addActor(new Zombie(name));;
					break;
				}
				
			}
		}
		else if (displayChar == 'q') {
			if(age == 9 || age == 19 ) {
				this.addCapability(MamboMarieCapability.CHANT); //add capability in from last turn and do it next turn
			}
			if (age == 29) {
				this.addCapability(MamboMarieCapability.VANISH); //add capability in from last turn and do it next turn
				age = 0;
			}
			
		}
	}

}
