/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 * A basic queue implementation based on a linked list
 * @author sami
 */
public class Queue<A> {
    private QueueNode<A> first;
    private QueueNode<A> last;
    private int size = 0;
    
    /**
     * Add a new node to the linked list at last node
     * @param data 
     */
    public void enqueue(A data) {
        QueueNode<A> node = new QueueNode(data);
        
        // If first is null, then the queue must be empty and we need to initialize the values
        if (first == null) {
            first = node;
            last = node;
        } else {
            last.setNext(node);
            last = node;
        }
        
        size++;
    }
    
    /**
     * Return the element first in the queue and decrement queue size
     * @return 
     */
    public A dequeue() {
        QueueNode<A> node = first;
        
        if (node == null) {
            throw new java.lang.RuntimeException("Attempted to dequeue an empty queue!");
        }
        
        if (node.hasNext()) {
            first = node.getNext();
        } else {
            first = null;
            last = null;
        }
        
        size--;
        
        return node.getContents();
    }
    
    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Iterate over the queue contents and returns if an element is on the queue.
     * @param data
     * @return True if element is in the queue, false otherwise
     */
    public boolean contains(A data) {
        QueueNode<A> node = first;
        
        while (node != null) {
            if (node.getContents().equals(data)) {
                return true;
            } else {
                node = node.getNext();
            }
        }
        
        return false;
    }
}
