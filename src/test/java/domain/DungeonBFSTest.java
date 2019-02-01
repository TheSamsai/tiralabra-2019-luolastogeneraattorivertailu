/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.Pair;

/**
 *
 * @author sami
 */
public class DungeonBFSTest {
    
    public DungeonBFSTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void bfsCompletesForADungeon() {
        Dungeon dungeon = new Dungeon(100,100);
        Room room = new Room(10,10,10,10);
        
        dungeon.carveRoom(room);
        
        DungeonBFS.traverseBFS(dungeon, room.center());
    }
    
    @Test
    public void bfsFindsAllTiles() {
        Dungeon dungeon = new Dungeon(100,100);
        Room room = new Room(10,10,10,10);
        
        dungeon.carveRoom(room);
        
        List<Pair<Integer, Integer>> tiles = DungeonBFS.traverseBFS(dungeon, room.center());
        
        for (int y = 0; y < dungeon.y; y++) {
            for (int x = 0; x < dungeon.x; x++) {
                if (dungeon.tiles[y][x]) {
                    assert(tiles.contains(new Pair(x, y)));
                }
            }
        }
    }
}
