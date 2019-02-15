/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellautomatagenerator;

import domain.Dungeon;
import domain.DungeonBFS;
import java.util.Random;
import util.ArrayList;
import util.Pair;

/**
 * Dungeon generator based on cellular automata
 * @author sami
 */
public class CellAutomataGenerator {
    Random rng;
    
    public CellAutomataGenerator() {
        rng = new Random();
    }
    
    /**
     * Initialise and populate a dungeon
     * @param x The width of the dungeon
     * @param y The height of the dungeon
     * @return A cave-like, connected dungeon
     */
    public Dungeon generate(int x, int y) {
        Dungeon dungeon = new Dungeon(x, y);
        
        // We fill the dungeon randomly with empty space
        initializeRandom(dungeon, 0.40);
        
        // Since the algorithm works in steps, we need to repeat
        // the steps a few times to get a cave-like dungeon
        for (int a = 0; a < 4; a++) {
            simulateStep(dungeon);
        }
        
        // The caves may have disjoint segments, so we will connect any
        // disjointed segments ourselves
        connectSegments(dungeon);
        
        return dungeon;
    }
    
    /**
     * Initialize the dungeon with randomized walls and floors.
     * @param dungeon The dungeon to be operated upon.
     * @param p The probability that a tile at any given point becomes a floor.
     */
    public void initializeRandom(Dungeon dungeon, double p) {
        for (int y = 1; y < dungeon.y - 1; y++) {
            for (int x = 1; x < dungeon.x - 1; x++) {
                if (rng.nextDouble() > p) {
                    dungeon.tiles[y][x] = true;
                }
            }
        }
    }
    
    /**
     * Simulate one step of the cellular automata
     * 
     * <p>
     * The cellular automata obeys the following rules:
     * <li>
     *  <ul>If a wall is surrounded by less than 4 walls, it becomes floor</ul>
     *  <ul>If a wall has 4 or more surrounding walls, it stays a wall</ul>
     *  <ul>If a floor is surrounded by more than 5 walls or it has less than two walls 2 tiles away, it turns into a wall</ul>
     * </li>
     * </p>
     * @param dungeon 
     */
    public void simulateStep(Dungeon dungeon) {
        for (int y = 0; y < dungeon.y; y++) {
            for (int x = 0; x < dungeon.x; x++) {
                int closeWalls = walls(dungeon, x, y, 1);
                int farWalls = walls(dungeon, x, y, 2);
                
                // If current tile is a wall
                if (!dungeon.isPassable(x, y)) {
                    // If has too few walls, turn into floor
                    if (closeWalls < 4) {
                        dungeon.tiles[y][x] = true;
                    }
                } else {
                    // If has enough wall neighbours, turn into a wall
                    if (closeWalls >= 5 || farWalls <= 2) {
                        dungeon.tiles[y][x] = false;
                    }
                }
            }
        }
    }
    
    /**
     * Count the amount of walls neighbouring a tile
     * @param dungeon The dungeon used to find tile information
     * @param x The X-coordinate of the tile to be inspected
     * @param y The Y-coordinate of the tile to be inspected
     * @param range The range at which we are inspecting tiles. Becomes inaccurate at further ranges.
     * @return 
     */
    public int walls(Dungeon dungeon, int x, int y, int range) {
        int sum  = 8;
        
        if (dungeon.isPassable(x - range, y)) sum--;
        if (dungeon.isPassable(x + range, y)) sum--;
        if (dungeon.isPassable(x, y - range)) sum--;
        if (dungeon.isPassable(x, y + range)) sum--;
        if (dungeon.isPassable(x - range, y - range)) sum--;
        if (dungeon.isPassable(x + range, y + range)) sum--;
        if (dungeon.isPassable(x - range, y + range)) sum--;
        if (dungeon.isPassable(x + range, y - range)) sum--;
        
        return sum;
    }
    
    /**
     * Connects all disjoint segments of the dungeon
     * 
     * <p>
     * The algorithm runs BFS on all tiles that haven't been identified belonging
     * to a known segment and uses the BFS to generate a new segment. After all
     * segments have been identified, segments are connected to each other one-by-one
     * with a tunnel.
     * </p>
     * @param dungeon 
     */
    public void connectSegments(Dungeon dungeon) {
        // List of all disjoint segments in a dungeon
        ArrayList<ArrayList<Pair<Integer, Integer>>> segments = new ArrayList<>();
        
        for (int y = 0; y < dungeon.y; y++) {
            for (int x = 0; x < dungeon.x; x++) {
                if (dungeon.isPassable(x, y)) {
                    boolean newSegment = true;
                    
                    // Check if tile belongs to any known segment
                    for (ArrayList<Pair<Integer, Integer>> list : segments) {
                        if (list.contains(new Pair(x, y))) {
                            newSegment = false;
                        }
                    }
                    
                    // If the tile is part of a new segment, run BFS and add a new segment to the list
                    if (newSegment) {
                        ArrayList<Pair<Integer, Integer>> segment = DungeonBFS.traverseBFS(dungeon, new Pair(x, y));
                        segments.add(segment);
                    }
                }
            }
        }
        
        // We keep track of the last segment to join the segments more or less in logical order
        ArrayList<Pair<Integer, Integer>> lastSegment = segments.get(0);
        
        // Connect segments one-by-one to the last segment until all segments have been connected.
        for (int i = 1; i < segments.getSize(); i++) {
            dungeon.carveTunnel(lastSegment.get(0), segments.get(i).get(0));
            lastSegment = segments.get(i);
        }
    }
}
