package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.World;

/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
			
		World world = new NewWorld(new Display());

		//creating new map here
		FancyGroundFactory groundFactory2 = new FancyGroundFactory(new Dirt(), new Fence(), new Tree(), new Car(), new ClosedShop(), new Floor(), new House());
		List<String> map2 = Arrays.asList(
				"--------##---------------SS---------SS------------+----N-N-N--------+-++--------",
				"---------#---------------SS---------SS---------+-------N-N-N-----+-+-+-+--------",
				"########-----------------SS----------------------+------------------------------",
				"--------------------------S---------SS----C---------------------#########--#####",
				"------------------------------------SS----C------------------####...............",
				"--++---------------------S----------SS--CC-----------------##...................",
				"---+++-----------SSS-----SS--SSSSSSSSS---------------------##...................",
				"--+---------SSSS----SSSSSSS---------SS-------SSSSS---------##...................",
				"---------------SSSSSS----SS---++.---SS---------CC----------##-##---#############",
				"-------------------------SS---++.---SS---C--------------------------------------",
				"-------------------------SS---++.---SS----C-------------------------------------",
				"-------------------------SS---++.----------CCCCC-------------N---N-C-N---N---N--",
				"-------------------------SS-------S-SS---CCCC-CC-----------N---N---N---N---N----",
				"-------------------------SS-------S-SS---CCC--CC-------------N---N---N---N---N--",
				"-------------------------S--SSSSSSSSS----CCCC-CC---------------N---N---N---N----",
				"...N...N...N...--------------------------CC--C--CC----------N---N----N---N---N--",
				"...............---------------------------CC-CC-C--------------N---N---N---N----",
				"...N...N...N...--------------------------------------------C------C----C-C------",
				"...............------------S--------------------------+-------------C-------++--",
				"...N...N...N...----------S-S-S-----------+-+---------+-+--------C-----------+++-",
				"...............------------S-------------++--------------------CSS-SS-SS-SSS-SS-",
				"...N...N...N...----------S---S-----------+-+------------------------------------",
				"--------------------------------------------------------SSSSSSSSSSSSS-SSSSSSSSSS",
				"--------------------------------------------------------------------------------",
				"--------------------------------------------------------------------------------");
		
		GameMap gameMap2 = new GameMap(groundFactory2, map2);
		world.addGameMap(gameMap2);
		
		
		int newX, newY;
		// Place some zombies on the map
		
		String[] zombies2 = {"Klin","Zee","uuurs","Merkas","Gatlis","Aarpe"};
		for(String name3: zombies2) {
			do {
				newX = (int) Math.floor(Math.random() * 60 + 10.0);
				newY = (int) Math.floor(Math.random() * 15 + 5.0);
			}
			while (gameMap2.at(newX, newY).containsAnActor());
			gameMap2.at(newX, newY).addActor(new Zombie(name3));
		}
		
		
	    // Place some random humans and farmers
		
		String[] humans2 = {"Vincent", "June", "Michael", "Andrew", "Winny",
				"Tiffy", "Lee", "Wong", "Peter","Jaslyn", "Nichole", "Calvin", "Vishnu"};
		
		
		for (String name : humans2) {
			do {
				newX = (int) Math.floor(Math.random() * 60 + 10.0);
				newY = (int) Math.floor(Math.random() * 15 + 5.0);
			} 
			while (gameMap2.at(newX, newY).containsAnActor());
			gameMap2.at(newX, newY).addActor(new Human(name));	
		}
		
		//add farmers to the places where contain dirts
		//while loop to handle cases where the location has an actor already
		
		int aX = 74;
		int aY = 5;
		while (gameMap2.at(aX, aY).containsAnActor()) {
			aX ++;
		}
		gameMap2.at(74, 5).addActor(new Farmer("Harden"));
		aX = 68;
		aY = 5;
		while (gameMap2.at(aX, aY).containsAnActor()) {
			aY ++;
		}
		gameMap2.at(68, 8).addActor(new Farmer("Gordon"));
		aX = 6;
		aY = 19;
		while (gameMap2.at(aX, aY).containsAnActor()) {
			aX ++;
		}
		gameMap2.at(6, 19).addActor(new Farmer("Aaron"));
		aX = 21;
		aY = 14;
		while (gameMap2.at(aX, aY).containsAnActor()) {
			aY ++;
		}
		gameMap2.at(21, 14).addActor(new Farmer("Lavine"));

		
		
		
		
		// place some simple weapons
		gameMap2.at(40, 20).addItem(new Plank());
		gameMap2.at(45, 12).addItem(new Plank());
		gameMap2.at(10, 12).addItem(new Steel());
		gameMap2.at(22, 6).addItem(new Steel());
		
		// place rifle and ammunition
		gameMap2.at(13, 2).addItem(new Rifle());
		gameMap2.at(1, 1).addItem(new RifleAmmunition());
		gameMap2.at(10, 10).addItem(new RifleAmmunition());
		gameMap2.at(35, 22).addItem(new RifleAmmunition());
		gameMap2.at(47, 9).addItem(new RifleAmmunition());
		
		// place shotgun and ammunition
		gameMap2.at(20, 2).addItem(new Shotgun());
		gameMap2.at(30, 2).addItem(new ShotgunAmmunition());
		gameMap2.at(11, 8).addItem(new ShotgunAmmunition());
		gameMap2.at(5, 22).addItem(new ShotgunAmmunition());
		gameMap2.at(19, 1).addItem(new ShotgunAmmunition());
		
		

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"....................................##########..................................",
		"..........................###########........#####..............................",
		"............++...........##......................########.......................",
		"..............++++.......#..............................##......................",
		".............+++...+++...#...............................#......................",
		".........................##..............................##.....................",
		"..........................#...............................#.....................",
		".........................##...............................##....................",
		".........................#...............................##.....................",
		".........................###..............................##....................",
		"...........................####......................######.....................",
		"..............................#########.........####............................",
		"............+++.......................#.........#...............................",
		".............+++++....................#.........#...............................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap gameMap = new GameMap(groundFactory, map );
		world.addGameMap(gameMap);
		
		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(42, 15));

		
		int x, y;
		// Place some zombies on the map
		
		String[] zombies = {"Groan","Boo","Uuuurgh","Mortalis","Gaaaah","Aaargh","Mike-Wazowski","Muahaha","Pikaaboo","DroppingEyes"};
		for(String name3: zombies) {
			do {
				x = (int) Math.floor(Math.random() * 50.0 + 10.0);
				y = (int) Math.floor(Math.random() * 20.0 + 5.0);
			}
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Zombie(name3));
		}
		
		
	    // Place some random humans and farmers
		
		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy",
				"Elina", "Winter", "Clem", "Jacob", "Jaquelyn"};
		
		String[] farmers = {"James", "Kobe"};
		
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Human(name));	
		}
		
		for (String name2 : farmers) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			}
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Farmer(name2));
		}
		
		
		
		// place some simple weapons
		gameMap.at(74, 20).addItem(new Plank());
		gameMap.at(54, 5).addItem(new Plank());
		gameMap.at(10, 2).addItem(new Plank());
		gameMap.at(45, 12).addItem(new Plank());
		gameMap.at(10, 13).addItem(new Steel());
		gameMap.at(22, 7).addItem(new Steel());
		gameMap.at(39, 18).addItem(new Steel());
		Vehicle Car = new Vehicle();
		int bX = 6;
		int bY = 1;
		while (gameMap.at(bX, bY).containsAnActor()) {
			bX ++;
		}
		Car.addAction(new MoveActorAction(gameMap2.at(bX, bY), "to Town!"));  //added vehicle to the map
		gameMap.at(22,16).addItem(Car);
		
		
		//if the coordinate has actor, has to use other coordinates
		Vehicle Car2 = new Vehicle();
		int myX = 23;
		int myY = 16;
		while (gameMap.at(myX, myY).containsAnActor()) {
			myX ++;
		}
		Car2.addAction(new MoveActorAction(gameMap.at(myX,myY), "to Village!"));
		gameMap2.at(5, 1).addItem(Car2);

		
		// place some rifle and shotgun ammunition
		gameMap.at(47, 14).addItem(new RifleAmmunition());
		gameMap.at(39, 3).addItem(new RifleAmmunition());
		gameMap.at(47, 15).addItem(new ShotgunAmmunition());
		gameMap.at(40, 3).addItem(new ShotgunAmmunition());
		
		
		world.run();
	}
}
