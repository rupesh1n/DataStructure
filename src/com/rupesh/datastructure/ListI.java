package com.rupesh.datastructure;

public interface ListI<E> {

	void addFirst(E item);
	void addLast(E item);
	void addPosition(E item, int position);
	E removeFirst();
	E removeLast();
	E remove(E obj);
	boolean contains(E obj);
	int size();
	E peekFirst();
	E peekLast();
	  /**
     * Return a String version of this list enclosed in
     * square brackets, []. Elements are in
     * are in order based on position in the 
     * list with the first element
     * first. Adjacent elements are seperated by comma's
     * @return a String representation of this IList
     */
    public String toString();
}
