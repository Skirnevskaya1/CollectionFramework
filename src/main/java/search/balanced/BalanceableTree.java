package search.balanced;

import tree.Node;
import search.BinarySearchTree;
import tree.binary.LinkedBinaryTree;

public abstract class BalanceableTree<E> extends BinarySearchTree<E> {

    /**
     * Sets new relationship between parent and child. This method is used by
     * {@link #rotate(com.getjavajob.training.init.severynv.algo.tree.Node)} for node and its grandparent,
     * node and its parent, node's child and node's parent relinking.
     *
     * @param parent new parent
     * @param child new child
     * @param makeLeftChild whether new child must be left or right
     */
    private void relink(NodeImpl<E> parent, LinkedBinaryTree.NodeImpl<E> child, boolean makeLeftChild) {
        // todo
    }

    /**
     * Rotates n with it's parent.
     *
     * @param n node to rotate above its parent
     */
    protected void rotate(Node<E> n) {
        // todo
    }

    /**
     * Performs one rotation of <i>n</i>'s parent node or two rotations of <i>n</i> by the means of
     * {@link #rotate(com.getjavajob.training.init.severynv.algo.tree.Node)} to reduce the height of subtree rooted at <i>n1</i>
     *
     * <pre>
     *     n1         n2           n1           n
     *    /          /  \         /            / \
     *   n2    ==>  n   n1  or  n2     ==>   n2   n1
     *  /                         \
     * n                           n
     * </pre>
     *
     * Similarly for subtree with right side children.
     *
     *
     * @param n grand child of subtree root node
     * @return new subtree root
     */
    protected Node<E> reduceSubtreeHeight(Node<E> n) {
        // todo
        return null;
    }

}
