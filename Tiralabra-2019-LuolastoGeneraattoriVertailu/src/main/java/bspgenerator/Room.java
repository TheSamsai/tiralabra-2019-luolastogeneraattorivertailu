/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bspgenerator;

/**
 *
 * @author sami
 */
public class Room {
    // Location of the room in the wider dungeon
    public int x;
    public int y;
    
    // The area of the room. All rooms are rectangular.
    public int w;
    public int h;
    
    public Room(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
}
