/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Random;
import java.util.concurrent.TimeUnit;
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
public class HashSetTest {
    
    public HashSetTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    
    java.util.HashSet<Integer> standardSet;
    HashSet<Integer> set;
    Random rng;
    
    @Before
    public void setUp() {
        set = new HashSet();
        standardSet = new java.util.HashSet<>();
        rng = new Random();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void setInitializesWithZeroSize() {
        assert(set.getSize() == 0);
    }
    
    @Test
    public void insertionIncreasesSize() {
        set.add(1);
        
        assert(set.getSize() == 1);
    }
    
    @Test
    public void canFindElementAfterInsertion() {
        set.add(1);
        
        assert(set.get(1) != null);
    }
    
    @Test
    public void setContainsElementAfterInsertion() {
        set.add(1);
        
        assert(set.contains(1));
    }
    
    @Test
    public void canFindAnElementsAfterInsertions() {
        set.add(1);
        set.add(2);
        set.add(3);
        
        assert(set.contains(1));
    }
    
    @Test
    public void removingElementReducesSize() {
        set.add(1);
        set.remove(1);
        
        assert(set.getSize() == 0);
    }
    
    @Test
    public void removingElementDoesNotRemoveAnother() {
        set.add(1);
        set.add(2);
        set.remove(1);
        
        assert(set.contains(2));
    }
    
    @Test
    public void rehashingChangesHashIndexes() {
        int oldIndex = set.hashIndex(1001);
        
        set.rehash();
        
        int newIndex = set.hashIndex(1001);
        
        assert(oldIndex != newIndex);
    }
    
    @Test
    public void valueCanBeFoundAfterRehash() {
        set.add(6969);
        set.rehash();
        
        assert(set.contains(6969));
    }
    
    @Test
    public void rehashingDoesNotCauseDataLoss() {
        for (int i = 0; i < 1500; i++) {
            set.add(i);
        }
        
        for (int i = 0; i < 1500; i++) {
            assert(set.contains(i));
        }
    }
    
    @Test
    public void performanceInsertionsSequential() {
        long standardTime = 0;
        long implTime = 0;
        
        for (int x = 0; x < 10; x++) {
            standardSet.clear();
            
            long startTime = System.nanoTime();
        
            for (int i = 0; i < 100000; i++) {
                standardSet.add(i);
            }
        
            long endTime = System.nanoTime();
            standardTime += endTime - startTime;
        }
        
        standardTime /= 10;
        
        for (int x = 0; x < 10; x++) {
            set.clear();
            
            long startTime = System.nanoTime();
        
            for (int i = 0; i < 100000; i++) {
                set.add(i);
            }
        
            long endTime = System.nanoTime();
            implTime += endTime - startTime;
        }
        
        implTime /= 10;
        
        System.out.println("Std HashSet: " + TimeUnit.NANOSECONDS.toMicros(standardTime) + " microseconds.");
        System.out.println("Custom HashSet: " + TimeUnit.NANOSECONDS.toMicros(implTime) + " microseconds");
    }
    
    @Test
    public void performanceInsertionsRandom() {
        long standardTime = 0;
        long implTime = 0;
        
        for (int x = 0; x < 10; x++) {
            standardSet.clear();
            
            long startTime = System.nanoTime();
        
            for (int i = 0; i < 100000; i++) {
                standardSet.add(rng.nextInt());
            }
        
            long endTime = System.nanoTime();
            standardTime += endTime - startTime;
        }
        
        standardTime /= 10;
        
        for (int x = 0; x < 10; x++) {
            set.clear();
            
            long startTime = System.nanoTime();
        
            for (int i = 0; i < 100000; i++) {
                set.add(rng.nextInt());
            }
        
            long endTime = System.nanoTime();
            implTime += endTime - startTime;
        }
        
        implTime /= 10;
        
        System.out.println("Std HashSet: " + TimeUnit.NANOSECONDS.toMicros(standardTime) + " microseconds.");
        System.out.println("Custom HashSet: " + TimeUnit.NANOSECONDS.toMicros(implTime) + " microseconds");
    }
    
    @Test
    public void performanceLookupsSequential() {
        long standardTime = 0;
        long implTime = 0;
        
        for (int i = 0; i < 100000; i++) {
            standardSet.add(i);
            set.add(i);
        }
        
        for (int x = 0; x < 10; x++) {
            long startTime = System.nanoTime();
        
            for (int i = 0; i < 100000; i++) {
                standardSet.contains(i);
            }
        
            long endTime = System.nanoTime();
            standardTime += endTime - startTime;
        }
        
        standardTime /= 10;
        
        for (int x = 0; x < 10; x++) {
            long startTime = System.nanoTime();
        
            for (int i = 0; i < 100000; i++) {
                set.contains(i);
            }
        
            long endTime = System.nanoTime();
            implTime += endTime - startTime;
        }
        
        implTime /= 10;
        
        System.out.println("Std HashSet: " + TimeUnit.NANOSECONDS.toMicros(standardTime) + " microseconds.");
        System.out.println("Custom HashSet: " + TimeUnit.NANOSECONDS.toMicros(implTime) + " microseconds");
    }
    
    @Test
    public void performanceLookupsRandom() {
        long standardTime = 0;
        long implTime = 0;
        
        for (int i = 0; i < 100000; i++) {
            standardSet.add(i);
            set.add(i);
        }
        
        for (int x = 0; x < 10; x++) {
            long startTime = System.nanoTime();
        
            for (int i = 0; i < 100000; i++) {
                standardSet.contains(rng.nextInt());
            }
        
            long endTime = System.nanoTime();
            standardTime += endTime - startTime;
        }
        
        standardTime /= 10;
        
        for (int x = 0; x < 10; x++) {
            long startTime = System.nanoTime();
        
            for (int i = 0; i < 100000; i++) {
                set.contains(rng.nextInt());
            }
        
            long endTime = System.nanoTime();
            implTime += endTime - startTime;
        }
        
        implTime /= 10;
        
        System.out.println("Std HashSet: " + TimeUnit.NANOSECONDS.toMicros(standardTime) + " microseconds.");
        System.out.println("Custom HashSet: " + TimeUnit.NANOSECONDS.toMicros(implTime) + " microseconds");
    }
    
    @Test
    public void performanceLookupsInsertionsAlternating() {
        long standardTime;
        long implTime;
        
        long startTime = System.nanoTime();
        
        for (int i = 0; i < 10000; i++) {
            standardSet.contains(i - 1);
            standardSet.add(i);
        }
        
        long endTime = System.nanoTime();
        standardTime = endTime - startTime;
        
        startTime = System.nanoTime();
        
        for (int i = 0; i < 10000; i++) {
            set.contains(i - 1);
            set.add(i);
        }
        
        endTime = System.nanoTime();
        implTime = endTime - startTime;
        
        System.out.println("Std HashSet: " + TimeUnit.NANOSECONDS.toMicros(standardTime) + " microseconds.");
        System.out.println("Custom HashSet: " + TimeUnit.NANOSECONDS.toMicros(implTime) + " microseconds");
    }
}
