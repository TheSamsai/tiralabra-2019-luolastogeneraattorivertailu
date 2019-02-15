/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author sami
 */
public class ArrayList<A> implements Iterable<A> {
    private Object list[];
    private final int DEFAULT_SIZE = 10;
    private int size = 0;
    private int capacity = 0;
    
    public ArrayList() {
        list = new Object[DEFAULT_SIZE];
        capacity = DEFAULT_SIZE;
    }
    
    public ArrayList(int size) {
        list = new Object[size];
        capacity = size;
    }
    
    /**
     * Add an item to the list and grow list if needed.
     * @param item The item to be added to the list.
     */
    public void add(A item) {
        if (size == capacity) {
            growArray();
        }
        
        list[size] = item;
        
        size++;
    }
    
    /**
     * Add an item to the list and grow list if needed.
     * @param item The item to be added to the list.
     */
    public void replace(int index, A item) {
        list[index] = item;
    }
    
    /**
     * Get the item at index i on the list
     * @param i Index of the item
     * @return The object stored in the index.
     */
    public A get(int i) {
        return (A) list[i];
    }
    
    /**
     * Remove the item at a given index and shift items left
     * @param i Index of the object to be removed
     */
    public void remove(int i) {
        list[i] = null;
        
        for (int index = i + 1; index < size; index++) {
            list[index - 1] = list[index];
        }
        
        size--;
    }
    
    /**
     * Iterate through the list and test if an object is on the list
     * @param item The object to be tested for
     * @return True if object is on the list, False otherwise
     */
    public boolean contains(A item) {
        for (int i = 0; i < size; i++) {
            A obj = (A) list[i];
            
            if (obj.equals(item)) {
                return true;
            }
        }
        
        return false;
    }
    
    public int getSize() {
        return size;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * Create a new array twice the size of the current one and copy
     * contents from the original array onto it and replace the old list.
     */
    public void growArray() {
        Object newList[] = new Object[capacity * 2];
        
        for (int i = 0; i < size; i++) {
            newList[i] = list[i];
        }
        
        list = newList;
        capacity = capacity * 2;
    }
    
    /**
     * Create an iterator to allow "for (x : y)" iteration syntax
     * @return An iterator over the current ArrayList
     */
    @Override
    public Iterator<A> iterator() {
        Iterator it =  new Iterator<A>() {
            private int index = 0;
            
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public A next() {
                return (A) list[index++];
            }
        
        };
        
        return it;
    }
}
