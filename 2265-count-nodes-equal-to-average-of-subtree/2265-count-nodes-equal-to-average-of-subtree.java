/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    int count = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return count;
    }

    // returns {sum, number of nodes}
    private int[] dfs(TreeNode node) {

        if (node == null) {
            return new int[]{0, 0};
        }

        // Left subtree
        int[] left = dfs(node.left);

        // Right subtree
        int[] right = dfs(node.right);

        // Sum of current subtree
        int sum = left[0] + right[0] + node.val;

        // Number of nodes in current subtree
        int nodes = left[1] + right[1] + 1;

        // Floor division automatically happens with integers
        int average = sum / nodes;

        if (node.val == average) {
            count++;
        }

        return new int[]{sum, nodes};
    }
}