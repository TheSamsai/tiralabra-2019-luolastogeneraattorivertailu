/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 * A single node in a Queue
 * @author sami
 */
public class QueueNode<A> {
    private QueueNode<A> next;
    private A data;
    
    public QueueNode(A data) {
        this.data = data;
    }
    
    public A getContents() {
        return data;
    }
    
    public boolean hasNext() {
        return next != null;
    }
    
    public QueueNode<A> getNext() {
        return next;
    }
    
    public void setNext(QueueNode<A> next) {
        this.next = next;
    }
}
