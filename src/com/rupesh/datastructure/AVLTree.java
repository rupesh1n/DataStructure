package com.rupesh.datastructure;

import java.util.Iterator;


public class AVLTree<E> implements IAVLTree<E> {
	
	@SuppressWarnings("hiding")
	private class Node<E>{
		E data;
		Node<E> left, right, parent;
		public Node(E obj) {
			data = obj;
			left = right = parent = null;
		}
		
	}
	private int currentSize;
	private Node<E> root;
	
	public AVLTree() {
		currentSize = 0;
		root = null;
	}

	@Override
	public void add(E obj) {
		Node<E> node = new Node<E>(obj);
		if(root == null) {
			root = node;
			currentSize++;
			return;
		}
		add(root, node);
	}

	@SuppressWarnings("unchecked")
	private void add(Node<E> parent, Node<E> newNode) {
		if(((Comparable<E>)newNode.data).compareTo(parent.data) > 0) {
			if(parent.right == null) {
				parent.right = newNode;
				newNode.parent = parent;
				currentSize++;
			} else {
				add(parent.right, newNode);
			}
		} else {
			if(parent.left == null) {
				parent.left = newNode;
				newNode.parent = parent;
				currentSize++;
			} else {
				add(parent.left, newNode);
			}
		}
		
		checkBalance(newNode);
	}

	private void checkBalance(Node<E> newNode) {
		if( (height(newNode.left) - height(newNode.right)) > 1 || (height(newNode.left) - height(newNode.right)) < -1) {
			reBlance(newNode);
		}
		if (newNode.parent == null) return;
		checkBalance(newNode.parent);
	}

	private void reBlance(Node<E> newNode) {
		if((height(newNode.left) - height(newNode.right)) > 1) {
			if(height(newNode.left.left) > height(newNode.left.right)) {
				newNode = rightRotate(newNode);
			} else {
				newNode = leftRightRotate(newNode);
			}
		} else {
			if(height(newNode.right.left) > height(newNode.right.right)) {
				newNode = leftRotate(newNode);
			} else {
				newNode = rightLeftRotate(newNode);
			}
			
		}
		
		if(newNode.parent == null)
			root = newNode;
	}

	private Node<E> leftRotate(Node<E> newNode) {
		Node<E> temp = newNode.right;
		newNode.right = temp.left;
		temp.left = newNode;
		return temp;
	}
	
	private Node<E> rightRotate(Node<E> newNode) {
		Node<E> temp = newNode.left;
		newNode.left = temp.right;
		temp.right = newNode;
		return temp;
	}

	private Node<E> leftRightRotate(Node<E> newNode) {
		newNode.left = leftRotate(newNode.left);
		return rightRotate(newNode);
	}

	private Node<E> rightLeftRotate(Node<E> newNode) {
		newNode.right = rightRotate(newNode.right);
		return leftRotate(newNode);
	}

	private int height(Node<E> node) {
		if(node == null) return 0;
		return Math.max(height(node.left), height(node.right));
	}

	@Override
	public boolean contains(E obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean empty() {
		return currentSize == 0;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public int height() {
		return heightTree(root);
	}

	private int heightTree(Node<E> node) {
		if(node == null) return 0;
		return Math.max(heightTree(node.left), heightTree(node.right) + 1);
	}

	@Override
	public Iterator<E> traverse(TreeTraversalOrder order) {
		// TODO Auto-generated method stub
		return null;
	}

	public void inOrder() {
		inOrder(root);
	}
	private void inOrder(Node<E> node) { 
		if (node == null) { 
			return; } 
		inOrder(node.left); 
		System.out.printf("%s", node.data); 
		inOrder(node.right);
		}

	@Override
	public void levelOrder() {
		for (int i = 1; i <= height(); i++) {
			levelOrder(root, i);
			System.out.println();
		}
		
	}

	private void levelOrder(Node<E> node, int level) {
		if(node == null) return;
		if(level == 1)
			System.out.print(node.data + " ");
		else if(level > 1) {
			levelOrder(node.left, level-1);
			System.out.print(" ");
			levelOrder(node.right, level-1);
			System.out.print(" ");
		}
	}
}
