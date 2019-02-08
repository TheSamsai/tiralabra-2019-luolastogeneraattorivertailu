/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

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
public class ArrayListTest {
    
    public ArrayListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    ArrayList<Integer> list;
    
    @Before
    public void setUp() {
        list = new ArrayList<Integer>();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void newListIsInitializedWithCapacity() {
        assert(list.getCapacity() > 0);
    }
    
    @Test
    public void addingItemStoresToList() {
        list.add(42);
        assert(list.get(0) == 42);
    }
    
    @Test
    public void addingItemIncrementsSize() {
        list.add(42);
        assert(list.getSize() == 1);
    }
    
    @Test
    public void listGrowsWhenCapacityExceeded() {
        int oldCap = list.getCapacity();
        
        for (int x = 0; x <= oldCap; x++) {
            list.add(42);
        }
        
        assert(list.getCapacity() == 2 * oldCap);
    }
    
    @Test
    public void removingItemLeavesNoNulls() {
        int oldCap = list.getCapacity();
        
        for (int x = 0; x <= oldCap; x++) {
            list.add(42);
        }
        
        list.remove(0);
        
        for (int i = 0; i < list.getSize(); i++) {
            if (list.get(i) == null) {
                assert(false);
            }
        }
        
        assert(true);
    }
}
