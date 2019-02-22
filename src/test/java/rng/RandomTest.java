/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rng;

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
public class RandomTest {
    
    public RandomTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    Random rng;
    
    @Before
    public void setUp() {
        rng = new Random(666l);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    @Test
    public void rngAlwaysLowerThanBound() {
        for (int i = 0; i < 2000; i++)
            assert(rng.nextInt(1) == 0);
    }
    
    @Test
    public void rngGeneratesDifferentValues() {
        boolean different = false;
        int previous = rng.nextInt(1000);
        
        for (int i = 0; i < 2000; i++) {
            int next = rng.nextInt(1000);
            if (next != previous) {
                different = true;
                break;
            }
        }
        
        assert(different);
    }
    
    @Test
    public void rngDoublesLessThanOne() {
        for (int i = 0; i < 2000; i++) {
            double next = rng.nextDouble();
            
            assert(next < 1.0);
            assert(next > -1.0);
        }
    }
}
