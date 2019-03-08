/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author sami
 */

import io.ConsoleIO;
import io.IO;
import util.ArrayList;
import util.Pair;

public class Dungeon {
    // Individual tiles of the dungeon. True for a room tile, false for a wall.
    public boolean tiles[][];
    public int x;
    public int y;
    
    // For drawing and testing we supply an I/O object used to print the dungeon
    IO io;
    
    public Dungeon(int x, int y) {
        this(new ConsoleIO(), x, y);
    }
    
    public Dungeon(IO io, int x, int y) {
        this.io = io;
        this.x = x;
        this.y = y;
        tiles = new boolean[y][x];
    }
    
    /**
     * Check if a tile in the (X, Y) position is passable
     * @param x The X-coordinate
     * @param y The Y-coordinate
     * @return True if the tile is floor, false if the tile is wall
     */
    public boolean isPassable(int x, int y) {
        if (x >= 0 && x < this.x && y >= 0 && y < this.y) {
            return tiles[y][x];
        }
        
        return false;
    }
    
    /**
     * Return all the passable neighbour positions of a given location
     * @param position The (X, Y) coordinate of the location inspected
     * @return All the neighbouring positions that are considered passable
     */
    public ArrayList<Pair<Integer, Integer>> getNeighbours(Pair<Integer, Integer> position) {
        int x = position.getFirst();
        int y = position.getSecond();
        
        ArrayList<Pair<Integer, Integer>> results = new ArrayList<>();
        
        if (isPassable(x - 1, y)) {
            results.add(new Pair(x - 1, y));
        } if (isPassable(x + 1, y)) {
            results.add(new Pair(x + 1, y));
        } if (isPassable(x, y - 1)) {
            results.add(new Pair(x, y -1));
        } if (isPassable(x, y + 1)) {
            results.add(new Pair(x, y + 1));
        } if (isPassable(x - 1, y - 1)) {
            results.add(new Pair(x - 1, y - 1));
        } if (isPassable(x + 1, y + 1)) {
            results.add(new Pair(x + 1, y + 1));
        } if (isPassable(x - 1, y + 1)) {
            results.add(new Pair(x - 1, y + 1));
        } if (isPassable(x + 1, y - 1)) {
            results.add(new Pair(x + 1, y - 1));
        }
        
        return results;
    }
    
    /**
     * Draws the dungeon onto an IO device.
     */
    public void print() {
        for (int y = 0; y < this.y; y++) {
            for (int x = 0; x < this.x; x++) {
                if (!tiles[y][x]) {
                    io.print(" ");
                } else {
                    io.print("X");
                }
            }
            io.println("");
        }
    }
    
    /**
     * Draws a rectangular room onto the dungeon by setting the boolean values to true.
     * @param room The room to be carved on the map.
     */
    public void carveRoom(Room room) {
        for (int y = room.y; y < (room.y + room.h); y++) {
            for (int x = room.x; x < (room.x + room.w); x++) {
                tiles[y][x] = true;
            }
        }
    }
    
    /**
     * Draws a tunnel between two points in the dungeon
     * 
     * <p>
     * The tunnel creation algorithm creates L-shaped tunnels, where it first
     * matches the X-coordinate of the two points and then the Y-coordinate.
     * </p>
     * 
     * @param start An (X, Y) coordinate of the starting location
     * @param target An (X, Y) coordinate of the target location
     */
    public void carveTunnel(Pair<Integer, Integer> start, Pair<Integer, Integer> target) {
        int x = start.getFirst();
        int y = start.getSecond();
        
        tiles[y][x] = true;
        
        if (start.getFirst() < target.getFirst()) {
            carveTunnel(new Pair(x+1, y), target);
        } else if (x > target.getFirst()) {
            carveTunnel(new Pair(x-1, y), target);
        } else if (y < target.getSecond()) {
            carveTunnel(new Pair(x, y+1), target);
        } else if (y > target.getSecond()) {
            carveTunnel(new Pair(x, y-1), target);
        }
    }
}
