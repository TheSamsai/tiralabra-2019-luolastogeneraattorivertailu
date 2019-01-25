/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bspgenerator;

import domain.Room;

/**
 *
 * @author sami
 */
public class BinaryTree {
    // The binary tree is recursively declared, the sub-trees are also binary trees.
    BinaryTree left;
    BinaryTree right;
    
    // The area of the current node in the binary tree.
    Room room;
    
    public BinaryTree() {
        left = null;
        right = null;
        
        room = null;
    }
    
    public BinaryTree(Room room) {
        left = null;
        right = null;
        
        this.room = room;
    }
    
    /**
     * Insert to the left node of the current tree.
     * 
     * @param tree The sub-tree to be inserted into the tree.
     */
    public void insertLeft(BinaryTree tree) {
        left = tree;
    }
    
    /**
     * Insert to the right node of the current tree.
     * 
     * @param tree The sub-tree to be inserted into the tree.
     */
    public void insertRight(BinaryTree tree) {
        right = tree;
    }
    
    public BinaryTree getLeft() {
        return left;
    }
    
    public BinaryTree getRight() {
        return right;
    }
    
    public Room getRoom() {
        return room;
    }
    
    /**
     * Partitions current room vertically into two smaller rooms.
     * <p>
     * The partition function will insert child nodes to the left and the
     * right side of the tree. The left node represents the upper part and
     * the right node the lower part of the partition.
     * </p>
     * @param y The Y-coordinate where the room should be partitioned.
     */
    public void partitionVertical(int y) {
        insertLeft(new BinaryTree(new Room(room.x, room.y, room.w, room.h - y)));
        insertRight(new BinaryTree(new Room(room.x, room.y + y, room.w, room.h - y)));
    }
    
    /**
     * Partition current room horizontally into two smaller rooms.
     * <p>
     * The partition function will insert child nodes to the left and the
     * right side of the tree. The left node represents the left part and
     * the right node the right part of the partition.
     * </p>
     * @param x The X-coordinate where the room should be partitioned.
     */
    public void partitionHorizontal(int x) {
        insertLeft(new BinaryTree(new Room(room.x, room.y, room.w - x, room.h)));
        insertRight(new BinaryTree(new Room(room.x + x, room.y, room.w - x, room.h)));
    }
}
