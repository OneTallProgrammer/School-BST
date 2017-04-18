/**
 * Created by Acer Customer on 4/18/2017.
 */
public class Node {
    private Node left;
    private Node right;
    private int data;

    public Node(int data) {
        this.left = null;
        this.right = null;
        this.data = data;
    }

    public Node(Node left, Node right, int data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }




}
