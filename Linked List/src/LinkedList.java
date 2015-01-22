/*  Student information for assignment:
 *
 *  On my honor, <NAME>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name:
 *  email address:
 *  UTEID:
 *  Section 5 digit ID: 
 *  Grader name:
 *  Number of slip days used on this assignment:
 */

import java.util.Iterator;

public class LinkedList<E> implements IList<E> {
    // CS314 student. Add you instance variables here.
    // You decide what instance variables to use. 
    // Must adhere to assignment requirements. No ArrayLists or Java LinkedLists.
	private DoubleListNode<E> first;
	private DoubleListNode<E> last;
	private int size;
	
    private class LLIterator implements Iterator<E>{
    	
    	private DoubleListNode<E> Dnode;
    	private boolean removeOK;
    	
    	private LLIterator() {
            Dnode = first;
            removeOK = false;
        }
        public boolean hasNext(){
        	return Dnode.getNext() != null;
        }
        
        public E next(){ 
        	if (!hasNext())
        		throw new IllegalStateException();
        	removeOK = true;
        	return Dnode.getNext().getData();
        }
        
        public void remove(){
    		if (!removeOK)
    			throw new IllegalArgumentException();
        	removeOK =false;
        	// case one if the node is the first one 
        	if (Dnode == first)
        		first = Dnode.getNext();
        	// if the node is the last one
        	else if (Dnode == last) {
        		DoubleListNode<E> newLastNode =  new DoubleListNode<E>();
        		newLastNode = last.getPrev();
        		last = newLastNode;
        	}	
        	// general case
        	Dnode.getPrev().setNext(Dnode.getNext());
        	Dnode.getNext().setPrev(Dnode.getPrev());
        	
        }
    }
    
    
    //Add an item to the end of this list. 
    //pre: item != null 
    //post: size() = old size() + 1, get(size() - 1) = item

	public void add(E item){
		addLast(item);
	}
	

	
	public void insert(int pos, E item){
		if (pos < 0 || pos > size - 1)
    		throw new IllegalArgumentException();
		// if pos == 0
		if (pos == 0){
			addFirst(item);
		}
		
		// if last element
		else if (pos == size - 1) {
			addLast(item);
		} else {	// general case 
			DoubleListNode<E> prevNode = getNodeAtIndex( pos - 1);
			DoubleListNode<E> afterNode = getNodeAtIndex( pos);
			DoubleListNode<E> newNode = new DoubleListNode<E>();
			prevNode.setNext(newNode);
			newNode.setData(item);
			newNode.setNext(afterNode);
			newNode.setPrev(prevNode);
			afterNode.setPrev(newNode);
		}
			
	}
	
	
	private DoubleListNode<E> getNodeAtIndex(int pos) {
		if (pos < 0 || pos > size - 1)
    		throw new IllegalArgumentException();
		if(pos == size - 1)
            return last;
		DoubleListNode<E> temp = first;
		for (int i = 0; i < pos; i++) {
			temp = temp.getNext();
		}
		return temp;
	}

	
	public E set(int pos, E item){
		getNodeAtIndex(pos).setData(item);
		return item;
	}

	
	public E get(int pos){
		getNodeAtIndex(pos).getData();		
		return null;
	}

	
	public E remove(int pos){
		if (pos < 0 || pos > size - 1)
    		throw new IllegalArgumentException();
		if (pos == 0)
			removeFirst();
		else if (pos == size -1) {
			removeLast();
		}
		else {
			DoubleListNode<E> prevNode = getNodeAtIndex(pos - 1);
			DoubleListNode<E> afterNode = getNodeAtIndex(pos + 1);
			prevNode.setNext(afterNode);
			afterNode.setPrev(prevNode);
		}
		DoubleListNode<E> Node = getNodeAtIndex(pos);
		size--;
		return Node.getData();
	}

	
	public boolean remove(E obj){
		if (obj == null)
			throw new IllegalArgumentException("cannot remove a null item");
//		Iterator<E> iterator = new LLIterator();
//		for (Iterator<E> iterator;iterator.hasNext();)
		// why above is not working???
		//LLIterator iter = iterator();
		for (Iterator<E> iterator = iterator();iterator.hasNext();) {
			E element = iterator.next();
			if (obj.equals(element)){
				iterator.remove();
				return true;
			}
		}
		
		return false;
	}
	
	public IList<E> getSubList(int start, int stop){
		// check pre
		if ( (start < 0 || start >= size) || (start < 0 || start >= size) )
			throw new IllegalArgumentException("out of the range of the index");
		IList<E> subList = new LinkedList<E>();
		int index = start;
		while (index < stop) {
			DoubleListNode<E> Node = getNodeAtIndex(index);
			subList.add(Node.getData());
			Node = Node.getNext();
			index++;
		}
		return subList;
	}

	
	public int size(){
		// TODO Auto-generated method stub
		return size;
	}

	
	public int indexOf(E item){
		if (item == null)
			throw new IllegalArgumentException("item cannot be null");
		return indexOf(item,0);

	}
	public int indexOf(E item, int pos){
		if (item == null || (pos < 0 || pos > size()))
			throw new IllegalArgumentException("item cannot be null");
		DoubleListNode<E> tempNode = getNodeAtIndex(pos);
		int index = pos;
		while (tempNode != null) {
			if (tempNode.getData() == item) {
				return index;
			}	
			tempNode = tempNode.getNext();
			index++;
		}
		return -1;
	}
	
	public void makeEmpty(){
		DoubleListNode<E> temp = first;
        while (temp != null) {
            DoubleListNode<E> trailer = temp;
            trailer.setData(null);        
            trailer.setPrev(null);
            trailer.setNext(null); 
        }
        first = last = null;
        size = 0; 
	}

	
	public Iterator<E> iterator(){
		// TODO Auto-generated method stub
		return new LLIterator();// why cannot create by myself 
	}
	
	
    public void removeRange(int start, int stop){
    	if ( start < 0 || stop > size() || start > stop )
    		throw new IllegalArgumentException("invalid param");
    	for (Iterator<E> iter = this.iterator();iter.hasNext();) {
			int counter = 0;
			if (counter >= start && counter < stop) {
				iter.remove();
			}
			counter++;
		}
    }
	
	/*
	 * add item to the front of the list.
	 * <br>pre: item != null`
	 * <br>post: size() = old size() + 1, get(0) = item
	 * @param item the data to add to the front of this list
	 */
	public void addFirst(E item){
		if (item == null)
    		throw new IllegalArgumentException();
		if (size == 0) {
			
		DoubleListNode<E> newNode = new DoubleListNode<E>(null,item, null);
		last = first = newNode;
		size++;
		}
		else {
			DoubleListNode<E> newFirstNode = new DoubleListNode<E>(null,item, first);
			first.setPrev(newFirstNode);
			first= newFirstNode;
			size++;
		}
	}


	/**
	 * add item to the end of the list.
	 * <br>pre: item != null
	 * <br>post: size() = old size() + 1, get(size() -1) = item
	 * @param item the data to add to the end of this list
	 */
	public void addLast(E item){
		if (item == null)
    		throw new IllegalArgumentException();
		if (size == 0) {
			
			DoubleListNode<E> newNode = new DoubleListNode<E>(null,item, null);
			last = first = newNode;
			size++;
			}
		else {
			DoubleListNode<E> newLastNode = new DoubleListNode<E>(last,item,null);
			last.setNext(newLastNode);
			last = newLastNode;
			size++;
		}
		
	}


	/**
	 * remove and return the first element of this list.
	 * <br>pre: size() > 0
	 * <br>post: size() = old size() - 1
	 * @return the old first element of this list
	 */
	public E removeFirst(){	
		//if list is empty
		E result = first.getData();
		if (size == 0)
			throw new IllegalArgumentException("size of the list cannot be zero");
		else if (size == 1) {
			makeEmpty();
		}
		else {
			first = first.getNext();
			first.setPrev(null);
			size--;
		}
		return result;
	}


	/**
	 * remove and return the last element of this list.
	 * <br>pre: size() > 0
	 * <br>post: size() = old size() - 1
	 * @return the old last element of this list
	 */
	public E removeLast() {	
		E result = last.getData();
		if (size == 0)
			throw new IllegalArgumentException("size of the list cannot be zero");
		else if (size == 1)
			makeEmpty();
		else {
			last = last.getPrev();
			last.setNext(null);
			size--;
		}
	    return result;
	}


	public String toString(){	
	    return null;
	}


	/**
	 * Check if this list is equal to another Object.
	 * Follow the contract of IList
	 * <br>pre: none
	 * @return true if other is a non null IList object
	 * with the same elements as this LinkedList in the same
	 * order.
	 */
	public boolean equals(Object other){	
	    return true;
	}


}