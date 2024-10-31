package tree.binary;

import tree.AbstractTree;
import tree.Node;

import java.util.ArrayList;
import java.util.Collection;


public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

    // получение близнеца узла
    @Override
    public Node<E> sibling(Node<E> n) throws IllegalArgumentException {
        NodeImpl<E> node = validate(n);
        NodeImpl<E> parent = node.getParent();

        if (parent == null) return null;
        if (n == parent.getLeft()) {
            return parent.getRight();
        } else {
            return parent.getLeft();
        }
    }

    //приводит тип Node<E> к NodeImpl<E> и проверяет его корректность
    protected NodeImpl<E> validate(Node<E> n) {
        if (!(n instanceof NodeImpl)) {
            throw new IllegalArgumentException("Invalid");
        }
        return (NodeImpl<E>) n;
    }

    // получение коллекции детей узла
    @Override
    public Collection<Node<E>> children(Node<E> n) throws IllegalArgumentException {
        NodeImpl<E> node = validate(n);
        NodeImpl<E> parent = node.getParent();

        Collection<Node<E>> children = new ArrayList<>(2);
        if (parent.getLeft() != null) children.add(parent.getLeft());
        if (parent.getRight() != null) children.add(parent.getRight());
        return children;
    }

    @Override
    public int childrenNumber(Node<E> n) throws IllegalArgumentException {
        NodeImpl<E> node = validate(n);
        NodeImpl<E> parent = node.getParent();

        int count = 0;
        if (parent.getLeft() != null) count++;
        if (parent.getRight() != null) count++;
        return count;
    }

    /**
     * @return an iterable collection of nodes of the tree in inorder
     */
    // Метод inOrder для получения узлов в порядке обхода "in-order"
    public Collection<NodeImpl<E>> inOrder() {
        Collection<NodeImpl<E>> snapshot = new ArrayList<>();
        if (getRoot() != null) {
            inOrderTraversal(getRoot(), snapshot);
        }
        return snapshot;
    }

    // Рекурсивный метод для заполнения коллекции узлов
    private void inOrderTraversal(NodeImpl<E> node, Collection<NodeImpl<E>> snapshot) {
        if (node.getLeft() != null) {
            inOrderTraversal(node.getLeft(), snapshot);
        }
        snapshot.add(node);
        if (node.getRight() != null) {
            inOrderTraversal(node.getRight(), snapshot);
        }
    }

    protected abstract NodeImpl<E> getRoot();

}
