package tree.binary;

import tree.AbstractTree;
import tree.Node;

import java.util.Collection;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    @Override
    public Node<E> sibling(Node<E> n) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Collection<Node<E>> children(Node<E> n) throws IllegalArgumentException {
        return null;
    }

    @Override
    public int childrenNumber(Node<E> n) throws IllegalArgumentException {
        return 0;
    }

    /**
     *
     * @return an iterable collection of nodes of the tree in inorder
     */
    public Collection<Node<E>> inOrder() {
        return null;
    }
}
