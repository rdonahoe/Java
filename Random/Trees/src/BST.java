/*
 * Binary Search Tree for fun.
 * 		add(recursive), remove(recursive), get(iterative), contains(iterative), traversal-printing(all recursive).
 * 		nodes contain a integer key, generic type value, left and right nodes, and a height variable. Height variable is updated as the tree adds/removes items. 
 * 		O(1) height lookup is not very necessary for this tree but would be useful if this tree became the parent class of an AVL tree.
 * 
 * by Ryan Donahoe
 */

public class BST<T> extends Tree<T> {
	
	public BST() {
		super();
	}
	
	public BST(int key, T value) {
		super(key, value);
	}
	
	public int getHeight() {
		return super.getHeight();
	}
	
	public void add(int key, T value) {
		TreeNode<T> temp = new TreeNode<T>(key, value);
		if(root == null) {
			root = temp;
		}
		else {
			recAdd(root, temp);
		}
	}
	
	// adds to tree recursively because otherwise updating the height would be much more complicated.
	// it is also private to keep programmers from trying to add the harder way.
	private int recAdd(TreeNode<T> parent, TreeNode<T> child) {
		if(child.key > parent.key) {
			if(parent.right == null) {
				// found free space on right. Update parent height and then pass it back 
				// to caller so they can update that node's height, etc.
				parent.right = child;
				parent.height = 1 + Math.max(TreeNode.getHeight(parent.left), 1);
				updateHeight(parent);
				return parent.height;
			}
			else {
				// continue on to the right node and weight for a height to pass back to update this height.
				parent.height = 1 + Math.max(TreeNode.getHeight(parent.left), recAdd(parent.right, child));
				updateHeight(parent);
				return parent.height;
			}
		}
		else if(child.key < parent.key) {
			if(parent.left == null) {
				parent.left = child;
				parent.height = 1 + Math.max(1, TreeNode.getHeight(parent.right));
				updateHeight(parent);
				return parent.height;
			}
			else {
				parent.height = 1 + Math.max(recAdd(parent.left, child), TreeNode.getHeight(parent.right));
				updateHeight(parent);
				return parent.height;
			}
		}
		else {
			// in the case that a node is found with the same key, update that nodes value and return this height, since it has not changed
			parent.value = child.value;
			return parent.height;
		}
	}
	
	public void remove(int key) {
		if(root != null) {
			if(root.key == key && root.left == null && root.right == null) {
				root = null;
			}
			else {
				recRemove(root, key);
			}
		}
	}
	
	// also recursive and private for the same reason as 'recAdd'
	// with the replacement of the removed node to make sure the tree is still organized correctly,
	// this algorithm is O(h) where h is the height of the tree.
	private int recRemove(TreeNode<T> parent, int key) {
		// check if the key we are looking for is greater, less than, or equal to. Also check if it is possible to go any further
		if(key > parent.key && parent.right != null) {
			// is the next node the one we are looking for.
			if(parent.right.key == key) {
				
				// if right has no children just null it out and return 0 because that is the height of a null node
				if(TreeNode.getHeight(parent.right.left) == 0 && TreeNode.getHeight(parent.right.right) == 0) {
					parent.right = null;
					return 0;
				}
				else {
					// move down to the node since we know we will be able to remove it without needing the parent
					// also update height and pass it up
					parent.height = 1 + Math.max(TreeNode.getHeight(parent.left), recRemove(parent.right, key));
					updateHeight(parent);
					return parent.height;
				}
			}
			else {
				// this isn't the node we are looking for so move to the right child and wait to update this height
				parent.height = 1 + Math.max(TreeNode.getHeight(parent.left), recRemove(parent.right, key));
				updateHeight(parent);
				return parent.height;
			}
		}
		else if(key < parent.key && parent.left != null) {
			if(parent.left.key == key) {
				if(TreeNode.getHeight(parent.left.left) == 0 && TreeNode.getHeight(parent.left.right) == 0) {
					parent.left = null;
					return 0;
				}
				else {
					parent.height = 1 + Math.max(recRemove(parent.left, key), TreeNode.getHeight(parent.right));
					updateHeight(parent);
					return parent.height;
				}
			}
			else {
				parent.height = 1 + Math.max(recRemove(parent.left, key), TreeNode.getHeight(parent.right));
				updateHeight(parent);
				return parent.height;
			}
		}
		else if (key == parent.key) {
			// this is the node we want to remove so we can't just say this = null. We have to replace this node with some deeper child
			int lh = TreeNode.getHeight(parent.left);
			int rh = TreeNode.getHeight(parent.right);
			
			// check which subtree we can take from to maintain balance
			if(lh > rh){
				// if tree only has one node, just take the key and value for the parent and null out the replacement node
				// otherwise travel into the subtree to find a suitable replacement
				if(lh > 1) {
					removeLargest(parent.left, parent);
				}
				else {
					parent.key = parent.left.key;
					parent.value = parent.left.value;
					parent.left = null;
				}
			}
			else {
				if(rh > 1) {
					removeSmallest(parent.right, parent);
				}
				else {
					parent.key = parent.right.key;
					parent.value = parent.right.value;
					parent.right = null;
				}
			}
			
			// once parent has been replaced, update height of this in case it was changed while grabbing a deeper node
			parent.height = 1 + Math.max(TreeNode.getHeight(parent.left), TreeNode.getHeight(parent.right));
			updateHeight(parent);
			return parent.height;
		}
		else {
			// this only occurs if the key is not contained in the tree, so pass the height back to show nothing was changed
			return parent.height;
		}
	}
	
	// no reason for this to be recursive to it just iterates down to the right of the tree with a cursor.
	public TreeNode<T> getLargest() {
		TreeNode<T> cur = root;
		while(cur.right != null) {
			cur = cur.right;
		}
		
		return cur;
	}
	
	// this function needs to update height so it is recursive
	// is passed a parent node (where the search starts) and a result node (the node being replaced)
	public int removeLargest(TreeNode<T> parent, TreeNode<T> res) {
		// if we can't go any further right, but we do have a left node
		// replace result with parent and then replace parent with parent's left child.
		if(parent.right == null && parent.left != null) {
			res.key = parent.key;
			res.value = parent.value;
			
			parent.key = parent.left.key;
			parent.value = parent.left.value;
			parent.left = parent.left.left;
			parent.right = parent.left.right;
			parent.height = parent.left.height;
			updateHeight(parent);
			return parent.height;
		}
		else if(parent.right.right == null) {
			// we know the next right node is the largest so replace result with right's data 
			res.key = parent.right.key;
			res.value = parent.right.value;
			
			// make sure our replacement node doesn't have children before we null it out
			if(parent.right.left == null) {
				parent.right = null;
			}
			else {
				// if it does have a left child, just replace the right node with the right node's left child
				parent.right = parent.right.left;
			}
			// update height and pass it up
			parent.height = 1 + Math.max(TreeNode.getHeight(parent.left), TreeNode.getHeight(parent.right));
			updateHeight(parent);
			return parent.height;
		}
		else {
			// we haven't found the largest yet so move down to right child while this call waits for an updated height
			parent.height = 1 + Math.max(TreeNode.getHeight(parent.left), removeLargest(parent.right, res));
			updateHeight(parent);
			return parent.height;
		}
	}
	
	// same as 'getLargest'
	public TreeNode<T> getSmallest() {
		TreeNode<T> cur = root;
		while(cur.left != null) {
			cur = cur.left;
		}
		
		return cur;
	}
	
	// same algorithm as 'removeLargest', just flipped
	public int removeSmallest(TreeNode<T> parent, TreeNode<T> res) {
		if(parent.left == null && parent.right != null) {
			res.key = parent.key;
			res.value = parent.value;
			
			parent.key = parent.left.key;
			parent.value = parent.right.value;
			parent.left = parent.right.left;
			parent.right = parent.right.right;
			parent.height = parent.right.height;
			updateHeight(parent);
			return parent.height;
		}
		else if(parent.left.left == null) {
			res.key = parent.left.key;
			res.value = parent.left.value;
			
			if(parent.left.right == null) {
				parent.left = null;
			}
			else {
				parent.left = parent.left.right;
			}
			parent.height = 1 + Math.max(TreeNode.getHeight(parent.left), TreeNode.getHeight(parent.right));
			updateHeight(parent);
			return parent.height;
		}
		else {
			parent.height = 1 + Math.max(removeSmallest(parent.left, res), TreeNode.getHeight(parent.right));
			updateHeight(parent);
			return parent.height;
		}
	}
	
	// a blank method that has no use for a BST but may be useful for an AVL tree in the future
	public void updateHeight(TreeNode<T> parent) {

	}
	
	// O(log(n)) operation to retrieve node's value
	public T get(int key) {
		TreeNode<T> cur = root;
		while(cur != null) {
			if(key == cur.key) {
				return cur.value;
			}
			else if(key > cur.key && cur.right != null) {
				cur = cur.right;
			}
			else if(key < cur.key && cur.left != null) {
				cur = cur.left;
			}
			else {
				return null;
			}
		}
		return null;
	}
	
	// uses 'get' method to check if tree contains a key
	public boolean contains(int key) {
		if(get(key) == null) return false;
		return true;
	}
	
	// different traversal methods to print a tree
	// used mainly for testing.
	public void printTree(int type) {
		switch(type) {
		case 1:
			// pre-order traversal
			preOrderPrint(root);
			break;
		case 2:
			// post-order traversal
			postOrderPrint(root);
			break;
		default:
			// in-order traversal
			inOrderPrint(root);
			break;
		}
	}
	
	// these traversals are done recursively because an iterative approach with this implementation 
	// would have a runtime of O(nlog(n)) while recursion has a runtime of just O(n)
	// an iterative approach is also much more complicated when it comes to traversals
	private void inOrderPrint(TreeNode<T> parent) {
		if(parent.left != null) inOrderPrint(parent.left);
		System.out.println(String.format("(key = %d, val = %s)", parent.key, parent.value));
		if(parent.right != null) inOrderPrint(parent.right);
	}
	
	private void preOrderPrint(TreeNode<T> parent) {
		System.out.println(String.format("(key = %d, val = %s)", parent.key, parent.value));
		if(parent.left != null) inOrderPrint(parent.left);
		if(parent.right != null) inOrderPrint(parent.right);
	}
	
	private void postOrderPrint(TreeNode<T> parent) {
		if(parent.left != null) inOrderPrint(parent.left);
		if(parent.right != null) inOrderPrint(parent.right);
		System.out.println(String.format("(key = %d, val = %s)", parent.key, parent.value));
	}
}
