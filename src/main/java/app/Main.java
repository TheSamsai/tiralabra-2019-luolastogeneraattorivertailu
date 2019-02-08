/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import bspgenerator.BSPGenerator;
import cellautomatagenerator.CellAutomataGenerator;
import domain.Dungeon;
import domain.Room;
import io.ConsoleIO;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author sami
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Dungeon generator!");
        BSPGenerator bspGen = new BSPGenerator();
        Dungeon bspDungeon = new Dungeon(100, 100);
        
        CellAutomataGenerator cellGen = new CellAutomataGenerator();
        Dungeon cellDungeon = new Dungeon(100, 100);
        
        long bspTime = 0;
        long cellTime = 0;
        
        long startTime;
        long endTime;
        
        for (int x = 0; x < 10; x++) {
            startTime = System.nanoTime();
            bspDungeon = bspGen.generate(50, 50);
            endTime = System.nanoTime();
        
            bspTime += endTime - startTime;
        }
        
        bspTime /= 10;
        
        for (int x = 0; x < 10; x++) {
            startTime = System.nanoTime();
            cellDungeon = cellGen.generate(50, 50);
            endTime = System.nanoTime();
        
            cellTime += endTime - startTime;
        }
        
        cellTime /= 10;
        
        System.out.println("GENERATOR RESULTS:");
        System.out.println("\tBSP-Generator: average time " + TimeUnit.NANOSECONDS.toMicros(bspTime) + " microseconds.");
        System.out.println("\tCellular Automata Generator: average time " + TimeUnit.NANOSECONDS.toMicros(cellTime) + " microseconds.");
        
        System.out.println("\nBSP dungeon: ");
        bspDungeon.print();
        
        System.out.println("\nCellular automata dungeon:");
        cellDungeon.print();
    }
    
    public static void runCellGen() {
        CellAutomataGenerator gen = new CellAutomataGenerator();
        
        Dungeon dungeon = gen.generate(60, 40);
        
        ConsoleIO io = new ConsoleIO();
        
        dungeon.print();
        
    }
    
    public static void runBSPGen() {
        BSPGenerator gen = new BSPGenerator();
        
        Dungeon dungeon = gen.generate(60, 40);
        
        dungeon.print();
    }
}
