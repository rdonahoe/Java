
public class AVLTree<T> extends BST<T> {

	public AVLTree() {
		super();
	}
	
	public AVLTree(int key, T value) {
		super(key, value);
	}
	
	// method override checking height changes and triggering rebalancing if height difference is 2 or more
	public void updateHeight(TreeNode<T> parent) {
		int lh = TreeNode.getHeight(parent.left);
		int rh = TreeNode.getHeight(parent.right);
		
		// check if tree needs rebalancing
		if(Math.abs(lh - rh) > 1) {
			if(lh > rh) {
				// make sure left subtree is not right heavy
				if(TreeNode.getHeight(parent.left.left) > TreeNode.getHeight(parent.left.right))
				{
					rightRotation(parent);
				}
				else {
					// perform preliminary rotation before balancing rotation
					leftRotation(parent.left);
					rightRotation(parent);
				}
			}
			else {
				// make sure right subtree is not left heavy
				if(TreeNode.getHeight(parent.right.left) < TreeNode.getHeight(parent.right.right))
				{
					leftRotation(parent);
				}
				else {
					// perform preliminary rotation before balancing rotation
					rightRotation(parent.right);
					leftRotation(parent);
				}
			}
		}
	}
	
	// rotates tree right for rebalancing. Slightly complex but allows operation to be done 
	// with O(1) run and O(1) space since it is using already allocated nodes
	private void rightRotation(TreeNode<T> parent) {
		TreeNode<T> newRight = parent.left;
		TreeNode<T> newLeft = parent.left.left;
		int tempKey = newRight.key;
		T tempVal = newRight.value;
		
		// old parent becomes new parent's right node
		newRight.left = newRight.right;
		newRight.right = parent.right;
		newRight.key = parent.key;
		newRight.value = parent.value;
		newRight.height = 1 + Math.max(TreeNode.getHeight(newRight.left), TreeNode.getHeight(newRight.right));

		// old left becomes new parent
		parent.key = tempKey;
		parent.value = tempVal;
		parent.right = newRight;
		parent.left = newLeft;
		parent.height = 1 + Math.max(TreeNode.getHeight(parent.left), TreeNode.getHeight(parent.right));
	}
	
	private void leftRotation(TreeNode<T> parent) {
		TreeNode<T> newLeft = parent.right;
		TreeNode<T> newRight = parent.right.right;
		int tempKey = newLeft.key;
		T tempVal = newLeft.value;
		
		// old parent becomes new parent's left node
		newLeft.right = newRight.left;
		newLeft.left = parent.left;
		newLeft.key = parent.key;
		newLeft.value = parent.value;
		newLeft.height = 1 + Math.max(TreeNode.getHeight(newLeft.left), TreeNode.getHeight(newLeft.right));

		// old right becomes new parent
		parent.key = tempKey;
		parent.value = tempVal;
		parent.right = newRight;
		parent.left = newLeft;
		parent.height = 1 + Math.max(TreeNode.getHeight(parent.left), TreeNode.getHeight(parent.right));
	}
	
	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		
		tree.add(3, 0);
		tree.add(5, 0);
		tree.add(4, 0);
		tree.add(6, 0);
		System.out.println(0);
	}
}
