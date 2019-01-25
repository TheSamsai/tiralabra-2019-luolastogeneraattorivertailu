/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import bspgenerator.BSPGenerator;
import domain.Dungeon;
import domain.Room;

/**
 *
 * @author sami
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        BSPGenerator gen = new BSPGenerator();
        
        Dungeon dungeon = gen.generate(100, 100);
        
        dungeon.print();
    }
}
