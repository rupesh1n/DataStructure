package com.rupesh.datastructure;

public class DoublyLinkedList<E> implements ListI<E> {
	
	@SuppressWarnings("hiding")
	class Node<E>{
		E data;
		Node<E> next, previous;
		public Node(E obj) {
			data = obj;
			next = previous = null;
		}
	}
	
	private Node<E> head, tail;
	private int currentSize;
	
	public DoublyLinkedList() {
		head = tail = null;
		currentSize = 0;
	}

	@Override
	public void addFirst(E item) {
		Node<E> node = new Node<E>(item);
		if (head == null) {
			head = tail = node;
			currentSize++;
			return;
		}
		node.next = head;
		head.previous = node;
		head = node;
		currentSize++;
	}

	@Override
	public void addLast(E item) {
		Node<E> node = new Node<E>(item);
		if(head == null) {
			addFirst(item);
		}
		tail.next = node;
		node.previous = tail;
		tail = node;
		currentSize++;
	}

	@Override
	public void addPosition(E item, int position) {
		Node<E> node = new Node<E>(item);
		if(position <=1) {
			addFirst(item);
			return;
		} else if(position > currentSize) {
			addLast(item);
			return;
		} else {
			Node<E> current = head, previous =null;
			for (int i = 1; i < position; i++) {
				previous = current;
				current = current.next;
			}
			node.next = current;
			node.previous = previous;
			previous.next = node;
			currentSize++;
		}
	}

	@Override
	public E removeFirst() {
		if(head == null) return null;
		E temp = head.data;
		if(head == tail) {
			head = tail = null;
			currentSize--;
			return temp;
		}
		head = head.next;
		head.previous = null;
		currentSize--;
		return temp;
	}

	@Override
	public E removeLast() {
		if(head == null) return null;
		if(head == tail) return removeFirst();
		E temp = tail.data;
		tail.previous.next = null;
		tail = tail.previous;
		currentSize--;
		return temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E remove(E obj) {
		Node<E> current = head, previous = null;
		while(current != null) {
			if(((Comparable<E>)obj).compareTo(current.data) == 0) {
				if(current == head) return removeFirst();
				if(current == tail) return removeLast();
				currentSize--;
				previous.next = current.next;
				current.next.previous = previous;
				return current.data;
			}
			previous = current;
			current = current.next;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
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

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public E peekFirst() {
		if (head == null) return null;
		return head.data;
	}

	@Override
	public E peekLast() {
		if(tail == null) return null;
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

}
