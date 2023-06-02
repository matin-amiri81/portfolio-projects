package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Ability;
import game.items.FlaskOfCrimsonTears;

import game.items.GodlyBFSMap;
import game.items.LostRunes;
import game.runes.RuneManager;
import game.utils.ResetManager;
import game.utils.Resettable;
import game.enums.Status;

/**
 * Abstract class that represents the player in the game.
 * @author Adrian Kristanto
 * Modified by: danny leung and matin amiri
 * @see Actor
 * @see Resettable
 * @see Status
 * @see Menu
 * @see LostRunes
 */
public abstract class Player extends Actor implements Resettable {
	public static final int PLAYER_STARTING_RUNES = 6000;

	private final Menu menu = new Menu();
	private LostRunes lostRunes;
	private Location lastLocation;
	private GodlyBFSMap godlyBFSMap = new GodlyBFSMap();


	/**
	 * Constructor. sets default capabilities and registers the player as a rune possessor.
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(int hitPoints) {
		super("Tarnished", '@', hitPoints);
		this.addItemToInventory(new FlaskOfCrimsonTears(this.getMaxHp()));
		this.addItemToInventory(this.godlyBFSMap);
		this.registerResettable();
		RuneManager.getInstance().registerRunePossessor(this,PLAYER_STARTING_RUNES);
		this.lostRunes = null;



		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.FOLLOWABLE);
		this.addCapability(Status.RESPAWNABLE);
		this.addCapability(Ability.Rest);
		this.addCapability(Ability.GetRuneFromKill);
		this.addCapability(Ability.Sell);
		this.addCapability(Ability.Buy);
		this.addCapability(Ability.PickUpRunes);
		this.addCapability(Ability.EnterFloor);
		this.addCapability(Ability.EnterGoldenDoor);
		this.addCapability(Ability.ConsumeGoldenRunes);

	}

	/**
	 * displays the health and runes of the player and gets an input from the
	 * user given a list of options.
	 * @return the action that the user has chosen.
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		display.println(this.printHp()+ " Runes: " +RuneManager.getInstance().getRunes(this));

		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		if (!this.isConscious()){
			return this.die(map, display);
		}

		this.lastLocation = map.locationOf(this);
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * resets the players health when the game is reset.
	 * @param map
	 */
	@Override
	public void reset(GameMap map) {
		this.resetMaxHp(this.getMaxHp());
	}

	/**
	 * stops the reset manager from removing the player ressetable
	 */
	@Override
	public void removeResettable() {
		//potentially update this class to hold its last site of lost grace
	}

	/**
	 * on player death the player drops all runes as a Lost Runes object and the game continues after a reset.
	 * @param map the map the actor is on
	 * @param display the display
	 * @return Do nothing action
	 */
	public Action die(GameMap map, Display display){
		display.println("You have died. but the game continues");
		if(this.lostRunes != null){
			lostRunes.reset();
			this.lostRunes = null;
		}
		Location here = lastLocation;
		int runes = RuneManager.getInstance().getRunes(this);
		this.lostRunes = new LostRunes(runes,here);
		here.addItem(this.lostRunes);
		this.godlyBFSMap.setDestination(here,"Lost Runes");
		RuneManager.getInstance().removeRunes(this,runes);
		ResetManager.getInstance().run(map);
		return new DoNothingAction();
		//potencial update, call reset action here and hold last site of lost grace
		//return a drop runes action also doable here instead of manual work
		//drop runes on last Location not current
	}

}
