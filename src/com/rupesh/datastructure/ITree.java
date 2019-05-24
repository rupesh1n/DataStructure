package com.rupesh.datastructure;

import java.util.Iterator;

public interface ITree<E> {
	void add(E obj);
	boolean contains(E obj);
	boolean remove(E obj);
	int height();
	boolean empty();
	int size();
	public Iterator <E> traverse(TreeTraversalOrder order);
}
