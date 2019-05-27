package com.rupesh.datastructure;

import java.util.Iterator;

public interface IAVLTree<E> {
	void add(E obj);
	boolean contains(E obj);
	boolean empty();
	int size();
	int height();
	public Iterator<E> traverse(TreeTraversalOrder order);
	void inOrder();
	void levelOrder();
}