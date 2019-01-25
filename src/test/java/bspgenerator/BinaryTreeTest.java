/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bspgenerator;

import domain.Room;
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
public class BinaryTreeTest {
    
    public BinaryTreeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    BinaryTree tree;
    
    @Before
    public void setUp() {
        tree = new BinaryTree(new Room(0, 0, 100, 100));
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
    public void insertLeftWorks() {
        tree.insertLeft(new BinaryTree(new Room(0,0,0,0)));
        
        assert(tree.left != null);
    }
    
    @Test
    public void insertRightWorks() {
        tree.insertRight(new BinaryTree(new Room(0,0,0,0)));
        
        assert(tree.right != null);
    }
    
    @Test
    public void horizontalPartitioningReducesArea() {
        tree.partitionHorizontal(tree.room.w / 2);
        
        assert(tree.getLeft().room.w < tree.room.w);
        assert(tree.getRight().room.w < tree.room.w);
    }
    
    @Test
    public void verticalPartitioningReducesArea() {
        System.out.println("");
        tree.partitionVertical(tree.room.h / 2);
        
        assert(tree.getLeft().room.h < tree.room.h);
        assert(tree.getRight().room.h < tree.room.h);
    }
    
    @Test
    public void doublePartitioningCreatesAQuarter() {
        tree.partitionHorizontal(tree.room.w / 2);
        tree.left.partitionVertical(tree.left.room.h / 2);
        
        Room room = tree.left.left.room;
        
        assert(room.w * room.h == tree.room.h * tree.room.w / 4);
    }
}
