import java.util.Random;

/**
 * Created by Dasi on 2017-05-09.
 */
public class Test {
    public static void main(String[] args) {
        BinarySearchTree<Integer> BST = new BinarySearchTree<>();
        BST.addNode(100);
        BST.addNode(150);
        BST.addNode(50);
        BST.addNode(25);
        BST.addNode(75);
        BST.addNode(175);
        BST.addNode(125);

        Random r = new Random();
        for(int i = 0; i<30; i++)  BST.addNode(r.nextInt(200));


        System.out.println("Minimum drzewa wynosi: " + BST.getMinimum(BST.getRoot()).getValue().toString());
        System.out.println("Maksimum drzewa wynosi: " + BST.getMaximum(BST.getRoot()).getValue().toString());

        System.out.print("Drzwo inOrder:   ");
        BST.inOrder(BST.getRoot());
        System.out.println();

        System.out.print("Drzwo postOrder: ");
        BST.postOrder(BST.getRoot());
        System.out.println();

        System.out.print("Drzwo preOrder:  ");
        BST.preOrder(BST.getRoot());
        System.out.println();

        System.out.println("Drzewo posiada: " + BST.countLeaves(BST.getRoot()) + " lisci.");
        System.out.println("Drzewo posiada: " + BST.countNodes(BST.getRoot()) + " wierzcholkow.");
        System.out.println("Drzewo posiada: " + BST.countHeight(BST.getRoot()) + " wysokosci.");

        new TreeDrawing<>(BST);

    }
}
