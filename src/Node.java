/**
 * Created by Dasi on 2017-05-03.
 */
public class Node<T>{
    private T value;

    Node<T> left;
    Node<T> right;
    private Node<T> parent;

    int height;
    int nodes;
    int leaves;

    public Node(T value){
        this.value = value;
    }

    protected T getValue(){ return value; }

    protected Node<T> getParent() { return parent; }
    protected Node<T> getLeft(){
        return  left;
    }
    protected Node<T> getRight(){
        return right;
    }

    protected int getHeight() { return height; }
    protected int getNodes() { return nodes; }
    protected int getLeaves() { return leaves; }


    protected void setParent(Node<T> parent){
        this.parent = parent;
    }
    protected void setLeft(Node<T> left){
        this.left = left;
    }
    protected void setRight(Node<T> right){
        this.right = right;
    }

    protected void setHeight(int height) { this.height = height; }
    protected void setNodes(int nodes) { this.nodes = nodes; }
    protected void setLeaves(int leaves) { this.leaves = leaves; }

    protected void setValue(T value){ this.value = value; }


}
