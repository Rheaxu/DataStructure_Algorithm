package dataStructure;

import java.util.NoSuchElementException;


/**
 * Java binary search tree:http://algs4.cs.princeton.edu/32bst/BST.java.html
 * @author Rhea
 *
 * @param <Key>
 * @param <Value>
 */
public class BST<Key extends Comparable<Key>, Value> {

	private Node root;	//root of BST
	
	private class Node{
		private Key key;	//sorted by key
		private Value val;	// associated data
		private Node left, right;	// left and right subtree
		private int N;	// number of nodes in subtree
		
		public Node(Key key, Value val, int N){
			this.key = key;
			this.val = val;
			this.N = N;
		}		
	}
	
	// is the symbol table empty?
	public boolean isEmpty(){
		return size() == 0;
	}
	
	// return number of key-value paires in BST
	public int size(){
		return size(root);
	}
	
	private int size(Node x){
		if(x==null) return 0;
		else return x.N;
	}
	
	/* ----------------------------------------------------------------
	 * Search BST for given key, and return associated value if found,
	 * return null if not found
	 * ----------------------------------------------------------------
	 */
	//dows there exist a key-value pair with given key?
	public boolean contains(Key key){
		return get(key) != null;
	}
	
	public Value get(Key key){
		return get(root,key);
	}
	
	private Value get(Node x, Key key){
		if(x==null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0) return get(x.left,key);
		else if(cmp>0) return get(x.right,key);
		else return x.val;
	}
	
	/* ----------------------------------------------------------------
	 *  Insert key-value pair into BST
	 *  If key already exists, update with new value
	 *  ----------------------------------------------------------------
	 */
	public void put(Key key, Value val){
		if(val == null){
			delete(key);
			return;
		}
		root = put(root,key,val);
		assert check();
	}
	
	private Node put(Node x, Key key, Value val){
		if(x==null) return new Node(key,val,1);
		int cmp = key.compareTo(x.key);
		if(cmp<0) x.left = put(x.left,key,val);
		else if(cmp>0)x.right = put(x.right,key,val);
		else
			x.N = 1+size(x.left)+size(x.right);
		return x;
	}
	
	/* ----------------------------------------------------------------
	 *  Delete
	 *  ----------------------------------------------------------------
	 */
	public void deleteMin(){
		if(isEmpty()) throw new NoSuchElementException("Symbol table underflow");
		root = deleteMin(root);
		assert check();
	}
	
	private Node deleteMin(Node x){
		if(x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right)+1;
	}
	
	public void deleteMax(){
		if(isEmpty()) throw new NoSuchElementException("Symbol table underflow");
		root = deleteMax(root);
		assert check();
	}
	
	private Node deleteMax(Node x){
		if(x.right==null)return x.left;
		x.right = deleteMax(x.right);
		x.N = size(x.left)+size(x.right)+1;
		return x;
	}
	
	public void delete(Key key){
		root = delete(root,key);
		assert check();
	}
	
	private Node delete(Node x,Key key){
		if(x == null)return null;
		int cmp=key.compareTo(x.key);
		if(cmp<0)x.left = delete(x.left,key);
		else if(cmp>0)x.left = delete(x.right,key);
		else{
			if(x.right == null) return x.left;
			if(x.left == null) return x.right;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left)+size(x.right)+1;
		return x;
	}
	
	/* ----------------------------------------------------------------
	 *  Min, max, floor, and ceiling
	 *  ----------------------------------------------------------------
	 */
	public Key min(){
		if(isEmpty()) return null;
		return min(root)key;
	}
	
	private Node min(Node x){
		if(x.left == null) return x;
		else return min(x.left);
	}
	
	public Key max(){
		if(isEmpty()) return null;
		return max(root).key;
	}
	
	private Node max(Node x){
		if(x.right == null) return x;
		else return max(x.right);
	}
	
	public Key floor(Key key){
		Node x = floor(root,key);
		if(x == null)return null;
		else return x.key;
	}
	
	private Node floor(Node x, Key key){
		if(x==null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp==0) return x;
		if(cmp<0) return floor(x.left,key);
		Node t = floor(x.right,key);
		if(t!=null)return t;
		else return x;
	}
	
	public Key ceiling(Key key){
		Node x = ceiling(root,key);
		if(x == null) return null;
		else return x.key;
	}
	
	private Node ceiling(Node x,Key key){
		if(x == null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp==0) return x;
		if(cmp<0){
			Node t = ceiling(x.left,key);
			if(t!=null) return t;
			else return x;
		}
		return ceiling(x.right,key);
	}
	
	
	
	
	
	
	public static void main(String[] args)
	{
	}
}
