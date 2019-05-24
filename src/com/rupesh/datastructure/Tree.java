package com.rupesh.datastructure;

public class Tree<E> implements ITree<E> {
	
	@SuppressWarnings("hiding")
	private class Node<E>{
		E data;
		Node<E> left, right;
		public Node(E obj) {
			data =obj;
			left = right = null;
		}
	}
	
	private int currentSize;
	private Node<E> root;
	
	public Tree() {
		currentSize = 0;
		root = null;
	}

	@Override
	public void add(E obj) {
		if (root == null) 
			root = new Node<E>(obj);
		else
			add(obj, root);
		currentSize++;
	}

	@SuppressWarnings("unchecked")
	private void add(E obj, Node<E> node) {
		if(((Comparable<E>)obj).compareTo(node.data) > 0) {
			if( node.right == null) {
				node.right = new Node<E>(obj);
				return;
			}
			add(obj, node.right);
			return;
		}
		if (node.left == null) {
			node.left = new Node<E>(obj);
			return;
		}
		add(obj, node.left);
		return;
	}

	@Override
	public boolean contains(E obj) {
		return contains(obj, root);
	}

	@SuppressWarnings("unchecked")
	private boolean contains(E obj, Node<E> node) {
		if(node == null)
			return false;
		if ((((Comparable<E>)obj).compareTo(node.data)) == 0)
			return true;
		if (((Comparable<E>)obj).compareTo(node.data) > 0)
			return contains(obj, node.right);
		return contains(obj, node.left);
	}

	@Override
	public boolean remove(E obj) {
		if(contains(obj)) {
			root = remove(obj, root);
			currentSize--;
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private Node<E> remove(E obj, Node<E> node) {
		if (((Comparable<E>)obj).compareTo(node.data) > 0)
			node.right = remove(obj, node.right);
		else if(((Comparable<E>)obj).compareTo(node.data) < 0)
			node.left = remove(obj, node.left);
		else {
			if (node.left == null) {
				Node<E> rightChild = node.right;
				node = null;
				return rightChild;
			} else if(node.right == null) {
				Node<E> leftChild = node.left;
				node = null;
				return leftChild;
			} else {
				//traversing as far a left as possible in the right subtree
				Node<E> temp = digLeftLastLeaf(node.right);
				//swap the data
				node.data = temp.data;
				node.right = remove(temp.data, node.right);
			}
		}
		return node;
	}

	private Node<E> digLeftLastLeaf(Node<E> node) {
		Node<E> current = node;
		while(current.left != null)
			current = current.left;
		return current;
	}

	@Override
	public int height() {
		return height(root);
	}

	private int height(Node<E> node) {
		if (node == null) return 0;
		return Math.max(height(node.left), height(node.right) + 1);
	}

	@Override
	public boolean empty() {
		return currentSize ==0;
	}

	@Override
	public int size() {
		return currentSize;
	}
   
}
