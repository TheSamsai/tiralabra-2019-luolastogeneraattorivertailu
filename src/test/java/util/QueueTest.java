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
public class QueueTest {
    
    public QueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    Queue<Integer> queue;
    
    @Before
    public void setUp() {
        queue = new Queue();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void newQueueInitializedWithProperValues() {
        assert(queue.getSize() == 0);
        assert(queue.isEmpty());
    }
    
    @Test
    public void enquingItemIncreasesSize() {
        queue.enqueue(42);
        
        assert(queue.getSize() == 1);
    }
    
    @Test
    public void dequeueingReturnsCorrectValue() {
        queue.enqueue(42);
        
        assert(queue.dequeue() == 42);
    }
    
    @Test
    public void dequeueingDecrementsSize() {
        queue.enqueue(42);
        queue.dequeue();
        
        assert(queue.getSize() == 0);
    }
    
    @Test
    public void emptyDequeReportsEmpty() {
        queue.enqueue(42);
        queue.dequeue();
        
        assert(queue.isEmpty());
    }
    
    @Test
    public void queueEmptiesInCorrectOrder() {
        queue.enqueue(42);
        queue.enqueue(420);
        queue.enqueue(47);
        
        assert(queue.dequeue() == 42);
        assert(queue.dequeue() == 420);
        assert(queue.dequeue() == 47);
    }
}
