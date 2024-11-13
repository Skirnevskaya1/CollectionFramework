package tree.binary;

import lombok.Getter;
import lombok.Setter;
import tree.Node;

@Setter
public class NodeImpl<E> implements Node<E> {
    private E element;
    @Getter
    private NodeImpl<E> parent;
    @Getter
    private NodeImpl<E> left;
    @Getter
    private NodeImpl<E> right;

    public NodeImpl(E element, NodeImpl<E> parent) {
        this.element = element;
        this.parent = parent;
    }

    public NodeImpl(E element, NodeImpl<E> left, NodeImpl<E> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    @Override
    public E getElement() {
        return element;
    }
}
