package tree.binary;

import tree.AbstractTree;
import tree.Node;

import java.util.Collection;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    @Override
    public Node<E> sibling(Node<E> n) throws IllegalArgumentException {
        return null; // TODO: 25.10.2024 djcrgr ------->>> implementation + test
    }

    @Override
    public Collection<Node<E>> children(Node<E> n) throws IllegalArgumentException {
        return null; // TODO: 25.10.2024 djcrgr ------->>> implementation + test
    }

    @Override
    public int childrenNumber(Node<E> n) throws IllegalArgumentException {
        return 0; // TODO: 25.10.2024 djcrgr ------->>> implementation + test
    }

    /**
     *
     * @return an iterable collection of nodes of the tree in inorder
     */
    public Collection<Node<E>> inOrder() {
        return null;
    }
}
