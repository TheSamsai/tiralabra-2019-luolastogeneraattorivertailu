/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bspgenerator;

import domain.Room;
import domain.Dungeon;
import java.util.Random;

/**
 *
 * @author sami
 */

public class BSPGenerator {
    Random rng;
    
    BinaryTree dungeonTree;
    
    /**
     * Generates a dungeon using simple binary space partitioning.
     * <p>
     * The algorithm takes a given area in X and Y coordinates and then
     * proceeds to partition it into sub-dungeons until they are the size of
     * a single room in the dungeon. At this point it draws rooms into these
     * sub-dungeons and connects the sub-dungeons into a cohesive maze of rooms
     * and corridors.
     * </p>
     * @param x The width of the dungeon in tiles.
     * @param y The height of the dungeon in tiles.
     * @return A complete dungeon.
     */
    public Dungeon generate(int x, int y) {
        Dungeon dungeon = new Dungeon(x, y);
        rng = new Random();
        
        dungeonTree = new BinaryTree(new Room(0,0,x,y));
        
        System.out.println("Generating BSP tree...");
        
        generateTree(dungeonTree);
        
        System.out.println("Carving rooms...");
        
        carveRooms(dungeonTree, dungeon);
        
        return dungeon;
    }
    
    /**
     * Retrieves the completed dungeon tree for analysis and testing.
     * @return A binary tree of the sub-dungeons.
     */
    public BinaryTree getTree() {
        return dungeonTree;
    }
    
    /**
     * Recursively partitions the tree until the tree cannot be partitioned further.
     * @param tree The binary tree that is used to store the sub-dungeons.
     */
    private void generateTree(BinaryTree tree) {
        int heightMiddle = tree.getRoom().h / 2;
        int widthMiddle = tree.getRoom().w / 2;
        
        if (tree.getRoom().area() > 15) {
            boolean split = false;
            
            if (rng.nextBoolean() && heightMiddle > 5) {
                System.out.println("Vertical split");
                tree.partitionVertical(heightMiddle);
                split = true;
            } else if (widthMiddle > 5) {
                System.out.println("Horizontal split");
                tree.partitionHorizontal(widthMiddle);
                split = true;
            }
            
            if (split) {
                generateTree(tree.getLeft());
        
                generateTree(tree.getRight());
            }
        }
    }
    
    /**
     * Carves rooms into the given Dungeon based on the binary tree of the sub-dungeons.
     * @param tree The binary tree containing a sub-dungeon.
     * @param dungeon The Dungeon object that the rooms are to be carved on.
     */
    private void carveRooms(BinaryTree tree, Dungeon dungeon) {
        if (tree.getLeft() == null && tree.getRight() == null) {
            Room room = tree.getRoom();
            
            double size = (rng.nextDouble() + 0.50) % 1.0;
            
            room.x += room.w - (room.w * size);
            room.y += room.h - (room.h * size);
            
            room.h *= size;
            room.w *= size;
            
            dungeon.carveRoom(room);
            
            return;
        }
        
        carveRooms(tree.getLeft(), dungeon);
        carveRooms(tree.getRight(), dungeon);
    }
}
