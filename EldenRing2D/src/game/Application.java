package game;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.actions.RestAction;
import game.actors.BobFridgeSteven;
import game.actors.FingerReadingEnia;
import game.actors.MerchentKale;
import game.actors.Player;
import game.actors.classes.Bandit;
import game.actors.enemies.LoneWolf;
import game.enums.Status;
import game.gounds.*;
import game.gounds.GraphicWalls.*;
import game.items.GodlyBFSMap;
import game.items.GoldenRunes;
import game.items.RemembranceOfTheGrafted;
import game.utils.FancyMessage;
import game.utils.RandomNumberGenerator;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(
				new Dirt(),
				new Wall(),
				new Floor(),
				new Graveyard(),
				new GustOfWind(),
				new PuddleOfWater(),
				new SiteOfLostGrace(),

				new Cliff(),
				new Barrack(),
				new Cage(),
				new LakeOfRot(),
				new SeaOfRot(),
				new OceanOfRot(),

				new Wall1(),
				new Wall2(),
				new Wall3(),
				new Wall4(),
				new Wall5(),
				new Wall6(),
				new Wall7(),
				new Wall8(),
				new Wall9(),
				new Wall10(),
				new Wall11()

		);
		// ╣ ╠ ╩ ╦ ╬ ╚ ╔ ╗ ╝ ║ ═

		List<String> limegraveMap = Arrays.asList(
				".......................................................~~~~~~~~~~~~~~~~~~~~",
				"......................╔════....═════╗....................~~~~~~~~~~~~~~~~~~",
				"..nnnn................║..___....____║......................~~~~~~~~~~~~~~~~",
				"..................................__║........................~~~~~~~~~~~~~~",
				"..nnnn................._____........║..........................~~~~~~~~~~~~",
				"......................║............_║......................................",
				"......................╚════╣....╠═══╝......................░░▒▒▒▒░░░.......",
				"........................................................░░░▒▒▒▓▓▒▒▒░░░░░...",
				"....~~~........................▲▲▲....................░░░░░░▒▒▒▒▒▓▓▓▒▒▒░░..",
				"..~~~~~~~.....................▲▲▲.╔═╣___╠═╗.............░░░░░░░░░▒▒▓▓▒▒░░░.",
				"..~~~~~~~....................▲▲▲..╩_______╩......nnnn.....░░░▒▒▒▒▒▒▓▒▒░░...",
				"..~~~~~~~.....................▲▲..____U____...................░░▒▒▒▒░░.....",
				"....~~~.................░░░....▲▲.╦_______╦......nnnn.......░▒▒▒▓▓▓▒▒▒░....",
				".....╔═══════════.......░▒▒░......╚═╗___╔═╝.................░░▒▒▒▓▓▒▒░░░...",
				".....║..................░▒▓▒░.......║___║....................░░▒▒▒▒▒░......",
				".....║..╔═════............░▒▒░.................................░░░░░.......",
				".....║..║..................░░░........▲▲...................................",
				".....║..║.........................▲..▲▲....................................",
				"..╔══╝__╚═╗........................▲▲▲........▲▲...&&&........╔═════..═╗...",
				"..║.....__║.........................▲▲▲▲....▲▲▲....&&&........║....____....",
				"..║___...............&&&.............▲▲▲..▲▲▲▲.....&&&..........__.....║...",
				"..╚═══__══╝..........&&&............▲▲▲..▲▲▲.................._.....__.║...",
				".....................&&&...........▲▲▲▲▲▲▲▲▲..................╚══..__══╝...",
				".....................................▲▲▲▲..▲▲▲▲▲...........................");

		GameMap Limgrave = new GameMap(groundFactory, limegraveMap);
		world.addGameMap(Limgrave);
		BobFridgeSteven bobFridgeSteven= new BobFridgeSteven();
		Limgrave.at(41, 12).addActor(bobFridgeSteven);


		List<String> stormveilCastleMap = Arrays.asList(
				".................................................................▲▲▲▲▲▲▲▲▲▲",
				"..................<...............<.................................▲▲▲▲▲▲▲",
				".......................................................................▲▲▲▲",
				"════════════════════════════╦════════════════╣...╠═════════════════════════",
				"............................║................║.......B..............B......",
				".....B...............B......╩................╩.............................",
				"...............................<.........<.................................",
				".....B...............B......╦................╦.......B..............B......",
				"............................║................║.............................",
				"═══════════════╦════╣..╠════╬═══════╣...╠════╬════╣.╠══╣..╠═══════╣...╠════",
				"...............║..░░▒▒▒▒░░..╩................║......░░░░░░║................",
				"...............║░▒▒▒▓▓▓▒▒▒░....<.........<...╩...░░▒▓▓▒▒░░║................",
				"...............║░░▒▒▒▓▓▒▒░░░....................▒▒▓▓▓▒░░..║................",
				"...............║....░░░▒▒░░░╦................╦...░▒▒▓▓▒▒░░║................",
				"════╣...╠══════╩═╣.....╠════╩══════╣...╠═════╩═════╣..╠═══╩═══════╣...╠═══╣",
				".._______........................B......B........................B.....B...",
				"_____..._..____....&&........<..............<..............................",
				".........____......&&......................................................",
				"...._______..................<..............<....................<.....<...",
				"════╣....╠══════╣..╠════╣..╠═══════╣___╠═══════╦══════╣......╠══╣...╠══╣...",
				"░░░░░░░░░░░░░▒▒▒▓▓▒▒▒░░░░░░╩...................║░░░░░░▒▒▒▓▓▒▒▒░░░░░▲▲▲▲▲▲▲▲",
				"▲░░░▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▒▒▒▒░░░░....................╩..▒▒▒▒▒▓▓▓▒▒▒▒▒▒▓▓▓▒▒▒░░▲▲▲",
				"▲▲▲░░░░░░░▒▒▒▒▒▒▒▓▓▓▒▒▒▒░░░╦.......................░░░░░░..░░░░░▒▒▓▓▒▒▒▒▒▒▲",
				"▲▲▲▲▲▲▲▲░░░░░░░░▒▒▒▒▒▒░░░░░║...................╦......░░░░░░▒▒▒▒▒▒▓▒▒░░░░▲▲");

		GameMap stormveilCastle = new GameMap(groundFactory, stormveilCastleMap);
		world.addGameMap(stormveilCastle);

		List<String> roundTableHoldMap = Arrays.asList(
				"╔══▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲",
				"║_______▲▲▲▲▲▲▲▲▲▲",
				"║____________▲▲▲▲▲",
				"║______________▲▲▲",
				"║_______________▲▲",
				"║________________▲",
				"║________________▲",
				"║________________║",
				"║________________║",
				"║________________║",
				"╚═══════___══════╝");
		GameMap roundTableHold = new GameMap(groundFactory, roundTableHoldMap);
		world.addGameMap(roundTableHold);
		roundTableHold.at(7,7).addActor(new FingerReadingEnia());

		List<String> bossRoomMapp = Arrays.asList(
				"▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲",
				"▲▲▲▲▲▲▲▲▲▲........▲▲▲▲▲▲▲",
				"▲▲▲▲▲.................▲▲▲",
				"▲▲......................▲",
				"▲.......................▲",
				"▲......................▲▲",
				"▲▲▲.................▲▲▲▲▲",
				"▲▲▲▲▲▲........▲▲▲▲▲▲▲▲▲▲▲",
				"▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲");
		GameMap bossRoom = new GameMap(groundFactory, bossRoomMapp);
		world.addGameMap(bossRoom);

		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}


		Limgrave.at(23, 17).addActor(new LoneWolf());
		Limgrave.at(38, 13).addActor(new MerchentKale());

		// HINT: what does it mean to prefer composition to inheritance?
//		Player, player = new Player("Tarnished", '@', 300);
//		Player player2 = new Bandit("Bandit", 'B');
		Player player = ClassManager.choosePlayerClass();
		world.addPlayer(player, Limgrave.at(36, 10));
		//inicial player respawn
		new RestAction("init","Site of Lost Grace",Limgrave.at(38,11)).execute(player, Limgrave);

		//golden doors
		addGoldenFogDoorBetween(Limgrave,"Lime Grave",roundTableHold,"Round Table Hold",bobFridgeSteven);
		addGoldenFogDoorBetween(Limgrave,"Lime Grave",stormveilCastle,"Storm Veil Castle",bobFridgeSteven);
		addGoldenFogDoorBetween(stormveilCastle,"Storm Veil Castle",bossRoom,"Boss Room",bobFridgeSteven);
		scatterGoldenRunes(23,Limgrave);

		player.addItemToInventory(new RemembranceOfTheGrafted());

		world.run();
	}

	private static void addGoldenFogDoorBetween(
			GameMap map1, String map1Name,
			GameMap map2, String map2Name,
			BobFridgeSteven merchant){

		Location goldenFogDoorLocation1 = randomLocation(map1,true);
		Location goldenFogDoorLocation2 = randomLocation(map2,true);
		merchant.addMap(new GodlyBFSMap(goldenFogDoorLocation1,map2Name));
		merchant.addMap(new GodlyBFSMap(goldenFogDoorLocation2,map1Name));

		GoldenFogDoor goldenFogDoor1 = new GoldenFogDoor();
		goldenFogDoor1.setExitLocation(goldenFogDoorLocation2);
		goldenFogDoorLocation1.setGround(goldenFogDoor1);

		GoldenFogDoor goldenFogDoor2 = new GoldenFogDoor();
		goldenFogDoor2.setExitLocation(goldenFogDoorLocation1);
		goldenFogDoorLocation2.setGround(goldenFogDoor2);
		
	}
	private static void scatterGoldenRunes(int amount,GameMap map){
		for (int i = 0; i < amount; i++) {
			Location goldenRuneLocation = randomLocation(map,true);
			GoldenRunes goldenRune = new GoldenRunes();
			if(goldenRuneLocation.getItems().size() == 0){
				goldenRuneLocation.addItem(goldenRune);
			}
			else{amount--;}
		}
	}
	private static Location randomLocation(GameMap map, boolean replaceability){
		int randomX1,randomY1;
		do {
			randomX1 = RandomNumberGenerator.getRandomInt(map.getXRange().max());
			randomY1 = RandomNumberGenerator.getRandomInt(map.getYRange().max());
		} while (replaceability
				&& !map.at(randomX1,randomY1).containsAnActor()
				&& !map.at(randomX1,randomY1).getGround().hasCapability(Status.REPLACEABLE));
		return map.at(randomX1,randomY1);
	}

}
