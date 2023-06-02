package game.utils;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.gounds.Dirt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import game.items.PathMarkers.*;

/**
 * A class that implements the Breadth First Search algorithm to find the shortest path between two locations
 * It also adds PathMarkers to the map to show the shortest path
 * @see PathMarker
 * @see game.actions.MapAction
 */
public class BFSMayhem {

    public static void BFS(Location start, Location target, Actor actor){
        Queue<Location> queue = new LinkedList<>();
        ArrayList<Location> visited= new ArrayList<>();
        HashMap<Location,Location> parent = new HashMap<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Location current = queue.peek();
            if (current == target) {break;}
            queue.remove();

            for (Exit exit : current.getExits()) {
                Location destination = exit.getDestination();

                if(!visited.contains(destination) && destination.canActorEnter(actor)){
                    queue.add(destination);
                    parent.put(destination,current);
                    visited.add(destination);
                }
            }
        }
        Location previous = target;
        Location current = target;
        while (parent.get(current) != start){
            current = parent.get(current);

            int currentX = current.x();
            int currentY = current.y();
            int previousX = previous.x();
            int previousY = previous.y();

            //going backward so arrows are flipped left right
            //up
            if(currentX == previousX && currentY == previousY + 1) current.addItem(new UpArrow());
            //upRight
            else if(currentX == previousX + 1 && currentY == previousY + 1) current.addItem(new LeftUpArrow());
            //right
            else if(currentX == previousX + 1 && currentY == previousY) current.addItem(new LeftArrow());
            //downRight
            else if(currentX == previousX + 1 && currentY == previousY - 1) current.addItem(new LeftDownArrow());
            //down
            else if(currentX == previousX && currentY == previousY - 1) current.addItem(new DownArrow());
            //downLeft
            else if(currentX == previousX - 1 && currentY == previousY - 1) current.addItem(new DownRightArrow());
            //left
            else if(currentX == previousX - 1 && currentY == previousY) current.addItem(new RightArrow());
            //upLeft
            else if(currentX == previousX - 1 && currentY == previousY + 1) current.addItem(new UpRightArrow());

            previous = current;
        }
    }
}
