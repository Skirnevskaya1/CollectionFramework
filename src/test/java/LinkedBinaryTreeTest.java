import org.junit.jupiter.api.Test;
import tree.binary.LinkedBinaryTree;
import tree.Node;


public class LinkedBinaryTreeTest {

    @Test
    void test() {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        Node<Integer> root = tree.addRoot(10
        );
        Node<Integer> leftChild = tree.addLeft(root, 7);
        Node<Integer> rightChild = tree.addRight(root, 19);

        tree.set(leftChild, 5);
        // tree.remove(rightChild);

        System.out.println("Корень дерева: " + root.getElement());
        System.out.println("Левый child: " + leftChild.getElement());
        System.out.println("Левый ребенок: " + tree.left(root).getElement());
        System.out.println("Правый child: " + rightChild.getElement());

        // итератор для обхода дерева
        System.out.println("Элементы дерева:");
        for (Integer elem : tree) {
            System.out.println(elem);
        }

        System.out.println("Узлы дерева:");
        for (Node<Integer> node : tree.nodes()) {
            System.out.println("Узел: " + node.getElement());
        }
        System.out.println("Элемент в корне дерева: " + tree.root().getElement());
    }
}
