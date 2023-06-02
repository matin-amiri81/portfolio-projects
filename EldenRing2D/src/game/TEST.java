package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actions.AttackAction;
import game.actions.DeathAction;
import game.actors.Player;
import game.actors.classes.Bandit;
import game.actors.enemies.Enemies;
import game.actors.enemies.GiantDog;
import game.actors.enemies.GiantEnemy;
import game.actors.enemies.LoneWolf;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Ability;
import game.enums.EnemyType;
import game.gounds.*;
import game.gounds.GraphicWalls.*;
import game.items.GoldenRunes;
import game.utils.BFSMayhem;
import game.utils.ResetManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TEST {
    public static void main(String[] args) {
        //testRunes();
        //testResetManager();
        //testActionList();
        //testArrayListAdd();
        //testNullEnumComparison();
        //testActorEnemyComparrison();
        bfsTest();


    }

    public static void bfsTest(){
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
        List<String> roundTableHoldMap = Arrays.asList(
                "╔══▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲",
                "║_______▲▲▲▲▲▲▲▲▲▲",
                "║____________▲▲▲▲▲",
                "║______________▲▲▲",
                "║═════════════__▲▲",
                "║________________▲",
                "║══════════_═══__▲",
                "║________________║",
                "║____════════════║",
                "║________________║",
                "╚═══════___══════╝");
        GameMap roundTableHold = new GameMap(groundFactory, roundTableHoldMap);
        roundTableHold.at(2,3).addItem(new GoldenRunes());
        roundTableHold.at(10,10).addItem(new GoldenRunes());
        Player player= new Bandit();
        Display display = new Display();
        World world = new World(display);
        world.addGameMap(roundTableHold);
        roundTableHold.draw(display);
        BFSMayhem.BFS(roundTableHold.at(2,3), roundTableHold.at(10,10),player);
        roundTableHold.draw(display);


    }


    //public static void testRunes(){
    //    Player p = new Player(100);
    //    System.out.println(p.getRunes()); // default 50
    //    p.addRunes(50);
    //    System.out.println(p.getRunes()); // 100
    //    p.removeRunes(50);
    //    System.out.println(p.getRunes()); // 50
//
    //    Player p2 = new Player("Bob", 'B', 100);
    //    System.out.println(p2.getRunes()); // default 50
    //    p.transferRunesFrom(p2);
    //    System.out.println(p.getRunes()); // 100
    //    System.out.println(p2.getRunes()); // 0
    //}
    //public static void testResetManager(){
    //    Player player0 = new Player(100);
    //    Enemies dog1 = new LoneWolf();
    //    Enemies dog2 = new LoneWolf();
    //    System.out.println(ResetManager.getInstance().getResettable()); // [Adrian, Bob]
    //    //ResetManager.getInstance().run(); tested before map argument added
    //    //System.out.println(ResetManager.getInstance().getResettable()); // []
//
    //}
//
    //public static void testActionList(){
    //    ActionList al = new ActionList();
    //    LoneWolf lw = new LoneWolf();
    //    al.add(new DeathAction());//
    //    al.add(new DeathAction());
    //    al.add(new DeathAction());
    //    al.add(new AttackAction(lw,"aaa"));
//
    //    Action testA = al.get(al.size()-1);
//
//
    //}
//
    //public static void testArrayListAdd(){
    //    List<Behaviour> lB = new ArrayList<>();
    //    lB.add(new WanderBehaviour());
    //    lB.add(0,new FollowBehaviour(new Player("Adrian", 'A', 100)));
//
    //}
    //public static void testNullEnumComparison(){
    //    EnemyType a = null;
    //    EnemyType b = EnemyType.GIANT;
    //    System.out.println(a == b);
    //}
//
    //private static void testActorEnemyComparrison() {
    //    Enemies  actor = new GiantDog();
    //    Actor actor1 = actor;
    //    System.out.println(actor == actor1);

    //}


}
