/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import bspgenerator.BSPGenerator;
import bspgenerator.BinaryTree;
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
        BSPGenerator bspGen = new BSPGenerator();
        Dungeon bspDungeon = new Dungeon(100, 100);
        
        CellAutomataGenerator cellGen = new CellAutomataGenerator();
        Dungeon cellDungeon = new Dungeon(100, 100);
        
        long bspTime = 0;
        long bspTreeTime = 0;
        long bspRoomTime = 0;
        long bspHallwayTime = 0;
        
        long cellTime = 0;
        long cellInitTime = 0;
        long cellSimulationTime = 0;
        long cellConnectSegmentTime = 0;
        
        long startTime;
        long endTime;
        
        for (int x = 0; x < 10; x++) {
            BinaryTree dungeonTree = new BinaryTree(new Room(0,0,50,50));
            bspDungeon = new Dungeon(50, 50);
            
            startTime = System.nanoTime();
            
            // Generate dungeon tree
            long startTree = System.nanoTime();
            
            bspGen.generateTree(dungeonTree, 25, 0.5, 5);
            
            long endTree = System.nanoTime();
            
            // Generate Rooms
            long startRooms = System.nanoTime();
            
            bspGen.carveRooms(dungeonTree, bspDungeon);
            
            long endRooms = System.nanoTime();
            
            // Generate Hallways
            long startHallways = System.nanoTime();
            
            bspGen.carveHallways(dungeonTree, bspDungeon);
            
            long endHallways = System.nanoTime();
            
            endTime = System.nanoTime();
            
            bspTreeTime += endTree - startTree;
            bspRoomTime += endRooms - startRooms;
            bspHallwayTime += endHallways - startHallways;
            bspTime += endTime - startTime;
        }
        
        bspTime /= 10;
        bspTreeTime /= 10;
        bspRoomTime /= 10;
        bspHallwayTime /= 10;
        
        for (int x = 0; x < 10; x++) {
            cellDungeon = new Dungeon(50, 50);
            
            startTime = System.nanoTime();
            
            long startInit = System.nanoTime();
            
            cellGen.initializeRandom(cellDungeon, 0.40);
            
            long endInit = System.nanoTime();
            
            long startSimulation = System.nanoTime();
            
            for (int step = 0; step < 4; step++)
                cellGen.simulateStep(cellDungeon);
            
            long endSimulation = System.nanoTime();
            
            long startSegmentation = System.nanoTime();
            
            cellGen.connectSegments(cellDungeon);
            
            long endSegmentation = System.nanoTime();
            
            cellInitTime += endInit - startInit;
            cellSimulationTime += endSimulation - startSimulation;
            cellConnectSegmentTime += endSegmentation - startSegmentation;
            endTime = System.nanoTime();
        
            cellTime += endTime - startTime;
        }
        
        cellTime /= 10;
        cellInitTime /= 10;
        cellSimulationTime /= 10;
        cellConnectSegmentTime /= 10;
        
        System.out.println("GENERATOR RESULTS:");
        System.out.println("\tBSP-Generator: average time " + TimeUnit.NANOSECONDS.toMicros(bspTime) + " microseconds.");
        System.out.println("\t\t tree generation: " + TimeUnit.NANOSECONDS.toMicros(bspTreeTime) + " microseconds");
        System.out.println("\t\t room generation: " + TimeUnit.NANOSECONDS.toMicros(bspRoomTime) + " microseconds");
        System.out.println("\t\t hallway generation: " + TimeUnit.NANOSECONDS.toMicros(bspHallwayTime) + " microseconds");
        
        System.out.println("");
        
        System.out.println("\tCellular Automata Generator: average time " + TimeUnit.NANOSECONDS.toMicros(cellTime) + " microseconds.");
        System.out.println("\t\t initialization: " + TimeUnit.NANOSECONDS.toMicros(cellInitTime) + " microseconds");
        System.out.println("\t\t simulation: " + TimeUnit.NANOSECONDS.toMicros(cellSimulationTime) + " microseconds");
        System.out.println("\t\t segment connecting: " + TimeUnit.NANOSECONDS.toMicros(cellConnectSegmentTime) + " microseconds");
        
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
