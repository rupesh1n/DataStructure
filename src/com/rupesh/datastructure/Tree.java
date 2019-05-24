package com.rupesh.datastructure;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Stack;

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

	@Override
	public Iterator<E> traverse(TreeTraversalOrder order) {
		switch (order) {
	      case PRE_ORDER: return preOrderTraversal();
	      case IN_ORDER: return inOrderTraversal();
	      case POST_ORDER: return postOrderTraversal();
	      case LEVEL_ORDER: return levelOrderTraversal();
	      default: return null;
	    }
	}

	private Iterator<E> preOrderTraversal() {
		final int expectedNodeCount = currentSize;
		Stack<Node<E>> stack = new Stack<Node<E>>();
		stack.push(root);
		return new Iterator<E>() {

					@Override
					public boolean hasNext() {
						if(expectedNodeCount != currentSize)
							throw new ConcurrentModificationException();
						return root != null && !stack.isEmpty();
					}

					@Override
					public E next() {
						if(expectedNodeCount != currentSize)
							throw new ConcurrentModificationException();
						Node<E> node = stack.pop();
						if(node.right != null)
							stack.push(node.right);
						if(node.left != null)
							stack.push(node.left);
						return node.data;
					}
					@Override public void remove() {
				        throw new UnsupportedOperationException();
				      }
		};
	}

	private Iterator<E> inOrderTraversal() {
		final int expectedNodeCount = currentSize;
		Stack<Node<E>> stack = new Stack<Node<E>>();
		stack.push(root);
		return new Iterator<E>() {
			Node<E> trav = root;
			@Override
			public boolean hasNext() {
				if(expectedNodeCount != currentSize)
					throw new ConcurrentModificationException();
				return root != null && !stack.isEmpty();
			}

			@Override
			public E next() {
				if(expectedNodeCount != currentSize)
					throw new ConcurrentModificationException();
				// Dig left
		        while(trav != null && trav.left != null) {
		          stack.push(trav.left);
		          trav = trav.left;
		        }
				
				Node<E> node = stack.pop();
				
				// Try moving down right once
		        if (node.right != null) {
		          stack.push(node.right);
		          trav = node.right;
		        }
		        
				return node.data;
			}
			@Override public void remove() {
		        throw new UnsupportedOperationException();
		      }
	
		};
	}

	private Iterator<E> postOrderTraversal() {
		final int expectedNodeCount = currentSize;
		Stack<Node<E>> stack1 = new Stack<Node<E>>();
		Stack<Node<E>> stack2 = new Stack<Node<E>>();
		stack1.push(root);
		while(!stack1.empty()) {
			Node<E> node = stack1.pop();
			if(node != null) {
				stack2.push(node);
				if(node.left != null) stack1.push(node.left);
				if(node.right != null) stack2.push(node.right);
			}
		}
		return new Iterator<E>() {

			@Override
			public boolean hasNext() {
				if(expectedNodeCount != currentSize)
					throw new ConcurrentModificationException();
				return root != null && !stack2.isEmpty();
			}

			@Override
			public E next() {
				if(expectedNodeCount != currentSize)
					throw new ConcurrentModificationException();
				return stack2.pop().data;
			}
			
			@Override public void remove() {
		        throw new UnsupportedOperationException();
		      } 
			};
	}

	private Iterator<E> levelOrderTraversal() {
		final int expectedNodeCount = currentSize;
	    final java.util.Queue <Node<E>> queue = new java.util.LinkedList<>();
	    queue.offer(root);

	    return new Iterator <E> () {
	      @Override public boolean hasNext() {
	        if (expectedNodeCount != currentSize) throw new ConcurrentModificationException();
	        return root != null && !queue.isEmpty();
	      }
	      @Override public E next () {
	        if (expectedNodeCount != currentSize) throw new ConcurrentModificationException();
	        Node<E> node = queue.poll();
	        if (node.left != null) queue.offer(node.left);
	        if (node.right != null) queue.offer(node.right);
	        return node.data;
	      }
	      @Override public void remove() {
	        throw new UnsupportedOperationException();
	      }      
	    };
	}
}
