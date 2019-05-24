package com.rupesh.datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class SinglyLinkedList<E> implements ListI<E> {
	
	// Inner class definition for Node
	@SuppressWarnings("hiding")
	private class Node<E> {
		E data;
		Node<E> next;	
		//Inner class constructor
		public Node(E obj) {
			data = obj;
			next = null;
		}
	}
    // Inner class END
	
	private Node<E> head, tail;
	private int currentListSize;
	
	//main class constructor
	public SinglyLinkedList() {
		head = tail = null;
		currentListSize = 0;
	}
	
          //Adding in LinkedList Methods
    //addFirst() Method of O(1) Complexity
	public void addFirst(E obj) {
		Node<E> node = new Node<E>(obj);
		
		if (head == null) {
			head = tail = node;
			currentListSize ++;
			return;
		}
		node.next = head;
		head = node;
		currentListSize++;
	}
	
	//addLast() Method of O(1) Complexity due to tail pointer
	public void addLast(E obj) {
		Node<E> node = new Node<E>(obj);
		if (head == null) {
			head = tail = node;
			currentListSize ++;
			return;
		}
		tail.next = node;
		tail = node;
		currentListSize++;
	}
	
	//addPosition() Method of O(n) Complexity 
	@Override
	public void addPosition(E obj, int position) {
		if (position <= 1) {
			addFirst(obj);
			return;
		} else if(position > currentListSize) {
			addLast(obj);
			return;
		} else {
		Node<E> current = head, previous = null;
		for(int i = 1; i < position; i++) {
			previous = current;
			current = current.next;
		}
		Node<E> node = new Node<E>(obj);
		node.next = current;
		previous.next = node;
		currentListSize++;
		}
	}
         //Removing in LinkedList Methods
	//removeFirst() Method of O(1) Complexity
	public E removeFirst() {
		if (head == null) return null;
		E temp = head.data;
		if (head == tail)
			head = tail = null;
		else
			head = head.next;
		currentListSize--;
		return temp;
	}
	
	//removeLast() method of O(n) Complexity
	public E removeLast() {
		if (head == null) return null;
		if(head == tail) return removeFirst();
		Node<E> current = head, previous = null;
		while (current != tail) {
			previous = current;
			current = current.next;
		}
		previous.next = null;
		tail = previous;
		currentListSize--;
		return current.data;
	}
	
	//remove() based on object O(n) Complexity
	@SuppressWarnings("unchecked")
	public E remove(E obj) {
		Node<E> current = head, previous = null;
		while(current != null) {
			if(((Comparable<E>)obj).compareTo(current.data) == 0) {
				if(current == head) return removeFirst();
				if(current == tail) return removeLast();
				currentListSize--;
				previous.next = current.next;
				return current.data;
				}
			previous = current;
			current = current.next;
		}
		return null;
	}
	
	//contains() method to find object.  O(n) Complexity
	@SuppressWarnings("unchecked")
	public boolean contains(E obj) {
		Node<E> current = head;
		while(current != null) {
			if(((Comparable<E>)obj).compareTo(current.data) == 0) {
				return true;
			}
			current = current.next;
		}
		return false;
	}
	
	//peekFirst() method of O(1) complexity
	public E peekFirst() {
		if(head == null) return null;
		return head.data;
	}
	
	//peekLast() method of O(1) complexity
	public E peekLast() {
		if (tail == null) return null;
		return tail.data;
	}
	
	//Display List method of O(n) complexity
	public String toString() {
		Node<E> current = head;
		StringBuffer result = new StringBuffer();
		result.append("[ ");
		
		if(head == null)
			return result.append(" EMPTY ]").toString();
		result.append(current.data);
		while(current.next != null) {
			current = current.next;
			result.append(", " + current.data);
		}
		return result.append("]").toString();	
	}
	
	@Override
	public int size() {
		return currentListSize;
	}
	
	class IteratorHelper implements Iterator<E>{
		Node<E> index;
		public IteratorHelper() {
			index = head;
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (index != null);
		}

		@Override
		public E next() {
			if(!hasNext())
				throw new NoSuchElementException();
			E value = index.data;
			index = index.next;
			return value;
		}
		
	}
}

