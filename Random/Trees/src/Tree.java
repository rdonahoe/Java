
public class Tree<K extends Comparable<K>, V> {
	public TreeNode<K, V> root;
	
	// implemented node class as static nested class just 
	// to keep entire tree implementation in one file
	public static class TreeNode<K extends Comparable<K>, V> {
		// K can only use classes that have implemented Comparable
		public K key;
		public V value;
		public TreeNode<K, V> left;
		public TreeNode<K, V> right;
		public int height;
	
		public TreeNode() {
			this.key = null;
			this.left = null;
			this.right = null;
			this.height = 1;
		}
		
		public TreeNode(K key, V value) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
			this.height = 1;
		}
		
		public TreeNode(TreeNode<K, V> rep) {
			this.key = rep.key;
			this.value = rep.value;
			this.left = rep.left;
			this.right = rep.right;
			this.height = rep.height;
		}
		
		// height getter is a static method so that it can be used on possibly null nodes to return 0.
		// it is an O(1) operation because height is updated a nodes are added or removed
		public static <K extends Comparable<K>, V> int getHeight(TreeNode<K, V> node) {
			if(node == null)
				return 0;
			else
				return node.height;
		}
}
	
	public Tree() {
		root = null;
	}
	
	public Tree(TreeNode<K, V> tn) {
		root = null;
	}
	
	public Tree(K key, V value) {
		root = new TreeNode<K, V>(key, value);
	}
	
	public int getHeight() {
		return TreeNode.getHeight(root);
	}
}
