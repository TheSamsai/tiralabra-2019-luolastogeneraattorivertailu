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
     * @return An index between 0 and array capacity
     */
    public int hashIndex(T element) {
        int index = element.hashCode() % capacity;
        
        if (index < 0) index *= -1;
        
        return index;
    }
    
    /**
     * Using a hash and quadratic probing, insert an element into the HashSet
     * @param element The element to be added to the set
     */
    public void add(T element) {
        int hash = hashIndex(element);
        
        int attempt = 1;
        
        int index = hash;
        
        // Use quadratic probing to find the next empty index
        // Stop if an element equal to the one being added is found
        while (index < capacity && list[index] != null && !element.equals(list[index])) {
            
            index = hash + attempt * attempt;
            
            attempt++;
            
            if (index >= capacity) {
                rehash();
                add(element);
                return;
            }
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
        
        // Iterate over each bucket in the list and rehash them on the new list
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
        int hash = hashIndex(element);
        
        int attempt = 1;
        int index = hash;
        
        // We need to also do quadratic probing here in order to find the element
        while (index < capacity && list[index] != null && !element.equals((T) list[index])) {
            index = hash + attempt * attempt;
            attempt++;
        }
        
        // If the index is within bounds, the index will contain either our value
        // or a null, either will do
        if (index < capacity) {
            return (T) list[index];
        }
        
        // If we exceed capacity, the set didn't contain our value
        return null;
    }
    
    /**
     * Remove an element equal to the one given as an argument
     * @param element 
     */
    public void remove(T element) {
        int hash = hashIndex(element);
        
        int attempt = 1;
        int index = hash;
        
        // Quadratic probing
        while (index < capacity && list[index] != null && !element.equals((T) list[index])) {
            index = hash + attempt * attempt;
            
            attempt++;
        }   
        
        if (index < capacity) {
            list[index] = null;
            size--;
        }
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
        
        if (result != null) return true;
        else return false;
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
     * @return An iterator over the current ArrayList
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
