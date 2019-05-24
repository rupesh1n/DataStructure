package com.rupesh.datastructure;

import java.util.Iterator;

public class Hash<K, V> implements HashI<K, V> {
	
	@SuppressWarnings("hiding")
	class HashElement<K, V> implements Comparable<HashElement<K, V>>{
		K key;
        V value;
        /**
		 * Constructor for the hash element. 
		 * The variable key and value initialized.
		 * @param key for the hash table.
		 * @param value the value added to the hash table.
		 */
        public HashElement(K key, V value) {
			this.key = key;
			this.value = value;
        }
        /**
		 * Uses the compareTo function to compare two keys.
		 * this.key < o.key returns -1.
		 * this.key == o.key returns 0.
		 * this.key > o.key returns 1.
		 * 
		 * @return -1 or 0 or 1 after comparing current key to new key.
		 */
		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(HashElement<K, V> o) {
			return (((Comparable<K>)this.key).compareTo(o.key));
		}
	}
	/** Contains the size of the table.	 */
	/** Count the number of elements inside the table.	 */
	
	int numElement, tableSize;
	
	/**The maximum Load Factor for the table.	 */
	
	double maxLoadFactor;
	
	/**Contains an array of LinkedLists.	 */
	HashListI<HashElement<K, V>>[] harray;
	
	/**
	 *  The Hash constructor accepts a single parameter, an integer, that
	 *  sets the initial size of the Dictionary.
	 *  @param tablesize the table size of the hash table.
	 */
	@SuppressWarnings("unchecked")
	public Hash(int tableSize) {
		this.tableSize = tableSize;
		numElement = 0;
		maxLoadFactor = 0.75;
		harray = (LinkedList<HashElement<K, V>>[]) new LinkedList[tableSize];
		for(int i = 0; i < tableSize; i++)
			harray[i] = new LinkedList<HashElement<K, V>>();
			}
			
	/**  
	 * Adds the given key and value that pair to the dictionary.  
	 * Returns false if the dictionary is full, or if the key is a duplicate. 
	 * Returns true if addition succeeded. 
	 *  
	 * @param key the key to add
	 * @param value the value associated with the key
	 * @return true if the key/value are added to the hash.
	 */
	@Override
	public boolean add(K key, V value) {
		//Checking arraySize need to grow or not
		if(loadFactor() > maxLoadFactor)
			resize(tableSize * 2);
		//Object Creation
		HashElement<K, V> hElement = new HashElement<K, V>(key, value);
		//finding index for array to add using hashCode
		int hasVal = key.hashCode() & 0x7FFFFFFF % tableSize;
		harray[hasVal].add(hElement);
		numElement++;
		return true;
	}

	/**
	 * Deletes the key and value pair by the key parameter.
	 * Returns true if found and removed,
	 * otherwise return false.
	 * 
	 * @param key to the hash table.
	 * @return true if found, otherwise false;
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean remove(K key) {
		V val = getValue(key);
		HashElement<K,V> hashElement = new HashElement (key,val);
		int hasVal = key.hashCode() & 0x7FFFFFFF % tableSize;
		harray[hasVal].remove(hashElement);
		numElement--;
		return true;
	}

	/**
	 * Returns the value associated with the parameter key.
	 * Returns null if the key is not found or the dictionary is empty.
	 * @param key the key to find the value for
	 * @return the value
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V getValue(K key) {
		int hasVal = key.hashCode() & 0x7FFFFFFF % tableSize;
		for (HashElement<K, V> hElement : harray[hasVal])
			if(((Comparable<K>)key).compareTo(hElement.key) == 0)
				return hElement.value;
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void resize(int newSize) {
		LinkedList<HashElement<K, V>>[] new_array = (LinkedList<HashElement<K, V>>[]) new LinkedList[newSize];
		for (int i = 0; i < newSize; i++)
			new_array[i] = new LinkedList<HashElement<K, V>>();
		
		for(K key : this) {
			V value = getValue(key);
			HashElement<K, V> hElement = new HashElement<K, V>(key, value);
			int hasVal = key.hashCode() & 0x7FFFFFFF % newSize;
			new_array[hasVal].add(hElement);
		}
		harray = new_array;
		tableSize = newSize;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean changeValue(K key, V value) {
		int hasVal = key.hashCode() & 0x7FFFFFFF % tableSize;
		for (HashElement<K, V> hElement : harray[hasVal])
			if(((Comparable<K>)key).compareTo(hElement.key) == 0) {
				hElement.value = value;
				return true;
			}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(K key) {
		int hasVal = key.hashCode() & 0x7FFFFFFF % tableSize;
		for(HashElement<K, V> hElement : harray[hasVal])
			if(((Comparable<K>)key).compareTo(hElement.key) == 0)
				return true;
		return false;
	}

	@Override
	public int size() {
		return numElement;
	}

	@Override
	public boolean isEmpty() {
		return numElement == 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void makeEmpty() {
		harray = (LinkedList<HashElement<K, V>>[]) new LinkedList[tableSize];
		for (int i = 0; i < tableSize; i++)
			harray[i] = new LinkedList<HashElement<K, V>>();
		numElement = 0;
		maxLoadFactor = 0.75;
	}

	@Override
	public double loadFactor() {
		return ((double) numElement/tableSize);
	}

	@Override
	public double getMaxLoadFactor() {
		return maxLoadFactor;
	}

	@Override
	public void setMaxLoadFActor(double loadfactor) {
		maxLoadFactor = loadfactor;
	}
	
	/**
	 * Returns an Iterator of the keys in the hash,
	 * in ascending sorted order.
	 * @return an iterator helper to assist the iterator.
	 */
	@Override
	public Iterator<K> iterator() {
		return new IteratorHelper<K>();
	}
	
	/**
	 * Help implements iterator with hasNext and next.
	 * An optimized way for abstract list.
	 *
	 * @param <T> The element that the iterator will use.
	 */
	class IteratorHelper<T> implements Iterator<T>{
		T[] keys;
		int position;
		/**
		 * Constructor for IteratorHelper.
		 * The variable keys is an array of <T>.
		 */
		@SuppressWarnings("unchecked")
		public IteratorHelper(){
			keys = (T[]) new Object[numElement];
			int p=0;
			for(int i=0; i<tableSize; i++){
				LinkedList<HashElement <K,V>> list = (LinkedList<HashElement<K,V>>) harray[i];
				for(HashElement<K,V> h:list)
					keys[p++] = (T)h.key;
			}
			position=0;
		}
		/**
		 * Test whether the hash has a next.
		 * @return true if it has next, otherwise false.
		 */
		public boolean hasNext(){
			return position < keys.length;
		}
		/**
		 * Returns the object and moves the pointer to the next position.
		 * Returns null when reach at the end of the hash.
		 * @return the object from the hash table, otherwise null.
		 */
		public T next(){
			if(!hasNext())
				return null;
			return keys[position ++];
		}
	}

}
