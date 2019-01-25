/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bspgenerator;

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
public class BSPGeneratorTest {
    
    public BSPGeneratorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    BSPGenerator gen;
    
    @Before
    public void setUp() {
        gen = new BSPGenerator();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void generatedDungeonTreeHasTwoLeavesPerBranch() {
        gen.generate(100, 100);
        
        BinaryTree tree = gen.getTree();
        
        assert(hasTwoLeavesPerBranch(tree));
    }
    
    public boolean hasTwoLeavesPerBranch(BinaryTree tree) {
        if (tree == null) {
            return false;
        } else if (tree.getLeft() == null && tree.getRight() == null) {
            return true;
        }
        
        boolean left = hasTwoLeavesPerBranch(tree.getLeft());
        boolean right = hasTwoLeavesPerBranch(tree.getRight());
        
        if (!left || !right) {
            return false;
        } else {
            return true;
        }
    }
    
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
}
