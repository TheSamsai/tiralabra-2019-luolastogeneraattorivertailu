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

public class Dungeon {
    // Individual tiles of the dungeon. True for a room tile, false for a wall.
    boolean tiles[][];
    int x;
    int y;
    
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
     * Draws the dungeon onto an IO device.
     */
    public void print() {
        for (int y = 0; y < this.y; y++) {
            for (int x = 0; x < this.x; x++) {
                if (!tiles[y][x]) {
                    io.print("-");
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
}
