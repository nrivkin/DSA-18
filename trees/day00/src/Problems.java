import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        // TODO
        return new BinarySearchTree<>();
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
//        if ((n1.hasLeftChild() && n1.hasRightChild() && !(n2.hasLeftChild() && n2.hasRightChild()))) return false;
//        if (n1.isLeaf() != n2.isLeaf()) return false;
        if (n1.key != n2.key) return false;
        if (n1.isLeaf() && n2.isLeaf()) return true;
        if (n1.hasRightChild() && n1.hasRightChild() && n2.hasLeftChild() && n1.hasRightChild()) {
            return (isIsomorphic(n1.rightChild, n2.rightChild) && isIsomorphic(n1.leftChild, n2.leftChild) ||
                    (isIsomorphic(n1.rightChild, n2.leftChild) && isIsomorphic(n1.leftChild, n2.rightChild)));
        } else if (n1.hasRightChild() == n2.hasRightChild()){
            return n1.hasRightChild() ? isIsomorphic(n1.rightChild, n2.rightChild) : isIsomorphic(n1.leftChild, n2.leftChild);
        } else {
            return n1.hasRightChild() ? isIsomorphic(n1.rightChild, n2.leftChild) : isIsomorphic(n1.leftChild, n2.rightChild);
        }
    }
}
