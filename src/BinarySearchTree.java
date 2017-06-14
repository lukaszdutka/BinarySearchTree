/**
 * Created by Dasi on 2017-05-03.
 */

public class BinarySearchTree<T> {
    Node<T> root;
    MyComparator<T> comparator = new MyComparator<>();

    /**************************************************************************************/
    /**
     * konstruktor
     **/

    public BinarySearchTree() {
        root = null;
    }

    /**************************************************************************************/
    /**
     * metoda zwracajaca najmniejszy element drzewa
     **/

    public Node<T> getMinimum(Node<T> fromThis) { //jesli pelne drzewo, to fromThis musi byc root
        Node<T> minimumNode = fromThis;

        while (minimumNode.getLeft() != null)
            minimumNode = minimumNode.getLeft();

        return minimumNode;
    }

    /**************************************************************************************/
    /**
     * metoda zwracajaca najwiekszy element drzewa
     **/

    public Node<T> getMaximum(Node<T> fromThis) { //jesli pelne drzewo, to fromThis musi byc root
        Node<T> maximumNode = fromThis;

        while (maximumNode.getRight() != null)
            maximumNode = maximumNode.getRight();

        return maximumNode;
    }

    /**************************************************************************************/
    /**
     * metoda dodajaca element do drzewa (w odpowiednie miejsce)
     **/

    public void addNode(T value) {
        Node<T> addThis = new Node<>(value);

        /** jesli dany element juz wystepuje w drzewie, wyswietlany jest komunikat **/
        if (contains(value)) {
            System.out.println(value + " - tree contains this object already.");
        } /** jesli dany element nie wystepuje, a drzewo jest puste (root == null) to element staje sie korzeniem (root)**/
        else if (root == null) root = addThis;
        /** jesli drzewo nie jest puste i nie zawiera danego elementu, to wkladamy element w odpowiednie miejsce**/
        else {
            Node<T> actual = root;
            Node<T> parent = null;
            /** wyszukanie miejsca **/
            while (actual != null) {
                parent = actual; //parent to element, ktory zostanie przodkiem nowego elementu
                if (comparator.compare(value, actual.getValue()) < 0) {
                    actual = actual.getLeft();
                } else {
                    actual = actual.getRight();
                }
            }
            /** decyzja, czy wkladany element zostanie lewym, czy prawym potomkiem i polaczenie go z przodkiem**/
            if (comparator.compare(value, parent.getValue()) < 0) {
                parent.setLeft(addThis);
                parent.getLeft().setParent(parent);
            } else {
                parent.setRight(addThis);
                parent.getRight().setParent(parent);
            }
        }
    }

    /**************************************************************************************/

    public Node<T> search(T value) throws MyException {
        Node<T> actual = root;
        while ((actual.getLeft() != null || actual.getRight() != null)&&(actual != null && comparator.compare(actual.getValue(), value) != 0)) {
            if(comparator.compare(actual.getValue(), value) > 0){
                actual = actual.getLeft();
            }else if(comparator.compare(actual.getValue(), value) < 0){
                actual = actual.getRight();
            }
        }
        if (actual == null)
            throw new MyException("Value not found");
        return actual;
    }

    /**************************************************************************************/

    public Node<T> getPredecessor(T value) throws MyException { //poprzednik
        Node<T> node = this.search(value);
        if (node.getLeft() != null) {
            node = node.getLeft();
            while (node.getRight() != null)
                node = node.getRight();
            return node;
        } else if (node.getLeft() == null && node != root && node != this.getMinimum(root)) {
            Node<T> parent = node.getParent();
            while (parent != root && comparator.compare(parent.getValue(), node.getValue()) > 0)
                parent = parent.getParent();
            return parent;
        } else
            throw new MyException("Predecessor not found.");
    }

    /**************************************************************************************/

    public Node<T> getSuccessor(T value) throws MyException { //nastepnik
        Node<T> node = this.search(value);

        /** Jesli aktualny wierzcholek ma prawego potomka, to przeskakujemy na niego, a pozniej maksymalnie w lewo **/
        if (node.getRight() != null) {
            node = node.getRight();
            while (node.getLeft() != null)
                node = node.getLeft();
            return node;
        } else if (node.getRight() == null && node != root && node != this.getMaximum(root)) {
            Node<T> parent = node.getParent();
            while (parent != root && comparator.compare(parent.getValue(), node.getValue()) < 0)
                parent = parent.getParent();
            return parent;
        } else
            throw new MyException("Successor not found.");
    }

    /**************************************************************************************/

    public boolean contains(T value) {
        Node<T> actual = root;
        while (actual != null && actual.getValue() != value)
            actual = (comparator.compare(actual.getValue(), value) > 0) ? actual.left : actual.right;
        if (actual == null) return false;
        return true;
    }

    /**************************************************************************************/

    public void inOrder(Node<T> node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.print(node.getValue() + ", ");
            inOrder(node.getRight());
        }
    }

    /**************************************************************************************/

    public void preOrder(Node<T> node) {
        if (node != null) {
            System.out.print(node.getValue() + ", ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    /**************************************************************************************/

    public void postOrder(Node<T> node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.print(node.getValue() + ", ");
        }
    }

    /**************************************************************************************/

    public int countHeight(Node<T> node) {
        if (node == null) return -1;
        node.setHeight(1 + Math.max(countHeight(node.getLeft()), countHeight(node.getRight())));
        return node.getHeight();
    }

    /**************************************************************************************/

    public int countNodes(Node<T> node) {
        if (node == null) return 0;
        node.setNodes(1 + countNodes(node.getLeft()) + countNodes(node.getRight()));
        return node.getNodes();
    }

    /**************************************************************************************/

    public int countLeaves(Node<T> node) {
        if (node == null) return 0;

        else if (node.getLeft() == null && node.getRight() == null) {
            node.setLeaves(1);
            return node.getLeaves();
        }

        node.setLeaves(countLeaves(node.getLeft()) + countLeaves(node.getRight()));

        return node.getLeaves();
    }

    /**************************************************************************************/

    public Node<T> getRoot() {
        return root;
    }

    /**************************************************************************************/

    public void setRoot(Node<T> newRoot){ root = newRoot;}

}
