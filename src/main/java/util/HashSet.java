/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Iterator;

/**
 *
 * @author sami
 */
public class HashSet<T> implements Iterable {
    // Set is an arraylist containing N buckets (also arraylists)
    Object list[];
    int DEFAULT_CAPACITY = 1000;
    int size;
    int capacity;
    
    public HashSet() {
        list = new Object[DEFAULT_CAPACITY];
        size = 0;
        capacity = DEFAULT_CAPACITY;
    }
    
    /**
     * Generate an index value from a given element's hashcode
     * @param element An object that implements a hashCode() function
     * @return A valid index between 0 and capacity, -1 if table is full
     */
    public int findIndex(T element) {
        int hash = element.hashCode() % capacity;
        
        if (hash < 0) hash *= -1;
        
        int index = hash;
        
        for (int attempt = 0; attempt < capacity; attempt++) {
            index = (hash + (attempt * attempt)) % capacity;
            
            if (list[index] == null) return index;
            if (list[index].equals(element)) return index;
        }
        
        return -1;
    }
    
    /**
     * Using a hash and quadratic probing, insert an element into the HashSet
     * @param element The element to be added to the set
     */
    public void add(T element) {
        int index = findIndex(element);
        
        if (index < 0) {
            rehash();
            add(element);
            return;
        }
        
        // Add item to the bucket at given index and increase size counter
        list[index] = element;
        size++;
        
        // Check load factor and apply rehashing
        if (((double) size / (double) capacity) > 0.75) {
            rehash();
        }
    }
    
    /**
     * Quadruple the size of the array and rehash all elements in the set
     */
    public void rehash() {
        // We will replace the list, so store it here
        Object[] oldSet = list;
        
        // Create the replacement list quadruple the capacity
        list = new Object[capacity * 4];
        capacity = capacity * 4;
        size = 0;
        
        // Iterate over each index in the old array and rehash them onto the new array
        for (int i = 0; i < oldSet.length; i++) {
            if (oldSet[i] != null) {
                add((T) oldSet[i]);
            }
        }
    }
    
    /**
     * Attempt to acquire an element same as the supplied one
     * @param element An element to be looked for
     * @return The element, if part of the set, null otherwise
     */
    public T get(T element) {
        int index = findIndex(element);
        
        if (index < 0) return null;
        
        // If we exceed capacity, the set didn't contain our value
        return (T) list[index];
    }
    
    /**
     * Remove an element equal to the one given as an argument
     * @param element 
     */
    public void remove(T element) {
        int index = findIndex(element);
        
        if (index < 0) return;
        
        list[index] = null;
        size--;
    }
    
    /**
     * Reset the size to 0 and clear the array
     */
    public void clear() {
        size = 0;
        list = new Object[capacity];
    }
    
    /**
     * Check if an element is part of the set
     * @param element An element to be looked up
     * @return True if element is found, false otherwise
     */
    public boolean contains(T element) {
        T result = get(element);
        
        if (result == null) return false;
        
        return result.equals(element);
    }
    
    /**
     * Transform all items in the set into an ArrayList
     * @return An ArrayList containing all elements of the set
     */
    public ArrayList<T> getElements() {
        ArrayList<T> results = new ArrayList<>();
        
        for (int i = 0; i < capacity; i++) {
            if (list[i] != null) {
                results.add((T) list[i]);
            }
        }
        
        return results;
    }
    public int getSize() {
        return size;
    }

    /**
     * Create an iterator to allow "for (x : y)" iteration syntax
     * @return An iterator over the elements in the HashSet
     */
    @Override
    public Iterator<T> iterator() {
        Iterator it =  new Iterator<T>() {
            private int elemNum = 0;
            private int lastIndex = -1;
            
            @Override
            public boolean hasNext() {
                return elemNum < size;
            }

            @Override
            public T next() {
                int index = lastIndex + 1;
                
                while (list[index] == null) index++;
                
                elemNum++;
                
                return (T) list[index];
            }
        
        };
        
        return it;
    }
}
