import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tree.binary.LinkedBinaryTree;
import tree.Node;
import tree.binary.NodeImpl;

import java.util.ArrayList;
import java.util.List;


public class LinkedBinaryTreeTest {
    LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
    Node<Integer> root;
    Node<Integer> rightChild;
    Node<Integer> leftChild;

    @Test
    void testRoot() {
        root = tree.addRoot(10);
        Assertions.assertEquals("10", root.getElement().toString());
    }

    @Test
    void testAddLeft() {
        testRoot();
        leftChild = tree.addLeft(root, 7);
        Assertions.assertEquals("7", leftChild.getElement().toString());
    }

    @Test
    void testAddRight() {
        testRoot();
        rightChild = tree.addRight(root, 19);
        Assertions.assertEquals("19", rightChild.getElement().toString());
    }


    @Test
    void testSet() {
        testAddLeft();
        tree.set(leftChild, 5);
        Assertions.assertEquals("5", leftChild.getElement().toString());
    }

    @Test
    void testTraversal() {
        tree.setRoot(new NodeImpl<>(1,
                new NodeImpl<>(2,
                        new NodeImpl<>(4, null, null),
                        new NodeImpl<>(5, null, null)),
                new NodeImpl<>(3, null, null)));

        List<Node<Integer>> nodes = new ArrayList<>();
        tree.collectNodes(tree.getRoot(), nodes);

        List<Integer> expectedOrder = List.of(4, 2, 5, 1, 3);

        List<Integer> actualOrder = new ArrayList<>();
        for (Node<Integer> node : tree.nodes()) {
            actualOrder.add(node.getElement());
        }

        Assertions.assertEquals(expectedOrder, actualOrder,
                "Порядок узлов не соответствует ожиданиям");
    }

    @Test
    void testRemove() {
        testAddRight();
        tree.remove(rightChild);
        Assertions.assertNull(rightChild.getElement());
    }
}
