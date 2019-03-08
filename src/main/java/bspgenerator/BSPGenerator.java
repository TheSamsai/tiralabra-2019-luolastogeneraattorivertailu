/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bspgenerator;

import domain.Room;
import domain.Dungeon;
import rng.Random;

/**
 *
 * @author sami
 */

public class BSPGenerator {
    Random rng;
    
    BinaryTree dungeonTree;
    
    public BSPGenerator() {
        rng = new Random();
    }
    
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
        
        dungeonTree = new BinaryTree(new Room(0,0,x,y));
        
        //System.out.println("Generating BSP tree...");
        
        generateTree(dungeonTree, 25, 0.5, 5);
        
        //System.out.println("Carving rooms...");
        
        carveRooms(dungeonTree, dungeon);
        
        carveHallways(dungeonTree, dungeon);
        
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
    public void generateTree(BinaryTree tree, int minArea, double balance, int iterations) {
        if (iterations == 0) {
            return;
        }
        
        // Calculate the center lines of the room
        int heightMiddle = tree.getRoom().h / 2;
        int widthMiddle = tree.getRoom().w / 2;
        
        // We need to get the room's 1/10th to calculate increments
        double height10 = tree.getRoom().h * 0.10;
        double width10 = tree.getRoom().w * 0.10;
        
        // Calculate an offset (up to 10% of the width or height) for various room sizes
        int heightIncrement = ((int) height10) - rng.nextInt((int) height10 * 2);
        int widthIncrement = ((int) width10) - rng.nextInt((int) height10 * 2);
        
        double newBalance = balance;
        
        // The balance determines which partition should be taken
        if (rng.nextDouble() > balance) {
            tree.partitionVertical(heightMiddle + heightIncrement);
            newBalance += 0.20;
        } else {
            tree.partitionHorizontal(widthMiddle + widthIncrement);
            newBalance -= 0.20;
        }
        
        // If the area in the generated leaf nodes are too small,
        // we replace the leaves with null, making this node a leaf
        if (tree.getLeft().getRoom().area() < minArea || tree.getRight().getRoom().area() < minArea) {
            tree.insertLeft(null);
            tree.insertRight(null);
            return;
        }
        
        if (heightMiddle > widthMiddle) {
            newBalance += 0.10;
        } else if (widthMiddle > heightMiddle) {
            newBalance -= 0.10;
        }
        
        // Recursively generate the sub dungeons to the left and right
        generateTree(tree.getLeft(), minArea, newBalance, iterations - 1);
        generateTree(tree.getRight(), minArea, newBalance, iterations - 1);
    }
    
    /**
     * Carves rooms into the given Dungeon based on the binary tree of the sub-dungeons.
     * @param tree The binary tree containing a sub-dungeon.
     * @param dungeon The Dungeon object that the rooms are to be carved on.
     */
    public void carveRooms(BinaryTree tree, Dungeon dungeon) {
        if (tree.getLeft() == null && tree.getRight() == null) {
            Room room = tree.getRoom();
            
            double size = (rng.nextDouble() + 0.80) % 1.0;
            
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
    
    /**
     * Does a semi-randomized DFS to find a leaf node (a room) in the dungeon tree
     * @param tree A BinaryTree containing the dungeon data
     * @return A room contained in a leaf node of the tree
     */
    public Room findRoom(BinaryTree tree) {
        if (tree.getLeft() == null && tree.getRight() == null) {
            return tree.getRoom();
        }
        
        if (rng.nextBoolean()) {
            return findRoom(tree.getLeft());
        } else {
            return findRoom(tree.getRight());
        }
    }
    
    /**
     * Carves hallways between the rooms in the leaf nodes of the dungeon tree
     * @param tree The BinaryTree representation of the dungeon
     * @param dungeon The dungeon object to be modified
     */
    public void carveHallways(BinaryTree tree, Dungeon dungeon) {
        if (tree.getLeft() == null || tree.getRight() == null) {
            return;
        }
        
        carveHallways(tree.getLeft(), dungeon);
        carveHallways(tree.getRight(), dungeon);
        
        dungeon.carveTunnel(findRoom(tree.getLeft()).center(), findRoom(tree.getRight()).center());
    }
}
