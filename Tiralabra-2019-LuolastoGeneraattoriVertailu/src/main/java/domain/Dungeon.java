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
public class Dungeon {
    boolean tiles[][];
    int x;
    int y;
    
    public Dungeon(int x, int y) {
        this.tiles = new boolean[y][x];
        this.x = x;
        this.y = y;
    }
    
    public void print() {
        for (int y = 0; y < this.y; y++) {
            for (int x = 0; x < this.x; x++) {
                if (tiles[y][x]) {
                    System.out.print(" ");
                } else {
                    System.out.print("X");
                }
            }
            System.out.println("");
        }
    }
}
