/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellautomatagenerator;

import domain.Dungeon;
import domain.DungeonBFS;
import domain.Room;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.ArrayList;
import util.Pair;

/**
 *
 * @author sami
 */
public class CellAutomataGeneratorTest {
    
    public CellAutomataGeneratorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    CellAutomataGenerator gen;
    
    @Before
    public void setUp() {
        gen = new CellAutomataGenerator();
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
    public void generatorCanGenerateAllSizesOfDungeons() {
        gen.generate(0, 0);
        gen.generate(1, 1);
        gen.generate(25, 25);
        gen.generate(50, 50);
        gen.generate(75, 75);
        gen.generate(100, 100);
        
        assert(true);
    }
    
    @Test
    public void allRoomsAreConnected() {
        Dungeon dungeon = gen.generate(100, 100);
        
        int sX = 0;
        int sY = 0;
        
        for (int y = 0; y < dungeon.y; y++) {
            for (int x = 0; x < dungeon.x; x++) {
                if (dungeon.isPassable(x, y)) {
                    sX = x;
                    sY = y;
                    break;
                }
            }
        }
        
        ArrayList<Pair<Integer, Integer>> tiles = DungeonBFS.traverseBFS(dungeon, new Pair(sX, sY));
        
        for (int y = 0; y < dungeon.y; y++) {
            for (int x = 0; x < dungeon.x; x++) {
                if (dungeon.tiles[y][x]) {
                    assert(tiles.contains(new Pair(x, y)));
                }
            }
        }
        
        assert(true);
    }
}
