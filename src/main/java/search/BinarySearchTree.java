package search;

import tree.Node;
import tree.binary.LinkedBinaryTree;

import java.util.Comparator;

/**
 * @author Vital Severyn
 * @since 31.07.15
 */
public class BinarySearchTree<E> extends LinkedBinaryTree<E> {
    private Comparator<E> comparator;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * Method for comparing two values
     *
     * @param val1
     * @param val2
     * @return
     */
    protected int compare(E val1, E val2) {
        if (comparator != null) {
            return comparator.compare(val1, val2);
        } else {
            return ((Comparable<E>) val1).compareTo(val2);
        }
    }

    /**
     * Returns the node in n's subtree by val
     *
     * @param n
     * @param val
     * @return
     */
    //поиск узла по значению
    public NodeImpl<E> treeSearch(NodeImpl<E> n, E val) {
        if (n == null || compare(val, n.getElement()) == 0) {
            return n;
        }
        if (compare(val, n.getElement()) < 0) {
            return treeSearch(n.getLeft(), val);
        } else {
            return treeSearch(n.getRight(), val);
        }
    }

    protected void afterElementRemoved(Node<E> n) {

    }

    protected void afterElementAdded(Node<E> n) {

    }

}
