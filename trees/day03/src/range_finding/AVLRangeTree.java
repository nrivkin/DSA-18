package range_finding;

import java.util.LinkedList;
import java.util.List;

public class AVLRangeTree extends BinarySearchTree<Integer> {

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    RangeNode<Integer> delete(RangeNode<Integer> n, Integer key) {
        n = super.delete(n, key);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            n.subtree = 1 + subtree(n.rightChild) + subtree(n.leftChild);
            return balance(n);
        }
        return null;
    }

    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    RangeNode<Integer> insert(RangeNode<Integer> n, Integer key) {
        n = super.insert(n, key);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            n.subtree = 1 + subtree(n.rightChild) + subtree(n.leftChild);
            return balance(n);
        }
        return null;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    RangeNode<Integer> deleteMin(RangeNode<Integer> n) {
        n = super.deleteMin(n);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            n.subtree = 1 + subtree(n.rightChild) + subtree(n.leftChild);
            return balance(n);
        }
        return null;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(RangeNode x) {
        if (x == null) return -1;
        return x.height;
    }

    // return size of nodes subtree
    private int subtree(RangeNode n){
        if (n == null) return 0;
        return n.subtree;
    }

    public int height() {
        return Math.max(height(root), 0);
    }

    public int subtree() {
        return Math.max(subtree(root), 0);
    }

    // Restores the AVL tree property of the subtree.
    RangeNode<Integer> balance(RangeNode<Integer> x) {
        if (balanceFactor(x) > 1) {
            if (balanceFactor(x.rightChild) < 0) {
                x.rightChild = rotateRight(x.rightChild);
            }
            x = rotateLeft(x);
        } else if (balanceFactor(x) < -1) {
            if (balanceFactor(x.leftChild) > 0) {
                x.leftChild = rotateLeft(x.leftChild);
            }
            x = rotateRight(x);
        }
        return x;
    }

    // Return all keys that are between [lo, hi] (inclusive).
    // TODO: runtime = O(log(n) + (hi - lo))
    public List<Integer> rangeIndex(int lo, int hi) {
        List<Integer> l = new LinkedList<>();
        rangeIndex(root, l, lo, hi);
        return l;
    }

    public void rangeIndex(RangeNode<Integer> node, List<Integer> list, int lo, int hi) {
        if (node != null) {
            if(node.key > lo) rangeIndex(node.leftChild, list, lo, hi);
            if(node.key >= lo && node.key <= hi) list.add(node.key);
            if(node.key < hi) rangeIndex(node.rightChild, list, lo, hi);
        }
    }

    // return the number of keys between [lo, hi], inclusive
    // TODO: runtime = O(log(n))
    public int rangeCount(int lo, int hi) {
        int overlap = 0;
        int loCount = rank(lo);
        int hiCount = rank(hi);
        if (super.contains(lo)) overlap++;
        return hiCount - loCount + overlap;
    }

    // return number of nodes with keys <= k
    private int rank(int k){
        NodeAndSum status = findLessThanOrEqual(new NodeAndSum(root, 0), k);
        return status == null ? 0 : status.sum;
    }

    private NodeAndSum findLessThanOrEqual(NodeAndSum status, Integer key) {
        if (status == null) return null;
        if (status.node == null) {
            return new NodeAndSum(null, 0);
        }
        if (status.node.key > key){
            status.node = status.node.leftChild;
            return findLessThanOrEqual(status, key);
        } else if (status.node.key < key){
            status.sum++; // adds the current node
            status.sum += subtree(status.node.leftChild); // adds all nodes on left, which are less than
            status.sum += findLessThanOrEqual(new NodeAndSum(status.node.rightChild, 0), key).sum;
            return status;
        }
        status.sum++;
        status.sum += subtree(status.node.leftChild);
        return status;
    }

    // allows passing
    public class NodeAndSum{
        RangeNode<Integer> node;
        int sum;

        NodeAndSum(RangeNode<Integer> node, int sum){
            this.node = node;
            this.sum = sum;
        }
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(RangeNode x) {
        return height(x.rightChild) - height(x.leftChild);
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private RangeNode<Integer> rotateRight(RangeNode<Integer> x) {
        RangeNode<Integer> y = x.leftChild;
        x.leftChild = y.rightChild;
        y.rightChild = x;
        x.height = 1 + Math.max(height(x.leftChild), height(x.rightChild));
        y.height = 1 + Math.max(height(y.leftChild), height(y.rightChild));
        x.subtree = 1 + subtree(x.leftChild) + subtree(x.rightChild);
        y.subtree = 1 + subtree(y.leftChild) + subtree(y.rightChild);
        return y;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private RangeNode<Integer> rotateLeft(RangeNode<Integer> x) {
        RangeNode<Integer> y = x.rightChild;
        x.rightChild = y.leftChild;
        y.leftChild = x;
        x.height = 1 + Math.max(height(x.leftChild), height(x.rightChild));
        y.height = 1 + Math.max(height(y.leftChild), height(y.rightChild));
        x.subtree = 1 + subtree(x.leftChild) + subtree(x.rightChild);
        y.subtree = 1 + subtree(y.leftChild) + subtree(y.rightChild);
        return y;
    }
}
