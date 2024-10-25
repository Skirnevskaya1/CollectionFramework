package tree.binary;

import tree.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure
 *
 * @param <E> element
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    // Хранение корня дерева и размера
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
    protected NodeImpl<E> validate(Node<E> n) throws IllegalArgumentException {
        if (!(n instanceof NodeImpl)) {
            throw new IllegalArgumentException("Некорректный узел");
        }
        NodeImpl<E> node = (NodeImpl<E>) n;
        if (node.getParent() == node) {
            throw new IllegalArgumentException("Этот узел больше не в дереве");
        }
        return node;
    }

    // update methods supported by this class

    @Override
    public Node<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) {
            throw new IllegalStateException("Дерево уже содержит корень");
        }
        root = new NodeImpl<>(e, null, null, null);
        size = 1;
        return root;
    }

    @Override
    public Node<E> add(Node<E> n, E e) throws IllegalArgumentException {
        NodeImpl<E> node = validate(n);
        if (node.getLeft() == null) {
            NodeImpl<E> leftChild = new NodeImpl<>(e, node, null, null);
            node.setLeft(leftChild);
            size++;
            return leftChild;
        } else if (node.getRight() == null) {
            NodeImpl<E> rightChild = new NodeImpl<>(e, node, null, null);
            node.setRight(rightChild);
            size++;
            return rightChild;
        } else {
            throw new IllegalArgumentException("У узла уже есть два потомка");
        }
    }

    @Override
    public Node<E> addLeft(Node<E> n, E e) throws IllegalArgumentException {
        NodeImpl<E> node = validate(n);
        if (node.getLeft() != null) {
            throw new IllegalArgumentException("Левый потомок уже существует"); // TODO: 25.10.2024 djcrgr ------->>>
            // перебалсировка???
        }
        NodeImpl<E> leftChild = new NodeImpl<>(e, node, null, null);
        node.setLeft(leftChild);
        size++;
        return leftChild;
    }

    @Override
    public Node<E> addRight(Node<E> n, E e) throws IllegalArgumentException {
        NodeImpl<E> node = validate(n);
        if (node.getRight() != null) {
            throw new IllegalArgumentException("Правый потомок уже существует");
        }
        NodeImpl<E> rightChild = new NodeImpl<>(e, node, null, null);
        node.setRight(rightChild);
        size++;
        return rightChild;
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
        List<E> elements = new ArrayList<>();
        inOrder(root, elements);
        return elements.iterator();
    }

    // рекурсия для in-order обхода дерева
    private void inOrder(NodeImpl<E> node, List<E> elements) {
        // TODO: 25.10.2024 djcrgr ------->>> call super
        if (node != null) {
            inOrder(node.getLeft(), elements);
            elements.add(node.getElement());
            inOrder(node.getRight(), elements);
        }
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
        // TODO: 25.10.2024 djcrgr ------->>> почитать алгоритмы обхода бинарного дерева
        // в глубину и в ширину + реализовать эти обходы
        if (node != null) {
            nodes.add(node);
            collectNodes(node.getLeft(), nodes);
            collectNodes(node.getRight(), nodes);
        }
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
// TODO: 25.10.2024 djcrgr ------->>> to another class file
    // use lombok fro getters and setters
    public static class NodeImpl<E> implements Node<E> {
        private E element;
        private NodeImpl<E> parent;
        private NodeImpl<E> left;
        private NodeImpl<E> right;

        public NodeImpl(E e, NodeImpl<E> parent, NodeImpl<E> left, NodeImpl<E> right) {
            this.element = e;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public NodeImpl<E> getParent() {
            return parent;
        }

        public void setParent(NodeImpl<E> parent) {
            this.parent = parent;
        }

        public NodeImpl<E> getLeft() {
            return left;
        }

        public void setLeft(NodeImpl<E> left) {
            this.left = left;
        }

        public NodeImpl<E> getRight() {
            return right;
        }

        public void setRight(NodeImpl<E> right) {
            this.right = right;
        }

        @Override
        public E getElement() {
            return element;
        }
    }
}