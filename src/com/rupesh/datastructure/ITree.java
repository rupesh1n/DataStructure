package com.rupesh.datastructure;

public interface ITree<E> {
	void add(E obj);
	boolean contains(E obj);
	boolean remove(E obj);
	int height();
	boolean empty();
	int size();
}
