package com.rupesh.datastructure;

public class Tester {
	public static void main(String[] arg) {
		
		IAVLTree<Integer> tree = new AVLTree<>();
		tree.add(10);
		tree.add(9);
		tree.add(11);
		tree.add(7);
		tree.add(8);
		tree.add(1);
		tree.add(4);
		tree.add(22);
		tree.add(12);
		tree.add(17);
		System.out.println("Contains 8 : " + tree.contains(8));
		System.out.println("Contains 55 : " + tree.contains(55));
		System.out.println("tree size : " + tree.size());
		System.out.println("tree height : " + tree.height());
		//Iterator <Integer> iter = tree.traverse(TreeTraversalOrder.LEVEL_ORDER);
		System.out.println("tree inorder : ");
		tree.levelOrder();
		
		/*ITree<Integer> tree = new Tree<>();
		tree.add(10);
		tree.add(9);
		tree.add(11);
		tree.add(7);
		tree.add(8);
		tree.add(1);
		tree.add(4);
		tree.add(22);
		tree.add(12);
		tree.add(17);
		System.out.println("Contains 8 : " + tree.contains(8));
		System.out.println("Contains 55 : " + tree.contains(55));
		System.out.println("tree size : " + tree.size());
		System.out.println("tree height : " + tree.height());
		//Iterator <Integer> iter = tree.traverse(TreeTraversalOrder.LEVEL_ORDER);
		System.out.println("tree inorder : ");
		tree.inOrder();*/
		

		
		/*HashI<String, Integer> hash = new Hash<>(16);
		hash.add("Rupesh", 10);
		hash.add("Rakesh", 3);
		System.out.println("hash has Rupesh : " + hash.contains("Rupesh"));
		System.out.println("hash has Mukesh : " + hash.contains("Mukesh"));
		System.out.println("hash has Rakesh : " + hash.contains("Rakesh"));
		System.out.println("hash Rakesh Value : " + hash.getValue("Rakesh"));
		System.out.println("Removing hash");
		hash.makeEmpty();
		System.out.println("Removing cleared");
		System.out.println("hash has Rupesh : " + hash.contains("Rupesh"));
		System.out.println("hash has Mukesh : " + hash.contains("Mukesh"));
		System.out.println("hash has Rakesh : " + hash.contains("Rakesh"));
		System.out.println("hash Rakesh Value : " + hash.getValue("Rakesh"));*/
		
		
		/* //ListI<Integer> list = new LinkedList<Integer>();
		ListI<Integer> list = new DoublyLinkedList<Integer>();
		 int n = 9;
		 for(int i = 0; i < n; i++)
			 list.addFirst(i);
		System.out.println("addFirst :" + list.toString());
		for(int i = 0; i < n; i++)
			 list.addLast(i);
		System.out.println("addLast : " + list.toString());
		System.out.println("Before Remove List size : " + list.size());
		list.removeFirst();
		System.out.println("After Remove List size : " + list.size());
		System.out.println("removeFirst : " + list.toString());
		list.removeLast();
		System.out.println("removeLast :" + list.toString());
		System.out.println("After Remove List size : " + list.size());
		System.out.println("removed Element : " + list.remove(0));
		System.out.println("After Remove List size : " + list.size());
		System.out.println("remove 0 :" + list.toString());
		System.out.println("peekFirst :" + list.peekFirst());
		System.out.println("peekLast : " + list.peekLast());
		System.out.println("contains 3 : " + list.contains(3));
		System.out.println("removed Element 4? : " + list.remove(4));
		System.out.println("After Remove List size : " + list.size());
		System.out.println("contains 4 :" + list.contains(4));
		list.addPosition(9,-2);
		System.out.println("addPosition:-2 item:9 : " + list.toString());
		list.addPosition(18,10);
		System.out.println("addPosition:10 item:18 : " + list.toString());
		System.out.println("List size : " + list.size());
		list.addPosition(27,2);
		System.out.println("addPosition:2 item:27 : " + list.toString());
		System.out.println("List size : " + list.size());
		list.addPosition(28,10);
		System.out.println("addPosition:10 item:28 : " + list.toString());
		System.out.println("List size : " + list.size());
		list.addPosition(26,10);
		System.out.println("addPosition:10 item:26 : " + list.toString());
		System.out.println("List size : " + list.size());*/
	}
}
