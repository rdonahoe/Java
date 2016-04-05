
public class Tree<T> {
	public TreeNode<T> root;
	
	// implemented node class as static nested class just 
	// to keep entire tree implementation in one file
	public static class TreeNode<T> {
		// keep key as an int just for simplicities sake. Real implementation wouldn't use this
		public int key;
		public T value;
		public TreeNode<T> left;
		public TreeNode<T> right;
		public int height;
	
		public TreeNode() {
			this.key = 0;
			this.left = null;
			this.right = null;
			this.height = 1;
		}
		
		public TreeNode(int key, T value) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
			this.height = 1;
		}
		
		// height getter is a static method so that it can be used on possibly null nodes to return 0.
		// it is an O(1) operation because height is updated a nodes are added or removed
		public static<T> int getHeight(TreeNode<T> node) {
			if(node == null)
				return 0;
			else
				return node.height;
		}
}
	
	public Tree() {
		root = null;
	}
	
	public Tree(TreeNode<T> tn) {
		root = null;
	}
	
	public Tree(int key, T value) {
		root = new TreeNode<T>(key, value);
	}
	
	public int getHeight() {
		return TreeNode.getHeight(root);
	}
}
