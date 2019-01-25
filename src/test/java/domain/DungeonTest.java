/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import io.StubIO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sami
 */
public class DungeonTest {
    
    public DungeonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    Dungeon dungeon;
    StubIO io;
    
    @Before
    public void setUp() {
        io = new StubIO();
        dungeon = new Dungeon(io, 100,100);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void ungeneratedDungeonIsEmpty() {
        for (int y = 0; y < dungeon.y; y++) {
            for (int x = 0; x < dungeon.x; x++) {
                assert(dungeon.tiles[y][x] == false);
            }
        }
    }
    
    @Test
    public void carvingRoomAddsRoomTiles() {
        dungeon.carveRoom(new Room(10,10, 5, 5));
        
        boolean foundTrue = false;
        
        for (int y = 0; y < dungeon.y; y++) {
            for (int x = 0; x < dungeon.x; x++) {
                if(dungeon.tiles[y][x]) {
                    foundTrue = true;
                    break;
                }
            }
        }
        
        assert(foundTrue);
    }
    
    @Test
    public void printingEmptyContainsOnlyDashes() {
        dungeon.print();
        
        for (String s : io.getOutput()) {
            assert(!s.contains("X"));
        }
    }
    
    @Test
    public void roomTilesRenderAsX() {
        dungeon.carveRoom(new Room(10, 10, 10, 10));
        dungeon.print();
        
        boolean foundTiles = false;
        
        for (String s : io.getOutput()) {
            if (s.contains("X")) {
                foundTiles = true;
                break;
            }
        }
        
        assert(foundTiles);
    }
}
