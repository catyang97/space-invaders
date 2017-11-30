/*
 *  Execution:  
 *  A generic LinkedList data structure that can hold any type of data.
 *  
 */ 

public class LinkedList<T> implements List<T> {
    // sentinel nodes for a doubly linked list
    private Node<T> head;
    private Node<T> tail;
    
    // inner Node class
    private class Node<T> {
        private T data;
        private Node<T> next, prev;
    }
    
    /* Description: the constructor initializes the head and tail nodes and
     * links them together.
     * Input: none
     */
    public LinkedList() {
        head = new Node<T>();
        tail = new Node<T>();
        head.next = tail;
        tail.prev = head;
    }
    
    /*
     * Adds the object x to the end of the list.
     * @param x the element to be added to this list
     * @return true
     */
    public boolean add(T x) {
        Node<T> newNode = new Node<T>();
        // put object x into the new node
        newNode.data = x;
        // link the new node to tail node and the previous last node
        newNode.next = tail;
        newNode.prev = tail.prev;
        tail.prev = newNode;
        newNode.prev.next = newNode;
        return true;
    }
    
     /*
     * Adds the object x at the specified position
     * @param index the position to add the element
     * @param x the element to be added to the list
     * @return true if the operation succeeded, false otherwise
     */
    public boolean add(int index, T x) {
        // throw an IllegalArgumentException if the index is invalid
        if (index > size() || index < 0) {
            throw new IllegalArgumentException("Error: Index needs to be positive " +
                                              "and less than the size of the list.");
        }

        Node<T> newNode = new Node<T>();
        // put object x into the new node
        newNode.data = x;
        // iterate to the indicated index
        Node<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        // link new node
        newNode.next = curr.next;
        newNode.prev = curr;
        curr.next.prev = newNode;
        curr.next = newNode;
        // return true if the operation succeeded
        return true;
    }
    
    /**
     * Returns the number of elements in this list
     * @return the number of elements in this list
     */
    public int size() {
        int size = 0;
        // iterate through each node and update size
        for (Node<T> curr = head.next; curr != tail; curr = curr.next) {
            size++;
        }
        return size;
    }
    
    /**
     * Returns the element with the specified position in this list
     * @param index the position of the element
     * @return the element at the specified position in this list
     */
    public T get(int index) {
        // throw an IllegalArgumentException if the list is empty
        if (head.next.next == null) { 
            throw new IllegalArgumentException("Error: Attempting to access " +
                                                   "element from an empty list.");
        }
        // throw an IllegalArgumentException if the index is invalid
        if (index >= size() || index < 0) {
            throw new IllegalArgumentException("Error: Index needs to be positive " +
                                              "and less than the size of the list.");
        }
        // iterate to the indicated index
        Node<T> curr = head.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        // return the object at the index
        return curr.data;
    }
    
    /**
     * Replaces the object at the specified position
     * @param index the position to replace
     * @param x the element to be stored
     * @return the previous value of the element at index
     */
    public T set(int index, T x) {
        // throw an IllegalArgumentException if the list is empty
        if (head.next.next == null) { 
            throw new IllegalArgumentException("Error: Attempting to access " +
                                                   "element from an empty list.");
        }
        // throw an IllegalArgumentException if the index is invalid
        if (index >= size() || index < 0) {
            throw new IllegalArgumentException("Error: Index needs to be positive " +
                                              "and less than the size of the list.");
        }
        // get the original object at the indicated index
        T old = get(index);
        // remove the node at the indicated index and replace with a node with 
        // the new object
        remove(index);
        add(index, x);
        return old;
    }
    
    /**
     * Removes the object at the specified position
     * @param index the position to remove
     * @return the object that was removed
     */
    public T remove(int index) {
        // throw an IllegalArgumentException if the list is empty
        if (head.next.next == null) { 
            throw new IllegalArgumentException("Error: Attempting to delete " +
                                                   "element from an empty list.");
        }
        // throw an IllegalArgumentException if the index is invalid
        if (index >= size() || index < 0) {
            throw new IllegalArgumentException("Error: Index is out of bounds.");
        }
        // get the original object at the indicated index
        T old = get(index);
        // iterate to the indicated index
        Node<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        // link the nodes before and after the node at the indicated index together
        curr.next.next.prev = curr;
        curr.next = curr.next.next;
        return old;
    }
    
    /**
     * Tests if this list has no elements.
     * @return  <tt>true</tt> if this list has no elements;
     *          <tt>false</tt> otherwise.
     */
    public boolean isEmpty() {
        // return true if the size is 0
        if (size() == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Returns true if this list contains the specified element.
     * @param element element whose presence in this List is to be tested.
     * @return true if the specified element is present; false otherwise.
     */
    public boolean contains(T element) {
        int i = 0;
        // iterate through the list and return true if the element at each index is
        // the element being searched for
        for (Node<T> curr = head.next; curr != tail; curr = curr.next) {
            if (get(i).equals(element)) {
                return true;
            }
            i++;
        }
        // return false if the element is not found
        return false;
    }
    
    /** 
     * Returns the index of the specified element
     * @param element the element we're looking for
     * @return the index of the element in the list, or -1 if it is not 
     * contained within the list
     */
    public int indexOf(T element) {
        int i = 0;
        // iterate through the list and return the index if element is found
        for (Node<T> curr = head.next; curr != tail; curr = curr.next) {
            if (get(i) == element) {
                return i;
            }
            i++;
        }
        // return -1 if the element is not found
        return -1;
    }
    
    // TODO- method header
    public T set2(int index, T x) {
        // throw an IllegalArgumentException if the list is empty
        if (head.next.next == null) { 
            throw new IllegalArgumentException("Error: Attempting to access " +
                                                   "element from an empty list.");
        }
        // throw an IllegalArgumentException if the index is invalid
        if (index >= size() || index < 0) {
            throw new IllegalArgumentException("Error: Index needs to be positive " +
                                              "and less than the size of the list.");
        }

        // remove the node at the indicated index and replace with a node with 
        // the new object
        remove(index);
        add(index, x);
        T newOne = get(index);
        return newOne;
    }
    
    
    public static void main(String[] args) {

        
    }
    
    
}
