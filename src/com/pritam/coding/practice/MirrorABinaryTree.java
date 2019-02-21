/**
 * 
 */
package com.pritam.coding.practice;

/**
 * Mirror a
 * 
 * @author pribiswas
 *
 */
public class MirrorABinaryTree {

	private static class TreeNode<T> {
		private final T data;
		private TreeNode<T> left;
		private TreeNode<T> right;

		/**
		 * @param data
		 */
		TreeNode(T data) {
			this.data = data;
		}

		/**
		 * @return the left
		 */
		public TreeNode<T> getLeft() {
			return left;
		}

		/**
		 * @param left
		 *            the left to set
		 */
		public void setLeft(TreeNode<T> left) {
			this.left = left;
		}

		/**
		 * @return the right
		 */
		public TreeNode<T> getRight() {
			return right;
		}

		/**
		 * @param right
		 *            the right to set
		 */
		public void setRight(TreeNode<T> right) {
			this.right = right;
		}

		/**
		 * @return the data
		 */
		public T getData() {
			return data;
		}

	}

	private static <T> void mirror(TreeNode<T> root) {
		if (root == null)
			return;
		TreeNode<T> left = root.getLeft();
		TreeNode<T> right = root.getRight();
		root.setLeft(right);
		root.setRight(left);
		mirror(left);
		mirror(right);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
