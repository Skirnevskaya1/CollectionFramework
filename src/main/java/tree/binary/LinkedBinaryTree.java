package tree.binary;

import lombok.Getter;
import lombok.Setter;
import tree.Node;

import java.util.*;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure
 *
 * @param <E> element
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    // Хранение корня дерева и размера
    @Getter
    private NodeImpl<E> root = null;
    private int size = 0;

    // nonpublic utility

    /**
     * Validates the node is an instance of supported {@link NodeImpl} type and casts to it
     *
     * @param n node
     * @return casted {@link NodeImpl} node
     * @throws IllegalArgumentException
     */
    protected NodeImpl<E> validate(Node<E> n) {
        if (n == null) {
            throw new IllegalArgumentException("Некорректный узел");
        }
        return (NodeImpl<E>) n;
    }

    // update methods supported by this class

    @Override
    public Node<E> addRoot(E e) {
        if (root != null) {
            throw new IllegalStateException("Корень уже существует в дереве");
        }
        root = new NodeImpl<>(e, null);
        size++;
        return root;
    }

    @Override
    public Node<E> add(Node<E> n, E e) throws IllegalArgumentException {

        NodeImpl<E> newNode = new NodeImpl<>(e, null);

        Queue<NodeImpl<E>> queue = new LinkedList<>();
        queue.add((NodeImpl<E>) n);

        while (!queue.isEmpty()) {
            NodeImpl<E> current = queue.poll();

            // доступные позиции для добавления узла
            if (current.getLeft() == null) {
                current.setLeft(newNode);
                newNode.setParent(current);
                size++;
                return newNode;
            } else if (current.getRight() == null) {
                current.setRight(newNode);
                newNode.setParent(current);
                size++;
                return newNode;
            } else {
                queue.add(current.getLeft());
                queue.add(current.getRight());
            }
        }
        return null;
    }

    @Override
    public Node<E> addLeft(Node<E> n, E e) throws IllegalArgumentException {
        NodeImpl<E> current = validate(n);

        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        NodeImpl<E> child = new NodeImpl<>(e, current);
        current.setLeft(child);
        size++;
        return child;
    }

    @Override
    public Node<E> addRight(Node<E> n, E e) throws IllegalArgumentException {
        NodeImpl<E> current = validate(n);

        while (current.getRight() != null) {
            current = current.getRight();
        }
        NodeImpl<E> child = new NodeImpl<>(e, current);
        current.setRight(child);
        size++;
        return child;
    }


    /**
     * Replaces the element at {@link Node} <i>n</i> with <i>e</i>
     *
     * @param n node
     * @param e element
     * @return replace element
     * @throws IllegalArgumentException
     */
    @Override
    public E set(Node<E> n, E e) throws IllegalArgumentException {
        NodeImpl<E> node = validate(n);
        E oldElement = node.getElement();
        node.setElement(e);
        return oldElement;
    }

    /**
     * Replaces the element at {@link Node} <i>n</i> with <i>e</i>
     *
     * @param n node
     * @return replace element
     * @throws IllegalArgumentException
     */
    @Override
    public E remove(Node<E> n) throws IllegalArgumentException {
        NodeImpl<E> node = validate(n);

        if (node.getLeft() != null && node.getRight() != null) {
            throw new IllegalArgumentException("У узла два потомка, его нельзя удалить");
        }

        NodeImpl<E> child = (node.getLeft() != null) ? node.getLeft() : node.getRight();

        if (child != null) {
            child.setParent(node.getParent());
        }

        if (node == root) {
            root = child;
        } else {
            NodeImpl<E> parent = node.getParent();
            if (node == parent.getLeft()) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }

        size--;
        E oldElement = node.getElement();

        // Помечаем узел как удаленный
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);

        return oldElement;
    }

    // {@link Tree} and {@link BinaryTree} implementations

    @Override
    public Node<E> left(Node<E> p) throws IllegalArgumentException {
        NodeImpl<E> node = validate(p);
        return node.getLeft();
    }

    @Override
    public Node<E> right(Node<E> p) throws IllegalArgumentException {
        NodeImpl<E> node = validate(p);
        return node.getRight();
    }

    @Override
    public Node<E> root() {
        return root;
    }

    @Override
    public Node<E> parent(Node<E> n) throws IllegalArgumentException {
        NodeImpl<E> node = validate(n);
        return node.getParent();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return super.iterator();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Collection<Node<E>> nodes() {
        List<Node<E>> nodes = new ArrayList<>();
        collectNodes(root, nodes);
        return nodes;
    }

    // рекурсивный метод для сбора узлов дерева
    private void collectNodes(NodeImpl<E> node, List<Node<E>> nodes) {
        if (node == null) return;
        collectNodes(node.getLeft(), nodes);
        nodes.add(node);
        collectNodes(node.getRight(), nodes);
    }

    //является ли узел внешним
    public boolean isExternal(Node<E> node) {
        NodeImpl<E> n = validate(node);
        return (n.getLeft() == null && n.getRight() == null);
    }

    //является ли узел внутренним
    public boolean isInternal(Node<E> node) {
        NodeImpl<E> n = validate(node);
        return (n.getLeft() != null || n.getRight() != null);
    }
}