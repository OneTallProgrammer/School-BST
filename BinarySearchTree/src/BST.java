

/**
 * Created by Acer Customer on 4/18/2017.
 */
public class BST {
    private Node root;
    private int size;

    public BST(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public boolean isRoot(Node node) {
        if(node == this.root) {
            return true;
        }

        return false;
    }



    public void insert(Node newNode, Node parent) {

        if(newNode.getData() <= parent.getData()){
            if(parent.getLeft() == null){
                parent.setLeft(newNode);
                System.out.println("newNode set as left child under " + parent.getData());
            }
            else{
                this.insert(newNode, parent.getLeft());
            }
        }
        else{
            if(parent.getRight() == null){
                parent.setRight(newNode);
                System.out.println("newNode set as right child under " + parent.getData());
            }
            else{
                this.insert(newNode, parent.getRight());
            }
        }

    }

    void delete(int data) {

    }

    void printInorder() {
        printInorderRecursive(this.root);
    }

    void printInorderRecursive(Node currentNode) {
        if(currentNode.getLeft() != null){
            printInorderRecursive(currentNode.getLeft());
        }

        System.out.print(currentNode.getData() + " ");

        if(currentNode.getRight() != null){
            printInorderRecursive(currentNode.getRight());
        }
    }

    void printPostorder() {
        printPostorderRecursively(this.root);
    }

    void printPostorderRecursively(Node currentNode) {
        System.out.print(currentNode.getData() + " ");

        if(currentNode.getLeft() != null) {
            printPostorderRecursively(currentNode.getLeft());
        }

        if(currentNode.getRight() != null) {
            printPostorderRecursively(currentNode.getRight());
        }
    }

    void printPreorder() {
        printPreorderRecursively(this.root);
    }

    void printPreorderRecursively(Node currentNode) {
        if(currentNode.getLeft() != null) {
            printPostorderRecursively(currentNode.getLeft());
        }

        if(currentNode.getRight() != null) {
            printPostorderRecursively(currentNode.getRight());
        }

        System.out.print(currentNode.getData() + " ");
    }

    Node search(int value) {
        return searchRecursively(value, this.root);
    }

    Node searchRecursively(int value, Node currentNode) {
        if(value == currentNode.getData()){
            return currentNode;
        }
        else if(currentNode.getLeft() == null && currentNode.getRight() == null) {
            return null;
        }
        else if(value < currentNode.getData()){
            return searchRecursively(value, currentNode.getLeft());
        }
        else if(value > currentNode.getData()) {
            return searchRecursively(value,currentNode.getRight());
        }

        return null;
    }


}
